package Code104;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 二叉树的最大深度
 * @date 2024/4/29
 * @level 简单
 * @url <a href="https://leetcode.cn/problems/maximum-depth-of-binary-tree/description/?envType=study-plan-v2&envId=top-100-liked">url</a>
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

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        //法1：dfs 深度优先遍历
        //递归，不断询问下一层，每层高度都是他左右子树最大高度+1
        //return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;

        //法2：bfs 广度优先遍历
        Deque<TreeNode> queue = new ArrayDeque<>();
        int height = 0;
        //把根节点添加
        queue.offer(root);
        while (!queue.isEmpty()) {
            //把这一层的所有节点的子节点存放，用于下一次遍历
            Deque<TreeNode> tmp = new ArrayDeque<>();
            //取出所以
            for (TreeNode node : queue) {
                if (node.left != null) tmp.offer(node.left);
                if (node.right != null) tmp.offer(node.right);
            }
            //更新queue，高度+1
            queue = tmp;
            height++;
        }
        return height;
    }
}