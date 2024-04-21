package Code15;

import java.util.*;

/**
 * @author 浮生
 * @description 三数之和
 * @date 2024/4/7
 * @level 中等
 * @score
 * @url <a href="https://leetcode.cn/problems/3sum/description/">...</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        //保留结果
        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        //异常情况
        if (nums == null || len < 3) return ans;

        //先升序排列，方便去重
        Arrays.sort(nums);

        for (int i = 0; i < len; i++) {
            //nums升序排列的，如果第一位大于0，后面三数之和一定大于0，结束循环
            if (nums[i] > 0) break;
            //如果跟上一位一样，则去重 继续下一位
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            //左边界
            int left = i + 1;
            //右边届
            int right = len - 1;
            //要找到num[i]能组合的所有nums[left]和nums[right]
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    //添加当前组合
                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    //去重，左右边界都移动到下一个不相同的位置
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    //缩小边界
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                }
            }
        }
        return ans;
    }
}