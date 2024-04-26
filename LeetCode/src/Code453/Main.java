package Code453;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 最小操作次数使数组元素相等
 * @date 2024/4/26
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/minimum-moves-to-equal-array-elements/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    /**
     * 每次让n-1个元素+1，返回使所有元素相等的最小操作次数
     *
     * @param nums
     * @return int
     */
    public int minMoves(int[] nums) {
        //等价于每次让一个元素-1，使所有元素等于最小值的步数
        int ans = 0;
        //1 <= nums.length <= 10^5 不会出现空指针异常
        int minNum = Arrays.stream(nums).min().getAsInt();

        for (int num : nums) {
            ans += num - minNum;
        }

        return ans;
    }

}