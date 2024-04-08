package OD222;

import java.util.Scanner;
import java.util.StringJoiner;

/**
 * @author Jacliscs
 * @description 素数之积
 * @date 2024/3/21
 * @level 6
 * @score 100
 */

/**
 * 题目描述
 * RSA加密算法在网络安全世界中无处不在，它利用了极大整数因数分解的困难度，数据越大，安全系数越高，给定一个 32 位正整数，请对其进行因数分解，找出是哪两个素数的乘积。
 * <p>
 * 输入描述
 * 一个正整数 num 0 < num < 2147483647
 * <p>
 * 输出描述
 * 如果成功找到，以单个空格分割，从小到大输出两个素数，分解失败，请输出-1, -1
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        System.out.println(getMaxPrimeMulti(n));

    }

    /**
     * 返回两个素数积 如果没有则返回-1 -1
     *
     * @param n
     * @return java.lang.String
     * @create 2024/3/21 16:54
     */
    public static String getMaxPrimeMulti(long n) {
        //如果n是素数，则肯定不能分解
        if (isPrime(n)) {
            return "-1 -1";
        }

        StringJoiner sj = new StringJoiner(" ");
        for (long i = 2; i <= Math.sqrt(n); i += 1) {
            //如果能被i整除，则判断i和整除后的结果是否是素数
            if (n % i == 0) {
                if (isPrime(i) && isPrime(n / i)) {
                    sj.add(i + "");
                    sj.add(n / i + "");
                    return sj.toString();
                } else {
                    //i或者n/i 其中有一个不是素数，则n不可能被分解为两个素数之积
                    //减少循环次数
                    break;
                }
            }
        }
        //如果没找到 则返回-1 -1
        return "-1 -1";
    }

    /**
     * 判断n是否是素数
     *
     * @param n
     * @return boolean
     * @create 2024/3/21 16:52
     */
    public static boolean isPrime(long n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            //如果能被除了1和自身的数整除，则不是素数
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

}