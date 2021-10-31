package com.lh.jdk8;

import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @program: deamon
 * @description: lambda表达式
 * @author: lh
 * @date: 2021-10-31 15:18
 **/
//@SpringBootTest(classes = AppSpringBoot.class)
public class LambdaTest {
    /**
     * -----------------
     *    方法引用
     *两个注意事项
     * 1.被引用的方法，参数要和接口中抽象方法的参数一样
     * 2.当接口抽象方法有返回值时，被引用的方法也必须返回值
     * -----------------
     */
    //对象：：实例方法
    @Test
    public void test001() {
        Date date = new Date();
        /*Supplier<Long> su = ()->{
          return date.getTime();
        };*/
        //使用方法引用
        Supplier<Long> su = date::getTime;
        Long time = su.get();
        System.out.println(time);
    }

    //类名：：静态方法
    @Test
    public void test002() {
//        Supplier<Long> su = ()->{
//          return System.currentTimeMillis();
//        };
        //使用方法引用
        Supplier<Long> su = System::currentTimeMillis;
        Long time = su.get();
        System.out.println(time);
    }



    //3.类名::实例方法
    @Test
    public void test003() {
//        Function<String,Integer> f1 = (str)->{
//              return str.length();
//        };
        Function<String,Integer> f1 = String::length;
        Integer length = f1.apply("hello");
        System.out.println(length);

        BiFunction<String,Integer,String> f2 = String::substring;
        String length2 = f2.apply("helloworld", 3);
        System.out.println(length2);
    }

    //4.类名：：new
    @Test
    public void test004() {
       /* Supplier<Person> su  =()->{
          return new Person();
        };*/
        Supplier<Person> su = Person::new;
        BiFunction<String,Integer,Person> biFunction = (name,age)->{
            return new Person("zs",33);
        };
        Person person = su.get();
        Person person1 = biFunction.apply("比尔", 18);
        System.out.println(person);
        System.out.println(person1);
    }

    //5.类型[]：：new
    @Test
    public void test005() {
       Function<Integer,int[]> f1 = (length)->{
           return new int[length];
       };
        Function<Integer,int[]> f2 = int[]::new;
        int[] arr1 = f1.apply(10);
        int[] arr2 = f2.apply(5);
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
    }
}
