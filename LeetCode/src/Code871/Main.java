package Code871;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 最低加油次数
 * @date 2024/4/12
 * @level 困难
 * @score
 * @url https://leetcode.cn/problems/minimum-number-of-refueling-stops/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    /**
     * 返回能到达目的地target的最低加油次数，如果不能到达返回-1
     *
     * @param target
     * @param startFuel
     * @param stations
     * @return int
     */
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        //加油站个数
        int n = stations.length;
        //dp[i]表示加油i次能行使的最大公里数
        long[] dp = new long[n + 1];

        //不加油的话只能到达初始油量的位置
        dp[0] = startFuel;

        for (int i = 0; i < n; i++) {
            //假设到达加油站i前已经加油了j次，0<=j<=i，则必须dp[j]能到达第i个加油站的位置才能再加油
            for (int j = i; j >= 0; j--) {
                //加油j次能到达i加油站
                if (dp[j] >= stations[i][0]) {
                    //可以再加一次油，更新加油j+1次能行使的距离
                    dp[j + 1] = Math.max(dp[j + 1], dp[j] + stations[i][1]);
                }
            }
        }

        //dp[]按加油次数升序排列，找到第一个dp[i]>=target的i就是最少加油次数
        for (int i = 0; i <= n; i++) {
            if (dp[i] >= target) {
                return i;
            }
        }

        //如果没有加油次数能到达目的地，返回-1
        return -1;
    }
}