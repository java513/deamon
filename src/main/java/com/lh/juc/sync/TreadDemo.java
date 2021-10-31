package com.lh.juc.sync;

/**
 * @program: deamon
 * @description: synchronized关键字演示多线程通讯
 * @author: lh
 * @date: 2021-10-30 13:09
 **/
class Share {
    public int number =0;

    //+1
    public synchronized void incr() throws InterruptedException {
        while (number != 0) {
            this.wait();  //wait在哪里睡，醒来从哪里开始执行，需要将if改为while,防止虚假唤醒
        }
        number++;
        System.out.println(Thread.currentThread().getName()+":"+number);
        this.notifyAll();
    }
    //-1
    public synchronized void decr() throws InterruptedException {
        while (number == 0) {
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+":"+number);
        this.notifyAll();
    }
}
public class TreadDemo {
    public static void main(String[] args) {
        Share share = new Share();
        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AAA").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BBB").start();
        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CCC").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"DDD").start();

    }
}
