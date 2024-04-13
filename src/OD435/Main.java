package OD435;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 二叉树的广度优先遍历
 * @date 2024/4/1
 * @level 6
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD435
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //后序遍历：左 右 根
        String post = sc.next();
        //中序遍历：左 根 右
        String mid = sc.next();
        System.out.println(getResult(post, mid));

    }

    /**
     * 根据后序遍历和中序遍历，返回二叉树层序遍历的结果
     *
     * @param post
     * @param mid
     * @return java.lang.String
     * @create 2024/4/1 21:44
     */
    public static String getResult(String post, String mid) {
        //广度优先搜索队列：先加入左子树，再加入右子树
        LinkedList<String[]> queue = new LinkedList<>();
        //层序遍历的结果
        ArrayList<Character> ans = new ArrayList<>();
        //分离出根，以及对应的左右子树，每次把根添加到ans中
        devideLR(post, mid, queue, ans);

        while (queue.size() > 0) {
            String[] tmp = queue.removeFirst();
            //tmp[0]是左/右子树的后序遍历、tmp[1]是左/右子树的中序遍历  先左子树，后右子树，逐层把根添加到ans，就是层序遍历的结果
            devideLR(tmp[0], tmp[1], queue, ans);
        }
        StringBuilder sb = new StringBuilder();
        for (Character c : ans) {
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 从后序遍历和中序遍历中，找到根节点，以及对应根节点的左右子树
     *
     * @param post  后序遍历结果
     * @param mid   中序遍历结果
     * @param queue 当前执行队列 String[]先左子树、后右子树
     * @param ans   题解
     * @return void
     * @create 2024/4/1 21:52
     */
    public static void devideLR(String post, String mid, LinkedList<String[]> queue, ArrayList<Character> ans) {
        //后续遍历的最后一个元素是根
        char rootEle = post.charAt(post.length() - 1);
        //将根加入题解
        ans.add(rootEle);

        //中序遍历中找到根的位置：左 根 右
        //根的索引=左子树长度
        int rootIdx = mid.indexOf(rootEle);
        //左子树长度 根左边就是左子树的长度

        //如果存在左子树
        if (rootIdx > 0) {
            //从后续遍历中，截取出左子树的后序遍历
            String left_post = post.substring(0, rootIdx);
            //从中序遍历中，截取出左子树的中序遍历
            String left_mid = mid.substring(0, rootIdx);
            //将左子树的后序、中序遍历加入执行队列
            queue.add(new String[]{left_post, left_mid});
        }
        //如果存在右子树
        if (post.length() - 1 - rootIdx > 0) {
            //从后续遍历中，截取出右子树的后序遍历
            String right_post = post.substring(rootIdx, post.length() - 1);
            //从中序遍历中，截取出右子树的中序遍历
            String right_mid = mid.substring(rootIdx + 1);
            //将右子树的后序、中序遍历加入执行队列
            queue.add(new String[]{right_post, right_mid});
        }
    }


}