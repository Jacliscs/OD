package Code43;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 字符串相乘
 * @date 2024/4/9
 * @level 中等
 * @score
 * @url https://leetcode.cn/problems/multiply-strings/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    /**
     * 不能用大数、转整数等函数
     *
     * @param num1
     * @param num2
     * @return java.lang.String
     */
    public String multiply(String num1, String num2) {
        //如果有一个是0，则结果是0
        if (num1.equals("0") || num2.equals("0")) return "0";

        int m = num1.length(), n = num2.length();
        //保存乘法后相应位置上的值
        int[] ans = new int[m + n];
        //从末尾开始相乘
        for (int i = m - 1; i >= 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                //每一个位置上的数，后面往前进位
                ans[i + j + 1] += x * y;
            }
        }

        //处理每一个位置上的进位
        for (int i = m + n - 1; i > 0; i--) {
            //右边的进位加到左边
            ans[i - 1] += ans[i] / 10;
            //右边的取余数
            ans[i] %= 10;
        }
        //ans[0]表示最高位有没有发生进位，如果没有发生进位，ans[0]=0,则从下标为1开始添加
        int index = ans[0] == 0 ? 1 : 0;
        //保存结果
        StringBuilder sb = new StringBuilder();
        while (index < m + n) {
            sb.append(ans[index]);
            index++;
        }
        return sb.toString();

    }
}