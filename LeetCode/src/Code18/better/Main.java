package Code18.better;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 四数之和
 * @date 2024/4/24
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/4sum/solutions/44281/ji-bai-9994de-yong-hu-you-dai-ma-you-zhu-shi-by-yo/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        //题解:-10^9 <= nums[i] <= 10^9
        List<List<Integer>> res = new ArrayList<>();

        //如果为空或不足4位
        if (nums == null || nums.length < 4) return res;

        //排序
        Arrays.sort(nums);

        //数组长度
        int len = nums.length;

        //确定两个数，然后用双指针确定剩下俩，参考三数之和
        for (int first = 0; first < len - 3; first++) {
            //去重
            if (first > 0 && nums[first] == nums[first - 1]) continue;

            //如果当前所有组合的最小值都大于target，则后续不可能=target
            if ((long) nums[first] + nums[first + 1] + nums[first + 2] + nums[first + 3] > target) break;

            //如果当前组合的最大值小于target，则说明first不能作为开头
            if ((long) nums[first] + nums[len - 1] + nums[len - 2] + nums[len - 3] < target) continue;

            //第二个数
            for (int second = first + 1; second < len - 2; second++) {
                //去重
                if (second > first + 1 && nums[second] == nums[second - 1]) continue;

                //如果当前所有组合的最小值都大于target，则后续不可能=target
                if ((long) nums[first] + nums[second] + nums[second + 1] + nums[second + 2] > target) break;

                //如果当前组合的最大值小于target，则说明second不能作为第二个数
                if ((long) nums[first] + nums[second] + nums[len - 1] + nums[len - 2] < target) continue;

                //第三个数 第四个数
                int third = second + 1;
                int fourth = len - 1;

                //双指针
                while (third < fourth) {
                    long sum = (long) nums[first] + nums[second] + nums[third] + nums[fourth];

                    //如果满足条件
                    if (sum == target) {
                        //加入列表
                        res.add(Arrays.asList(nums[first], nums[second], nums[third], nums[fourth]));

                        //去重
                        while (third < fourth && nums[third] == nums[third + 1]) third++;
                        while (third < fourth && nums[fourth] == nums[fourth - 1]) fourth--;

                        third++;
                        fourth--;
                    } else if (sum < target) {
                        third++;
                    } else {
                        fourth--;
                    }
                }
            }
        }
        return res;
    }
}