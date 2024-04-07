package Code739;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 每日温度
 * @date 2024/4/7
 * @level 中等
 * @score
 * @url https://leetcode.cn/problems/daily-temperatures/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;

        //结果
        int[] ans = new int[n];

        //暴力
        for (int i = 0; i < n; i++) {
            //如果后面没有温度比自己高的，则ans[i]=0
            int res = 0;
            for (int j = i + 1; j < n; j++) {
                if (temperatures[j] > temperatures[i]) {
                    res = j - i;
                    break;
                }
            }
            ans[i] = res;
        }
        return ans;
    }
}