package OD367;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 剩余银饰的重量
 * @date 2024/3/23
 * @level 4
 * @score 100
 * @type 二分查找
 */

/**
 * 题目描述
 * 有 N 块二手市场收集的银饰，每块银饰的重量都是正整数，收集到的银饰会被熔化用于打造新的饰品。
 * <p>
 * 每一回合，从中选出三块最重的银饰，然后一起熔掉。
 * <p>
 * 假设银饰的重量分别为 x 、y和z，且 x ≤ y ≤ z。那么熔掉的可能结果如下：
 * <p>
 * 如果 x == y == z，那么三块银饰都会被完全熔掉；
 * 如果 x == y 且 y != z，会剩余重量为 z - y 的银块无法被熔掉；
 * 如果 x != y 且 y == z，会剩余重量为 y - x 的银块无法被熔掉；
 * 如果 x != y 且 y != z，会剩余重量为 z - y 与 y - x 差值 的银块无法被熔掉。
 * 最后，
 * <p>
 * 如果剩余两块，返回较大的重量（若两块重量相同，返回任意一块皆可）
 * 如果只剩下一块，返回该块的重量
 * 如果没有剩下，就返回 0
 * 输入描述
 * 输入数据为两行：
 * <p>
 * 第一行为银饰数组长度 n，1 ≤ n ≤ 40，
 * 第二行为n块银饰的重量，重量的取值范围为[1，2000]，重量之间使用空格隔开
 * 输出描述
 * 如果剩余两块，返回较大的重量（若两块重量相同，返回任意一块皆可）；
 * <p>
 * 如果只剩下一块，返回该块的重量；
 * <p>
 * 如果没有剩下，就返回 0。
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //银饰个数
        int n = sc.nextInt();
        //n块银饰的重量 使用LinkedList方便排序和插入
        LinkedList<Integer> weight = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            weight.add(sc.nextInt());
        }
        System.out.println(getResult(weight));

    }

    //计算剩余银饰
    public static int getResult(LinkedList<Integer> weight) {
        //int res = 0;
        //升序排列，每次从最后取三个
        weight.sort(Integer::compareTo);
        //如果剩余银饰>=3个，则每次取后三个
        while (weight.size() >= 3) {
            int z = weight.removeLast();
            int y = weight.removeLast();
            int x = weight.removeLast();

            //取出差值
            // 如果 x == y == z，那么下面公式结果：remain=0, 表示三块银饰完全融掉
            // 如果 x == y && y != z，那么下面公式结果：remain = z - y
            // 如果 x != y && y == z，那么下面公式结果：remain = y - x
            // 如果 x != y && y != z，那么下面公式结果：remain = Math.abs((z - y) - (y - x))
            int remain = Math.abs((z - y) - (y - x));
            //如果有剩余，则把剩余的银饰插入到原升序列表中
            if (remain != 0) {
                //如果 remain 被找到，返回值是它在列表中的索引。
                //如果 remain 没有被找到，返回值是它应该插入的位置，以便保持列表的排序顺序。
                //这个值通常是在列表中的一个负数，其绝对值表示 remain 应该插入到的位置的索引。具体来说，-(insertion point + 1)。
                int index = Collections.binarySearch(weight, remain);
                if (index < 0) {
                    index = -(index + 1);
                }
                //插入到index位置
                weight.add(index, remain);
            }
        }
        //如果剩两个，返回较大值
        if (weight.size() == 2) {
            return Math.max(weight.get(0), weight.get(1));
        } else if (weight.size() == 1) {
            return weight.get(0);
        } else {
            //没有剩，则返回0
            return 0;
        }
    }
}