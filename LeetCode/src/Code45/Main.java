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
        //反向查找，每次都跳到能到达该点的最小索引处
        int pos = nums.length - 1;
        int step = 0;
        while (pos > 0) {
            //从0到pos遍历，找到第一个能跳到pos的点就返回
            for (int i = 0; i < pos; i++) {
                //i点能跳跃到pos，则i是离pos最远的点
                if (i + nums[i] >= pos) {
                    pos = i;
                    step++;
                    break;
                }
            }
        }

        return step;
    }

}