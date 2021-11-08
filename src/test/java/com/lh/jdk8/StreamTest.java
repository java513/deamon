package com.lh.jdk8;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @program: deamon
 * @description: stream流
 * @author: lh
 * @date: 2021-10-31 15:52
 **/
public class StreamTest {
    /**
     * 1.获取stream流
     * 方式一：通过Collection获取流
     * 方式二：Stream中的静态方法of获取流
     */
    @Test
    public void getStream() {
        //方式一：通过Collection获取流
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();
        Set<String> set = new HashSet<>();
        Stream<String> stream1 = set.stream();

        Map<String, String> map = new HashMap<>();
        Stream<String> keyStream = map.keySet().stream();
        Stream<String> valueStream = map.values().stream();
        Stream<Map.Entry<String, String>> entryStream = map.entrySet().stream();

        //方式二：Stream中的静态方法of获取流
        //基本数据类型的数组不行
        Stream<String> stringStream = Stream.of("aa", "bb", "cc");
        String[] strs = {"aa", "bb", "cc", "dd"};
        Stream<String> stringStream1 = Stream.of(strs);
    }

    /**
     * 2.Stream注意事项
     *   2.1.Stream只能操作一次
     *   2.2.Stream方法返回的是新的流
     *   2.3.Stream不调用终结方法，中间的操作不会执行
     */

    /**
     * 3.测试forEach
     */
    @Test
    public void forEach() {
        List<String> strings = new ArrayList<>();
        Collections.addAll(strings, "熊大", "熊二", "光头强");
        // strings.forEach(System.out::println);
        strings.stream().forEach(System.out::println);
    }

    /**
     * 4.测试count
     */
    @Test
    public void count() {
        List<String> strings = new ArrayList<>();
        Collections.addAll(strings, "熊大", "熊二", "光头强");
        System.out.println(strings.stream().count());
    }

    /**
     * 5.测试filter
     */
    @Test
    public void filter() {
        List<String> strings = new ArrayList<>();
        Collections.addAll(strings, "熊大", "熊二", "光头强");

        strings.stream().filter((s) -> s.contains("熊")).forEach(System.out::println);
    }

    /**
     * 6.测试limit
     */
    @Test
    public void limit() {
        List<String> strings = new ArrayList<>();
        Collections.addAll(strings, "熊大", "熊二", "光头强");
        //获取前2个
        strings.stream().limit(2).forEach(System.out::println);
    }

    /**
     * 7.测试skip
     */
    @Test
    public void skip() {
        List<String> strings = new ArrayList<>();
        Collections.addAll(strings, "熊大", "熊二", "光头强");
        //获取前2个
        strings.stream().skip(2).forEach(System.out::println);
    }

    /**
     * 8.测试map(映射)
     */
    @Test
    public void map() {
        List<String> strings = new ArrayList<>();
        Collections.addAll(strings, "11", "22", "33");
        //将流中的String转成Integer
        // Stream<Integer> integerStream = strings.stream().map(s -> Integer.parseInt(s));
        Stream<Integer> integerStream = strings.stream().map(Integer::parseInt);
        integerStream.forEach(System.out::println);
    }

    /**
     * 9.测试sorted(排序)
     */
    @Test
    public void sorted() {
        Stream<Integer> stream = Stream.of(33, 11, 55);
        //sorted()根据元素的自然顺序排序
        //stream.sorted().forEach(System.out::println);
        //降序
        stream.sorted((i1, i2) -> {
            return i2 - i1;
        }).forEach(System.out::println);
        //升序
        //stream.sorted((i1,i2)->i1-i2).forEach(System.out::println);
    }

    /**
     * 10.测试distinct(去重)
     */
    @Test
    public void distinct() {
        Stream<Integer> stream = Stream.of(33, 11, 55, 11, 22, 33);
        stream.distinct().forEach(System.out::println);

        Stream<String> strStream = Stream.of("11", "44", "22", "11", "44");
        strStream.distinct().forEach(System.out::println);

        //对象类型去重，重写hashcode和equals方法
        Stream<Person> personStream = Stream.of(
                new Person("貂蝉", 18),
                new Person("杨玉环", 22),
                new Person("貂蝉", 20),
                new Person("貂蝉", 18),
                new Person("西施", 22),
                new Person("王昭君", 18),
                new Person("杨玉环", 22)
        );
        personStream.distinct().forEach(System.out::println);
    }

