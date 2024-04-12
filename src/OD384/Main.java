package OD384;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 部门人力分配
 * @date 2024/3/25
 * @level 6
 * @score 200
 */

/**
 * 题目描述
 * 部门在进行需求开发时需要进行人力安排。
 * <p>
 * 当前部门需要完成 N 个需求，需求用 requirements 表述，requirements[i] 表示第 i 个需求的工作量大小，单位：人月。
 * <p>
 * 这部分需求需要在 M 个月内完成开发，进行人力安排后每个月人力时固定的。
 * <p>
 * 目前要求每个月最多有2个需求开发，并且每个月需要完成的需求不能超过部门人力。
 * <p>
 * 请帮助部门评估在满足需求开发进度的情况下，每个月需要的最小人力是多少？
 * <p>
 * 输入描述
 * 输入为 M 和 requirements，M 表示需求开发时间要求，requirements 表示每个需求工作量大小，N 为 requirements长度，
 * <p>
 * 1 ≤ N/2 ≤ M ≤ N ≤ 10000
 * 1 ≤ requirements[i] ≤ 10^9
 * 输出描述
 * 对于每一组测试数据，输出部门需要人力需求，行末无多余的空格
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //需求开发时间要求
        int m = Integer.parseInt(sc.nextLine());
        //每个需求工作量大小
        long[] requirements = Arrays.stream(sc.nextLine().split(" ")).mapToLong(Long::parseLong).toArray();
        System.out.println(getResult(m, requirements));

    }

    //二分法找到能满足m要求的最小人力
    public static long getResult(int m, long[] requirements) {
        //每个月要么完成一个需求，要么完成两个，不能完成1.5个，剩下0.5个到下个月完成
        int n = requirements.length;
        //按升序排序
        Arrays.sort(requirements);
        //最少人力至少是max，才能一个月完成该需求
        long min = requirements[n - 1];
        //最多最大和第二大的需求和 因为一个月最多完成两个需求
        long max = requirements[n - 1] + requirements[n - 2];
        //记录结果 取最小的
        long ans = max;
        //使用二分法
        while (min <= max) {
            long mid = (min + max) / 2;
            if (check(mid, m, requirements)) {
                //如果当前mid能完成，则说明mid是一个可能解，不一定是最优解，取最小值，再用二分法逼近最优值
                ans = Math.min(ans, mid);
                max = mid - 1;
            } else {
                //如果不能解决，则说明mid偏小，需要加大
                min = mid + 1;
            }
        }
        return ans;
    }

    /**
     * 每个月limit的人力是否能在m个月内处理完所有requirement
     *
     * @param limit
     * @param m
     * @param requirements 已升序排列
     * @return boolean
     * @create 2024/3/25 16:53
     */
    public static boolean check(long limit, long m, long[] requirements) {
        //双指针
        int l = 0;
        int r = requirements.length - 1;
        //在当前limit的条件下，处理完requirements需要的时间
        int need = 0;
        while (l <= r) {
            //如果最小和最大需求能在一个月内处理完，则合并处理，如果不能，则最大的单独一个月处理
            if (requirements[l] + requirements[r] <= limit) {
                l++;
            }
            r--;
            need++;
        }
        //如果need<=m 则表示可以在m个月内完成，返回true
        return need <= m;
    }
}