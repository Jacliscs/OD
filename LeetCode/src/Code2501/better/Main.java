package Code2501.better;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 数组中最长的方波-dp动态规划
 * @date 2024/5/1
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/longest-square-streak-in-an-array/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    /**
     * dp动态规划求
     *
     * @param nums
     * @return int
     */
    public int longestSquareStreak(int[] nums) {
        //排序
        Arrays.sort(nums);
        int n = nums.length;
        //最大值
        int max = nums[n - 1];
        //默认题解
        int ans = -1;

        //dp[i] 表示以i结尾的方波数组的长度
        int[] dp = new int[max + 1];

        //初始化，每个数字以自己结尾的方波长度为1
        for (int i = 0; i < n; i++) {
            dp[nums[i]] = 1;
        }

        //动态规划
        for (int i = 0; i < n; i++) {
            int x = (int) Math.sqrt(nums[i]);
            //如果nums[i]不能整除，则跳过
            if (x * x != nums[i]) continue;

            //如果x这个数不存在数组中，则dp[x]=0 dp[i]=0+1=1 不改变值
            //否则，dp[x]表示以x结尾的方波数组的长度，dp[i]=dp[x]+1 长度+1
            dp[nums[i]] = dp[x] + 1;
        }

        for (int i = 0; i < n; i++) {
            if (dp[nums[i]] > 2) {
                ans = Math.max(ans, dp[nums[i]]);
            }
        }
        return ans;

    }
}