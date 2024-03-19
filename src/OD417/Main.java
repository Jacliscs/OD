package OD417;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author Jacliscs
 * @description 火星文计算
 * @date 2024/3/19
 * @level 6
 * @score 100
 */

/**
 * 题目描述
 * 已知火星人使用的运算符为#、$，其与地球人的等价公式如下：
 * <p>
 * x#y = 4*x+3*y+2
 * x$y = 2*x+y+3
 * 其中 x、y 是无符号整数
 * 地球人公式按C语言规则计算
 * 火星人公式中，#的优先级高于$，相同的运算符，按从左到右的顺序计算
 * 现有一段火星人的字符串报文，请你来翻译并计算结果。
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //由输入保证合法
        String s = sc.nextLine();
        System.out.println(solve(s));
    }

    //计算整个火星文结果
    public static long solve(String s) {
        char[] chars = s.toCharArray();
        //StringBuilder sb = new StringBuilder();
        long number = 0;
        //标记前面是否已有操作符
        boolean hasOp = false;
        Stack<Long> stack = new Stack<>();
        //上一个符号
        char lastOp = ' ';
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            //因为是无符号整数，所以遇到数字直接拼
            if (c >= '0' && c <= '9') {
                number = number * 10 + (c - '0');
            }
            //遇到第一个符号，刷新
            if (!Character.isDigit(c) && !hasOp) {
                hasOp = true;
                lastOp = c;
                stack.push(number);
                number = 0;
                continue;
            }

            //遇到符号或者已经到最后一位 只做#运算，$运算最后依次计算
            if (!Character.isDigit(c) || i == chars.length - 1 && hasOp) {
                //#的优先级高于$ 直接进行#计算并入栈
                if (lastOp == '#') {
                    stack.push(cal(stack.pop(), number, lastOp));
                } else if (lastOp == '$') {
                    //如果是#运算，则直接入栈，最后统一操作
                    stack.push(number);
                }
                //刷新
                //hasOp = false;
                lastOp = c;
                number = 0;
            }
        }
        //栈中存的是从左到右需要做$运算的数，要倒置栈
        Stack<Long> reverseStack = new Stack<>();
        while (!stack.isEmpty()) {
            reverseStack.push(stack.pop());
        }
        //int sum = 0;
        while (reverseStack.size() != 1) {
            //从栈顶取两个数做$运算，再把结果入栈，循环
            long temp = cal(reverseStack.pop(), reverseStack.pop(), '$');
            reverseStack.push(temp);
        }
        //栈中最后元素就是结果
        return reverseStack.pop();
    }


    //计算两种运算符结果：x#y = 4*x+3*y+2 x$y = 2*x+y+3
    public static long cal(long x, long y, char c) {
        if (c == '#') {
            return 4 * x + 3 * y + 2;
        } else {
            return 2 * x + y + 3;
        }
    }
}