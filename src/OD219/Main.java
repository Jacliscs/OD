package OD219;

import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 执行时长
 * @date 2024/3/20
 * @level 5
 * @score 100
 */

/**
 * 题目描述
 * 为了充分发挥GPU算力，需要尽可能多的将任务交给GPU执行，现在有一个任务数组，数组元素表示在这1秒内新增的任务个数且每秒都有新增任务。
 * <p>
 * 假设GPU最多一次执行n个任务，一次执行耗时1秒，在保证GPU不空闲情况下，最少需要多长时间执行完成。
 * <p>
 * 输入描述
 * 第一个参数为GPU一次最多执行的任务个数，取值范围[1, 10000]
 * <p>
 * 第二个参数为任务数组长度，取值范围[1, 10000]
 * <p>
 * 第三个参数为任务数组，数字范围[1, 10000]
 * <p>
 * 输出描述
 * 执行完所有任务最少需要多少秒。
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //一次最多执行的个数
        int max = Integer.parseInt(sc.nextLine());
        //任务数组长度
        int length = Integer.parseInt(sc.nextLine());
        //任务
        int[] tasks = new int[length];
        for (int i = 0; i < length; i++) {
            tasks[i] = sc.nextInt();
        }
        //需要多少秒执行完
        System.out.println(getSeconds(tasks, max));
    }

    //需要多少秒执行完
    public static int getSeconds(int[] tasks, int max) {
        int time = 0;
        int remain = 0;
        for (int task : tasks) {
            if (task + remain > max) {
                //本次处理完后，还有多余任务遗留
                remain = task + remain - max;
            } else {
                //本次处理完后，没有遗留
                remain = 0;
            }
            //每一次都会花费1秒
            time++;
        }
        //如果最后还有多余任务
        while (remain > 0) {
            remain -= max;
            time++;
        }
        return time;
    }
}