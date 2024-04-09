package Code344;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 反转字符串
 * @date 2024/4/9
 * @level 简单
 * @score
 * @url https://leetcode.cn/problems/reverse-string/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    /**
     * 原地修改字符串
     *
     * @param s
     * @return void
     */
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char tmp = s[right];
            s[right] = s[left];
            s[left] = tmp;
            left++;
            right--;
        }
    }
}