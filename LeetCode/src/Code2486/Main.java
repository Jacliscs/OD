package Code2486;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 追加字符以获得子序列
 * @date 2024/4/10
 * @level 中等
 * @score
 * @url https://leetcode.cn/problems/append-characters-to-string-to-make-subsequence/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    /**
     * 向s末尾追加字符
     *
     * @param s
     * @param t
     * @return int
     */
    public int appendCharacters(String s, String t) {
        int n = s.length();
        int m = t.length();
        int res = 0;
        //指向t中的字符
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
                //相同字符数+1
                res++;
            } else {
                i++;
            }
        }
        return t.length() - res;
    }
}