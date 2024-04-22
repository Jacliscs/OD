package Code402;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 移掉k位数字
 * @date 2024/4/22
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/remove-k-digits/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public String removeKdigits(String num, int k) {
        int len = num.length();

        //单调队列，存放从小到大的数字 每次与末尾比较大小
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < len; i++) {
            char c = num.charAt(i);

            while (!stack.isEmpty() && k > 0 && stack.peekLast() > c) {
                //弹出末尾的
                stack.pollLast();
                k--;
            }

            //0不能添加到开头，需要去掉前导0
            if (c != '0' || !stack.isEmpty()) {
                stack.offerLast(c);
            }
        }

        //如果还没去掉k个，则继续去掉末尾的
        while (k > 0) {
            stack.pollLast();
            k--;
        }

        //拼接字符串
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pollFirst());
        }

        String res = sb.toString();

        //如果删完了数字就返回“0”
        return "".equals(res) ? "0" : res;
    }
}