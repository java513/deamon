package com.lh.juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: deamon
 * @description: lock锁演示多线程通讯
 * @author: lh
 * @date: 2021-10-30 13:28
 **/
class Share {
    public int number = 0;
    private final Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void incr() {
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + ":" + number);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decr() {
        lock.lock();
        try {
            while (number == 0) {
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + ":" + number);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class ThreadDemo2 {
    public static void main(String[] args) {
        Share share = new Share();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                share.incr();
            }
        }, "aaa").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                share.decr();
            }
        }, "bbb").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                share.incr();
            }
        }, "ccc").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                share.decr();
            }
        }, "ddd").start();
    }
}
