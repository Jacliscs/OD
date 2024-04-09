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
        //能跳的最远距离
        int fast = 0;
        //目标位置
        int target = nums.length - 1;
        //如果目标位置就是起点
        if (target == 0) return true;

        //逐个更新最远距离，一旦发现能到达target，就返回true
        for (int i = 0; i < nums.length; i++) {
            //如果当前位置是能到达的
            if (i <= fast) {
                //更新最远位置
                fast = Math.max(fast, i + nums[i]);
                //是否能到target
                if (fast >= target) return true;
            }
        }
        //遍历完没有返回true，则不嫩到达
        return false;
    }
}