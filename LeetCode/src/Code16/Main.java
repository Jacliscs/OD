package Code16;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 最接近的三数之和
 * @date 2024/5/1
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/3sum-closest/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    /**
     * 返回最接近target的三数之和
     *
     * @param nums
     * @param target
     * @return int
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        //3 <= nums.length <= 1000
        int n = nums.length;

        //三数之和与target的差值
        int diff = Integer.MAX_VALUE;

        //结果，最接近的三数和
        int ans = 0;

        //第一个数
        for (int i = 0; i < n; i++) {
            //去重
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            //左右边界
            int left = i + 1;
            int right = n - 1;

            //当i=n-2时，left=right
            while (left < right) {
                //此时的三数之和
                int sum = nums[i] + nums[left] + nums[right];

                //与target差值
                int curDiff = Math.abs(sum - target);

                //如果差值为0，则一定是这组数
                if (curDiff == 0) return sum;

                //如果差值更小，则更新
                if (curDiff < diff) {
                    diff = curDiff;
                    ans = sum;
                }

                //如果sum在target左边，想接近target只能移动left
                if (sum < target) left++;
                else if (sum > target) right--;
            }
        }

        return ans;
    }
}