package OD364;

import java.util.*;

/**
 * @author 浮生
 * @description 电脑病毒感染
 * @date 2024/4/3
 * @level 6
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD364
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //不是并查集！
        //有n台电脑
        int n = sc.nextInt();

        //电脑i，电脑i能到达的下一台电脑以及耗时
        HashMap<Integer, ArrayList<int[]>> graph = new HashMap<>();

        //m条相连信息
        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int time = sc.nextInt();

            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(new int[]{to, time});
        }

        //源点
        int src = sc.nextInt();

        //记录源点感染到其他电脑的最短耗时 下标从1开始
        int[] dist = new int[n + 1];

        //初始时，源点默认与其他点不相连，耗时为无穷大
        Arrays.fill(dist, Integer.MAX_VALUE);
        //源点到自己的耗时为0
        dist[src] = 0;

        //优先队列，已经被探索到的点中耗时越小的越优先
        PriorityQueue<Integer> needCheck = new PriorityQueue<>((a, b) -> dist[a] - dist[b]);
        //记录点是否被探索过
        boolean[] visited = new boolean[n + 1];

        //初始时探索的点只有源点
        needCheck.add(src);
        visited[src] = true;

        while (needCheck.size() > 0) {
            //取出当前探索的点需要耗时最少的
            int cur = needCheck.poll();
            visited[cur] = false;

            //如果cur有能到达的其他点
            if (graph.containsKey(cur)) {
                //遍历cur能到达的所有其他点
                for (int[] next : graph.get(cur)) {
                    //可到达的点
                    int v = next[0];
                    //花费时间
                    int t = next[1];

                    //从源点到v的时间是dist[v] 通过cur到达v的时间是dist[cur]+t
                    //第一次遍历时则是newDist = dist[src] + t(与源点直接相连花费的时间)
                    int newDist = dist[cur] + t;
                    if (newDist < dist[v]) {
                        dist[v] = newDist;
                        //如果v不在已探索的路径中，则添加
                        if (!visited[v]) {
                            visited[v] = true;
                            needCheck.add(v);
                        }
                    }
                }
            }
        }

        //dist[]是源点到其他各点的最短耗时，时间是共用的，最大值就是感染到所有电脑花费的时间
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, dist[i]);
        }

        //如果有无法感染的电脑，则源点到该电脑的dist是无穷大
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }
}