package Code13;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 罗马数字转整数
 * @date 2024/4/9
 * @level 简单
 * @score
 * @url https://leetcode.cn/problems/roman-to-integer/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        Solution solution = new Solution();
        System.out.println(solution.romanToInt(s));

    }
}

class Solution {
    public int romanToInt(String s) {
        //记录题解[1,3999]
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'M') {
                ans += 1000;
            } else if (c == 'D') {
                ans += 500;
            } else if (c == 'C') {
                //右边是否有D、M
                if (i + 1 < s.length() && s.charAt(i + 1) == 'D') {
                    ans += 400;
                    i++;
                } else if (i + 1 < s.length() && s.charAt(i + 1) == 'M') {
                    ans += 900;
                    i++;
                } else {
                    ans += 100;
                }
            } else if (c == 'L') {
                ans += 50;
            } else if (c == 'X') {
                //右边是否是L、C
                if (i + 1 < s.length() && s.charAt(i + 1) == 'L') {
                    ans += 40;
                    i++;
                } else if (i + 1 < s.length() && s.charAt(i + 1) == 'C') {
                    ans += 90;
                    i++;
                } else {
                    ans += 10;
                }
            } else if (c == 'V') {
                ans += 5;
            } else if (c == 'I') {
                //右边是否有V、X
                if (i + 1 < s.length() && s.charAt(i + 1) == 'V') {
                    ans += 4;
                    i++;
                } else if (i + 1 < s.length() && s.charAt(i + 1) == 'X') {
                    ans += 9;
                    i++;
                } else {
                    ans++;
                }
            }
        }
        return ans;
    }
}