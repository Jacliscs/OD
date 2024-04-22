package Code581;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 最短无序连续子数组
 * @date 2024/4/21
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/shortest-unsorted-continuous-subarray/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public int findUnsortedSubarray(int[] nums) {
        //假设nums由n1 n2 n3三段数组组成，排序n2后整个nums都升序

        int[] sorted = Arrays.copyOf(nums, nums.length);

        //排序
        Arrays.sort(sorted);

        //如果排序后与原数组相同，返回0
        if (Arrays.equals(nums, sorted)) return 0;

        //无序子数组的左右边界
        int left = 0;
        int right = nums.length - 1;

        while (nums[left] == sorted[left]) left++;

        while (nums[right] == sorted[right]) right--;

        return right - left + 1;
    }
}