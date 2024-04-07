package Code227;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author 浮生
 * @description 基本计算器
 * @date 2024/4/7
 * @level 中等
 * @score
 * @url https://leetcode.cn/problems/basic-calculator-ii/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public int calculate(String s) {
        //移除空格
        s = s.replaceAll(" ", "");

        //栈存放操作数
        Stack<Integer> stack = new Stack<>();

        //初始化数字
        int number = 0;

        //初始化符号
        char sign = '+';

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            //遇到数字，拼数字
            if (Character.isDigit(ch)) {
                number = 10 * number + (ch - '0');
            }

            //遇到符号或最后一位
            if (i == s.length() - 1 || !Character.isDigit(ch)) {
                //如果当前数字前的符号是'+'
                if (sign == '+') {
                    //直接入栈
                    stack.push(number);
                } else if (sign == '-') {
                    //当前数 * -1 入栈
                    stack.push(-number);
                } else if (sign == '*') {
                    //弹出一个数，相乘后入栈
                    stack.push(stack.pop() * number);
                } else if (sign == '/') {
                    //弹出一个数，相除后入栈 只做整数除法
                    stack.push(stack.pop() / number);
                }
                //刷新符号
                sign = ch;
                //刷新数字
                number = 0;
            }
        }
        //现在stack里面存放的需要顺序计算的操作数，求和
        return stack.stream().mapToInt(Integer::intValue).sum();
    }
}