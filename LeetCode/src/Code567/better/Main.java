package Code567.better;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 字符串的排列
 * @date 2024/4/25
 * @level 中等
 * @url <a href="">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    /**
     * 滑动窗口比较
     *
     * @param s1
     * @param s2
     * @return boolean
     */
    public boolean checkInclusion(String s1, String s2) {
        //在s2中设定一个长度为n的窗口，比较窗口中的各字符数量跟s1中各字符数量是否相同
        //都是小写
        int[] cnt = new int[26];
        for (char c : s1.toCharArray()) {
            cnt[c - 'a']++;
        }

        //双指针
        int left = 0;
        for (int right = 0; right < s2.length(); right++) {
            int x = s2.charAt(right) - 'a';
            cnt[x]--;
            //是cnt[x]==0 此时窗口内的字符数量和s1相同
            while (cnt[x] < 0) {
                int y = s2.charAt(left) - 'a';
                cnt[y]++;
                left++;
            }

            //对应字符的次数相同，如果长度相同，则是子序列
            if (right - left + 1 == s1.length()) {
                return true;
            }
        }

        return false;
    }
}