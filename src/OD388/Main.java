package OD388;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 会议占用时间
 * @date 2024/3/23
 * @level 4
 * @score 100
 */

/**
 * 题目描述
 * 现有若干个会议，所有会议共享一个会议室，用数组表示各个会议的开始时间和结束时间，格式为：
 * <p>
 * [[会议1开始时间, 会议1结束时间], [会议2开始时间, 会议2结束时间]]
 * <p>
 * 请计算会议室占用时间段。
 * <p>
 * 输入描述
 * 第一行输入一个整数 n，表示会议数量
 * <p>
 * 之后输入n行，每行两个整数，以空格分隔，分别表示会议开始时间，会议结束时间
 * <p>
 * 输出描述
 * 输出多行，每个两个整数，以空格分隔，分别表示会议室占用时间段开始和结束
 * <p>
 * 备注
 * 会议室个数范围：[1, 100]
 * 会议室时间段：[1, 24]题目描述
 * 现有若干个会议，所有会议共享一个会议室，用数组表示各个会议的开始时间和结束时间，格式为：
 * <p>
 * [[会议1开始时间, 会议1结束时间], [会议2开始时间, 会议2结束时间]]
 * <p>
 * 请计算会议室占用时间段。
 * <p>
 * 输入描述
 * 第一行输入一个整数 n，表示会议数量
 * <p>
 * 之后输入n行，每行两个整数，以空格分隔，分别表示会议开始时间，会议结束时间
 * <p>
 * 输出描述
 * 输出多行，每个两个整数，以空格分隔，分别表示会议室占用时间段开始和结束
 * <p>
 * 备注
 * 会议室个数范围：[1, 100]
 * 会议室时间段：[1, 24]
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //会议个数
        int n = sc.nextInt();//[1,100]
        int[][] meets = new int[n][2];
        for (int i = 0; i < n; i++) {
            meets[i][0] = sc.nextInt();
            meets[i][1] = sc.nextInt();
        }
        //存放结果
        int[][] res = merge(meets);

        Arrays.stream(res).forEach(time -> System.out.println(time[0] + " " + time[1]));
        //for (int[] time : res) {
        //    System.out.println(time[0] + " " + time[1]);
        //}
    }

    //输出会议室占用时间，重复的合并
    public static int[][] merge(int[][] meets) {
        int n = meets.length;
        //按开始时间排序
        Arrays.sort(meets, (a, b) -> a[0] - b[0]);
        //存放结果
        ArrayList<int[]> list = new ArrayList<>();
        //上一个占用时间
        int[] pre = meets[0];
        for (int i = 1; i < n; i++) {
            //当前占用时间
            int[] cur = meets[i];
            //当前开始时间一定会大于上一个开始时间，如果小于等于上一个结束时间，则可以合并
            if (cur[0] <= pre[1]) {
                pre[1] = Math.max(pre[1], cur[1]);
            } else {
                //与上一个没有重合，把上一个添加进结果
                list.add(pre);
                //刷新状态
                pre = cur;
            }
        }
        //最后的pre一定与前面那个没重合
        list.add(pre);
        return list.toArray(new int[list.size()][]);
    }

}