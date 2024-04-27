package Code48;

import java.nio.file.spi.FileSystemProvider;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 旋转图像
 * @date 2024/4/27
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/rotate-image/description/?envType=study-plan-v2&envId=top-100-liked">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //

    }
}

class Solution {
    /**
     * 原地旋转90度
     *
     * @param matrix
     * @return void
     */
    public void rotate(int[][] matrix) {
        //顺时针旋转90° = 先上下反转 + 主对角线反转
        int n = matrix.length;

        //上下水平反转
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                //交换上下
                int tmp = matrix[i][j];
                //第i行与倒数第i行换 列不变
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = tmp;
            }
        }

        //主对角线反转
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }
}