package com.lh.jdk8;

import org.junit.Test;

import java.util.Optional;

/**
 * @program: deamon
 * @description:
 * @author: lh
 * @date: 2021-11-02 22:40
 **/
public class OptionalTest {


    /**
     * optional的基本使用
     */
    @Test
    public void testOptional() {
        //1。创建Optional对象
        //of不能传null
        Optional<Integer> optional = Optional.of(12);
        //ofNullable 既可以传入具体值，也可以传入null
        Optional<Object> o = Optional.ofNullable(null);
        //empty存入的是null
        Optional<Object> empty = Optional.empty();

        //2.isPresent:判断Optional中是否包含具体值，有值返回true，没有返回false
        boolean present = optional.isPresent();
        System.out.println(present);

        //3.get:获取Optional中的值，如果有值就返回具体值，没有就报错
        System.out.println(empty.get());

        if (optional.isPresent()) {
            System.out.println(optional.get());
        } else {
            System.out.println("没有值！");
        }
    }

    /**
     * optional的高级使用
     * ifPresent
     * orElse
     */
    @Test
    public void optionalAdvance() {
        Optional<String> optional = Optional.of("大白");
        Optional<Object> empty = Optional.empty();
        //有值做什么
        optional.ifPresent(s -> System.out.println("有值：" + s));
        empty.ifPresent(s -> System.out.println("有值：" + s));
        //没有值做什么

       // Person person = new Person("jack", 64);
        Person person = new Person(null, 64);
        Optional<Person> optionalPerson = Optional.of(person);
        System.out.println(toUppercase(person));

        System.out.println(toUppercaseOptional(optionalPerson));
    }

    //传统方式
    private String toUppercase(Person person) {
        if (person != null) {
            String name = person.getName();
            if (name != null) {
                return name.toUpperCase();
            }
        }
        return null;
    }
    //optional方式
    private String toUppercaseOptional(Optional<Person> optionalPerson) {
        return optionalPerson.map(Person::getName).map(String::toUpperCase).orElse("null");
    }
}
