package Code1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 两数之和
 * @date 2024/4/9
 * @level 简单
 * @score
 * @url https://leetcode.cn/problems/two-sum/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public int[] twoSum(int[] nums, int target) {
        //转为List
        int[] ans = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    ans = new int[]{i, j};
                    break;
                }
            }
        }
        return ans;
    }
}