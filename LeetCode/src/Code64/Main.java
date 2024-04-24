package Code64;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 最小路径和
 * @date 2024/4/24
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/minimum-path-sum/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    /**
     * 请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     *
     * @param grid
     * @return int
     */
    public int minPathSum(int[][] grid) {
        //长 宽 1 <= m, n <= 200
        int m = grid.length;
        int n = grid[0].length;

        //dp[i][j]表示从左上角到(i,j)的最小路径
        int[][] dp = new int[m][n];

        //起点
        dp[0][0] = grid[0][0];

        //初始化 第一行 只能从左边来
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        //初始化第一列 只能从上方来
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        //遍历
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[m - 1][n - 1];
    }
}