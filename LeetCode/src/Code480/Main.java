package Code480;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 滑动窗口中位数
 * @date 2024/5/9
 * @level 困难
 * @url <a href="https://leetcode.cn/problems/sliding-window-median/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    /**
     * 找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组
     *
     * @param nums
     * @param k
     * @return double[]
     */
    public double[] medianSlidingWindow(int[] nums, int k) {
        //中位数数组
        double[] ans = new double[nums.length - k + 1];

        //滑窗形成
        int left = 0;
        int right = k;
        int[] window;
        int index = 0;

        while (index < ans.length) {
            //此时窗口内数组，复制nums数组下标从left到right
            window = Arrays.copyOfRange(nums, left, right);

            //排序
            Arrays.sort(window);

            //此时窗口中位数
            ans[index++] = findMid(window);

            //移动窗口
            left++;
            right++;
        }

        return ans;
    }

    /**
     * 寻找一个数组的中位数
     *
     * @param nums
     * @return boolean
     */
    public double findMid(int[] nums) {
        int n = nums.length;
        //如果是奇数
        if (n % 2 != 0) {
            return nums[n / 2];
        } else {
            //偶数
            return (double) ((long) nums[n / 2] + nums[n / 2 - 1]) / 2;
        }
    }

}