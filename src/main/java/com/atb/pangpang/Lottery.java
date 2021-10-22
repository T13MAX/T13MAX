package com.atb.pangpang;

import java.util.HashSet;
import java.util.Random;

/**
 * 庞庞的彩票号码生成类
 *
 * @Author 呆呆
 * @Datetime 2021/10/22 16:31
 */
public class Lottery {
    public static void main(String[] args) {
        caipiao(50);
    }

    public static void caipiao(int n) {
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            HashSet<Integer> arrays1 = new HashSet<>();
            HashSet<Integer> arrays2 = new HashSet<>();
            while (arrays1.size() < 5)
                arrays1.add(random.nextInt(35) + 1);
            while (arrays2.size() < 2)
                arrays2.add(random.nextInt(12) + 1);
            if (i < 9) {
                System.out.print("第" + (i + 1) + " 组 " + "前两个数:");
            } else {
                System.out.print("第" + (i + 1) + "组 " + "前两个数:");
            }
            arrays2.stream().forEach(integer -> {
                if (integer < 10) {
                    System.out.print(integer + "  ");
                } else {
                    System.out.print(integer + " ");
                }
            });
            System.out.print("后五个数:");
            arrays1.stream().forEach(integer -> {
                if (integer < 10) {
                    System.out.print(integer + "  ");
                } else {
                    System.out.print(integer + " ");
                }
            });
            System.out.println();
        }

    }
}
