package OD416;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 寻找最优的路测线路
 * @date 2024/4/5
 * @level 7
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD416
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {

    private static int r;
    private static int c;
    private static int[][] matrix;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //行 列
        r = sc.nextInt();
        c = sc.nextInt();

        matrix = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        //输出结果
        System.out.println(getResult());


    }

    public static int getResult() {
        //dist[i][j] 表示的是从起点(0,0)到点(i,j)在“最大的”最小权值节点
        int[][] dist = new int[r][c];

        //初始化起点
        dist[0][0] = matrix[0][0];

        //优先队列：记录终点坐标(i,j)，谁的最小权值节点的值越大，优先级越高
        PriorityQueue<Integer[]> pq = new PriorityQueue<>((a, b) -> dist[b[0]][b[1]] - dist[a[0]][a[1]]);

        //初始化，加入起点
        pq.add(new Integer[]{0, 0});

        //上下左右偏移量
        int[][] offsets = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        while (pq.size() > 0) {
            //取出队列中优先级最大的路径终点
            Integer[] pos = pq.poll();
            int x = pos[0];
            int y = pos[1];

            //如果已经是终点，则是最优解，提前结束循环
            if (x == r - 1 && y == c - 1) break;

            //向四个方向扩散
            for (int[] offset : offsets) {
                int newX = x + offset[0];
                int newY = y + offset[1];

                //位置越界
                if (newX < 0 || newX >= r || newY < 0 || newY >= c) continue;

                //当前路径终点是(newX,newY)时，更新最小权值 上一个节点是(x,y) 取较小值
                int newDist = Math.min(dist[x][y], matrix[newX][newY]);

                //dist[newX][newY]记录的是不同路径到终点(newX,newY)的最小权值中的最大值，比较newDist与原来的，保留较大值
                if (dist[newX][newY] < newDist) {
                    dist[newX][newY] = newDist;
                    //添加如优先队列，参加下一轮比较
                    pq.add(new Integer[]{newX, newY});
                }
            }
        }

        return dist[r - 1][c - 1];
    }
}