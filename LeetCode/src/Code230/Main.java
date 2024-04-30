package Code230;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 二叉搜索树中第k小的元素
 * @date 2024/4/30
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/kth-smallest-element-in-a-bst/description/?envType=study-plan-v2&envId=top-100-liked">url</a>
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
     * 求二叉搜索树的第k小的元素
     *
     * @param root
     * @param k
     * @return int
     */
    public int kthSmallest(TreeNode root, int k) {
        //二叉搜索树：左<根<右 相当于求中序遍历的第k个元素
        List<Integer> list = new ArrayList<>();

        inorder(root, list);

        return list.get(k - 1);
    }

    /**
     * 把root的中序遍历添加进list
     *
     * @param root
     * @param list
     * @return void
     */
    public static void inorder(TreeNode root, List<Integer> list) {
        //先求左
        if (root.left != null) {
            inorder(root.left, list);
        }

        //添加根
        list.add(root.val);

        //再求右
        if (root.right != null) {
            inorder(root.right, list);
        }
    }

}