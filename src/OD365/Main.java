package OD365;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 传递悄悄话
 * @date 2024/3/23
 * @level 4
 * @score 100
 */

/**
 * 题目描述
 * 给定一个二叉树，每个节点上站一个人，节点数字表示父节点到该节点传递悄悄话需要花费的时间。
 * <p>
 * 初始时，根节点所在位置的人有一个悄悄话想要传递给其他人，求二叉树所有节点上的人都接收到悄悄话花费的时间。
 * <p>
 * 输入描述
 * 给定二叉树
 * <p>
 * 0 9 20 -1 -1 15 7 -1 -1 -1 -1 3 2
 * <p>
 * 注：-1表示空节点
 * <p>
 * image
 * <p>
 * 输出描述
 * 返回所有节点都接收到悄悄话花费的时间
 * <p>
 * 38
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //输入节点
        int[] times = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //最大时间
        int maxTime = 0;

        //记录节点索引
        LinkedList<Integer> queue = new LinkedList<>();
        //根节点的索引是0
        queue.addLast(0);
        while (!queue.isEmpty()) {
            //取父节点索引
            int father_index = queue.removeFirst();
            //左右子节点索引为 2k+1 2k+2
            int left_index = 2 * father_index + 1;
            int right_index = 2 * father_index + 2;

            //判断父节点是否有子节点
            boolean left_exist = left_index < times.length && times[left_index] != -1;
            boolean right_exist = right_index < times.length && times[right_index] != -1;

            //如果有左子节点，则从根节点到该节点的时间加上父节点
            if (left_exist) {
                times[left_index] += times[father_index];
                queue.addLast(left_index);
            }
            //如果有右子节点
            if (right_exist) {
                times[right_index] += times[father_index];
                queue.addLast(right_index);
            }
            //如果没有子节点
            if (!left_exist && !right_exist) {
                maxTime = Math.max(maxTime, times[father_index]);
            }
        }
        System.out.println(maxTime);
    }
}