package Code122.better;

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
    /**
     * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
     *
     * @param prices
     * @return int
     */
    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            //贪心思维：只要后一天比这一天收益高，则赚差价
            if (prices[i + 1] > prices[i]) {
                profit += prices[i + 1] - prices[i];
            }
        }
        return profit;
    }
}