    /**
     * 11.测试match
     */
    @Test
    public void match() {
        Stream<Integer> stream = Stream.of(33, 11, 55, 11, 22, 33);
//        boolean allMatch = stream.allMatch(s->s > 20);
//        System.out.println(allMatch);
//        boolean anyMatch = stream.anyMatch(s -> s > 54);
//        System.out.println(anyMatch);
        boolean noneMatch = stream.noneMatch(s -> s < 0); //所有元素都不满足条件
        System.out.println(noneMatch);
    }

    /**
     * 12.测试find
     */
    @Test
    public void find() {
        Stream<Integer> stream = Stream.of(33, 11, 55, 11, 22, 33);
//        Optional<Integer> first = stream.findFirst();
//        System.out.println(first.get());
        Optional<Integer> any = stream.findAny();
        System.out.println(any.get());
    }

    /**
     * 13.测试max min
     */
    @Test
    public void maxAndMin() {
        Stream<Integer> stream = Stream.of(33, 11, 55, 11, 22, 33);
//        Optional<Integer> max = stream.max(((o1, o2) -> o1 - o2));
//        System.out.println(max.get()); //获取最大值
        Optional<Integer> min = stream.min((o1, o2) -> o1 - o2);
        System.out.println(min.get()); //获取最小值
    }

    /**
     * 14.测试reduce
     */
    @Test
    public void reduce() {
        Stream<Integer> stream = Stream.of(4, 5, 3, 2, 8);
        // Integer reduce = stream.reduce(0, (x, y) -> x + y);
//        Integer reduce = stream.reduce(0, Integer::sum);
//        System.out.println(reduce);
        //求最大值
        Integer max = stream.reduce(0, (x, y) -> x > y ? x : y);
        System.out.println(max);
    }

