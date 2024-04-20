package Code1695;

import java.util.HashSet;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 删除子数组的最大得分
 * @date 2024/4/20
 * @level 中等
 * @url https://leetcode.cn/problems/maximum-erasure-value/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    /**
     * 返回 只删除一个 子数组可获得的 最大得分
     *
     * @param nums
     * @return int
     */
    public int maximumUniqueSubarray(int[] nums) {
        //题解：子数组最大得分
        int ans = 0;

        //窗口内元素和
        int sum = 0;

        //记录<元素，下标>
        HashSet<Integer> set = new HashSet<>();


        //双指针
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            //如果即将纳入的元素重复
            while (set.contains(nums[right])) {
                //不断移出窗口左端元素，直到不重复
                sum -= nums[left];
                set.remove(nums[left]);
                left++;
            }
            //此时[left,right]无重复元素
            set.add(nums[right]);
            sum += nums[right];

            //比较最大值
            ans = Math.max(ans, sum);
        }

        //返回最大窗口内元素和
        return ans;

    }
}