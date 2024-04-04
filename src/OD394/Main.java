package OD394;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 跳格子3
 * @date 2024/3/26
 * @level 7
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD394
 */

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //格子总数
        int n = Integer.parseInt(sc.nextLine());
        //每个格子的分数
        int[] score = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //每次最大步长
        int k = Integer.parseInt(sc.nextLine());
        System.out.println(getResult(n, score, k));

    }

    public static int getResult(int n, int[] score, int k) {
        //滑动窗口长度：自身+前面k个格子
        int len = k + 1;

        //dp[i]表示跳到第i个格子得到的最大分数
        int[] dp = new int[n];
        dp[0] = score[0];

        //单调队列：保存dp[i-1]到dp[i-k]个值，降序
        LinkedList<Integer> queue = new LinkedList<>();
        //初始化
        queue.addLast(dp[0]);

        //滑窗形成过程
        for (int i = 1; i < Math.min(len, n); i++) {
            // dp[i] = max(dp[0]~dp[i-1]) + scores[i]
            // 即单调队列队首保存的是max(dp[0]~dp[i-1])
            dp[i] = queue.getFirst() + score[i];

            //保持单调递减
            while (queue.size() > 0 && dp[i] > queue.getLast()) {
                //队尾出栈
                queue.removeLast();
            }

            //当队尾没有比dp[i]更小的元素，dp[i]入栈
            queue.addLast(dp[i]);
        }

        //滑窗移动过程：每移动到一个新格子，这个格子能得到的最大值都是前面k个格子的最大值+自身，前面k个格子的最大值就是queue队首
        for (int i = len; i < n; i++) {
            //如果失去的刚好是前面第k个，则弹出队首，往后移动
            if (dp[i - len] == queue.getFirst()) {
                queue.removeFirst();
            }

            dp[i] = queue.getFirst() + score[i];

            while (queue.size() > 0 && dp[i] > queue.getLast()) {
                queue.removeLast();
            }

            queue.addLast(dp[i]);
        }

        //返回跳到第n个格子的最大值
        return dp[n - 1];
    }

}