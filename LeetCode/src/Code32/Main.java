package Code32;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 最长有效括号
 * @date 2024/4/26
 * @level 困难
 * @url <a href="https://leetcode.cn/problems/longest-valid-parentheses/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    /**
     * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度
     *
     * @param s
     * @return int
     */
    public int longestValidParentheses(String s) {
        //题解：最长有效括号长度
        int ans = 0;

        //stack存放最后一个未匹配的右括号的下标，默认添加-1维护边界条件
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                //遇到'('直接入栈
                stack.push(i);
            } else {
                //遇到')'
                //先弹出一个元素
                stack.pop();

                //此时如果栈为空说明之前没有匹配的括号，此时直接入栈
                if (stack.isEmpty()) stack.push(i);
                    //否则说明之前有匹配的括号，此时计算长度
                else ans = Math.max(ans, i - stack.peek());
            }
        }
        return ans;
    }
}