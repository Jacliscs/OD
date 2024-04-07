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
        //从右往左遍历
        for (int i = n - 2; i >= 0; i--) {
            //ans[j]表示下一个比自己大的数间隔了几位，每次都跳转到下一个比自己大的位置，减少循环次数
            for (int j = i + 1; j < n; j += ans[j]) {
                if (temperatures[j] > temperatures[i]) {
                    ans[i] = j - i;
                    break;
                } else if (ans[j] == 0) {
                    //后面没有比ans[j]大的数了
                    ans[i] = 0;
                    break;
                }
            }
        }
        return ans;
    }
}