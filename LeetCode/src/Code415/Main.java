package Code415;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 字符串相加
 * @date 2024/4/7
 * @level 简单
 * @score
 * @url https://leetcode.cn/problems/add-strings/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        //进位标志
        int carry = 0;
        //只要还有一个没计算完，都要进入
        while (i >= 0 || j >= 0) {
            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            int res = n1 + n2 + carry;

            //是否发生了进位
            carry = res / 10;

            //将个位的数字添加进结果，最后倒序
            sb.append(res % 10);
            i--;
            j--;
        }
        //如果最后还有进位
        if (carry == 1) sb.append(1);

        //返回逆序后的结果
        return sb.reverse().toString();


    }
}