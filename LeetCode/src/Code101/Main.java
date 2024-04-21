package Code101;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 对称二叉树
 * @date 2024/4/21
 * @level 简单
 * @url <a href="https://leetcode.cn/problems/symmetric-tree/description/">url</a>
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
     * 返回二叉树是否对称
     *
     * @param root
     * @return boolean
     */
    public boolean isSymmetric(TreeNode root) {
        //如果是空，则是对称的
        if (root == null) return true;

        //否则，递归判断左右子树
        return dfs(root.left, root.right);

    }

    /**
     * 判断左右节点是否对称
     *
     * @param left
     * @param right
     * @return boolean
     */
    public static boolean dfs(TreeNode left, TreeNode right) {
        //返回条件：遍历到叶子结点
        //如果两个都为null，则对称
        if (left == null && right == null) return true;

        //如果只有一个是空，另一个不为空，则不对称
        if (left == null || right == null) return false;

        //现在比较左右节点的值是否相等
        if (left.val != right.val) return false;

        //递归比较左节点的左子树=右节点的右子树 和 左节点的右子树=右节点的左子树
        return dfs(left.left, right.right) && dfs(left.right, right.left);
    }

}