package Code113;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 路径总和II
 * @date 2024/4/20
 * @level 中等
 * @url https://leetcode.cn/problems/path-sum-ii/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}


class Solution {

    LinkedList<Integer> path = new LinkedList<>();
    LinkedList<List<Integer>> res = new LinkedList<List<Integer>>();

    public void dfs(TreeNode root, int targetSum) {
        //如果root为空，返回
        if (root == null) return;

        //不为空，则把当前节点加入路径
        path.addLast(root.val);
        //目标值
        targetSum -= root.val;

        //返回标志 是叶子节点且把目标值消为0
        if (root.left == null && root.right == null && targetSum == 0) {
            //把path里的加入到res
            res.add(new LinkedList<Integer>(path));
        }

        //递归左右子树
        dfs(root.left, targetSum);
        dfs(root.right, targetSum);

        //回溯
        path.removeLast();
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum);

        return res;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}