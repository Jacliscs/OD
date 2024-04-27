package Code283;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 移动零
 * @date 2024/4/27
 * @level 简单
 * @url <a href="https://leetcode.cn/problems/move-zeroes/description/?envType=study-plan-v2&envId=top-100-liked">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    /**
     * 在不复制数组的情况下原地对数组进行操作。
     *
     * @param nums
     * @return void
     */
    public void moveZeroes(int[] nums) {
        //双指针：左指针指向0，右指针往右遍历，遇到非0则与左指针交换
        int len = nums.length;
        int left = 0, right = 0;
        while (right < len) {
            if (nums[right] != 0) {
                //交换
                int tmp = nums[right];
                nums[right] = nums[left];
                nums[left] = tmp;
                left++;
            }
            //右指针遇到0和非零都会移动
            right++;
        }
    }
}