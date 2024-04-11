package OD361;

import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 小华最多能得到多少克黄金
 * @date 2024/3/21
 * @level 5
 * @score 100
 */

/**
 * 题目描述
 * 小华按照地图去寻宝，地图上被划分成 m 行和 n 列的方格，横纵坐标范围分别是 [0, n-1] 和 [0, m-1]。
 * <p>
 * 在横坐标和纵坐标的数位之和不大于 k 的方格中存在黄金（每个方格中仅存在一克黄金），但横坐标和纵坐标之和大于 k 的方格存在危险不可进入。小华从入口 (0,0) 进入，任何时候只能向左，右，上，下四个方向移动一格。
 * <p>
 * 请问小华最多能获得多少克黄金？
 * <p>
 * 输入描述
 * 坐标取值范围如下：
 * <p>
 * 0 ≤ m ≤ 50
 * 0 ≤ n ≤ 50
 * k 的取值范围如下：
 * <p>
 * 0 ≤ k ≤ 100
 * 输入中包含3个字数，分别是m, n, k
 * <p>
 * 输出描述
 * 输出小华最多能获得多少克黄金
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    //能取得的黄金数
    static int ans = 0;
    //行 列 设为static 方便dfs中判断边界
    static int m, n, k;
    //标记是否被访问过
    //存放对应下标的数位和
    static int[] digitSum;
    static boolean[][] isUsed;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //行 列 数位和阈值k
        m = sc.nextInt();
        n = sc.nextInt();
        k = sc.nextInt();
        isUsed = new boolean[m][n];
        //存放数位和 长度是m n 最大值的数位和
        digitSum = getDigitSum(Math.max(m, n));
        dfs(0, 0);
        System.out.println(ans);

    }

    //递归
    public static void dfs(int x, int y) {
        //返回上一层标志 下标越界 被使用 数位和超过k
        if (x < 0 || y < 0 || x >= m || y >= n || isUsed[x][y] || digitSum[x] + digitSum[y] > k) {
            return;//返回上一层
        }
        //如果没有返回上一层，说明该位置还没被使用，刷新
        isUsed[x][y] = true;
        ans++;
        //遍历上下左右位置 使用位移偏移数组遍历
        int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] offset : offsets) {
            int newX = x + offset[0];
            int newY = y + offset[1];
            dfs(newX, newY);
        }
    }

    //存放从0-n-1的数位和数组 下标是数，值是数位和
    public static int[] getDigitSum(int n) {
        int[] temp = new int[n];
        for (int i = 0; i < n; i++) {
            int num = i;
            while (num > 0) {
                temp[i] += num % 10;
                num /= 10;
            }
        }
        return temp;
    }


}