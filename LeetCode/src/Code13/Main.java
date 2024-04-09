package Code13;

import java.util.HashMap;
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
        int n = s.length();

        //存放对应字符的数字
        HashMap<Character, Integer> map = new HashMap<>() {
            {
                put('I', 1);
                put('V', 5);
                put('X', 10);
                put('L', 50);
                put('C', 100);
                put('D', 500);
                put('M', 1000);
            }
        };

        for (int i = 0; i < n; i++) {
            int cur = map.get(s.charAt(i));
            //比较下一位数字与当前位置的大小关系
            if (i + 1 < n && cur < map.get(s.charAt(i + 1))) {
                //小于则减去cur
                ans -= cur;
            } else {
                //大于等于则加上cur
                ans += cur;
            }
        }

        return ans;
    }
}