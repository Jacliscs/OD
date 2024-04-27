package Code11;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 盛水最多的容器
 * @date 2024/4/27
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/container-with-most-water/description/?envType=study-plan-v2&envId=top-100-liked">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    /**
     * 返回容器可以储存的最大水量。
     *
     * @param height
     * @return int
     */
    public int maxArea(int[] height) {
        //双指针：遍历所有能作为容器左右边界的边，每次移动较小的边
        int left = 0;
        int right = height.length - 1;

        //题解：最大水量
        int ans = 0;
        while (left < right) {
            //面积由最短边 * 宽度决定
            int minHeight = Math.min(height[left], height[right]);

            //更新最大水
            ans = Math.max(ans, minHeight * (right - left));

            //移动短的那条边：宽度已经一定变小，如果新的高度小于等于短边，则一定更小
            while (left < right && height[left] <= minHeight) left++;

            while (left < right && height[right] <= minHeight) right--;

        }
        return ans;

    }
}