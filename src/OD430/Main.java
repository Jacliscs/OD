package OD430;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 智能驾驶
 * @date 2024/3/31
 * @level 8
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD430
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {

    private static int m;
    private static int n;
    private static int[][] matrix;
    //位置偏移量
    static int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    //自定义类 记录路径中位置的状态
    static class Node {
        int x;
        int y;
        int init;//到达该位置所需最少的初始油量
        int remain;//该位置还剩余的油量
        boolean flag;//到达该位置前是否有加过油

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //m行n列
        int[] temp = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        m = temp[0];
        n = temp[1];

        //每一个消耗的油 0障碍物 -1加油站
        matrix = new int[m][n];

        for (int i = 0; i < m; i++) {
            matrix[i] = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        }

        //输出从左上角到右下角需要的最少油量
        System.out.println(bfs());
    }

    public static int bfs() {
        //如果起点或终点不可达
        if (matrix[0][0] == 0 || matrix[m - 1][n - 1] == 0) {
            return -1;
        }

        //广搜队列
        LinkedList<Node> queue = new LinkedList<>();

        //起始位置
        Node start = new Node(0, 0);

        if (matrix[0][0] == -1) {
            //如果起点就是加油站
            start.init = 0;//到达该点不需要初始油量
            start.remain = 100;//该点后续还剩100油量
            start.flag = true;//发生过加油
        } else {
            //不是起点位置，那么起点的初始油量至少为matrix[0][0]
            start.init = matrix[0][0];
            start.remain = 0;
            start.flag = false;
        }

        //添加到队列
        queue.add(start);

        //dist_init[x][y] 用于记录起点 (0, 0) 到达 (x, y) 的所有可达路径中最优路径（即初始油量需求最少的路径）的初始油量
        int[][] dist_init = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dist_init[i][j] = Integer.MAX_VALUE;
            }
        }

        //dist_remain记录从(0,0)点到(x,y)点时剩的油量
        int[][] dist_remain = new int[m][n];
        dist_init[0][0] = start.init;
        dist_remain[0][0] = start.remain;


        //广搜bfs
        while (queue.size() > 0) {
            Node cur = queue.pollFirst();

            //从当前位置选择四个位置
            for (int[] offset : offsets) {
                int newX = cur.x + offset[0];
                int newY = cur.y + offset[1];

                //下标越界或为障碍物 因为可以回开，所以不需要boolean[][] used
                if (newX < 0 || newX >= m || newY < 0 || newY >= n || matrix[newX][newY] == 0) continue;

                //新位置可以到达，则计算当前位置的最少初始油量、当前剩余油量和是否加过油
                int init = cur.init;
                int remain = cur.remain;
                boolean flag = cur.flag;

                //如果新位置是加油站
                if (matrix[newX][newY] == -1) {
                    remain = 100;
                    flag = true;
                } else {
                    //减去该位置所耗费的油
                    remain -= matrix[newX][newY];
                }

                //如果到达新位置后，剩余油量小于0，则考虑是否在起点加油
                if (remain < 0) {
                    //如果已经加过油了都还不行，则不能通过在初始状态添的油用在这一步
                    if (flag) continue;
                    else {
                        //如果没加过油，则可以把差值添加到第一步
                        init -= remain;//remain 此时是负数
                        remain = 0;
                    }
                }

                //如果到达下一个位置后，添加完初始油量，造成init大于100，则不满足条件
                if (init > 100) continue;

                //如果到达新位置，则看是否是更小的初始油量，如果不是，则说明不是最优方案
                if (init > dist_init[newX][newY]) continue;

                //前面的都通过了，则判断当前路径到达newX,newY点初始油量更少，或者前面路径一样但后面剩的油更多
                if (init < dist_init[newX][newY] || (init == dist_init[newX][newY] && remain > dist_remain[newX][newY])) {
                    //当前路径策略更新，选初始油量更低或剩余油量更多的
                    dist_init[newX][newY] = init;
                    dist_remain[newX][newY] = remain;

                    //将当前新位置插入bfs
                    Node next = new Node(newX, newY);
                    next.init = init;
                    next.remain = remain;
                    next.flag = flag;

                    //加入队列
                    queue.add(next);
                }
            }
        }

        //如果到达右下角的值仍未MAX，则说明中间有障碍物，走不通，返回-1
        return dist_init[m - 1][n - 1] == Integer.MAX_VALUE ? -1 : dist_init[m - 1][n - 1];
    }
}