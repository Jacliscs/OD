package Code20;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author 浮生
 * @description 有效的括号
 * @date 2024/4/7
 * @level 简单
 * @score
 * @url https://leetcode.cn/problems/valid-parentheses/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public boolean isValid(String s) {
        int n = s.length();
        //如果不是2的倍数，则一定非法
        if (n % 2 != 0) return false;

        //存放对应的配对关系
        Map<Character, Character> map = new HashMap<>() {
            {
                put(')', '(');
                put(']', '[');
                put('}', '{');
            }
        };

        //栈，存放未消去的左括号
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            //如果是右括号
            if (map.containsKey(ch)) {
                //stack为空 或 当前栈顶的左括号不匹配，则返回false
                if (stack.isEmpty() || stack.peek() != map.get(ch)) {
                    return false;
                }
                //合法，则弹出栈顶左括号
                stack.pop();
            } else {
                //是左括号，直接入栈
                stack.push(ch);
            }
        }
        //最后栈要清空才合法
        return stack.isEmpty();

    }
}