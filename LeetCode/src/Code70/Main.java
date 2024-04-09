package Code70;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 爬楼梯
 * @date 2024/4/9
 * @level 简单
 * @score
 * @url https://leetcode.cn/problems/climbing-stairs/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public int climbStairs(int n) {
        //楼梯数 1 <= n <= 45
        //dp[i]表示爬到第i层台阶有几种方法
        int[] dp = new int[n + 1];

        //初始化
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];

    }
}