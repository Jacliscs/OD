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
        //指向t的字符
        int p = 0;
        for (char c : s.toCharArray()) {
            //如果字符跟当前p指向的相同，则p往右移
            if (c == t.charAt(p)) p++;
            //如果p已经到了t末尾，则跳出
            if (p == t.length()) break;
        }
        //p指向t中不需要追加的字符末尾
        return t.length() - p;
    }
}