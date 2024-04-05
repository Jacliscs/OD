package OD426;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 抢7游戏
 * @date 2024/4/5
 * @level 8
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD426
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //开始的数字m，A先叫
        int m = sc.nextInt();

        //dpA[i] 表示A叫到i的方案数 因为需要用到dpA[m+1]，所以初始化长度为m+2
        BigInteger[] dpA = new BigInteger[m + 2];
        //初始化 默认为0
        for (int i = 0; i < m + 2; i++) dpA[i] = new BigInteger("0");

        //A是从m开始叫的，所以dpA[m]=1
        dpA[m] = new BigInteger("1");

        //dpB[i]表示B叫到i的方案数
        BigInteger[] dpB = new BigInteger[m + 2];
        //初始化
        for (int i = 0; i < m + 2; i++) dpB[i] = new BigInteger("0");

        //动态规划
        for (int i = m - 1; i >= 7; i--) {
            //B叫i的方案数 = A叫i+1的方案数 + A叫i+2的方案数 初始时dpB[m-1] = dpA[m]+dpA[m+1] = 1+0=1
            dpB[i] = dpA[i + 1].add(dpA[i + 2]);

            //A叫i的方案数 =  B叫i+1的方案数 + B叫i+2的方案数
            dpA[i] = dpB[i + 1].add(dpB[i + 2]);
        }
        //返回B叫到7的方案数
        System.out.println(dpB[7]);

    }
}