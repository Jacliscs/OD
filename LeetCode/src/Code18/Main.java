package Code18;

import java.util.*;

/**
 * @author 浮生
 * @description 四数之和
 * @date 2024/4/23
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/4sum/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int target = Integer.parseInt(sc.nextLine());
        Solution solution = new Solution();
        List<List<Integer>> res = solution.fourSum(nums, target);
        System.out.println(res);
    }
}

class Solution {
    /**
     * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。
     * 请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）
     *
     * @param nums
     * @param target
     * @return java.util.List<java.util.List < java.lang.Integer>>
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        //先升序排序
        Arrays.sort(nums);

        //存放结果
        List<List<Integer>> res = new ArrayList<>();

        //异常情况1 <= nums.length <= 200
        if (nums.length < 4) return res;

        dfs(nums, target, 0, new boolean[nums.length], new ArrayList<>(), res);
        //res会有重复：如[2,0,0,-2] [0,0,2,-2]算同一组
        //去重
        HashSet<List<Integer>> set = new HashSet<>();
        //排序后添加，相同组就不会加入到set
        for (List<Integer> list : res) {
            Collections.sort(list);
            set.add(list);
        }

        return new ArrayList<>(set);

    }

    /**
     * 返回所有四元组等于target的组合
     *
     * @param nums
     * @param target
     * @param used
     * @param list
     * @param res
     * @return void
     */
    public static void dfs(int[] nums, int target, int curSum, boolean[] used, List<Integer> list, List<List<Integer>> res) {
        //返回标志
        if (list.size() == 4 && curSum == target) {
            //加入结果
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            //如果数字已经使用过
            if (used[i]) continue;

            //树层去重后仍会有重复 如[2,0,0,-2] [0,0,2,-2]算同一组
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;

            //将该数字加入列表
            list.add(nums[i]);
            //将该数字标记为已使用
            used[i] = true;
            //递归
            dfs(nums, target, curSum + nums[i], used, list, res);
            //回溯
            used[i] = false;
            list.remove(list.size() - 1);
        }
    }

}