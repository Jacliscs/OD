package Code409;

import java.util.HashMap;
import java.util.Scanner;
import java.util.UnknownFormatConversionException;

/**
 * @author 浮生
 * @description 最长回文串
 * @date 2024/4/9
 * @level 简单
 * @score
 * @url https://leetcode.cn/problems/longest-palindrome/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public int longestPalindrome(String s) {
        int length = s.length();
        //存放不同的字符和次数 使用哈希表效率不高
        //HashMap<Character, Integer> map = new HashMap<>();

        //使用数组存放单个字符出现的次数
        //s 只由小写 和/或 大写英文字母组成 直接使用128的长度，包含所有ASCII表
        char[] cnts = new char[128];
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            cnts[ch]++;
        }

        //题解：最长回文串长度
        int ans = 0;
        for (int cnt : cnts) {
            ans += cnt / 2 * 2;
            //如果是奇数次，且当前长度是偶数
            if (cnt % 2 == 1 && ans % 2 == 0) {
                ans++;
            }
        }
        return ans;
    }
}