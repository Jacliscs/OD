package Code55;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 跳跃游戏
 * @date 2024/4/9
 * @level 中等
 * @score
 * @url https://leetcode.cn/problems/jump-game/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nums = {3, 2, 1, 0, 4};
        Solution solution = new Solution();
        System.out.println(solution.canJump(nums));

    }
}

class Solution {
    public boolean canJump(int[] nums) {
        boolean[] used = new boolean[nums.length];

        return dfs(nums, 0, used);
    }

    public static boolean dfs(int[] nums, int index, boolean[] used) {
        //返回标志
        if (index == nums.length - 1) return true;

        //如果跳到的nums[index]格为0，且不是最后一格，则返回false
        if (nums[index] == 0 && index != nums.length - 1) return false;

        //如果可以直接到达末尾，返回true
        if (index + nums[index] >= nums.length - 1) return true;

        for (int i = index; i <= index + nums[index]; i++) {
            if (used[i]) continue;

            //每个位置可以跳1-nums[index]步
            for (int j = 1; j <= nums[i]; j++) {
                used[i] = true;
                //递归，在i位置跳j步，后续能否到达终点
                if (dfs(nums, i + j, used)) return true;
                //恢复
                used[i] = false;
            }
        }

        //没返回true，则说明不能到达终点
        return false;
    }

}