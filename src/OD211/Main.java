package OD211;

import java.util.Scanner;
import java.util.StringJoiner;

/**
 * @author Jacliscs
 * @description 找朋友
 * @date 2024/3/24
 * @level 6
 * @score 100
 */

/**
 * 题目描述
 * 在学校中，N个小朋友站成一队， 第i个小朋友的身高为height[i]，
 * <p>
 * 第i个小朋友可以看到的第一个比自己身高更高的小朋友j，那么j是i的好朋友(要求j > i)。
 * <p>
 * 请重新生成一个列表，对应位置的输出是每个小朋友的好朋友位置，如果没有看到好朋友，请在该位置用0代替。
 * <p>
 * 小朋友人数范围是 [0, 40000]。
 * <p>
 * 输入描述
 * 第一行输入N，N表示有N个小朋友
 * <p>
 * 第二行输入N个小朋友的身高height[i]，都是整数
 * <p>
 * 输出描述
 * 输出N个小朋友的好朋友的位置
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //小朋友个数
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        System.out.println(getResult(nums));
    }

    //每个位置从左到右第一个比自己身高更高的位置
    public static String getResult(int[] nums) {
        int n = nums.length;
        StringJoiner sj = new StringJoiner(" ");
        //双指针 右指针=left+1
        int left = 0;
        int right = 1;
        while (left < n) {
            //右指针往右移动，找第一个比自己高的
            if (right >= n) {
                sj.add("0");
                left++;
                right = left + 1;
                continue;
            }
            if (nums[right] <= nums[left]) {
                right++;
            } else {
                //找到的第一个比自己高的
                sj.add(right + "");
                left++;
                right = left + 1;
            }
        }
        return sj.toString();
    }

}