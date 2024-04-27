package Code53;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 最大子数组和
 * @date 2024/4/27
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/maximum-subarray/description/?envType=study-plan-v2&envId=top-100-liked">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    /**
     * 请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和
     *
     * @param nums
     * @return int
     */
    public int maxSubArray(int[] nums) {
        //题解：运用前缀和 如果遍历到nums[i]时，此时nums[i]之前的和<=0 那么以i结尾的数组的最大和就是他本身
        int preSum = 0;
        int res = Integer.MIN_VALUE;
        for (int num : nums) {
            preSum = Math.max(preSum + num, num);
            res = Math.max(res, preSum);
        }

        return res;
    }
}