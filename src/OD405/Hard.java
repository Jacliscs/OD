package OD405;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

/**
 * @author 浮生
 * @description 不超时
 * @date 2024/3/27
 * @level
 * @score
 * @url
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Hard {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //n个任务
        int n = sc.nextInt();
        int[][] tasks = new int[n][2];
        //任务开始时间和截止时间
        for (int i = 0; i < n; i++) {
            tasks[i][0] = sc.nextInt();
            tasks[i][1] = sc.nextInt();
        }
        //按截止时间降序
        Arrays.sort(tasks, (a, b) -> b[1] - a[1]);

        //优先队列按任务开始时间降序排列，开始时间越大，优先级越高，越先执行
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        //pq_end是优先队列中的任务的相同截止时间
        int pq_end = Integer.MAX_VALUE;
        //题解，可以执行的最大任务数
        int count = 0;

        for (int[] task : tasks) {
            int start = task[0];
            int end = task[1];
            //如果优先队列中有需要完成的任务，并且当前任务的截止时间小于优先队列的最大截止时间
            while (pq.size() > 0 && end < pq_end) {
                //开始时间越大的越优先执行，且开始时间不能大于pq_end
                if (pq.poll() <= pq_end) {
                    //该任务可以执行
                    count++;
                    //将优先队列里面的共同截止时间-1
                    pq_end--;
                }
            }
            //更新优先队列的结束时间 tasks按截止时间降序排列
            pq.add(start);
            pq_end = end;
        }
        //收尾处理，如果优先队列里还有任务，且满足条件，则可以执行
        while (pq.size() > 0) {
            if (pq.poll() <= pq_end) {
                count++;
                pq_end--;
            }
        }
        System.out.println(count);
    }
}