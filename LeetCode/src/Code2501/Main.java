package Code2501;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 数组中最长的方波
 * @date 2024/5/1
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/longest-square-streak-in-an-array/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public int longestSquareStreak(int[] nums) {
        int n = nums.length;

        //求出nums所有子集
        List<List<Integer>> list = new ArrayList<>();
        //先添加空集
        list.add(new ArrayList<>());

        for (int num : nums) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                List<Integer> newSub = new ArrayList<>(list.get(i));
                newSub.add(num);
                list.add(newSub);
            }
        }

        //题解：最大方波长度
        int ans = -1;
        for (List<Integer> sub : list) {
            if (check(sub)) {
                ans = Math.max(ans, sub.size());
            }
        }
        return ans;
    }

    /**
     * 判断数组是否是方波
     *
     * @param list
     * @return boolean
     */
    public boolean check(List<Integer> list) {
        if (list.size() < 2) return false;

        //排序
        Collections.sort(list);

        //从第2个开始，每个数是前一个数的平方
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) != list.get(i - 1) * list.get(i - 1)) {
                return false;
            }
        }

        return true;
    }
}