package Code78;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 子集
 * @date 2024/5/1
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/subsets/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    /**
     * 返回数组所有可能的子集
     *
     * @param nums
     * @return java.util.List<java.util.List < java.lang.Integer>>
     */
    public List<List<Integer>> subsets(int[] nums) {
        //1 <= nums.length <= 10
        //题解
        List<List<Integer>> ans = new ArrayList<>();

        //先添加空集
        ans.add(new ArrayList<>());

        //每次递归到一个新数字，遍历当前ans中的每一个集合，加上新数字就是一个新的子集
        for (Integer num : nums) {
            //当前ans的大小
            int size = ans.size();
            for (int i = 0; i < size; i++) {
                List<Integer> newSub = new ArrayList<>(ans.get(i));
                //把num加入到新集合，添加回ans
                newSub.add(num);
                ans.add(newSub);
            }
        }
        return ans;

    }
}