package Code5;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 最长回文子串
 * @date 2024/4/7
 * @level 中等
 * @score
 * @url https://leetcode.cn/problems/longest-palindromic-substring/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public String longestPalindrome(String s) {
        //最大回文串长度
        int maxLen = 0;
        String ans = "";
        for (int i = 0; i < s.length(); i++) {
            //从右往左遍历，
            for (int j = s.length(); j > i; j--) {
                String tmp = s.substring(i, j);
                if (check(tmp) && tmp.length() > maxLen) {
                    maxLen = tmp.length();
                    ans = tmp;
                }
            }
        }
        return ans;
    }

    /**
     * 判断字符串s是否是回文串
     *
     * @param s
     * @return boolean
     */
    public static boolean check(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString().equals(s);
    }
}