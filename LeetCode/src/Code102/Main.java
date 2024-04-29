package Code102;

import javax.swing.tree.TreeNode;
import java.util.*;

/**
 * @author 浮生
 * @description 二叉树的层序遍历
 * @date 2024/4/9
 * @level 中等
 * @score
 * @url https://leetcode.cn/problems/binary-tree-level-order-traversal/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {

        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 返回二叉树的层序遍历
     *
     * @param root
     * @return java.util.List<java.util.List < java.lang.Integer>>
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        //队列：广度优先搜索，逐层搜索
        Queue<TreeNode> queue = new LinkedList<>();
        //把根节点添加
        queue.offer(root);
        while (!queue.isEmpty()) {
            //存放这一层的层序遍历
            List<Integer> level = new ArrayList<>();
            //存放下一层的节点
            Queue<TreeNode> nextLevel = new LinkedList<>();
            for (TreeNode node : queue) {
                level.add(node.val);
                //顺序添加：左、右子树
                if (node.left != null) nextLevel.offer(node.left);
                if (node.right != null) nextLevel.offer(node.right);
            }
            //把level层的层序遍历结果添加到ans中
            ans.add(level);
            //更新层
            queue = nextLevel;
        }
        return ans;
    }
}