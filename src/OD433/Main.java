package OD433;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 快递员的烦恼
 * @date 2024/4/1
 * @level 8
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD433
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {

    private static int n;
    private static int m;
    private static int[][] dist;
    private static int[][] path;
    private static int ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //已知n个客户到快递站的距离
        n = sc.nextInt();
        //已知m对客户之间的距离
        m = sc.nextInt();

        //dist[i][j] 表示点i -> j的最短距离
        dist = new int[n + 1][n + 1];
        //path[i][j] 表示i -> j之间需要经过的中转点，默认无中转点 -1
        path = new int[n + 1][n + 1];

        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                //默认客户直接不相连，则两点之间距离为无穷大
                if (i != j) {
                    dist[i][j] = Integer.MAX_VALUE;
                }
                //默认 i->j 没有中转点
                path[i][j] = -1;
            }
        }

        //因为客户id不是连续的，离散化处理
        HashMap<Integer, Integer> map = new HashMap<>();

        //输入n条客户到投递站的距离
        for (int i = 1; i <= n; i++) {
            int id = sc.nextInt();
            int dis = sc.nextInt();

            //将id转为连续的数0 1 2 3
            map.put(id, i);

            //初始化投递站到客户的距离
            dist[0][i] = dis;
            dist[i][0] = dis;
        }

        //输入m对 客户之间的距离
        for (int i = 1; i <= m; i++) {
            int id1 = sc.nextInt();
            int id2 = sc.nextInt();
            int dis = sc.nextInt();

            //id转为索引
            int index1 = map.get(id1);
            int index2 = map.get(id2);

            //更新客户之间的距离
            dist[index1][index2] = dis;
            dist[index2][index1] = dis;
        }

        //floyd算法：遍历更新dist，使两点之间的路径成为最短路径
        floyd();

        //记录题解，最后的最短路径
        ans = Integer.MAX_VALUE;

        //dfs求全排列，找出最小ans
        dfs(0, 0, new boolean[n + 1], 0);
        System.out.println(ans);

    }

    /**
     * 根据已有条件解出最短路径
     *
     * @param
     * @return void
     * @create 2024/4/1 20:07
     */
    public static void floyd() {
        //第一层是中转点
        for (int k = 0; k < n + 1; k++) {
            //第二层是起始点
            for (int i = 0; i < n + 1; i++) {
                //第三层是终点
                for (int j = 0; j < n + 1; j++) {
                    //比较原始i->j 的距离 和i->k->j的距离
                    int new_dist = dist[i][k] + dist[k][j];
                    if (new_dist < dist[i][j]) {
                        dist[i][j] = new_dist;
                        //经过点k
                        path[i][j] = k;
                    }
                }
            }
        }
    }

    /**
     * 找所有排列的路径长度，更新最短路径ans
     *
     * @param pre   上一个点 初始为0，即从快递站出发
     * @param sum   当前排列的路径和
     * @param used  标记点是否被使用过
     * @param level 排列的长度，需要从0排列到n
     * @return void
     * @create 2024/4/1 20:10
     */
    public static void dfs(int pre, int sum, boolean[] used, int level) {
        //返回标志，已经排列了n个
        if (level == n) {
            //sum是已经走过的路径和，最后还需要返回快递站
            ans = Math.min(ans, sum + dist[pre][0]);
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (used[i]) continue;

            used[i] = true;
            dfs(i, sum + dist[pre][i], used, level + 1);
            //恢复
            used[i] = false;
        }
    }


}