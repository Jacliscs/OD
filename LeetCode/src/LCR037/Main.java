package LCR037;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 行星碰撞
 * @date 2024/4/10
 * @level 中等
 * @score
 * @url https://leetcode.cn/problems/XagZNi/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        //用栈模拟爆炸（栈顶出栈）
        Deque<Integer> stack = new LinkedList<>();
        //指向当前行星
        int p = 0;
        while (p < asteroids.length) {
            //入栈条件：栈为空、前一个向左运动(不可能碰撞)、当前向右运动
            if (stack.isEmpty() || stack.peek() < 0 || asteroids[p] > 0) {
                stack.push(asteroids[p]);
            }
            //前一个一定向右且当前向左，一定会碰撞 比较大小看谁存活
            else if (stack.peek() <= -asteroids[p]) {
                //如果栈顶元素要被摧毁，则继续比较下一个栈顶
                if (stack.pop() < -asteroids[p]) {
                    continue;
                }
                //如果栈顶与当前大小相等，都被摧毁，则当前star不入栈，star++，遍历下一个
            }
            p++;
        }

        int[] ans = new int[stack.size()];
        //逆序填入
        for (int i = stack.size() - 1; i >= 0; i--) {
            ans[i] = stack.pop();
        }
        return ans;
    }
}