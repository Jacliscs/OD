package Code128;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author 浮生
 * @description 最长连续序列
 * @date 2024/4/9
 * @level 中等
 * @score
 * @url https://leetcode.cn/problems/longest-consecutive-sequence/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    /**
     * 要求时间复杂度为0(n)
     *
     * @param nums
     * @return int
     */
    public int longestConsecutive(int[] nums) {
        //去重，存放出现的数字
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        //题解
        int ans = 0;

        for (int num : set) {
            //必须是一个连续序列的开头才行，不能有比自己小1的
            if (!set.contains(num - 1)) {
                //当前这个序列的长度
                int curLen = 1;
                //如果有比num大1的数，则不断+1
                while (set.contains(++num)) curLen++;
                //更新最大长度
                ans = Math.max(ans, curLen);
            }
        }
        return ans;
    }
}