package com.lh.juc.sync;

/**
 * @program: deamon
 * @description: 卖票演示多线程
 * 多线程编程步骤
 *      第一步：创建资源类，在资源类创建属性和操作办法
 *      第二步：创建多个线程，调用资源类的操作方法
 * @author: lh
 * @date: 2021-10-30 11:47
 **/
//第一步：创建资源类，在资源类创建属性和操作办法
class Ticket{
    private int number = 1000000;

    public synchronized void sale() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName()+":卖出"+(number--)+"剩下："+number);
        }
    }
}

//第二步：创建多个线程，调用资源类的操作方法
public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        int num  = 1000000;
        //创建3个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < num; i++) {
                    ticket.sale();
                }
            }
        },"AAA").start();

        new Thread(() -> {
            for (int i = 0; i < num; i++) {
                ticket.sale();
            }
        },"BBB").start();

        new Thread(() -> {
            for (int i = 0; i < num; i++) {
                ticket.sale();
            }
        },"CCC").start();
    }
}