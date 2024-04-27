package Code54;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 螺旋矩阵
 * @date 2024/4/27
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/spiral-matrix/description/?envType=study-plan-v2&envId=top-100-liked">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    /**
     * 请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素
     *
     * @param matrix
     * @return java.util.List<java.lang.Integer>
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();

        //1 <= m, n <= 10
        int m = matrix.length;
        int n = matrix[0].length;

        //总共有多少个数
        int total = m * n;

        //从(0,0)开始
        int x = 0;
        int y = 0;
        //标记matrix[i][j]是否访问过
        boolean[][] visited = new boolean[m][n];

        while (total > 0) {
            //先横着x不变 y++
            while (y < n && total > 0 && !visited[x][y]) {
                ans.add(matrix[x][y]);
                visited[x][y] = true;
                total--;
                y++;
            }
            //此时x=0，y=n，从最后一列往下遍历，起点更新x=1,y=n-1
            x++;
            y--;

            //向下
            while (x < m && total > 0 && !visited[x][y]) {
                ans.add(matrix[x][y]);
                visited[x][y] = true;
                total--;
                x++;
            }
            //此时x=m,y=n-1，从最后一行往左遍历，起点更新x=m-1,y=n-2
            x--;
            y--;

            //向左
            while (y >= 0 && total > 0 && !visited[x][y]) {
                ans.add(matrix[x][y]);
                visited[x][y] = true;
                total--;
                y--;
            }
            //此时x=m-1,y=-1，从最上一行往右遍历，起点更新x=m-2,y=0
            x--;
            y++;

            //向上
            while (x >= 0 && total > 0 && !visited[x][y]) {
                ans.add(matrix[x][y]);
                visited[x][y] = true;
                total--;
                x--;
            }
            //此时x=0,y=0，继续循环往右遍历,起点x=1,y=1
            x++;
            y++;
        }

        return ans;
    }
}