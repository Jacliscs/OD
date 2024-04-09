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
        //只有单个字符，本身就是最长回文串
        if (length == 1) return 1;

        //题解：最长回文串长度
        int ans = 0;

        //存放不同的字符和次数
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        //每个字符的次数用times/2 * 2，如3->2，4->4
        //如果当前回文串长度为偶数，则+1
        for (Character c : map.keySet()) {
            ans += map.get(c) / 2 * 2;
            //如果当前c的次数是奇数次，且当前回文串是偶数长度
            if (map.get(c) % 2 == 1 && ans % 2 == 0) ans++;
        }
        return ans;
    }
}