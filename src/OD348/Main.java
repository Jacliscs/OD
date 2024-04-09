package OD348;

import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 分月饼
 * @date 2024/3/19
 * @level 7
 * @score 200
 */

/**
 * 题目描述
 * 中秋节，公司分月饼，m 个员工，买了 n 个月饼，m ≤ n，每个员工至少分 1 个月饼，但可以分多个，
 * <p>
 * 单人分到最多月饼的个数是 Max1 ，单人分到第二多月饼个数是 Max2 ，Max1 - Max2 ≤ 3 ，
 * 单人分到第 n - 1 多月饼个数是 Max(n-1)，单人分到第n多月饼个数是 Max(n) ，Max(n-1) – Max(n) ≤ 3,
 * 问有多少种分月饼的方法？
 * <p>
 * 输入描述
 * 每一行输入m n，表示m个员工，n个月饼，m ≤ n
 * <p>
 * 输出描述
 * 输出有多少种月饼分法
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    //月饼个数
    static int n;
    //员工数
    static int m;
    //最大差值
    static int maxDiff = 3;
    //方案个数
    static int ans = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //员工
        m = sc.nextInt();
        //月饼
        n = sc.nextInt();
        //如果只有一个员工

        getResult(0, 1, n / m, n);

        System.out.println(ans);
    }

    /**
     * 递归判断分月饼的总方案数
     *
     * @param index  当前员工编号
     * @param min    当前员工分的最少个数
     * @param max    当前员工分的最大个数
     * @param remain 分给当前员工前，还剩的月饼个数
     * @return boolean
     * @create 2024/3/20 13:52
     */
    public static void getResult(int index, int min, int max, int remain) {
        //如果只有一个人，就只有一种分法
        if (m == 1) {
            ans = 1;
            return;
        }

        //结束递归条件：最后一个分的月饼与上一个分的月饼相差小于3 因为去重控制，每一个员工的min值就是上一个员工分的月饼数量
        if (index == m - 1) {
            if (remain - min <= maxDiff) {
                ans++;
            }
            //返回上层递归
            return;
        }

        //分月饼：每个位置都有max-min种分法，且因为控制递归，所有每个人的max不能超过剩下月饼平均分配的数量
        //如果超过了平均数量，后面就一定会有人分的比第i个人少
        for (int i = min; i <= max; i++) {
            //把i个月饼分给当前员工
            remain -= i;
            //看后面是否能成功递归
            getResult(index + 1, i, Math.min(i + maxDiff, remain / (m - index - 1)), remain);
            //不管上一步能否最终分发完，i+1，开始下一轮递归
            remain += i;
        }
    }
}