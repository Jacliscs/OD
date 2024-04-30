package Code114;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 二叉树展开为链表
 * @date 2024/4/30
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/description/?envType=study-plan-v2&envId=top-100-liked">url</a>
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

    /**
     * 二叉树展开为链表——先序遍历
     *
     * @param root
     * @return void
     */
    public void flatten(TreeNode root) {
        while (root != null) {
            //没有左子树，则考虑下一个节点
            if (root.left == null) {
                root = root.right;
            } else {
                //先找左子树的左右边的节点
                TreeNode pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                //此时pre就是root的左子树中的最右边的节点
                //把root.right接到pre的右子树
                pre.right = root.right;

                //root的左子树插入到右子树，左子树变为null
                root.right = root.left;
                root.left = null;

                //继续下一个节点
                root = root.right;

            }
        }
    }
}