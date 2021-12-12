package com.lh.nowcoder;

import java.util.Scanner;

/**
 * @program: deamon
 * @description:
 * @author: lh
 * @date: 2021-11-16 22:02
 **/
public class MoneyTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int money = sc.nextInt();
            System.out.println(cal(money));
        }
    }

    public static int cal(int n) {
        int sum = 0;
        int tmp = n / 3;
        for (int i = 0; i <= tmp; i++) {
            sum += (n - 3 * i) / 2 + 1;
        }
        return sum;
    }
}
