package com.lh.juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: deamon
 * @description: 定制多线程通讯
 * @author: lh
 * @date: 2021-10-30 13:43
 **/
class ShareSource {
    //定义标识位
    private int flag = 1;
    private Lock lock = new ReentrantLock();
    //创建3个condition
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    //打印5次。参数第几轮
    public void print5(int loop) {
        lock.lock();
        try {
            while (flag != 1) {
                c1.await();
            }
            //干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i + ":" + "第：" + loop + "轮");
            }
            //通知
            flag = 2;
            c2.signal(); //先修改标识位，再唤醒bb
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //打印10次。参数第几轮
    public void print10(int loop) {
        lock.lock();
        try {
            while (flag != 2) {
                c2.await();
            }
            //干活
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i + ":" + "第：" + loop + "轮");
            }
            //通知
            flag = 3;
            c3.signal(); //先修改标识位，再唤醒cc
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //打印15次。参数第几轮
    public void print15(int loop) {
        lock.lock();
        try {
            while (flag != 3) {
                c3.await();
            }
            //干活
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i + ":" + "第：" + loop + "轮");
            }
            //通知
            flag = 1;
            c1.signal(); //先修改标识位，再唤醒aa
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class ThreadDemo3 {
    public static void main(String[] args) {
        ShareSource source = new ShareSource();
        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                source.print5(i);
            }
        },"aa").start();
        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                source.print10(i);
            }
        },"bb").start();
        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                source.print15(i);
            }
        },"cc").start();
    }
}
