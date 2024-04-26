package Code72;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 编辑距离
 * @date 2024/4/26
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/edit-distance/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public int minDistance(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();

        //dp[i][j]表示将word1的前i个字符转变为word2的前j个字符的最小步骤数
        int[][] dp = new int[n1 + 1][n2 + 1];

        //第一行
        for (int j = 1; j <= n2; j++) {
            dp[0][j] = dp[0][j - 1] + 1;
        }

        //第一列
        for (int i = 1; i <= n1; i++) {
            dp[i][0] = dp[i - 1][0] + 1;
        }

        //其他位置
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                //如果新位置相同，则不需要新增步骤
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 如果不同，则需要从替换、删除、插入三个操作中的最小值+1
                    // dp[i-1][j-1]表示替换word1的第i个字符为word2的第j个字符
                    // dp[i-1][j]表示删除word1的第i个字符
                    // dp[i][j-1]表示插入word2的第j个字符
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[n1][n2];
    }
}