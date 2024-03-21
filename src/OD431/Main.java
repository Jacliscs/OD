package OD431;

import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 来自异国的客人
 * @date 2024/3/21
 * @level 3
 * @score 100
 */

/**
 * 题目描述
 * 有位客人来自异国，在该国使用 m 进制计数。
 * <p>
 * 该客人有个幸运数字n（n < m），每次购物时，其总是喜欢计算本次支付的花费（折算为异国的价格后）中存在多少幸运数字。
 * <p>
 * 问：当其购买一个在我国价值 k 的产品时，其中包含多少幸运数字?
 * <p>
 * 输入描述
 * 第一行输入为 k，n，m。
 * <p>
 * 其中：
 * <p>
 * k 表示该客人购买的物品价值（以十进制计算的价格）
 * n 表示该客人的幸运数字
 * m 表示该客人所在国度采用的进制
 * 输出描述
 * 输出幸运数字的个数，行末无空格
 * <p>
 * 备注
 * 当输入非法内容时，输出0
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //价格 幸运数字 m进制
        long k = sc.nextLong();
        long n = sc.nextLong();
        long m = sc.nextLong();
        System.out.println(numOfLuck(k, n, m));
    }

    /**
     * 将10进制k转为m进制后，有多少个幸运数字n
     *
     * @param k 十进制
     * @param n 幸运数字
     * @param m m进制
     * @return int
     * @create 2024/3/21 16:30
     */
    public static long numOfLuck(long k, long n, long m) {
        //如果幸运数字大于m进制，则不存在幸运数字
        if (n >= m) {
            return 0;
        }
        //统计幸运数字个数
        long count = 0;
        //转换m进制后的数
        String result = "";
        //除留取余 从最低位到最高位依次取值
        while (k > 0) {
            long remain = k % m;//如10->2   此时最低位为0
            //从最低位到最高位添加
            result = remain + result;

            //每计算出一个位，都比较是否跟幸运数相等
            if (remain == n) {
                count++;
            }
            //开始取下一位 10/2 = 5 5%2=1 倒数第二位为1 5/2=2 2%2=0 倒数第三位为0 2/2=1 1%2=1 第四位为1
            //最后结果 10 -> 1010
            k /= m;
        }
        return count;
    }
}