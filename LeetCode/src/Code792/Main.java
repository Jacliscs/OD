package Code792;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 匹配子序列的单词书
 * @date 2024/4/24
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/number-of-matching-subsequences/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    /**
     * 返回  words[i] 中是s的子序列的单词个数
     *
     * @param s
     * @param words
     * @return int
     */
    public int numMatchingSubseq(String s, String[] words) {
        //题解
        int ans = 0;

        //双指针
        for (String word : words) {
            //s中的指针
            int sp = 0;
            //word中的指针
            int wp = 0;

            while (sp < s.length() && wp < word.length()) {
                //如果对应位置相同，wp移动到下一位
                if (s.charAt(sp) == word.charAt(wp)) {
                    wp++;
                }
                //不管相同还是不同，sp都要移动
                sp++;
            }

            //如果word能在s中全部找到，结束时wp应等于word.length
            ans += wp == word.length() ? 1 : 0;
        }

        return ans;
    }
}