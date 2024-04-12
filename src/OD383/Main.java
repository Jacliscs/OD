package OD383;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 求幸存数之和
 * @date 2024/3/23
 * @level 5
 * @score 100
 */

/**
 * 题目描述
 * 给一个正整数数列 nums，一个跳数 jump，及幸存数量 left。
 * <p>
 * 运算过程为：从索引0的位置开始向后跳，中间跳过 J 个数字，命中索引为 J+1 的数字，该数被敲出，并从该点起跳，以此类推，直到幸存 left 个数为止，然后返回幸存数之和。
 * <p>
 * 约束：
 * <p>
 * 0是第一个起跳点
 * 起跳点和命中点之间间隔 jump 个数字，已被敲出的数字不计入在内。
 * 跳到末尾时无缝从头开始（循环查找），并可以多次循环。
 * 若起始时 left > len(nums) 则无需跳数处理过程。
 * 方法设计：
 * <p>
 * /**
 * * @param nums 正整数数列，长度范围 [1, 10000]
 * * @param jump 跳数，范围 [1, 10000]
 * * @param left 幸存数量，范围 [0, 10000]
 * * @return 幸存数之和
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //正整数序列
        int[] nums = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        //跳数
        int jump = Integer.parseInt(sc.nextLine());
        //幸存数量
        int left = Integer.parseInt(sc.nextLine());
        System.out.println(sumOfLeft(nums, jump, left));
    }

    //返回剩余数字的和
    public static int sumOfLeft(int[] nums, int jump, int left) {
        LinkedList<Integer> list = new LinkedList<>();
        //添加到list
        Arrays.stream(nums).forEach(list::add);

        int start = 1;
        //如果幸存数量大于剩余数字，则不需要删除，否则需要删除
        while (list.size() > left) {
            //从0开始起跳 中间间隔jump个数字 第一个被移除的下标为jump+1
            //到末尾时回到o
            start += jump;
            //防止越界
            start %= list.size();
            list.remove(start);
        }

        //返回幸存数之和
        return list.stream().reduce(Integer::sum).orElse(0);
    }
}