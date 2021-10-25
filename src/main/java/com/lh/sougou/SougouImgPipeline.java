package com.lh.sougou;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: deamon
 * @description: 图片下载类
 * @author: lh
 * @date: 2021-10-20 22:03
 **/
public class SougouImgPipeline {
    private String extension = ".jpg";
    private String path;

    private volatile AtomicInteger suc;
    private volatile AtomicInteger fails;

    public SougouImgPipeline() {
        setPath("/sougou/image");
        suc = new AtomicInteger();
        fails = new AtomicInteger();
    }

    public SougouImgPipeline(String path) {
        setPath(path);
        suc = new AtomicInteger();
        fails = new AtomicInteger();
    }

    public SougouImgPipeline(String path, String extension) {
        setPath(path);
        this.extension = extension;
        suc = new AtomicInteger();
        fails = new AtomicInteger();
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 下载
     *
     * @param url
     * @param cate
     * @param name
     * @throws Exception
     */
    public void downloadImg(String url, String cate, String name) throws Exception {
        String path = this.path + "/" + cate + "/";
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        String realExt = url.substring(url.lastIndexOf(".")); //获取扩展名
        String filename = name + realExt;
        filename = filename.replaceAll("-", "");
        String filePath = path + filename;
        File image = new File(filePath);
        if (image.exists()) {
            System.out.println(String.format("文件%s已存在本地目录", filename));
            return;
        }
        URLConnection connection = new URL(url).openConnection();
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        InputStream inputStream = connection.getInputStream();
        byte[] bytes = new byte[1024];
        File file = new File(filename);
        FileOutputStream fos = new FileOutputStream(file, true);
        //开始读取。写入
        int len;
        while ((len = inputStream.read(bytes)) != -1) {
            fos.write(bytes, 0, len);
        }
        System.out.println("picUrl:" + url);
        System.out.println(String.format("正在下载第%s张图片", suc.getAndIncrement()));
    }

    /**
     * 单线程处理
     *
     * @param data
     * @param word
     */
    public void process(List<String> data, String word) {
        long startTime = System.currentTimeMillis();
        for (String picUrl : data) {
            if (picUrl == null) {
                continue;
            }
            try {
                downloadImg(picUrl, word, picUrl);
            } catch (Exception e) {
                fails.getAndIncrement();
            }
        }
        System.out.println("下载成功：" + suc.get());
        System.out.println("下载失败：" + fails.get());
        long endTime = System.currentTimeMillis();
        System.out.println("耗时：" + (endTime - startTime) / 1000 + "秒");
    }

    /**
     * 多线程处理
     * @param data
     * @param word
     */
    public void processSync(List<String> data, String word) {
        long start = System.currentTimeMillis();
        int count = 0;
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < data.size(); i++) {
            String picUrl = data.get(i);
            if (picUrl == null) {
                continue;
            }
            String name = "";
            if (i < 10) {
                name = "000" + i;
            } else if (i < 100) {
                name = "00" + i;
            } else if (i < 1000) {
                name = "0" + i;
            }
            String filename = name;
            threadPool.execute(() -> {
                try {
                    downloadImg(picUrl, word, filename);
                } catch (Exception e) {
                    fails.getAndIncrement();
                }
            });
            count++;
        }
        threadPool.shutdown();

        try {
            if (!threadPool.awaitTermination(6000, TimeUnit.SECONDS)) {
                // 超时的时候向线程池中所有的线程发出中断(interrupted)。
                // executorService.shutdownNow();
            }
            System.out.println("awaitTermination finish");
            System.out.println("共有URL："+data.size());
            System.out.println("成功下载："+suc);
            System.out.println("失败下载："+fails);

            File dir = new File(this.path + "/" + word + "/");
            int length = Objects.requireNonNull(dir.list()).length;
            System.out.println("当前共有文件："+length);
            long end = System.currentTimeMillis();
            System.out.println("共耗时："+(end-start)/1000+"秒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 多线程分段处理
     * @param data
     * @param word
     * @param threadNum
     */
    public void processSync2(List<String> data, final String word, int threadNum) {
        if (data.size() < threadNum) {
            process(data, word);
        } else {
            ExecutorService threadPool = Executors.newCachedThreadPool();
            int num  = (data.size()/threadNum); //每段要处理的数量
            for (int i = 0; i < threadNum; i++) {
                int start= i*num;
                int end = (i+1)*num;
                if (i == data.size() - 1) {
                    end=data.size();
                }
                final List<String> cutList = data.subList(start, end);
                threadPool.execute(()->process(cutList,word));
            }
            threadPool.shutdown();
        }
    }
}
