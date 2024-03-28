package OD407;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 两个字符串间的最短路径
 * @date 2024/3/28
 * @level 8
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD407
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {

    private static int m;
    private static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String A = sc.next();
        String B = sc.next();
        //m行 n列
        m = B.length();
        n = A.length();

        System.out.println(getResult(A, B));
    }

    public static int getResult(String A, String B) {
        //dp[i][j] 是从(0,0)点到点(i,j)的最短路径
        int[][] dp = new int[m + 1][n + 1];

        //第一行 (0,0)点到(0,j)点的最短路径就是长度
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        //第一列
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        //从第二行第二列开始
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                //如果该位置A和B的字符相同，则可以走斜线
                if (A.charAt(j - 1) == B.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    //不能走斜线，则只能从上方和左边来，找更小的
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + 1;
                }
            }
        }

        //返回走到(m,n)的最短路径
        return dp[m][n];
    }

}