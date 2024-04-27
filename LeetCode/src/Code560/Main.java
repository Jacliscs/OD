package Code560;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 和为k的子数组
 * @date 2024/4/27
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/subarray-sum-equals-k/description/?envType=study-plan-v2&envId=top-100-liked">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    /**
     * 返回和为k的连续非空子串的个数
     *
     * @param nums
     * @param k
     * @return int
     */
    public int subarraySum(int[] nums, int k) {
        //从前往后计算前缀和：比如遍历到第i位时前缀和为sum，只需要看前面是否出现过前缀和为sum-k的
        HashMap<Integer, Integer> map = new HashMap<>();
        //初始化添加一个(0,1) sum=k时次数加一
        map.put(0, 1);


        int preSum = 0;
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];

            //[0,...i]的和为preSum 如果存在[0,..j]的和为preSum-k，则[j,..i]的和为k
            if (map.containsKey(preSum - k)) ans += map.get(preSum - k);

            //把前缀和为preSum的次数加一，因为nums是随机数组，-1000 <= nums[i] <= 1000，所以后面可能会出现相同的preSum
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }

        return ans;

    }
}