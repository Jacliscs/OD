package OD432;

import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * @author 浮生
 * @description 查找一个有向网络的头结点和尾节点
 * @date 2024/4/1
 * @level 6
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD432
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {

    private static int n;
    private static HashMap<Integer, Integer> inDegree;
    private static HashMap<Integer, ArrayList<Integer>> next;
    private static HashSet<Integer> set;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //有几个键值对
        n = sc.nextInt();

        //存放节点的入度，当入度为0时，需要剥离
        inDegree = new HashMap<>();

        //每个节点的后继节点
        next = new HashMap<>();

        //所有节点的集合 set不会重复添加
        set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            //前驱节点
            int pre_node = sc.nextInt();
            //后继节点
            int next_node = sc.nextInt();

            //添加节点
            set.add(pre_node);
            set.add(next_node);

            //后驱节点的入度+1
            inDegree.put(next_node, inDegree.getOrDefault(next_node, 0) + 1);

            //把后继节点加入前驱结点的后继集合
            next.putIfAbsent(pre_node, new ArrayList<>());
            next.get(pre_node).add(next_node);
        }
        //打印结果
        System.out.println(getResult());
    }


    /**
     * 返回头结点和尾节点，如果是环则返回-1
     *
     * @param
     * @return java.lang.String
     * @create 2024/4/1 19:28
     */
    public static String getResult() {
        //总共有几个节点
        int total = set.size();

        //记录头结点
        int head = 0;

        //记录入度为0的节点
        LinkedList<Integer> queue = new LinkedList<>();
        //遍历所有节点
        for (int p : set) {
            //头结点没有入度，不在inDegree中
            if (!inDegree.containsKey(p)) {
                //找到了头结点
                head = p;
                queue.add(p);
                //只有一个头结点，找到就退出遍历
                break;
            }
        }

        //记录所有尾节点
        ArrayList<Integer> tails = new ArrayList<>();

        //记录已被剥夺的节点个数，如果存在环，则count<total
        int count = 0;

        //count记录已被剥离的节点个数
        while (queue.size() > 0) {
            //剥离入度为0的点
            int fa = queue.removeFirst();
            //个数加1
            count++;


            //如果fa没有后继节点，则是尾结点
            if (!next.containsKey(fa)) {
                tails.add(fa);
                continue;
            }

            //有后继节点，则后继节点入度-1
            for (int ch : next.get(fa)) {
                inDegree.put(ch, inDegree.get(ch) - 1);
                //如果减1之后，入度为0，则加入队列，继续剥离
                if (inDegree.get(ch) == 0) {
                    queue.add(ch);
                }
            }
        }
        StringJoiner sj = new StringJoiner(" ");
        //检测是否有环 组成环的节点不存在入度为0，剥离不出来
        if (count != total) {
            sj.add("-1");
        } else {
            sj.add(head + "");
            //尾节点升序排列加入结果
            tails.stream().sorted((a, b) -> a - b).forEach(p -> sj.add(p + ""));
        }
        return sj.toString();
    }
}