package OD392;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author Jacliscs
 * @description 符号运算
 * @date 2024/3/25
 * @level 8
 * @score 200
 */

/**
 * https://hydro.ac/d/HWOD2023/p/OD392
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //输入表达式 支持 +-*/ 所有数字为正整数
        String str = sc.nextLine();
        System.out.println(getResult(str));
    }

    /**
     * 定义数字类对象
     */
    static class Digit {
        int ch;//分子
        int fa;//分母

        public Digit(int ch, int fa) {
            this.ch = ch;
            this.fa = fa;
        }
    }

    /**
     * 计算表达式的值 返回整数或最简分数
     *
     * @param str
     * @return java.lang.String
     * @create 2024/3/25 21:13
     */
    public static String getResult(String str) {
        //先去除空格
        str = str.replaceAll(" ", "");
        char[] chs = str.toCharArray();
        int n = chs.length;
        //存放操作数
        Stack<Digit> stack = new Stack<>();
        //初始化数字和符号
        Digit digit = new Digit(0, 1);
        char sign = '+';
        for (int i = 0; i < n; i++) {
            char c = chs[i];
            //遇到数字，拼数字
            if (Character.isDigit(c)) {
                digit.ch = 10 * digit.ch + (c - '0');
            }
            //遇到括号，递归解决
            if (c == '(') {
                //统计括号数量
                int count = 1;
                //移到下一位
                int j = i + 1;
                while (count > 0) {
                    if (chs[j] == ')') count--;
                    if (chs[j] == '(') count++;
                    j++;
                }
                //递归解决 分有分数和没分数两种情况
                String temp_result = getResult(str.substring(i + 1, j - 1));
                if (temp_result.contains("/")) {
                    String[] ch_fa = temp_result.split("/");
                    digit.ch = Integer.parseInt(ch_fa[0]);
                    digit.fa = Integer.parseInt(ch_fa[1]);
                } else {
                    //是个整数
                    digit.ch = Integer.parseInt(temp_result);
                }
                i = j - 1;
            }
            //遇到符号或已经到最后一位 初始符号是+ 初始digit是 0/1
            if (!Character.isDigit(c) || i == n - 1) {
                if (sign == '+') {
                    stack.push(new Digit(digit.ch, digit.fa));
                } else if (sign == '-') {
                    stack.push(new Digit(-1 * digit.ch, digit.fa));
                } else if (sign == '*') {
                    Digit temp = stack.pop();
                    stack.push(new Digit(digit.ch * temp.ch, temp.fa * digit.fa));
                } else if (sign == '/') {
                    //如果/号后面是0
                    if (digit.ch == '0') {
                        return "ERROR";
                    }
                    Digit temp = stack.pop();
                    stack.push(new Digit(temp.ch * digit.fa, digit.ch * temp.fa));
                }
                //更新符号
                sign = c;
                //刷新数字
                digit = new Digit(0, 1);
            }
        }
        //从stack中取操作数对象  两两相加
        while (stack.size() > 1) {
            Digit num1 = stack.pop();//操作数1
            Digit num2 = stack.pop();//操作数2
            Digit temp = new Digit(num1.ch * num2.fa + num1.fa * num2.ch, num1.fa * num2.fa);
            stack.push(temp);
        }
        //最后剩的就是结果
        Digit res = stack.pop();
        //化简
        return getSimpleResult(res.ch, res.fa);
    }

    /**
     * 化简结果，能整除则返回整数，不能则保留最简分数
     *
     * @param ch 分子
     * @param fa 分母
     * @return java.lang.String
     * @create 2024/3/25 21:14
     */
    public static String getSimpleResult(int ch, int fa) {
        if (fa == 0) {
            return "ERROR";
        }
        //最大公约数
        int base = gcd(Math.abs(ch), Math.abs(fa));
        //判断正负
        if (ch > 0 && fa > 0 || ch < 0 && fa < 0) {
            //是正数
            if (ch % fa == 0) {
                return String.valueOf(Math.abs(ch) / Math.abs(fa));
            } else {
                //不能整除
                return Math.abs(ch / base) + "/" + Math.abs(fa / base);
            }
        } else {
            //是负数
            if (ch % fa == 0) {
                return String.valueOf(ch / fa);
            } else {
                //不能整除
                return "-" + Math.abs(ch / base) + "/" + Math.abs(fa / base);
            }
        }
    }

    /**
     * 求最大公约数
     *
     * @param num1
     * @param num2
     * @return int
     * @create 2024/3/25 21:15
     */
    public static int gcd(int num1, int num2) {
        if (num2 == 0) {
            return num1;
        }
        return gcd(num2, num1 % num2);
    }
}