    /**
     * 15.测试reduce和map组合使用
     */
    @Test
    public void reduceAndMap() {
        //求出所有年龄的总和
        //1。得到所有的年龄
        //2。让年龄相加
        Stream<Person> stream = Stream.of(
                new Person("刘德华", 58),
                new Person("张学友", 56),
                new Person("郭富城", 54),
                new Person("黎明", 52)
        );
//        Optional<Integer> totalAge = stream.map(p -> p.getAge()).reduce(Integer::sum);
//        System.out.println(totalAge.get());

        //找出最大的年龄
        // Integer max = stream.map(p -> p.getAge()).reduce(0, (x, y) -> x > y ? x : y);
        //Integer max = stream.map(p -> p.getAge()).reduce(0, Integer::max);
        Integer max = stream.map(p -> p.getAge()).reduce(0, Math::max);
        System.out.println(max);

        //统计a出现的次数
        Stream<String> stringStream = Stream.of("a", "b", "c", "a", "a", "d");
        Integer total = stringStream.map(s -> {
                    if ("a".equals(s)) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
        ).reduce(0, Integer::sum);
        System.out.println(total);
    }

    /**
     * 16.测试 mapToInt
     */
    @Test
    public void mapToInt() {
        Stream<Integer> stream = Stream.of(4, 5, 3, 2, 8);
        stream.mapToInt(Integer::intValue).forEach(System.out::println);
    }

    /**
     * 17.测试 concat  concat是Stream接口的静态方法，可以将两个
     * stream流合并成一个新的stream流
     */
    @Test
    public void concat() {
        Stream<String> stream1 = Stream.of("郭靖");
        Stream<String> stream2 = Stream.of("黄蓉");

        Stream<String> concat = Stream.concat(stream1, stream2);

        concat.forEach(System.out::println);
    }

    //分组
    @Test
    public void testGroup() {
        Stream<Student> stream = Stream.of(
                new Student("赵丽颖",21,95),
                new Student("杨颖",24,88),
                new Student("高圆圆",24,59),
                new Student("杨幂",21,55)
        );
        Map<String, List<Student>> map = stream.collect(Collectors.groupingBy(s -> {
            if (s.getScore() < 60) {
                return "不及格";
            } else {
                return "及格";
            }
        }));
        map.forEach((k,v)->System.out.println(k+":"+v));
    }

    //分区，true一组，false一组
    @Test
    public void testPartition() {
        Stream<Student> stream = Stream.of(
                new Student("赵丽颖",21,95),
                new Student("杨颖",24,88),
                new Student("高圆圆",24,59),
                new Student("杨幂",21,55)
        );
        Map<Boolean, List<Student>> map = stream.collect(Collectors.partitioningBy(s -> s.getScore() > 60));

        map.forEach((k,v)->System.out.println(k+":"+v));
    }

    //测试拼接操作
    @Test
    public void testJoining() {
        Stream<Student> stream = Stream.of(
                new Student("赵丽颖",21,95),
                new Student("杨颖",24,88),
                new Student("高圆圆",24,59),
                new Student("杨幂",21,55)
        );

        //无参
//        String str = stream.map(Student::getName).collect(Collectors.joining());
//        System.out.println(str);
        //一个参数
//        String str = stream.map(Student::getName).collect(Collectors.joining("，"));
//        System.out.println(str);

        //三个参数
        String str = stream.map(Student::getName).collect(Collectors.joining(",","\"","\""));
        System.out.println(str);
    }

    //串行流
    @Test
    public void testSerial() {
        Stream.of(4,5,9,2,3,10).filter(s->{
            System.out.println(Thread.currentThread()+":"+s);
            return s>3;
        }).count();
    }

    //并行stream流
    @Test
    public void testParallelStream() {
        //获取并行stream流的两种方式
        //方式一：直接获取并行的Stream
        List<String> list = new ArrayList<>();
        Stream<String> parallelStream = list.parallelStream();
        //方式二：将串行流转成并行流
        Stream<String> parallel = list.stream().parallel();

        Stream.of(4,5,9,2,3,10).parallel().filter(s->{
            System.out.println(Thread.currentThread()+":"+s);
            return s>3;
        }).count();
    }

    //并行stream流测试
    @Test
    public void parallelStream() {
        long start = System.currentTimeMillis();
        LongStream.rangeClosed(0,500000000).parallel().reduce(0,Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("消耗时间："+(end-start)); //消耗时间：130
    }

    //串行stream流测试
    @Test
    public void serialStream() {
        long start = System.currentTimeMillis();
        LongStream.rangeClosed(0,500000000).reduce(0,Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("消耗时间："+(end-start));  //消耗时间：263
    }

    @Test
    public void testFor() {
        long start = System.currentTimeMillis();
        sum(500000000);
        long end = System.currentTimeMillis();
        System.out.println("消耗时间："+(end-start)); //消耗时间：166
    }

    private void sum(int num) {
        int sum = 0;
        for (int i = 0; i < num; i++) {
            sum += i;
        }
    }

    //并行parallelStream线程不安全
    @Test
    public void parallel() {
        List<Integer> list = new ArrayList<>();
//        IntStream.rangeClosed(1,1000).parallel().forEach(
//                list::add
//        );
//        System.out.println("list:"+list.size());

        //解决并行流线程安全问题，方案一：使用同步代码块
//        Object lock = new Object();
//        IntStream.rangeClosed(1,1000).parallel().forEach(
//                i->{
//                    synchronized (lock) {
//                        list.add(i);
//                    }
//                }
//        );
//        System.out.println("list:"+list.size());
        //方案二：使用线程安全的集合
//        Vector<Integer> vector = new Vector<>();
//        IntStream.rangeClosed(1,1000).parallel().forEach(
//                vector::add
//        );
//        System.out.println("vector:"+vector.size());
//        List<Integer> synchronizedList = Collections.synchronizedList(list);
//        IntStream.rangeClosed(1,1000).parallel().forEach(
//                synchronizedList::add
//        );
//        System.out.println("synchronizedList:"+synchronizedList.size());
        //方案三：调用Stream流的collect/toArray
        List<Integer> collect = IntStream.rangeClosed(1, 1000).parallel().boxed().collect(Collectors.toList());
        System.out.println("collect:"+collect.size());
    }

}
