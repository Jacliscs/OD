package OD414;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 浮生
 * @description Wonderland
 * @date 2024/3/29
 * @level 6
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD414
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {

    private static int[] cost;
    private static int[] days;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //票价数组
        cost = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //游玩日期
        days = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(getResult());
    }

    /**
     * 使用dp动态规划完成游玩计划的最低消费
     *
     * @param
     * @return int
     * @create 2024/3/29 17:21
     */
    public static int getResult() {
        //days默认升序
        int maxDay = days[days.length - 1];

        //dp[i]表示完成前i天的最少花费
        int[] dp = new int[maxDay + 1];

        //index表示游玩日days[]中的元素
        int index = 0;

        //使用dp
        for (int i = 1; i <= maxDay; i++) {
            //如果第i天是游玩日
            if (i == days[index]) {
                //如果买一日票 那么第i天的最低花费就是第i-1天的最低花费+一日票
                int buy_1 = dp[i - 1] + cost[0];

                //买3日票
                int buy_3 = (i >= 3 ? dp[i - 3] : 0) + cost[1];

                //买7日票
                int buy_7 = (i >= 7 ? dp[i - 7] : 0) + cost[2];

                //买30日票
                int buy_30 = (i >= 30 ? dp[i - 30] : 0) + cost[3];

                //第i天的最低花费是取买上面四种票的最小值
                dp[i] = Math.min(buy_1, Math.min(buy_3, Math.min(buy_7, buy_30)));

                //index匹配下一个游玩日
                index++;

            } else {
                //如果不是游玩日，第i天的花费跟第i-1天一样
                dp[i] = dp[i - 1];
            }
        }

        //返回最大游玩日时的最低花费
        return dp[maxDay];

    }
}