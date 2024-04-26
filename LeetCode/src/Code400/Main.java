package Code400;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 第N位数字
 * @date 2024/4/25
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/nth-digit/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    /**
     * 给你一个整数 n ，请你在无限的整数序列 [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...] 中找出并返回第 n 位上的数字
     *
     * @param n
     * @return int
     */
    public int findNthDigit(int n) {
        //确认n所在的数据端
        long base = 1, digitCnt = 1;
        while (n > base * 9 * digitCnt) {
            n -= base * 9 * digitCnt;
            base *= 10;
            digitCnt++;
        }
        //n还需要--
        n--;

        //返回n所在那个数字的第n个
        return getKthDigit(base + n / digitCnt, n % digitCnt);

    }

    private int getKthDigit(long num, long k) {
        //返回num的第k位数字
        return String.valueOf(num).charAt((int) k) - '0';
    }
}