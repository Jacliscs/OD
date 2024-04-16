package OD357;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 园区参观路径
 * @date 2024/3/24
 * @level 4
 * @score 200
 */

/**
 * 题目描述
 * 园区某部门举办了Family Day，邀请员工及其家属参加；
 * <p>
 * 将公司园区视为一个矩形，起始园区设置在左上角，终点园区设置在右下角；
 * <p>
 * 家属参观园区时，只能向右和向下园区前进，求从起始园区到终点园区会有多少条不同的参观路径。
 * <p>
 * image
 * <p>
 * 输入描述
 * 第一行为园区的长和宽；
 * <p>
 * 后面每一行表示该园区是否可以参观，0表示可以参观，1表示不能参观
 * <p>
 * 输出描述
 * 输出为不同的路径数量
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    //dfs算法中最后结果
    static int res = 0;
    static int m;
    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //长 宽
        m = sc.nextInt();
        n = sc.nextInt();
        //园区
        int[][] park = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                park[i][j] = sc.nextInt();
            }
        }
        //输出有多少条不同的路径
        //dfs(0,0,park);
        long result = getResult(park);

        System.out.println(result);
    }

    //动态规划 返回从起点到终点的路径条数
    public static long getResult(int[][] park) {
        //起点和终点不能访问，则返回0
        if (park[0][0] == 1 || park[m - 1][n - 1] == 1) {
            return 0;
        }
        //dp[i][j]表示从起点到(i,j)点的路径条数  dp[i][j] = dp[i-1][j]+dp[i][j-1]
        long[][] dp = new long[m][n];
        dp[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //遇到障碍，跳过
                if (park[i][j] == 1) continue;
                //只能向右和向下走 所以到达点(i,j)的上一步只能是从上或者从左边来
                if (i > 0) {
                    dp[i][j] += dp[i - 1][j];
                }
                if (j > 0) {
                    dp[i][j] += dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 深度搜索 递归 能到达终点的个数 数量级大时会超时
     *
     * @param x
     * @param y
     * @param park
     * @return boolean
     * @create 2024/3/24 21:49
     */
    public static boolean dfs(int x, int y, int[][] park) {
        //如果园区起点和终点不能参观，直接false
        if (park[0][0] == 1 || park[m - 1][n - 1] == 1) {
            return false;
        }
        //返回标志
        if (x == park.length - 1 && y == park[0].length - 1) {
            return true;
        }
        //只能往右和往下走，不会重复
        //如果右边不为1 则往右走
        if (y + 1 < park[0].length && park[x][y + 1] != 1) {
            if (dfs(x, y + 1, park)) {
                res++;
            }
        }
        //如果下面不是1，则可以往下走
        if (x + 1 < park.length && park[x + 1][y] != 1) {
            if (dfs(x + 1, y, park)) {
                res++;
            }
        }
        //如果向下和向右都不能走
        return false;
    }


}