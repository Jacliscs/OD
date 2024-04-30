package Code98;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 验证二叉搜索树
 * @date 2024/4/29
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/validate-binary-search-tree/description/?envType=study-plan-v2&envId=top-100-liked">url</a>
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
     * 验证是否是一个有效的二叉搜索树。
     *
     * @param root
     * @return boolean
     */
    public boolean isValidBST(TreeNode root) {
        //递归判断
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);

    }

    /**
     * 判断root节点的val是否大于min小于max
     *
     * @param root
     * @param min
     * @param max
     * @return boolean
     */
    public boolean isValidBST(TreeNode root, long min, long max) {
        if (root == null) return true;

        if (root.val <= min || root.val >= max) return false;

        //递归：root的左子节点必须比自己小，右节点必须比自己大
        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }

}