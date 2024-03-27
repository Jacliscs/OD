package OD405;

import java.time.OffsetDateTime;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 任务处理、可以处理的最大任务数
 * @date 2024/3/27
 * @level 7
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD405
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    //任务类 记录开始时间 结束时间
    static class Task {
        int start;
        int end;

        public Task(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //任务数量
        int n = sc.nextInt();
        //优先队列 先按结束时间降序 结束时间相同 按开始时间降序
        PriorityQueue<Task> pq = new PriorityQueue<>((a, b) -> {
            if (a.end != b.end) return b.end - a.end;
            else return b.start - a.start;
        });
        //添加任务
        for (int i = 0; i < n; i++) {
            pq.offer(new Task(sc.nextInt(), sc.nextInt()));
        }
        //记录最大能完成的任务数
        int count = 0;
        //记录上一个任务的结束时间
        int last_end = Integer.MAX_VALUE;
        while (!pq.isEmpty()) {
            //弹出优先级最高的任务
            Task task = pq.poll();
            if (task.end < last_end) {
                //如果当前任务结束时间比上一个小，则在结束时间的时候执行该任务
                last_end = task.end;
                //可执行的任务数量加一
                count++;
            }
            //同时更新后的end时间last_end-1不能小于自己的开始时间，否则不能执行 所以last_end>=task_start+1
            else if (last_end > task.start) {
                //当前任务结束时间>=上一个 则更新当前任务结束时间为last_end-1 重新加入优先队列排队
                task.end = last_end - 1;
                pq.offer(task);
            }
        }
        System.out.println(count);
    }
}