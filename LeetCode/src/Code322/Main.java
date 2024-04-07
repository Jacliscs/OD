package Code322;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 零钱兑换
 * @date 2024/4/7
 * @level 中等
 * @score
 * @url https://leetcode.cn/problems/coin-change/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public int coinChange(int[] coins, int amount) {
        //动态规划dp[i]表示找零i元最少需要的硬币数量
        int[] dp = new int[amount + 1];
        //初始化
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                //硬币面值必须小于需要找零的钱，才能找零
                if (coins[j] <= i) {
                    //dp[i - coins[j]]表示只需要在当前找零条件下再找一次硬币coins[j]就可以满足条件
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        //如果最后dp[amount] = amount+1 ，则说明无法找零，返回-1
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}