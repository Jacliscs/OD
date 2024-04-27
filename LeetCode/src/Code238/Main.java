package Code238;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 除自身以外数组的乘积
 * @date 2024/4/27
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/product-of-array-except-self/description/?envType=study-plan-v2&envId=top-100-liked">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;

        //题解
        int[] ans = new int[n];

        //前缀积
        int[] prefix = new int[n + 1];
        prefix[0] = 1;
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] * nums[i - 1];
        }

        //后缀积
        int[] suffix = new int[n + 1];
        suffix[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] * nums[i + 1];
        }

        //遍历每个位置
        for (int i = 0; i < n; i++) {
            ans[i] = prefix[i] * suffix[i];
        }

        return ans;

    }
}