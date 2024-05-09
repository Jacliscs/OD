package Code132;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 分隔回文串II
 * @date 2024/5/9
 * @level 困难
 * @url <a href="https://leetcode.cn/problems/palindrome-partitioning-ii/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {

    /**
     * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文串
     * 返回符合要求的 最少分割次数
     *
     * @param s
     * @return int
     */
    public int minCut(String s) {
        //dp动态规划
        int len = s.length();

        //长度为0 1 时，不需要分隔
        if (len < 2) return 0;

        //dp[i]表示s的[0,i]的最少分隔次数，初始化为默认都分隔为单字符
        int[] dp = new int[len];
        for (int i = 0; i < len; i++) {
            dp[i] = i;
        }

        //check[i][j]表示s的[i,j]是回文串
        boolean[][] check = new boolean[len][len];

        //判断s中哪些部分是回文串
        for (int right = 0; right < len; right++) {
            //单字符时也需要判断，用<=
            for (int left = 0; left <= right; left++) {
                if (s.charAt(left) == s.charAt(right) && (right - left <= 2 || check[left + 1][right - 1])) {
                    check[left][right] = true;
                    //如果是回文串，则不需要分隔
                }
            }
        }

        //dp动态规划 一个字符的时候不用判断，从1开始
        for (int i = 1; i < len; i++) {
            //如果[0,i]是回文串，则不用分隔，dp[i]=0
            if (check[0][i]) {
                dp[i] = 0;
                continue;
            }

            //如果不是回文串，则需要分隔
            for (int j = 0; j < i; j++) {
                //dp[j]表示长度[0,j]分隔为回文串的最少次数，如果[j+1,i]是回文串
                if (check[j + 1][i]) {
                    //更新dp[i]
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }

        //需要分隔整个s
        return dp[len - 1];
    }

}