package Code226;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 翻转二叉树
 * @date 2024/4/29
 * @level 简单
 * @url <a href="https://leetcode.cn/problems/invert-binary-tree/description/?envType=study-plan-v2&envId=top-100-liked">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public class TreeNode {
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

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        //递归
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        //翻转后再交换左右子树
        root.left = right;
        root.right = left;

        return root;
    }
}