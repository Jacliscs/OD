package Code542;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 01矩阵
 * @date 2024/4/20
 * @level 中等
 * @score
 * @url https://leetcode.cn/problems/01-matrix/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    /**
     * 给定一个由 0 和 1 组成的矩阵 mat ，请输出一个大小相同的矩阵，其中每一个格子是 mat 中对应位置元素到最近的 0 的距离。
     *
     * @param mat
     * @return int[][]
     */
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        //题解
        int[][] dist = new int[m][n];

        //广度优先搜索bfs:每一个0往四周扩散，每扩散一层，步数+1
        Queue<int[]> queue = new LinkedList<>();
        //标记是否被探索过
        boolean[][] used = new boolean[m][n];

        //把所有的0添加进队列
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    queue.add(new int[]{i, j});
                    used[i][j] = true;
                }
            }
        }

        int[][] offsets = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while (!queue.isEmpty()) {
            //取出当前需要探索的位置
            int[] pos = queue.poll();
            int x = pos[0];
            int y = pos[1];

            //探索四个方向
            for (int[] offset : offsets) {
                int newX = x + offset[0];
                int newY = y + offset[1];

                //如果越界或已经访问过，跳过
                if (newX < 0 || newX >= m || newY < 0 || newY >= n || used[newX][newY]) continue;

                //合法位置，且一定是1，因为0的位置used都为true
                //更新dist
                dist[newX][newY] = dist[x][y] + 1;
                //把新位置加入队列
                queue.add(new int[]{newX, newY});
                //标记为已访问
                used[newX][newY] = true;
            }
        }

        return dist;

    }
}