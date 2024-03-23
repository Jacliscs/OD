package OD355;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 小明找位置
 * @date 2024/3/23
 * @level 4
 * @score 100
 */

/**
 * 题目描述
 * 小朋友出操，按学号从小到大排成一列；
 * <p>
 * 小明来迟了，请你给小明出个主意，让他尽快找到他应该排的位置。
 * <p>
 * 算法复杂度要求不高于nLog(n)；学号为整数类型，队列规模 ≤ 10000；
 * <p>
 * 输入描述
 * 第一行：输入已排成队列的小朋友的学号（正整数），以","隔开；例如：
 * <p>
 * 93,95,97,100,102,123,155
 * <p>
 * 第二行：小明学号，如：
 * <p>
 * 110
 * <p>
 * 输出描述
 * 输出一个数字，代表队列位置（从1开始）。例如：
 * <p>
 * 6
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //已排列好的小朋友学号 从小到大
        int[] arr = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        //小明学号
        int n = sc.nextInt();
        System.out.println(getResult(arr, n));
    }

    //找到小明应该站的位置 序列从1开始
    public static int getResult(int[] arr, int n) {
        //二分法，从中间开始站
        int index = Arrays.binarySearch(arr, n);
        if (index < 0) {
            index = -index - 1;
        }
        return index + 1;
        //int left = 0;
        //int right = arr.length - 1;
        //int index = 0;
        ////如果第一位大于n，则小明排第一个
        //if (arr[0] > n) {
        //    return 1;
        //}
        //
        //while (right - left > 1) {
        //    int mid = (left + right) / 2;
        //    if (arr[mid] < n) {
        //        left = mid;
        //    } else if (arr[mid] > n) {
        //        right = mid;
        //    } else {
        //        //arr[mid] = n
        //        index = mid;
        //    }
        //}
        ////最后n应该添加到left的左边或right的右边
        //index = left + 2;
        //return index;
    }
}