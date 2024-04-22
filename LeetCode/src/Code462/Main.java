package Code462;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 最小操作次数使数组元素相等II
 * @date 2024/4/22
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/minimum-moves-to-equal-array-elements-ii/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public int minMoves2(int[] nums) {
        //排序后找中位数，都变成中位数所需步骤和最小
        Arrays.sort(nums);

        int n = nums.length;
        int step = 0;

        //中位数：奇数的话就是刚好中位数，偶数个的话，变为nums[n/2]和变为nums[n/2-1]是一样的
        int mid = nums[n / 2];

        for (int i = 0; i < n; i++) {
            step += Math.abs(nums[i] - mid);
        }

        return step;

    }

}