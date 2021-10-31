package com.lh.juc.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: deamon
 * @description: 使用Lock锁
 * @author: lh
 * @date: 2021-10-30 12:04
 **/
class LockTicket {
    private int number  = 10000;
    private final ReentrantLock lock = new ReentrantLock();
    public void saleTicket(){
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName()+":卖出"+(number--)+"剩下："+number);
            }
        }finally {
            lock.unlock();
        }
    }
}
public class LockSaleTicket {
    public static void main(String[] args) {
        LockTicket lockTicket = new LockTicket();
        int num = 10000;
        System.out.println("-------begin-----");
        new Thread(()->{
            for (int i = 0; i < num; i++) {
                lockTicket.saleTicket();
            }
        },"AAA").start();
        new Thread(()->{
            for (int i = 0; i < num; i++) {
                lockTicket.saleTicket();
            }
        },"BBB").start();
        new Thread(()->{
            for (int i = 0; i < num; i++) {
                lockTicket.saleTicket();
            }
        },"CCC").start();
        System.out.println("-------end-----");
    }
}
