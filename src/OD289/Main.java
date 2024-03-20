package OD289;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 整数对最小和
 * @date 2024/3/20
 * @level 6
 * @score 100
 */

/**
 * 题目描述
 * 给定两个整数数组array1、array2，数组元素按升序排列。
 * <p>
 * 假设从array1、array2中分别取出一个元素可构成一对元素，现在需要取出k对元素，
 * <p>
 * 并对取出的所有元素求和，计算和的最小值。
 * <p>
 * 注意：
 * <p>
 * 两对元素如果对应于array1、array2中的两个下标均相同，则视为同一对元素。
 * <p>
 * 输入描述
 * 输入两行数组array1、array2，每行首个数字为数组大小size(0 < size <= 100);
 * <p>
 * 0 < array1[i] <= 1000
 * <p>
 * 0 < array2[i] <= 1000
 * <p>
 * 接下来一行为正整数k
 * <p>
 * 0 < k <= array1.size() * array2.size()
 * <p>
 * 输出描述
 * 满足要求的最小和
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //第一个数组大小
        int n1 = sc.nextInt();
        //第一个数组
        int[] arr1 = new int[n1];
        for (int i = 0; i < n1; i++) {
            arr1[i] = sc.nextInt();
        }
        //第二个数组大小
        int n2 = sc.nextInt();
        int[] arr2 = new int[n2];
        for (int i = 0; i < n2; i++) {
            arr2[i] = sc.nextInt();
        }
        //需要找出k对
        int k = sc.nextInt();
        System.out.println(getMinSum(arr1, arr2, k));

    }

    //从两个数组中取出k对，并求最小和
    public static int getMinSum(int[] arr1, int[] arr2, int k) {
        int sum = 0;
        //记录所有整数对的和
        List<Integer> list = new ArrayList<>();
        //两个数组默认按升序排列
        //直接添加所有整数和，升序排列，从前往后取k个
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                list.add(arr1[i] + arr2[j]);
            }
        }
        //排序整数对
        Collections.sort(list);
        //取出k个值相加
        for (int i = 0; i < k; i++) {
            sum += list.get(i);
        }
        return sum;
    }
}