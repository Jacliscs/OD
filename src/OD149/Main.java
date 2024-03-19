package OD149;

import java.math.BigInteger;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author Jacliscs
 * @description 求字符串中所有整数的最小和
 * @date 2024/3/19
 * @level 7
 * @score 100
 */

/**
 * 题目描述
 * 输入字符串s，输出s中包含所有整数的最小和。
 * <p>
 * 说明：
 * <p>
 * 字符串s，只包含 a-z A-Z ±
 * <p>
 * 合法的整数包括
 * <p>
 * 1）正整数：一个或者多个0-9组成，如 0 2 3 002 102
 * <p>
 * 2）负整数：负号 – 开头，数字部分由一个或者多个0-9组成，如 -0 -012 -23 -00023
 * <p>
 * 输入描述
 * 包含数字的字符串
 * <p>
 * 输出描述
 * 所有整数的最小和
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //读取
        String str = sc.nextLine();
        System.out.println(getMinSum(str));
    }

    //读取字符串中的最小整数和 数字没有限制长度，要用大数处理
    public static BigInteger getMinSum(String str) {
        //字符串str，只包含 a-z A-Z ±
        char[] chars = str.toCharArray();
        int n = chars.length;
        //初始化数字
        BigInteger number = new BigInteger("0");
        //初始化符号
        char sign = '+';
        //存放操作数，正数单个加，负数整体加
        Stack<BigInteger> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            //因为要找最小和，遇到正号直接加，遇到负号把后面数字拼起来加
            char c = chars[i];

            //遇到数字，判断正数直接入栈、负数整体入栈
            if (Character.isDigit(c)) {
                if (sign == '+') {
                    stack.push(new BigInteger(String.valueOf(c)));
                } else {
                    //拼数字
                    number = number.multiply(new BigInteger("10")).add(new BigInteger(String.valueOf(c)));
                }
            }

            //不是数字，则判断是符号 输入控制只有a-z A-Z ±
            if (!Character.isDigit(c) || i == n - 1) {
                //如果符号为-，把前面整体入栈
                if (sign == '-') {
                    //把前面整体入栈
                    stack.push(number.multiply(new BigInteger("-1")));
                }
                //刷新符号 遇到字母的话刷新为+号
                sign = c == '-' ? '-' : '+';
                //刷新数字
                number = new BigInteger("0");
            }
        }
        //把stack中的数字加起来
        BigInteger sum = new BigInteger("0");
        for (BigInteger i : stack) {
            sum = sum.add(i);
        }
        return sum;
    }
}
