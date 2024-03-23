package OD360;

import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 计算三叉搜索树的高度
 * @date 2024/3/23
 * @level 4
 * @score 100
 */

/**
 * 题目描述
 * 定义构造三叉搜索树规则如下：
 * <p>
 * 每个节点都存有一个数，当插入一个新的数时，从根节点向下寻找，直到找到一个合适的空节点插入。查找的规则是：
 * <p>
 * 如果数小于节点的数减去500，则将数插入节点的左子树
 * 如果数大于节点的数加上500，则将数插入节点的右子树
 * 否则，将数插入节点的中子树
 * 给你一系列数，请按以上规则，按顺序将数插入树中，构建出一棵三叉搜索树，最后输出树的高度。
 * <p>
 * 输入描述
 * 第一行为一个数 N，表示有 N 个数，1 ≤ N ≤ 10000
 * <p>
 * 第二行为 N 个空格分隔的整数，每个数的范围为[1,10000]
 * <p>
 * 输出描述
 * 输出树的高度（根节点的高度为1）
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //可能有多行输入try-catch
        int n = sc.nextInt();
        Tree tree = new Tree();
        for (int i = 0; i < n; i++) {
            int val = sc.nextInt();
            tree.add(val);
        }
        System.out.println(tree.tree_height);
    }

    //定义树节点
    static class TreeNode {
        int val;//节点值
        int height;//节点高度
        TreeNode left;//左子树
        TreeNode mid;//中子树
        TreeNode right;//右子树

        //赋值构造方法
        public TreeNode(int val) {
            this.val = val;
        }
    }

    //定义三叉树 和添加节点的逻辑
    static class Tree {
        //根节点
        TreeNode root;
        //数的高度
        int tree_height;

        //add方法
        public void add(int val) {
            TreeNode temp = new TreeNode(val);
            //如果没有根节点
            if (this.root == null) {
                temp.height = 1;//根节点高度为1
                this.root = temp;//temp定为root节点
                this.tree_height = 1;//目前数的高度为1
            } else {
                //已存在根节点，则用temp从根节点去逐层比较
                TreeNode cur = this.root;
                while (true) {
                    //假设temp是当前cur节点的子节点
                    temp.height = cur.height + 1;
                    //更新树的高度
                    this.tree_height = Math.max(this.tree_height, temp.height);
                    //如果小于当前节点的数-500，则插入到cur的左子树
                    if (val < cur.val - 500) {
                        //如果没有左子树，则新建左子树，如果有，则更新cur=cur.left
                        if (cur.left == null) {
                            cur.left = temp;
                            //插入后就跳出
                            break;
                        } else {
                            //否则回到while继续搜索
                            cur = cur.left;
                        }
                    } else if (val > cur.val + 500) {
                        //插入右子树
                        if (cur.right == null) {
                            cur.right = temp;
                            break;
                        } else {
                            cur = cur.right;
                        }
                    } else {
                        //插入中子树
                        if (cur.mid == null) {
                            cur.mid = temp;
                            break;
                        } else {
                            cur = cur.mid;
                        }
                    }
                }
            }
        }
    }
}