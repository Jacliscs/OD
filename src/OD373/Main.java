package OD373;

import java.util.*;

/**
 * @author 浮生
 * @description 二叉树计算
 * @date 2024/4/4
 * @level 7
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD373
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {

    private static int[] midOrder;
    private static int[] preOrder;
    private static HashMap<Integer, ArrayList<Integer>> midIndexMap;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //中序遍历  左 根 右
        midOrder = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //前序遍历  根 左 右
        preOrder = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        //存放中序遍历元素对应的位置，可能存在多个
        midIndexMap = new HashMap<>();
        int n = midOrder.length;
        for (int i = 0; i < n; i++) {
            int num = midOrder[i];
            midIndexMap.putIfAbsent(num, new ArrayList<>());
            //添加元素num的位置i
            midIndexMap.get(num).add(i);
        }

        //根据中序、前序的还原求和树
        TreeNode root = buildTree(0, n - 1, 0, n - 1);

        //记录还原后的求和树
        StringJoiner sj = new StringJoiner(" ");
        getMidOrder(root, sj);
        System.out.println(sj);
    }

    /**
     * 中序遍历求和树，并添加到sj中
     *
     * @param root 根节点
     * @param sj   输出列表
     * @return void
     * @create 2024/4/4 16:09
     */
    public static void getMidOrder(TreeNode root, StringJoiner sj) {
        //左 根 右
        if (root == null) {
            return;
        }

        //先遍历左子树
        TreeNode leftChild = root.leftChild;
        if (leftChild != null) {
            getMidOrder(leftChild, sj);
        }

        //遍历根，直接添加
        sj.add(root.childSum + "");

        //遍历右子树
        TreeNode rightChild = root.rightChild;
        if (rightChild != null) {
            getMidOrder(rightChild, sj);
        }
    }


    /**
     * 根据前序遍历、中序遍历，还原树结构
     *
     * @param midL 中序遍历子序列的左边界
     * @param midR 中序遍历子序列的右边届
     * @param preL 前序遍历子序列的左边界
     * @param preR 前序遍历子序列的右边界
     * @return OD373.Main.TreeNode
     * @create 2024/4/4 16:12
     */
    public static TreeNode buildTree(int midL, int midR, int preL, int preR) {
        //子序列下标越界
        if (preL > preR) return null;

        //根据前序遍历找到当前子树的根 前序遍历的左边界就是根：根 左 右
        int rootNum = preOrder[preL];
        TreeNode root = new TreeNode(rootNum);

        //在中序列表中，找到当前根的位置，根左边是左子树，右边是右子树
        for (int idx : midIndexMap.get(rootNum)) {
            //可能有重复元素，多个位置只有一个是合法的

            //下标越界，非法
            if (idx < midL || idx > midR) continue;

            //如果中序遍历中当前根左边的左子树，和前序遍历中的左子树不同，则非法
            int leftLen = idx - midL;//左子树长度  左 根 右
            //preL是根，前序遍历左子树下标要+1
            if (notEquals(midL, preL + 1, leftLen)) continue;

            //如果中序遍历中的右子树跟前序遍历的右子树不同，则说明idx位置不正确
            int rightLen = midR - idx;
            if (notEquals(idx + 1, preR - rightLen + 1, rightLen)) continue;

            //位置正确，则分治递归处理左右子树
            root.leftChild = buildTree(midL, idx - 1, preL + 1, preL + leftLen);
            root.rightChild = buildTree(idx + 1, midR, preR - rightLen + 1, preR);

            //记录该节点的左右子树的和 递归
            root.childSum = (root.leftChild == null ? 0 : (root.leftChild.num + root.leftChild.childSum))
                    + (root.rightChild == null ? 0 : (root.rightChild.num + root.rightChild.childSum));
            break;
        }

        //返回根节点，已经是一个完整的树了
        return root;
    }


    /**
     * 判断中序遍历从位置[midL,midL+size]数组是否和前序遍历中[preL,size]数组元素相同，顺序可以不同
     *
     * @param midL
     * @param preL
     * @param size
     * @return boolean
     */
    public static boolean notEquals(int midL, int preL, int size) {
        int[] arr1 = Arrays.stream(Arrays.copyOfRange(midOrder, midL, midL + size)).sorted().toArray();
        int[] arr2 = Arrays.stream(Arrays.copyOfRange(preOrder, preL, preL + size)).sorted().toArray();

        //因为已经排序了，所以对应位置应该相同
        for (int i = 0; i < size; i++) {
            if (arr1[i] != arr2[i]) {
                return true;
            }
        }
        //否则，两个数组相同
        return false;
    }


    //树
    static class TreeNode {
        //当前节点值
        int num;
        //当前节点的左右子树和
        int childSum;
        TreeNode leftChild;
        TreeNode rightChild;

        public TreeNode(int num) {
            this.num = num;
            this.childSum = 0;
            this.leftChild = null;
            this.rightChild = null;
        }


    }
}