package Code122;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 买卖股票的最佳时机
 * @date 2024/4/24
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        //dp0表示当前手里没有股票的最大收益，dp1表示当前手里有股票的最大收益
        int dp0 = 0;
        int dp1 = -prices[0];

        for (int i = 1; i < n; i++) {
            //当前手里没有股票：昨天没有股票或者昨天有股票+今天卖出的收益的最大值
            int newDp0 = Math.max(dp0, dp1 + prices[i]);
            //当前手里有股票：昨天有股票或者昨天没有股票+今天买入的收益的最大值
            int newDp1 = Math.max(dp1, dp0 - prices[i]);

            //更新
            dp0 = newDp0;
            dp1 = newDp1;
        }

        //最后卖出股票的收益dp0一定比有股票的收益大
        return dp0;
    }
}