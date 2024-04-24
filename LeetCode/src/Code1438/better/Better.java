package Code1438.better;

import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author 浮生
 * @description 绝对差不超过限制的最长连续子数组
 * @date 2024/4/24
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Better {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public int longestSubarray(int[] nums, int limit) {
        //维护窗口保证窗口中数字绝对差不超过limit 使用TreeMap有序存储key
        TreeMap<Integer, Integer> map = new TreeMap<>();
        //双指针
        int left = 0;
        int right = 0;
        //题解
        int ans = 0;
        while (right < nums.length) {
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);

            //如果此时窗口的右端点和左端点的值绝对差大于limit，则移动左指针
            while (map.lastKey() - map.firstKey() > limit) {
                map.put(nums[left], map.get(nums[left]) - 1);
                if (map.get(nums[left]) == 0) {
                    map.remove(nums[left]);
                }
                left++;
            }
            ans = Math.max(ans, right - left + 1);
            right++;
        }
        return ans;
    }
}