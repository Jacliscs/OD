package Code200;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 岛屿数量
 * @date 2024/4/7
 * @level 中等
 * @score
 * @url https://leetcode.cn/problems/number-of-islands/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                //如果是陆地，则从该点往周围扩散，把相连的1全改为0，岛屿数+1
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 从(i,j)位置往周围搜索，把1变为0，防止重复搜索
     *
     * @param grid
     * @param i
     * @param j
     * @return void
     */
    public static void dfs(char[][] grid, int i, int j) {
        //如果越界或不为1，则返回
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1') return;

        //否则将该位置变为0
        grid[i][j] = '0';

        //遍历其他四个方向
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }

}
