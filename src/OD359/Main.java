package OD359;

import com.sun.source.tree.Tree;

import java.util.*;

/**
 * @author Jacliscs
 * @description 执行任务赚积分
 * @date 2024/3/23
 * @level 7
 * @score 100
 */

/**
 * 题目描述
 * 现有N个任务需要处理，同一时间只能处理一个任务，处理每个任务所需要的时间固定为1。
 * <p>
 * 每个任务都有最晚处理时间限制和积分值，在最晚处理时间点之前处理完成任务才可获得对应的积分奖励。
 * <p>
 * 可用于处理任务的时间有限，请问在有限的时间内，可获得的最多积分。
 * <p>
 * 输入描述
 * 第一行为一个数 N，表示有 N 个任务
 * <p>
 * 1 ≤ N ≤ 100
 * 第二行为一个数 T，表示可用于处理任务的时间
 * <p>
 * 1 ≤ T ≤ 100
 * 接下来 N 行，每行两个空格分隔的整数（SLA 和 V），SLA 表示任务的最晚处理时间，V 表示任务对应的积分。
 * <p>
 * 1 ≤ SLA ≤ 100
 * 0 ≤ V ≤ 100000
 * 输出描述
 * 可获得的最多积分
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //有n个任务
        int n = sc.nextInt();
        //用于处理任务的时间
        int t = sc.nextInt();
        //n个任务的 最晚处理时间 对应积分
        int[][] wos = new int[n][2];
        for (int i = 0; i < n; i++) {
            wos[i][0] = sc.nextInt();
            wos[i][1] = sc.nextInt();
        }

        //可获得的最多积分
        System.out.println(getResult(t, wos));

    }

    //再t时间内可获得的最多积分
    public static int getResult(int t, int[][] wos) {
        //按endTime升序排序
        Arrays.sort(wos, (a, b) -> a[0] - b[0]);
        //已获得的积分
        int value = 0;
        //当前时间
        int curTime = 0;
        //存放可能获得的积分
        ArrayList<Integer> list = new ArrayList<>();
        //遍历
        for (int[] wo : wos) {
            int endTime = wo[0];
            int score = wo[1];
            //当curTime<endTime 时，加入
            if (curTime < endTime) {
                list.add(score);
                value += score;
                curTime++;
            } else {
                //当curTime >= endTime时，用list总最小值与当前score比较，放入较大值
                //总是能存放最大的几个值
                list.sort((a, b) -> a - b);
                int min_score = list.get(0);
                if (score > min_score) {
                    list.remove(0);
                    list.add(score);
                    value += score - min_score;
                }
            }
        }
        //升序排列
        list.sort((a, b) -> a - b);
        //只保留最大的t个值
        while (list.size() > t) {
            value -= list.remove(0);
        }
        return value;

    }


}