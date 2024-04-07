package Code46;

import java.util.*;

/**
 * @author 浮生
 * @description 全排列
 * @date 2024/4/7
 * @level 中等
 * @score
 * @url https://leetcode.cn/problems/permutations/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public static void dfs(int[] nums, LinkedList<Integer> path, boolean[] used, List<List<Integer>> ans) {
        //排列完一个组合
        if (path.size() == nums.length) {
            //加入到ans
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;

            //没有被使用，则添加到路径
            path.addLast(nums[i]);
            used[i] = true;

            //递归
            dfs(nums, path, used, ans);

            //恢复状态
            path.removeLast();
            used[i] = false;
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        //nums不含重复数字
        //升序
        Arrays.sort(nums);

        //记录路径
        LinkedList<Integer> path = new LinkedList<>();

        //数字是否被用过
        boolean[] used = new boolean[nums.length];

        //记录结果
        List<List<Integer>> ans = new ArrayList<>();

        //dfs
        dfs(nums, path, used, ans);

        return ans;

    }

}