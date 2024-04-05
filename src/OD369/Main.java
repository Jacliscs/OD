package OD369;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description CPU算力分配
 * @date 2024/3/18
 * @level 6
 * @score 100
 */

/**
 * 题目描述
 * 现有两组服务器A和B，每组有多个算力不同的CPU，其中 A[i] 是 A 组第 i 个CPU的运算能力，B[i] 是 B组 第 i 个CPU的运算能力。
 * <p>
 * 一组服务器的总算力是各CPU的算力之和。
 * <p>
 * 为了让两组服务器的算力相等，允许从每组各选出一个CPU进行一次交换，
 * <p>
 * 求两组服务器中，用于交换的CPU的算力，并且要求从A组服务器中选出的CPU，算力尽可能小。
 * <p>
 * 输入描述
 * 第一行输入为L1和L2，以空格分隔，L1表示A组服务器中的CPU数量，L2表示B组服务器中的CPU数量。
 * <p>
 * 第二行输入为A组服务器中各个CPU的算力值，以空格分隔。
 * <p>
 * 第三行输入为B组服务器中各个CPU的算力值，以空格分隔。
 * <p>
 * 1 ≤ L1 ≤ 10000
 * 1 ≤ L2 ≤ 10000
 * 1 ≤ A[i] ≤ 100000
 * 1 ≤ B[i] ≤ 100000
 * 输出描述
 * 对于每组测试数据，输出两个整数，以空格分隔，依次表示A组选出的CPU算力，B组选出的CPU算力。
 * <p>
 * 要求从A组选出的CPU的算力尽可能小。
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //两组服务器分别的CPU数量
        int n1 = sc.nextInt();
        int n2 = sc.nextInt();
        //服务器A的CPU算力
        int[] a = new int[n1];
        for (int i = 0; i < n1; i++) {
            a[i] = sc.nextInt();
        }
        //服务器B的CPU算力
        int[] b = new int[n2];
        for (int i = 0; i < n2; i++) {
            b[i] = sc.nextInt();
        }
        System.out.println(swap(a, b));
    }

    //交换两个数组中的一个值，使两数组和相等，要求从a中取的数要尽量小
    public static String swap(int[] arrA, int[] arrB) {
        int sumA = sum(arrA);
        int sumB = sum(arrB);
        //因为答案肯定存在，所以一定能被2整除
        int target = (sumA - sumB) / 2;

        //交换的值尽可能小
        //int min = Integer.MAX_VALUE;

        //把A升序排序，从头遍历，找到的第一个可交换的就一定是最小的
        Arrays.sort(arrA);


        //记录题解
        String ans = "";

        //sumA - a + b = sumB -b + a
        // a-b = (sumA-sumB) / 2
        //即 a-b = target
        for (int a : arrA) {
            int temp = a - target;
            //如果arrB中存在temp，因为A是升序排序，则当前a，b就是最优解
            for (int b : arrB) {
                if (b == temp) {
                    return a + " " + b;
                }
            }
        }
        //输入保证答案肯定存在
        return ans;

    }

    //求数组和
    public static int sum(int[] a) {
        int ans = 0;
        for (int i : a) {
            ans += i;
        }
        return ans;
    }

}