package OD354;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 爱吃蟠桃的孙悟空
 * @date 2024/3/24
 * @level 5
 * @score 200
 */

/**
 * 题目描述
 * 孙悟空爱吃蟠桃，有一天趁着蟠桃园守卫不在来偷吃。已知蟠桃园有 N 棵桃树，每颗树上都有桃子，守卫将在 H 小时后回来。
 * <p>
 * 孙悟空可以决定他吃蟠桃的速度K（个/小时），每个小时选一颗桃树，并从树上吃掉 K 个，如果树上的桃子少于 K 个，则全部吃掉，并且这一小时剩余的时间里不再吃桃。
 * <p>
 * 孙悟空喜欢慢慢吃，但又想在守卫回来前吃完桃子。
 * <p>
 * 请返回孙悟空可以在 H 小时内吃掉所有桃子的最小速度 K（K为整数）。如果以任何速度都吃不完所有桃子，则返回0。
 * <p>
 * 输入描述
 * 第一行输入为 N 个数字，N 表示桃树的数量，这 N 个数字表示每颗桃树上蟠桃的数量。
 * <p>
 * 第二行输入为一个数字，表示守卫离开的时间 H。
 * <p>
 * 其中数字通过空格分割，N、H为正整数，每颗树上都有蟠桃，且 0 < N < 10000，0 < H < 10000。
 * <p>
 * 输出描述
 * 吃掉所有蟠桃的最小速度 K，无解或输入异常时输出 0。
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //第一行为每颗桃树上的桃子的数量
        int[] peach = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //H小时内吃完
        int h = sc.nextInt();
        System.out.println(getResult(peach, h));

    }

    //返回孙悟空能在h小时内吃完所有桃子的最小速度
    public static int getResult(int[] peach, int h) {
        int n = peach.length;
        //一个小时最多吃一棵树，多出的时间不能用
        if (n > h) {
            //一定吃不完
            return 0;
        }
        //桃树果子最大值
        int max = Arrays.stream(peach).max().orElse(0);
        //如果刚好数量与h相同，则只能按最大值的速度吃
        if (n == h) {
            return max;
        }
        //h>n 时，用二分法逐渐逼近最小值
        int min = 1;
        //记录结果
        int ans = max;
        while (min <= max) {
            int mid = (min + max) / 2;
            //花费的小时数
            int cost = 0;
            for (int i : peach) {
                cost += i / mid + (i % mid > 0 ? 1 : 0);
            }
            //如果cost<h 则更新，但不一定是最小值
            if (cost <= h) {
                ans = Math.min(ans, mid);
                //往左逼近
                max = mid - 1;
            } else {
                //吃不完 往右逼近
                min = mid + 1;
            }
        }
        return ans;

    }


}