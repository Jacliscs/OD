package OD438;

import org.w3c.dom.Node;

import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * @author Jacliscs
 * @description 生成哈夫曼树
 * @date 2024/3/23
 * @level 6
 * @score 100
 */

/**
 * 题目描述
 * 给定长度为 n 的无序的数字数组，每个数字代表二叉树的叶子节点的权值，数字数组的值均大于等于1。
 * <p>
 * 请完成一个函数，根据输入的数字数组，生成哈夫曼树，并将哈夫曼树按照中序遍历输出。
 * <p>
 * 为了保证输出的二叉树中序遍历结果统一，增加以下限制：
 * <p>
 * 二叉树节点中，左节点权值小于右节点权值，根节点权值为左右节点权值之和。当左右节点权值相同时，左子树高度小于等于右子树高度。
 * <p>
 * 注意：
 * <p>
 * 所有用例保证有效，并能生成哈夫曼树。
 * <p>
 * 提醒：
 * <p>
 * 哈夫曼树又称为最优二叉树，是一种带权路径长度最短的二叉树。
 * <p>
 * 所谓树的带权路径长度，就是树中所有的叶节点的权值乘上其到根节点的路径长度（若根节点为 0 层，叶节点到根节点的路径长度为叶节点的层数）
 * <p>
 * 输入描述
 * 例如：由叶子节点：5 15 40 30 10，生成的最优二叉树如下图所示，该树的最短带权路径长度为：40 * 1 + 30 * 2 + 5 * 4 + 10 * 4 = 205。
 * <p>
 * image
 * <p>
 * 输出描述
 * 输出一个哈夫曼树的中序遍历数组，数值间以空格分隔
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //节点数
        int n = sc.nextInt();
        //优先队列，自定义优先级：权重不同时，权重升序；权重相同优先节点高度，高度升序
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.weight != b.weight ?
                a.weight - b.weight : a.height - b.height);

        //创建n个哈夫曼节点
        for (int i = 0; i < n; i++) {
            int weight = sc.nextInt();
            Node node = new Node(null, null, weight, 0);
            //加入优先队列
            pq.offer(node);
        }
        //从下往上合并节点，当只有一个节点时，是根节点
        while (pq.size() > 1) {
            //先取出的是权重小的，或者高度小的 poll()访问并移出首元素
            Node l_child = pq.poll();
            Node r_child = pq.poll();
            //合并两个节点 父节点权重是两个子节点权重和
            int fa_weight = l_child.weight + r_child.weight;
            //父节点子树的高度 比子节点最大高度+1
            int fa_height = Math.max(l_child.height, r_child.height) + 1;

            //合并的父节点加入优先队列
            Node fa = new Node(l_child, r_child, fa_weight, fa_height);
            pq.offer(fa);
            //每次取两个最小权重的 合并成一个大的 +2 -1 队列每次减一
        }
        //最后剩的节点，就是最上面的根节点
        Node root = pq.poll();
        StringJoiner sj = new StringJoiner(" ");
        //中序遍历： 先遍历左子树，再遍历右子树
        midOrder(root, sj);
        System.out.println(sj);
    }

    public static void midOrder(Node root, StringJoiner sj) {
        //如果左子树存在，则遍历左子树
        if (root.l_child != null) {
            midOrder(root.l_child, sj);
        }

        //当左子树不存在时，记录节点权重
        sj.add(root.weight + "");

        //右节点
        if (root.r_child != null) {
            midOrder(root.r_child, sj);
        }


    }


    //定义哈夫曼树节点
    static class Node {
        Node l_child;//左子节点
        Node r_child;//右子节点
        int weight;//当前节点权重
        int height;//当前节点代表子树的高度

        public Node(Node l_child, Node r_child, int weight, int height) {
            this.l_child = l_child;
            this.r_child = r_child;
            this.weight = weight;
            this.height = height;
        }
    }
}