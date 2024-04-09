package Code45;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 跳跃游戏2
 * @date 2024/4/9
 * @level 中等
 * @score
 * @url https://leetcode.cn/problems/jump-game-ii/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    /**
     * 返回跳跃到最后一格的最少步数
     *
     * @param nums
     * @return int
     */
    public int jump(int[] nums) {
        //由输入控制一定能跳到最后
        //当前能跳到的边界位置，每次边界位置都更新为能跳的最大距离
        int end = 0;
        //在第i步内，所能到达的最远位置，每到达边界位置时才会步数+1
        int maxPos = 0;
        int step = 0;

        //不遍历最后一个位置
        for (int i = 0; i < nums.length - 1; i++) {
            maxPos = Math.max(maxPos, i + nums[i]);
            //到达边界时，更新下一步能到达的最远距离
            if (i == end) {
                end = maxPos;
                step++;
            }
        }
        return step;
    }
}