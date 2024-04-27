package Code41;

import java.util.HashSet;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 缺失的第一个正数
 * @date 2024/4/27
 * @level 困难
 * @url <a href="https://leetcode.cn/problems/first-missing-positive/description/?envType=study-plan-v2&envId=top-100-liked">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    /**
     * 找出其中没有出现的最小的正整数。
     *
     * @param nums
     * @return int
     */
    public int firstMissingPositive(int[] nums) {
        //数组长度：nums最多包含n个正整数
        int n = nums.length;

        //缺失的最小正整数一定在[1,n+1]范围内
        boolean[] exist = new boolean[n + 1];

        for (int num : nums) {
            //如果在[1,n+1]范围内，则修改状态
            if (num >= 1 && num < n + 1) {
                exist[num] = true;
            }

            //不在范围内的不影响结果
        }
        //找到第一个不存在的，如果都存在，则最小的就是n+1
        for (int i = 1; i < exist.length; i++) {
            if (!exist[i]) return i;
        }

        return n + 1;
    }
}