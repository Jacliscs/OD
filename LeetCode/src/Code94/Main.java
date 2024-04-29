package Code94;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 二叉树的中序遍历
 * @date 2024/4/29
 * @level 简单
 * @url <a href="https://leetcode.cn/problems/binary-tree-inorder-traversal/?envType=study-plan-v2&envId=top-100-liked">url</a>
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

    public List<Integer> inorderTraversal(TreeNode root) {
        //题解
        List<Integer> list = new ArrayList<>();

        inorder(root, list);

        return list;

    }

    /**
     * 中序遍历，添加进list
     *
     * @param root
     * @param list
     * @return void
     */
    public static void inorder(TreeNode root, List<Integer> list) {
        //返回条件，递归到null则返回
        if (root == null) return;

        //先遍历左子树
        if (root.left != null) {
            inorder(root.left, list);
        }

        //添加根
        list.add(root.val);

        //遍历右子树
        if (root.right != null) {
            inorder(root.right, list);
        }
    }
}