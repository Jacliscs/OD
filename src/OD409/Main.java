package OD409;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 路口最短时间问题
 * @date 2024/4/5
 * @level 7
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD409
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //n行 m列
        int n = sc.nextInt();
        int m = sc.nextInt();

        //信号灯矩阵
        int[][] lights = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                lights[i][j] = sc.nextInt();
            }
        }
        //每过一个路口需要的时间
        int timePerRoad = sc.nextInt();

        //开始的横纵坐标
        int rowStart = sc.nextInt();
        int colStart = sc.nextInt();

        //结束的横纵坐标
        int rowEnd = sc.nextInt();
        int colEnd = sc.nextInt();

        //输出从开始坐标到结束坐标的最短通行时间
        System.out.println(new Solution().calcTime(lights, timePerRoad, rowStart, colStart, rowEnd, colEnd));

    }

}

class Solution {
    //位移偏量
    static int[][] offsets = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    /**
     * 获取拐弯方向
     *
     * @param preX
     * @param preY
     * @param curX
     * @param curY
     * @param nextX
     * @param nextY
     * @return int
     */
    public static int getDirection(int preX, int preY, int curX, int curY, int nextX, int nextY) {
        //前一步的向量
        int dx1 = curX - preX;
        int dy1 = curY - preY;

        //后一步的向量
        int dx2 = nextX - curX;
        int dy2 = nextY - curY;

        //向量的叉积：axb
        // =0 -> 直行
        // >0 ->左拐
        // <0 ->右拐
        return dx1 * dy2 - dx2 * dy1;

    }

    //获取方向

    //核心代码模式
    int calcTime(int[][] lights, int timePerRoad, int rowStart, int colStart, int rowEnd, int colEnd) {
        //行数
        int n = lights.length;
        //列数
        int m = lights[0].length;

        //dist[i][j][k] 表示从k方向到达(i,j)位置的最短时间 k=[0-3].表示从上下左右方向
        int[][][] dist = new int[n][m][4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < 4; k++) {
                    //初始化为无穷大
                    dist[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        //优先队列：int[] = [前一个位置行号，前一个位置列号，当前位置行号，当前位置列号，到达当前位置的最短时间]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[4] - b[4]);
        //初始化出发点，四个方向到达出发点的时间都为0
        for (int k = 0; k < 4; k++) {
            dist[rowStart][colStart][k] = 0;
            //四个方向的起点都放入队列 出发点的前一个点坐标为(-1,-1)
            pq.add(new int[]{-1, -1, rowStart, colStart, 0});
        }

        //每次取出最短时间
        while (pq.size() > 0) {
            //最短时间
            int[] tmp = pq.poll();
            int preX = tmp[0];
            int preY = tmp[1];
            int curX = tmp[2];
            int curY = tmp[3];
            int cost = tmp[4];

            //向四个方向扩散
            for (int k = 0; k < 4; k++) {
                int newX = curX + offsets[k][0];
                int newY = curY + offsets[k][1];

                //位置越界，跳过
                if (newX < 0 || newX >= n || newY < 0 || newY >= m) continue;

                //不可掉头
                if (preX == newX && preY == newY) continue;

                //可以移动，每走一格耗费timePerRoad
                int newCost = cost + timePerRoad;

                //起点、右转不需要等待红绿灯，否则需要加上等待红绿灯的时间
                if (preX != -1 && preY != -1 && getDirection(preX, preY, curX, curY, newX, newY) >= 0) {
                    //加上信号灯时间
                    newCost += lights[curX][curY];
                }

                //更新dist[newX][newY][k] 取较小值，如果newCost更大，则不做操作
                if (newCost >= dist[newX][newY][k]) continue;

                //否则更新
                dist[newX][newY][k] = newCost;

                //将该路径加入探索队列
                pq.add(new int[]{curX, curY, newX, newY, newCost});
            }
        }
        //重点是rowEnd,colEnd 取四个方向的最小值
        int ans = Integer.MAX_VALUE;
        for (int k = 0; k < 4; k++) {
            ans = Math.min(ans, dist[rowEnd][colEnd][k]);
        }
        return ans;
    }
}
