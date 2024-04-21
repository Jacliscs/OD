package Code504;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 七进制数
 * @date 2024/4/21
 * @level 简单
 * @url <a href="https://leetcode.cn/problems/base-7/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {

    /**
     * 给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
     *
     * @param num
     * @return java.lang.String
     */
    public String convertToBase7(int num) {
        if (num == 0) return "0";

        //最后根据正负添加“-”
        int tmp = Math.abs(num);

        //符号
        String flag = num < 0 ? "-" : "";

        StringBuilder sb = new StringBuilder();
        //除留取余
        while (tmp > 0) {
            sb.append(tmp % 7);
            tmp /= 7;
        }
        //加上符号
        sb.append(flag);

        return sb.reverse().toString();

    }
}