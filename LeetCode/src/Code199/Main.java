package Code199;

import java.util.*;

/**
 * @author 浮生
 * @description 二叉树的右视图
 * @date 2024/4/30
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/binary-tree-right-side-view/?envType=study-plan-v2&envId=top-100-liked">url</a>
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
     * 右视图————每层层序遍历的最后一位
     *
     * @param root
     * @return java.util.List<java.lang.Integer>
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();

        if (root == null) return ans;

        //层序遍历 取每层的最后一个进ans
        Deque<TreeNode> deque = new ArrayDeque<>();

        //加入根节点
        deque.offer(root);

        while (!deque.isEmpty()) {
            //本层节点个数
            int count = deque.size();

            //依次取出，并把每个节点的左右子树添加
            for (int i = 0; i < count; i++) {
                TreeNode node = deque.poll();
                //添加子树
                if (node.left != null) deque.offer(node.left);
                if (node.right != null) deque.offer(node.right);

                //如果是最后一个，则添加到ans
                if (i == count - 1) ans.add(node.val);
            }
        }

        //返回结果
        return ans;
    }
}