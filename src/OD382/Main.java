package OD382;

import java.util.Scanner;
import java.util.StringJoiner;

/**
 * @author Jacliscs
 * @description 数的分解
 * @date 2024/3/21
 * @level 8
 * @score 100
 */

/**
 * 题目描述
 * 给定一个正整数 n，如果能够分解为 m（m > 1）个连续正整数之和，请输出所有分解中，m最小的分解。
 * <p>
 * 如果给定整数无法分解为连续正整数，则输出字符串"N"。
 * <p>
 * 输入描述
 * 输入数据为一整数，范围为 (1, 2^30]
 * <p>
 * 输出描述
 * 比如输入为：
 * <p>
 * 21
 * <p>
 * 输出：
 * <p>
 * 21=10+11
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //需要分解的数
        long n = sc.nextLong();
        System.out.println(getShortestSequence(n));

    }

    //得到分解一个整数的最短连续序列
    public static String getShortestSequence(long n) {
        //如果n=1 无法分解
        if (n == 1) {
            return "N";
        }

        //存放结果
        StringJoiner sj = new StringJoiner("+", n + "=", "");
        //如果是奇数，则一定可以分成n/2 n/2+1
        if (n % 2 != 0) {
            sj.add(n / 2 + "");
            sj.add((n / 2 + 1) + "");
            return sj.toString();
        } else {
            //先求出n的最大奇因数
            long x = n / 2;
            while (x % 2 == 0) {
                x /= 2;
            }
            //1.分解出的最短序列是偶数 如10=1+2+3+4
            //len = n*2/x
            //x/2 是最大奇因数分解后的左边那个数
            //minEven_len / 2 - 1 是距离
            long minEven_len = n / x * 2;
            long minEven_start = x / 2 - (minEven_len / 2 - 1);
            //2.最短序列是奇数 如12=3+4+5 最短长度就是能被n整除的最小奇数(大于1)
            long minOdd_len = getMinOddLen(n, x);
            // n/minOdd_len 是中位数
            // (minOdd_len-1)/2是距离
            long minOdd_start = n / minOdd_len - ((minOdd_len - 1) / 2);
            //3.不能分解
            long len;
            long start;
            //如果两个都能分解
            if (minOdd_start >= 1 && minEven_start >= 1) {
                //谁长度更短就分解成谁
                if (minEven_len < minOdd_len) {
                    len = minEven_len;
                    start = minEven_start;
                } else {
                    len = minOdd_len;
                    start = minOdd_start;
                }
            } else if (minOdd_start >= 1) {
                len = minOdd_len;
                start = minOdd_start;
            } else if (minEven_start >= 1) {
                len = minEven_len;
                start = minEven_start;
            } else {
                //两个的启示分解位置都<=0
                return "N";
            }
            for (long i = start; i < start + len; i++) {
                sj.add(i + "");
            }
            return sj.toString();
        }
    }

    /**
     * 得到分解一个偶数的最短奇数序列
     *
     * @param n 要分解的偶数
     * @param x 最大奇因数
     * @return long 分解出的最短奇数长度
     * @create 2024/3/21 16:12
     */
    public static long getMinOddLen(long n, long x) {
        //x<3 则x=1，无效
        if (x < 3) {
            return -1;
        }
        //如12 = 3+4+5 12的最大奇因数为3 需要找到能被n整除的最小奇数
        for (long i = 3; i * i <= x; i += 2) {
            if (x % i == 0) {
                return i;
            }
        }
        //如果无法分解，则返回x
        return x;
    }
}