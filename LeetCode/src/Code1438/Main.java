package Code1438;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 绝对差不超过限制的最长连续子数组
 * @date 2024/4/24
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public int longestSubarray(int[] nums, int limit) {
        //题解：最长连续子数组的长度
        int maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            //如果当前最大长度小于maxLen，则break
            if (maxLen >= nums.length - i) break;

            for (int j = i; j < nums.length; j++) {
                //如果当前长度小于maxLen，直接跳过
                if (j - i + 1 < maxLen) continue;

                //复制数组[i,j]部分
                int[] newNum = Arrays.copyOfRange(nums, i, j + 1);

                //如果绝对差不超过limit，且长度更长，更新
                if (getMax(newNum) <= limit && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                }
            }
        }
        return maxLen;
    }


    /**
     * 获得一个数组的最大绝对差
     *
     * @param nums
     * @return int
     */
    public static int getMax(int[] nums) {
        //最大值
        int max = Arrays.stream(nums).max().orElse(0);
        //最小值
        int min = Arrays.stream(nums).min().orElse(0);

        //返回最大绝对差
        return Math.abs(max - min);
    }
}