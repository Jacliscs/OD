package Code405;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 数字转换为十六进制数
 * @date 2024/4/21
 * @level 简单
 * @url <a href="https://leetcode.cn/problems/convert-a-number-to-hexadecimal/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {

    /**
     * 转换为十六进制数
     *
     * @param num
     * @return java.lang.String
     */
    public String toHex(int num) {
        //定义十进制 -> 十六进制的对应关系
        char[] arr = "0123456789abcdef".toCharArray();

        if (num == 0) return "0";

        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.append(arr[num & 15]);
            num >>>= 4;
        }

        return sb.reverse().toString();

    }
}