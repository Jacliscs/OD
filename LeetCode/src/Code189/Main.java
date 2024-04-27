package Code189;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 轮转数组
 * @date 2024/4/27
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/rotate-array/description/?envType=study-plan-v2&envId=top-100-liked">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public void rotate(int[] nums, int k) {
        //法一：复制数组
        //创建新数组存放移动后的位置，然后复制给nums
        //method(nums, k);

        //法二：反转数组
        k %= nums.length;//防止下标越界

        //先整体反转，再反转前面k个，再反转剩下的
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public static void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int tmp = nums[right];
            nums[right] = nums[left];
            nums[left] = tmp;
            left++;
            right--;
        }
    }


    private static void method(int[] nums, int k) {
        int[] newArr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            newArr[(i + k) % nums.length] = nums[i];
        }
        //复制
        System.arraycopy(newArr, 0, nums, 0, nums.length);
    }
}