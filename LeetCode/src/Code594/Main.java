package Code594;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 最长和谐子序列
 * @date 2024/4/9
 * @level 简单
 * @score
 * @url https://leetcode.cn/problems/longest-harmonious-subsequence/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public int findLHS(int[] nums) {
        //先排序
        Arrays.sort(nums);
        //题解：最长和谐子序列的长度
        int ans = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            //left是较小数字的左边界 right是较大数字的右边届
            while (nums[right] - nums[left] > 1) {
                left++;
            }

            //如果两个数字差刚好为1
            if (nums[right] - nums[left] == 1) {
                ans = Math.max(ans, right - left + 1);
            }
        }
        //存放每个数字出现的次数
        //HashMap<Integer, Integer> map = new HashMap<>();
        //for (int i = 0; i < nums.length; i++) {
        //    map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        //}
        //for (int key : map.keySet()) {
        //    //如果存在刚好比key大1的数
        //    if (map.containsKey(key + 1)) {
        //        //最长和谐子序列的长度就是key key+1的次数和
        //        ans = Math.max(ans, map.get(key) + map.get(key + 1));
        //    }
        //}
        return ans;
    }
}