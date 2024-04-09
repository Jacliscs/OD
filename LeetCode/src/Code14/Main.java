package Code14;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 最长公共前缀
 * @date 2024/4/9
 * @level 简单
 * @score
 * @url https://leetcode.cn/problems/longest-common-prefix/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public String longestCommonPrefix(String[] strs) {
        //如果数组为空
        if (strs == null || strs.length == 0) {
            return "";
        }

        //每次与后一个更新最长前缀
        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {
            prefix = getMaxCommonPrefix(prefix, strs[i]);
            //如果与后一个没有公共前缀，则prefix=="";
            if (prefix.length() == 0) break;
        }
        return prefix;
    }

    public static String getMaxCommonPrefix(String s1, String s2) {
        int length = Math.min(s1.length(), s2.length());
        int index = 0;
        while (index < length && s1.charAt(index) == s2.charAt(index)) {
            index++;
        }
        //公共前缀
        return s1.substring(0, index);
    }

}