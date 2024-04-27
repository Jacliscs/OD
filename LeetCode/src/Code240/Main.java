package Code240;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 搜索二维矩阵II
 * @date 2024/4/27
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/search-a-2d-matrix-ii/description/?envType=study-plan-v2&envId=top-100-liked">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        //每行的元素从左到右升序排列。
        //每列的元素从上到下升序排列。
        //1 <= n, m <= 300
        int m = matrix.length;
        int n = matrix[0].length;

        //从左下角开始遍历，如果matrix[x][y]<target ，列++，如果> 行--
        int x = m - 1;
        int y = 0;

        while (x >= 0 && y < n) {
            if (matrix[x][y] < target) y++;
            else if (matrix[x][y] > target) x--;
            else return true;
        }

        return false;

    }
}