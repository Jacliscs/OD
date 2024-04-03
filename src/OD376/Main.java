package OD376;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 5G网络建设
 * @date 2024/4/3
 * @level 7
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD376
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {

    static class Edge {
        //边起点
        int from;
        //边终点
        int to;
        //边权重
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //基站数量 节点数 [1,n]
        int n = sc.nextInt();

        //基站连接数量
        int m = sc.nextInt();

        Edge[] edges = new Edge[m];
        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int weight = sc.nextInt();
            int flag = sc.nextInt();//1表示已经相连，边权重视为0

            edges[i] = new Edge(from, to, flag == 1 ? 0 : weight);
        }
        //输出结果
        System.out.println(kruskal(edges, n));
    }

    public static int kruskal(Edge[] edges, int n) {
        //最小权重和
        int minWeight = 0;

        //按照权重升序排列
        Arrays.sort(edges, (a, b) -> a.weight - b.weight);

        //并查集 序号从1开始 [1,n+1]
        UnionFindSet ufs = new UnionFindSet(n + 1);

        //最先遍历出来的是权重最小的
        for (Edge edge : edges) {
            //必须这条边的from和to节点不在当前最小生成树中，才能合并
            if (ufs.find(edge.from) != ufs.find(edge.to)) {
                minWeight += edge.weight;
                //合并
                ufs.union(edge.from, edge.to);

                //因为下标是从1开始，而0的根节点是0，最后会有两个最小生成树
                if (ufs.count == 2) {
                    return minWeight;
                }
            }
        }
        //如果最后不能找到除0之外的最小生成树，则返回-1
        return -1;
    }
}

//并查集实现
class UnionFindSet {
    int[] fa;
    //连通数量
    int count;

    public UnionFindSet(int n) {
        this.fa = new int[n];
        this.count = n;
        for (int i = 0; i < n; i++) {
            fa[i] = i;
        }
    }

    public int find(int x) {
        if (x != this.fa[x]) {
            this.fa[x] = this.find(this.fa[x]);
            return this.fa[x];
        }
        return x;
    }

    //合并
    public void union(int x, int y) {
        int x_fa = this.find(x);
        int y_fa = this.find(y);
        if (x_fa != y_fa) {
            this.fa[x_fa] = y_fa;
            this.count--;
        }
    }

}