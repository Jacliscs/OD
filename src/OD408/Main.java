package OD408;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 跳马
 * @date 2024/3/28
 * @level 7
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD408
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    //棋盘行数
    private static int m;
    //列数
    private static int n;
    //棋盘
    private static char[][] chess;
    //最小步数和 记录所有马到达chess[i][j]的最小步数和
    private static int[][] stepChess;
    //记录所有马可以到达的公共位置坐标
    //private static HashSet<int[]> reach; //二维坐标
    private static HashSet<Integer> reach;//二维转一维

    //马走日的偏移量
    static int[][] offsets = {{1, 2}, {1, -2}, {2, 1}, {2, -1}, {-1, 2}, {-1, -2}, {-2, 1}, {-2, -1}};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //行
        m = sc.nextInt();
        //列
        n = sc.nextInt();
        //棋盘
        chess = new char[m][n];
        //最小步数和
        stepChess = new int[m][n];
        //能到达的公共位置坐标
        reach = new HashSet<>();

        //初始化棋盘
        for (int i = 0; i < m; i++) {
            chess[i] = sc.next().toCharArray();
            //初始时 假设所有位置都是所有马可到达的 后面取交集
            for (int j = 0; j < n; j++) {
                //reach.add(new int[]{i,j});//二维
                reach.add(i * n + j);//一维
            }
        }
        //输出所有马可到达共同点的最小步数和
        System.out.println(getResult());

    }

    public static int getResult() {
        //遍历棋盘
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //如果是马，则bfs
                if (chess[i][j] != '.') {
                    //马能跳的步数 char -> int
                    int k = chess[i][j] - '0';
                    bfs(i, j, k);
                }
            }
        }

        //如果所有马走完，没有公共可达位置
        if (reach.isEmpty()) {
            return -1;
        }

        //记录所有马都可以到达的位置的最小步数和
        int minStep = Integer.MAX_VALUE;
        //二维
        //for (int[] pos : reach){
        //    int x =  pos[0];
        //    int y =  pos[1];
        //    minStep = Math.min(minStep,stepChess[x][y]);
        //}
        //一维
        for (int pos : reach) {
            //x,y是所有马都可到达的位置
            int x = pos / n;
            int y = pos % n;
            minStep = Math.min(minStep, stepChess[x][y]);
        }
        return minStep;
    }


    public static void bfs(int x, int y, int k) {
        //记录马到达位置x,y需要的步数
        LinkedList<int[]> queue = new LinkedList<>();

        //x,y是初始位置，需要0步
        queue.add(new int[]{x, y, 0});

        //记录该马可以到达的位置
        //HashSet<int[]> visit =  new HashSet<>();//二维
        //visit.add(new int[]{x,y});

        HashSet<Integer> visit = new HashSet<>();//一维
        visit.add(x * n + y);

        //k为该马剩余可走的步数
        while (queue.size() > 0 && k > 0) {
            //记录这一步内 马能到达的新位置
            LinkedList<int[]> newQueue = new LinkedList<>();

            //遍历每一层 第一层是从初始位置 第二层是从初始位置跳了一步之后的所有位置
            for (int[] temp : queue) {
                int preX = temp[0];
                int preY = temp[1];
                int step = temp[2];
                //遍历从该位置能走的点
                for (int[] offset : offsets) {
                    int newX = preX + offset[0];
                    int newY = preY + offset[1];

                    //int[] pos =  new int[]{newX,newY};
                    int pos = newX * n + newY;
                    //如果下标越界，或者已经访问过，则跳过
                    if (newX < 0 || newX >= m || newY < 0 || newY >= n || visit.contains(pos)) continue;

                    //将新位置插入到newQueue
                    newQueue.add(new int[]{newX, newY, step + 1});

                    //该马到达该位置的最小步数为step+1，首次到达即最小才会记录，已经被访问过则不是最小
                    stepChess[newX][newY] += step + 1;

                    //记录该马已经访问过
                    visit.add(pos);
                }
            }
            //刷新队列queue
            queue = newQueue;
            //剩余步数-1
            k--;
        }
        //将该马能到达的所有位置跟公共可到达位置取交集，初始默认公共可到达所有位置 后续每只马都跟前一只马取交集
        //数组是引用结构 reach中的[1,2]和visit中的[1,2]不相等
        //要么转一维数组 要么自定义方法
        reach.retainAll(visit);
    }

    public static HashSet<int[]> retain(HashSet<int[]> reach, HashSet<int[]> visit) {
        HashSet<int[]> result = new HashSet<>();
        for (int[] tmp : reach) {
            if (visit.contains(tmp)) {
                result.add(tmp);
            }
        }
        return result;
    }

}