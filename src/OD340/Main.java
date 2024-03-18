package OD340;

import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Jacliscs
 * @description 提取字符串中的最长表达式
 * @date 2024/3/18
 * @level 7
 * @score 100
 * @type 双指针、正则表达式、栈
 */

/**
 * 题目描述
 * 提取字符串中的最长合法简单数学表达式，字符串长度最长的，并计算表达式的值。如果没有，则返回 0
 * <p>
 * 简单数学表达式只能包含以下内容：
 * <p>
 * 0-9数字，符号+-*
 * 说明：
 * <p>
 * 所有数字，计算结果都不超过long
 * 如果有多个长度一样的，请返回第一个表达式的结果
 * 数学表达式，必须是最长的，合法的
 * 操作符不能连续出现，如 +--+1 是不合法的
 * 输入描述
 * 字符串
 * <p>
 * 输出描述
 * 表达式值
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(getMaxMath(str));

    }

    //提取字符串中最长合法表达式并计算值(只包含两个操作数的，且第一个数带正负号)，如有多个长度一样，则返回第一个
    public static long getMaxMath(String str) {
        char[] chars = str.toCharArray();
        int n = chars.length;
        //创建正则 匹配 (带正负号0个或1个) 数字1个或多个，然后是 + - * 必须1个 ，然后是 (不带正负号的数字)1个或多个
        Pattern pattern = Pattern.compile("^([+-]?\\d+)([+*-]{1}\\d+)$");
        //如果匹配不只两个操作数

        int max = 0;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                String sub = str.substring(i, j + 1);
                Matcher matcher = pattern.matcher(sub);
                //如果匹配成功，且长度大于当前保存的最长，则更新
                if (matcher.find() && sub.length() > max) {
                    max = sub.length();
                    sum = cal(sub);
                }
            }
        }
        return sum;
    }


    //计算合法且无括号表达式的值：如 1 + 2 * 3 返回 7
    public static long cal(String str) {
        char[] chars = str.toCharArray();
        int n = chars.length;
        //存放操作数
        Stack<Long> stack = new Stack<>();
        //初始化符号和数字
        long number = 0;
        char sign = '+';
        //默认符号为"+" 即使第一位是-1 会+0 再 -1
        for (int i = 0; i < n; i++) {
            char c = chars[i];
            //如果遇到数字，拼数字
            if (c >= '0' && c <= '9') {
                number = number * 10 + (c - '0');
            }
            //没有括号和空格等不合法行为
            //遇到符号或已经到最后一位，将数字入栈、并刷新符号和数字
            if (!Character.isDigit(c) || i == n - 1) {
                if (sign == '+') {
                    //该数字前面符号是“+”直接入栈
                    stack.push(number);
                } else if (sign == '-') {
                    //该数字前面的符号是“-”，变负数后入栈
                    stack.push(-1 * number);
                } else if (sign == '*') {
                    //该数字前面是乘，弹出一个相乘后再入栈
                    stack.push(stack.pop() * number);
                }
                //刷新符号
                sign = c;
                //刷新数字
                number = 0;
            }
        }
        //将栈中的操作数求和
        long sum = 0;
        for (long i : stack) {
            sum += i;
        }
        return sum;
    }
}