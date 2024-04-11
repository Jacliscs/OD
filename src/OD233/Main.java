package OD233;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 可以组成网络的服务器
 * @date 2024/4/3
 * @level 6
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD233
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {

    static int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static int n;
    private static int m;
    private static int[][] matrix;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //n行 m列
        n = sc.nextInt();
        m = sc.nextInt();

        //服务器矩阵
        matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        //输出最大局域网的服务器个数
        System.out.println(getResult());
    }

    public static int getResult() {
        //记录题解，不断更新较大值
        int ans = 0;
        //遍历，从每一个起点扩散
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //如果是1，则进行bfs递归，找到该点最多能扩散的局域网服务器个数
                if (matrix[i][j] == 1) {
                    ans = Math.max(ans, bfs(i, j));
                }
            }
        }

        return ans;
    }


    /**
     * 从matrix[i][j]为中心，扩散寻找最多的局域网服务器
     * 类似LeetCode200题，岛屿数量 ： https://leetcode.cn/problems/number-of-islands/description/
     *
     * @param i
     * @param j
     * @return int
     * @create 2024/4/3 17:13
     */
    public static int bfs(int i, int j) {
        //储存找到服务器的坐标
        LinkedList<int[]> path = new LinkedList<>();
        //初始matrix[i][j]=1，所以初始count=1
        matrix[i][j] = 0;
        int count = 1;

        //添加起点
        path.add(new int[]{i, j});

        while (path.size() > 0) {
            int[] pos = path.removeFirst();
            int x = pos[0];
            int y = pos[1];

            //向四周位移扩散
            for (int[] offset : offsets) {
                int newX = x + offset[0];
                int newY = y + offset[1];
                //如果没有越界且是服务器
                if (newX >= 0 && newX < n && newY >= 0 && newY < m && matrix[newX][newY] == 1) {
                    //找到的服务器+1
                    count++;
                    //添加到坐标集合
                    path.add(new int[]{newX, newY});
                    //把该点设为1，防止回头
                    matrix[newX][newY] = 0;
                }
            }
        }
        //返回最大局域网服务器个数
        return count;
    }


}