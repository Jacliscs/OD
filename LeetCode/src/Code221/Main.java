package Code221;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 最大正方形
 * @date 2024/4/19
 * @level 中等
 * @score
 * @url https://leetcode.cn/problems/maximal-square/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    /**
     * 求1的最大正方形面积
     *
     * @param matrix
     * @return int
     */
    public int maximalSquare(char[][] matrix) {
        //正方形最大边长
        int maxSide = 0;

        //判空
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return maxSide;

        int row = matrix.length;
        int cow = matrix[0].length;

        //dp[i][j]表示以matrix[i][j]为正方形右下角的最大边长
        int[][] dp = new int[row][cow];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < cow; j++) {
                //默认dp[][]都是0
                //如果是1，则修改
                if (matrix[i][j] == '1') {
                    //如果刚好在第一行或者第一列，作为右下角，最大边长只能是1
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        //状态转移：取决于左、上、左上三个点的最小边长，只要有一个为0，则matrix[i][j]为右下角的正方形边长只能为1
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    //更新最大边长
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }

        //题解：最大面积
        return maxSide * maxSide;
    }
}