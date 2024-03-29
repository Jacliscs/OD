package OD420;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 矩阵匹配
 * @date 2024/3/29
 * @level 8
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD420
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {

    private static int n;
    private static int m;
    private static int k;
    private static int[][] matrix;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //行
        n = sc.nextInt();
        //列
        m = sc.nextInt();
        //从不同行不同列组合的n位组合中的第k大数字的最小值
        k = sc.nextInt();

        //二分法的边界
        int min = 1;
        int max = Integer.MIN_VALUE;

        //矩阵
        matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = sc.nextInt();
                max = Math.max(max, matrix[i][j]);
            }
        }

        //二分法 枚举第k大的值
        //假如第k大的值是kth，则有k-1个数比kth大，有n-(k-1)个数比kth小
        //使用min max来逼近kth，找到刚好的kth
        while (min <= max) {
            int mid = (min + max) / 2;

            //当mid作为N个数中第k大的数时，是否有n-k+1个数不大于它的值
            if (check(mid)) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        System.out.println(min);

    }


    /**
     * 第k大的数为kth，则一定有n-k+1个数比自己小
     *
     * @param kth
     * @return boolean
     * @create 2024/3/29 19:48
     */
    public static boolean check(int kth) {
        //记录N个数中小于等于kth的个数
        int smallerCount = 0;

        //记录每个列号所匹配的行号 n行m列
        int[] match = new int[m];

        //若不初始化，则默认值为0，即默认每一列都匹配的第0行
        //初始化，-1表示还没匹配
        Arrays.fill(match, -1);

        //遍历行号  n行m列
        for (int i = 0; i < n; i++) {
            //记录某列是否被该行访问过
            boolean[] vis = new boolean[m];
            if (dfs(i, kth, match, vis)) {
                smallerCount++;
            }
        }
        //是否找到n-k+1个数比kth小
        return smallerCount >= n - k + 1;


    }

    /**
     * 第i行能否成功与当前的列配对
     *
     * @param i     发起申请的行
     * @param kth   该行中比kth小的数
     * @param match 已经匹配的列--对应的行号
     * @param vis   该列是否被访问过
     * @return boolean
     * @create 2024/3/29 19:41
     */
    public static boolean dfs(int i, int kth, int[] match, boolean[] vis) {
        //行号i发起匹配

        //遍历列号
        for (int j = 0; j < m; j++) {
            //该列没被访问，且该数字小于kth
            if (!vis[j] && matrix[i][j] <= kth) {
                //刷新状态
                vis[j] = true;

                //匈牙利算法：该列还没有匹配 或者该列匹配的行还能匹配到其他列，则把第i行匹配给i
                if (match[j] == -1 || dfs(match[j], kth, match, vis)) {
                    //把i行与j列匹配
                    match[j] = i;
                    return true;
                }
            }
        }
        //遍历完了，无法匹配
        return false;

    }

}