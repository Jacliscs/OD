package Code108;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 将有序数组转换为二叉搜索树
 * @date 2024/4/29
 * @level 简单
 * @url <a href="https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/description/?envType=study-plan-v2&envId=top-100-liked">url</a>
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
     * 将有序数组转换为平衡二叉树
     *
     * @param nums
     * @return Code108.Solution.TreeNode
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        //递归 每次找数组中的中位数 然后中位数左边的递归为左子结点，右边的递归为右子结点
        int n = nums.length;

        if (n == 0) return null;

        //找中位数
        int index = n / 2;

        TreeNode root = new TreeNode(nums[index]);
        root.left = sortedArrayToBST(Arrays.copyOfRange(nums, 0, index));
        root.right = sortedArrayToBST(Arrays.copyOfRange(nums, index + 1, n));

        return root;
    }
}