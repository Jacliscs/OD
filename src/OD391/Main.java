package OD391;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 找数字
 * @date 2024/3/25
 * @level 6
 * @score 200
 */

/**
 * 题目描述
 * 小扇和小船今天又玩起来了数字游戏，
 * <p>
 * 小船给小扇一个正整数 n（1 ≤ n ≤ 1e9），小扇需要找到一个比 n 大的数字 m，使得 m 和 n 对应的二进制中 1 的个数要相同，如：
 * <p>
 * 4对应二进制100
 * <p>
 * 8对应二进制1000
 * <p>
 * 其中1的个数都为1个
 * <p>
 * 现在求 m 的最小值。
 * <p>
 * 输入描述
 * 输入一个正整数 n（1 ≤ n ≤ 1e9）
 * <p>
 * 输出描述
 * 输出一个正整数 m
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //输入n
        int n = sc.nextInt();
        //输入与n二进制中1数字相同的最小的m
        System.out.println(getResult(n));

    }

    //方法
    public static int getResult(int n) {
        //如 10011100 -> 10100011
        //把n转为二进制，然后统计1的个数 从右遍历到第一个“01”改为“10”，并把右边的1全部加到末尾
        //首部添0，如111 -> 0111 -> 1011
        String n_binary = "0" + Integer.toBinaryString(n);
        char[] chars = n_binary.toCharArray();
        //统计改变位置右边的1的个数
        int count_one = 0;
        for (int i = chars.length - 2; i >= 0; i--) {
            if (chars[i] == '0' && chars[i + 1] == '1') {
                //把从右到左遍历到的第一个“01”变为“10”
                chars[i] = '1';
                chars[i + 1] = '0';
                if (count_one > 0) {
                    //将该位置右边的所有1集中到尾部
                    for (int j = i + 2; j < chars.length; j++) {
                        //后面要添加几个0  chars.length-count_one
                        if (j < chars.length - count_one) {
                            chars[j] = '0';
                        } else {
                            chars[j] = '1';
                        }
                    }
                }
                //一旦找到01 变为10 并将后面的1放置末尾，则跳出循环
                break;
            }
            //统计i右边的1的个数
            if (chars[i + 1] == '1') {
                count_one++;
            }
        }
        //把2进制chars数组转为10进制
        return Integer.parseInt(new String(chars), 2);
    }

}