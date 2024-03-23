package OD353;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 机器人仓库搬砖
 * @date 2024/3/23
 * @level 5
 * @score 100
 * @type 二分法
 */

/**
 * 题目描述
 * 机器人搬砖，一共有 N 堆砖存放在 N 个不同的仓库中，第 i 堆砖中有 bricks[i] 块砖头，要求在 8 小时内搬完。
 * <p>
 * 机器人每小时能搬砖的数量取决于有多少能量格，机器人一个小时中只能在一个仓库中搬砖，机器人的能量格只在这一个小时有效，为使得机器人损耗最小化，应尽量减小每次补充的能量格数。
 * <p>
 * 为了保障在 8 小时内能完成搬砖任务，请计算每小时给机器人充能的最小能量格数。
 * <p>
 * 无需考虑机器人补充能力格的耗时；
 * 无需考虑机器人搬砖的耗时；
 * 机器人每小时补充能量格只在这一个小时中有效；
 * 输入描述
 * 第一行为一行数字，空格分隔
 * <p>
 * 输出描述
 * 机器人每小时最少需要充的能量格，若无法完成任务，输出 -1
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //输入一行数字，空格分隔
        int[] bricks = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //8小时内完成，每小时最少补充的能量
        System.out.println(getMinEnergy(bricks));
    }

    //每小时最少补充的能量
    public static int getMinEnergy(int[] bricks) {
        //如果仓库数量大于8，不可能完成任务
        if (bricks.length > 8) {
            return -1;
        }
        //仓库砖的最大值
        int max = Arrays.stream(bricks).max().orElse(0);
        //因为每小时只能在一个仓库，如果仓库数量=8，则必须满足仓库中的最大值，才能完成任务
        if (bricks.length == 8) {
            return max;
        }
        //仓库小于8个，用二分法逐渐逼近最小的能量补充，且补充max时一定能在8小时内完成
        int min = 1;
        int ans = max;
        while (min <= max) {
            int mid = (min + max) / 2;
            //已花费的小时数
            int cost = 0;
            for (int brick : bricks) {
                cost += brick / mid + (brick % mid > 0 ? 1 : 0);
            }
            //如果花费<=8小时，则说明当前mid可以满足，但不一定是最优解
            if (cost <= 8) {
                ans = Math.min(ans, mid);
                max = mid - 1;
            } else {
                //大于等于8，则说明当前补充的能量无法满足
                min = mid + 1;
            }
        }
        return ans;
    }

}