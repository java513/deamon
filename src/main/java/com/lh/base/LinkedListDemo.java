package com.lh.base;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: deamon
 * @description:
 * @author: lh
 * @date: 2021-08-22 22:15
 **/
public class LinkedListDemo {
    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        for (int i = 0; i < 2000; i++) {
            list.add("a"+i);
        }
        //测试随机访问时间
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            list.get(i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("随机访问耗时："+(endTime-startTime)); //4
        //测试顺序访问时间
        startTime =System.currentTimeMillis();
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        endTime=System.currentTimeMillis();
        System.out.println("顺序访问耗时："+(endTime-startTime)); //5
    }
}
