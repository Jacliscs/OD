package Code826;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 安排工作以达到最大收益
 * @date 2024/4/25
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/most-profit-assigning-work/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public static class Job {
        //工作难度
        int difficulty;
        //工作收益
        int profit;

        public Job(int difficulty, int profit) {
            this.difficulty = difficulty;
            this.profit = profit;
        }
    }


    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length;
        int m = worker.length;

        //初始化工作
        Job[] jobs = new Job[n];

        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(difficulty[i], profit[i]);
        }

        //按工作难度升序
        Arrays.sort(jobs, Comparator.comparingInt(o -> o.difficulty));

        //工人也按能力升序
        Arrays.sort(worker);


        //top指某种难度下至少能获得的利润
        int top = 0, ans = 0;
        //i指向能完成的工作，j指向当前工人
        for (int i = 0, j = 0; i < n; i++) {
            //工人j无法承担i任务
            while (j < m && worker[j] < jobs[i].difficulty) j++;

            //找到工人j能完成i任务 如果j已经指向末尾，表明没有人能完成i任务及后面的任务
            if (j == m) break;

            //当前工人j能完成i任务，所以后面的m-j个工人至少能完成i任务，先去掉之前更低的收入
            ans -= (m - j) * top;

            //更新当前j及后面的工人所能获得的最大收益
            top = Math.max(top, jobs[i].profit);

            //加上最大收益
            ans += (m - j) * top;
        }

        return ans;

    }
}