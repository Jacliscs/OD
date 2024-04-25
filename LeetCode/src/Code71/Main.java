package Code71;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * @author 浮生
 * @description 简化路径
 * @date 2024/4/25
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/simplify-path/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public String simplifyPath(String path) {
        //栈存放路径，遇到..就弹出
        String[] paths = path.split("/");
        Deque<String> stack = new ArrayDeque<>();
        for (String s : paths) {
            if ("..".equals(s)) {
                //不为空则弹出最后一个
                if (!stack.isEmpty()) {
                    stack.pollLast();
                }
            } else if (s.length() != 0 && !".".equals(s)) {
                //有效路径
                stack.addLast(s);
            }
        }

        //以"/"开头，以"/"分隔
        StringJoiner sj = new StringJoiner("/", "/", "");

        while (!stack.isEmpty()) {
            sj.add(stack.pollFirst());
        }

        return sj.toString();
    }
}