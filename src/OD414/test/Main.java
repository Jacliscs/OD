package OD414.test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 浮生
 * @description Wonderland
 * @date 2024/4/7
 * @level 6
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p?q=category%3AC%E5%8D%B7
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //售票价格 一日票 三日票 周票 月票
        int[] costs = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        //计划游玩日期
        int[] days = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        //返回完成游玩计划的最低消费
        System.out.println(getResult(costs, days));


    }

    public static int getResult(int[] costs, int[] days) {
        //最大游玩天数
        int maxDay = days[days.length - 1];
        //dp[i]表示游玩i天花费的最少花费 最后返回dp[maxDay]，所以长度取maxDay+1
        int[] dp = new int[maxDay + 1];

        //遍历days中的元素
        int index = 0;

        //遍历
        for (int i = 1; i <= maxDay; i++) {
            //如果是游玩日
            if (i == days[index]) {
                //如果买一日票，花费就是dp[i-1]+买一日票的钱
                int buy_one = dp[i - 1] + costs[0];

                //买三日票
                int buy_three = (i >= 3 ? dp[i - 3] : 0) + costs[1];

                //买七日票
                int buy_week = (i >= 7 ? dp[i - 7] : 0) + costs[2];

                //买月票
                int buy_month = (i >= 30 ? dp[i - 30] : 0) + costs[3];

                //dp[i]取最小值
                dp[i] = Math.min(buy_one, Math.min(buy_three, Math.min(buy_week, buy_month)));

                //匹配下一个游玩日
                index++;
            } else {
                //如果不是游玩日，则花费跟前一天一样
                dp[i] = dp[i - 1];
            }
        }

        return dp[maxDay];
    }

}