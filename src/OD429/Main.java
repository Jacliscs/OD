package OD429;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 篮球游戏
 * @date 2024/3/29
 * @level 6
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD429
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //放进的篮球编号
        int[] inputs = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        //要取出的篮球编号
        int[] outputs = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();

        System.out.println(getResult(inputs, outputs));
    }

    //返回取出篮球的顺序
    public static String getResult(int[] inputs, int[] outputs) {
        //模拟桶 从右边放进 可以从左边和右边出
        LinkedList<Integer> queue = new LinkedList<>();

        //指向取出篮球的索引
        int index = 0;

        //记录题解
        StringBuilder sb = new StringBuilder();

        for (int input : inputs) {
            //按顺序遍历从右边放进球
            queue.addLast(input);
            //每放进一个球，都判断是否能从左或者从右取球
            while (queue.size() > 0) {
                //从左边取
                int left = queue.getFirst();
                //从右边取
                int right = queue.getLast();
                //优先从左边取
                if (left == outputs[index]) {
                    sb.append("L");
                    //取出
                    queue.removeFirst();
                    //看下一个待取出的球是否能在当前queue的左边或右边取出
                    index++;
                } else if (right == outputs[index]) {
                    sb.append("R");
                    queue.removeLast();
                    index++;
                } else {
                    //左和右都不能取 则跳过 放下一个球
                    break;
                }
            }
        }
        //如果outputs中的所有球取出，则index==outputs.length，否则打印NO
        //这样不管是inputs里面有冗余的数还是outputs里面有没放入的球的编号，都能检测
        if (index == outputs.length) {
            return sb.toString();
        } else {
            return "NO";
        }
    }
}