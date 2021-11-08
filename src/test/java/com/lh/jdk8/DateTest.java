package com.lh.jdk8;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @program: deamon
 * @description:
 * @author: lh
 * @date: 2021-11-02 23:08
 **/
public class DateTest {

    @Test
    public void traditionDate() {
        //旧版日期时间API存在的问题
        //1。设计不合理
        Date date = new Date(1991, 9, 16);
        System.out.println(date);
        //2。时间格式化和解析是线程不安全的
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 50; i++) {
            new Thread(()->{
                try {
                    Date parse = sdf.parse("2021-11-02");
                    System.out.println("date:"+parse);
                } catch (ParseException e) {
                    System.out.println("解析出错");
                }
            }).start();
        }
    }

    @Test
    public void testLocalDate() {
        LocalDate now = LocalDate.now();
        System.out.println(now);

        //指定时间
        LocalDate date = LocalDate.of(2008, 8, 8);
        System.out.println(date);

        System.out.println(date.getYear());
        System.out.println(date.getMonth());
        System.out.println(date.getMonthValue());
        System.out.println(date.getDayOfMonth());
    }

    @Test
    public void testLocalTime() {
        LocalTime now = LocalTime.now();
        System.out.println(now);

        //指定时间
        LocalTime time = LocalTime.of(23, 20, 30);
        System.out.println(time);

        System.out.println(time.getHour());
        System.out.println(time.getMinute());
        System.out.println(time.getSecond());
        System.out.println(time.getNano());
    }

    @Test
    public void testLocalDateTime() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        //指定时间
        LocalDateTime time = LocalDateTime.of(2021,11,2,23, 20, 30);
        System.out.println(time);
        System.out.println(time.getHour());
        System.out.println(time.getMinute());
        System.out.println(time.getSecond());
        System.out.println(time.getNano());
    }

    //修改时间
    @Test
    public void updateDateTime() {
        LocalDateTime now = LocalDateTime.now();
        //修改时间
        LocalDateTime with = now.withYear(9102);
        System.out.println("dateTime："+with);
        System.out.println(now==with);

        //加
        System.out.println(now.plusYears(2));
        //减
        System.out.println(now.minusYears(10));
    }

    //比较时间
    @Test
    public void compareTime() {
        LocalDateTime time = LocalDateTime.of(2021,11,2,23, 20, 30);

        LocalDateTime now = LocalDateTime.now();

        System.out.println(now.isAfter(time)); //true
        System.out.println(now.isBefore(time)); //false

        System.out.println(now.isEqual(time)); //false
    }

    //日期格式化
    @Test
    public void formatTime() {
        LocalDateTime now =  LocalDateTime.now();
        //jdk8自带的格式
        //DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        //自定义格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒");
        String format = now.format(formatter);
        System.out.println(format);

        //解析
        for (int i = 0; i < 50; i++) {
            new Thread(()->{
                LocalDateTime parse = LocalDateTime.parse("2021年11月03日 20时20分20秒",formatter);
                System.out.println(parse);
            }).start();
        }
    }

    /**
     * 时间戳
     */
    @Test
    public void timestamp() {
        //Instant内部保存流秒和纳秒，一般时方便程序使用做一些统计
        Instant now = Instant.now();
        System.out.println("now="+now);

        Instant instant = now.plusSeconds(20);
        System.out.println("instant="+instant);

        Instant minusSeconds = now.minusSeconds(20);
        System.out.println("minusSeconds="+minusSeconds);

        //得到秒和纳秒
        System.out.println(now.getEpochSecond());
        System.out.println(now.getNano());
    }

    /**
     * 计算日期时间差
     */
    @Test
    public void period() {
        LocalTime now = LocalTime.now();
        LocalTime time = LocalTime.of(14, 25, 20);
        //Duration 计算时间的距离
        Duration between = Duration.between(time, now);
        System.out.println("相差的天数："+between.toDays());
        System.out.println("相差的小时数："+between.toHours());
        System.out.println("相差的分钟数："+between.toMinutes());
        System.out.println("相差的毫秒数："+between.toMillis());
        //Period 计算日期的距离
        LocalDate localDate = LocalDate.now();
        LocalDate date = LocalDate.of(1991, 9, 16);
        Period period = Period.between(date,localDate);
        System.out.println("相差的年："+period.getYears());
        System.out.println("相差的月："+period.getMonths());
        System.out.println("相差的日："+period.getDays());
    }

    /**
     * 时间矫正器
     */
    @Test
    public void adjust() {
        LocalDateTime now = LocalDateTime.now();

        TemporalAdjuster firstDayOfNextMonth = temporal -> {
            LocalDateTime dateTime = (LocalDateTime)now;
           return dateTime.plusMonths(1).withDayOfMonth(1); //下个月的第一天
        };
        LocalDateTime newDateTime = now.with(firstDayOfNextMonth);
        System.out.println("newDateTime="+newDateTime);

        //JDK中自带了很多时间调整器
        LocalDateTime dateTime = now.with(TemporalAdjusters.firstDayOfYear());
        System.out.println("dateTime="+dateTime);
    }

    /**
     * jdk8设置日期时间的时区
     */
    @Test
    public void zone() {
        //获取所有的时区ID
        //ZoneId.getAvailableZoneIds().forEach(System.out::println);
        //不带时间，获取计算机的当前时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now="+now);

        //2。操作带时区的类
        //创建世界标准时间
        ZonedDateTime bz = ZonedDateTime.now(Clock.systemUTC());
        System.out.println("bz="+bz);

        //使用计算机的默认时区，创建日期时间
        ZonedDateTime now1 = ZonedDateTime.now();
        System.out.println("now1="+now1);

        //使用指定的时区创建日期时间
        ZonedDateTime now2 = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        System.out.println("now2="+now2);
    }
}

