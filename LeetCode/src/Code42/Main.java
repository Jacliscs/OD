package Code42;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 接雨水
 * @date 2024/4/27
 * @level 困难
 * @url <a href="https://leetcode.cn/problems/trapping-rain-water/description/?envType=study-plan-v2&envId=top-100-liked">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    /**
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水
     *
     * @param height
     * @return int
     */
    public int trap(int[] height) {
        //从左到右遍历每一列：找到这一列左边最高的墙，和右边最高的墙，能存多少水取决于短边
        int n = height.length;

        //使用max_left[i]存放第i列左边最高的墙
        int[] max_left = new int[n];

        //使用max_right[i]存放第i列右边最高的墙
        int[] max_right = new int[n];

        //从左到右遍历每一列，找到左边最高的墙
        for (int i = 0; i < n; i++) {
            //比较第i-1列左边最高和第i-1列，取更高值
            max_left[i] = i == 0 ? 0 : Math.max(max_left[i - 1], height[i - 1]);
        }

        //从右到左遍历每一列，找到右边最高的墙
        for (int i = n - 1; i >= 0; i--) {
            max_right[i] = i == n - 1 ? 0 : Math.max(max_right[i + 1], height[i + 1]);
        }

        //计算每一列能存多少水
        int res = 0;
        for (int i = 0; i < n; i++) {
            //这一列必须小于左右两边的最短边，形成一个"凹字形水坑"才能存水
            int minHeight = Math.min(max_left[i], max_right[i]);
            if (minHeight > height[i]) {
                res += minHeight - height[i];
            }
        }
        return res;
    }
}