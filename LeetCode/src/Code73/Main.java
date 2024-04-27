package Code73;

import java.util.*;

/**
 * @author 浮生
 * @description 矩阵置零
 * @date 2024/4/27
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/set-matrix-zeroes/description/?envType=study-plan-v2&envId=top-100-liked">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public void setZeroes(int[][] matrix) {
        //1 <= m, n <= 200
        int m = matrix.length;
        int n = matrix[0].length;

        //标记行、列是否存在0
        boolean[] rows = new boolean[m];
        boolean[] cols = new boolean[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = true;
                    cols[j] = true;
                }
            }
        }

        //遍历，如果(i,j)所在的行或列存在0，则变为0
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rows[i] || cols[j]) {
                    matrix[i][j] = 0;
                }
            }
        }

    }
}