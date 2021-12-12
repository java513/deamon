package com.lh.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: deamon
 * @description:
 * @author: lh
 * @date: 2021-12-12 13:33
 **/
public class DateUtil {
    /**
     * ThreadLocal 适用于如下两种场景
     * 1.每个线程需要有自己单独的实例
     * 2.实例需要在多个方法中共享，但不希望被多线程共享
     * 对于第一点，每个线程拥有自己实例，实现它的方式很多。例如可以在线程内部构建一个单独的实例。ThreadLocal 可以以非常方便的形式满足该需求。
     *
     * 对于第二点，可以在满足第一点（每个线程有自己的实例）的条件下，通过方法间引用传递的形式实现。ThreadLocal 使得代码耦合度更低，且实现更优雅。
     */
    private static ThreadLocal<SimpleDateFormat> format = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static String formateDate(Date date){
        return format.get().format(date);
    }

}
