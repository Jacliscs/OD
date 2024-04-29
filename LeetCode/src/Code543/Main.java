package Code543;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 二叉树的直径
 * @date 2024/4/29
 * @level 简单
 * @url <a href="https://leetcode.cn/problems/diameter-of-binary-tree/description/?envType=study-plan-v2&envId=top-100-liked">url</a>
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

    //题解
    int ans = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        //树中节点数目在范围 [1, 104] 内
        //在求root的深度同时更新ans
        getDepth(root);

        return ans;
    }

    public int getDepth(TreeNode root) {
        //走到空节点了
        if (root == null) return 0;

        //左子树的深度
        int left = getDepth(root.left);
        //右子树的深度
        int right = getDepth(root.right);

        //更新最长路径 左子树深度+右子树深度
        ans = Math.max(ans, left + right);

        //自身的深度 = 左右子树中的最大深度 + 1
        return Math.max(left, right) + 1;
    }
}