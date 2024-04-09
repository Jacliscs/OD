package Code1004;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 最大连续1的个数III
 * @date 2024/4/9
 * @level 中等
 * @score
 * @url https://leetcode.cn/problems/max-consecutive-ones-iii/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public int longestOnes(int[] nums, int k) {
        //滑动窗口中0的个数满足cnt0<=k时，窗口的最大长度

        //题解
        int ans = 0;
        //左指针
        int left = 0;
        //滑窗中0的数量<=k
        int cnt0 = 0;

        //移动滑窗
        for (int right = 0; right < nums.length; right++) {
            //nums[]如果是0，则+1
            cnt0 += 1 - nums[right];

            //如果窗口内0的数量大于k，则需要移出
            while (cnt0 > k) {
                cnt0 -= 1 - nums[left++];
            }

            //现在[left,right]内cnt0=k
            ans = Math.max(ans, right - left + 1);
        }
        return ans;

    }
}