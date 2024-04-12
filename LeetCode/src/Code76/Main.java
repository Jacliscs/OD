package Code76;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 最小覆盖子串
 * @date 2024/4/12
 * @level 困难
 * @score
 * @url https://leetcode.cn/problems/minimum-window-substring/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    /**
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     *
     * @param s
     * @param t
     * @return java.lang.String
     */
    public String minWindow(String s, String t) {
        //需要覆盖的字符个数
        int total = t.length();

        //统计t中字符出现的次数
        int[] count = new int[128];
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            count[c]++;
        }

        //左右边界
        int l = 0, r = 0;
        //最小能覆盖住t的长度
        int minLen = s.length() + 1;

        //能覆盖住t的子串在s中的起始位置
        int start = 0;

        while (r < s.length()) {
            char c = s.charAt(r);

            //count[c]不管是否大于0都-1，如果减之前>0，则说明是需要覆盖的字符
            if (count[c]-- > 0) {
                //需要的总次数-1
                total--;
            }

            //如果已经覆盖了，但不一定是最短的
            while (total == 0) {
                if (minLen > r - l + 1) {
                    //更新最短覆盖长度
                    minLen = r - l + 1;
                    //更新起始位置
                    start = l;
                }
                //移动窗口的左边界
                char temp = s.charAt(l);
                if (count[temp]++ == 0) {
                    //如果是需要覆盖的字符，则需要覆盖的总次数+1
                    total++;
                }
                l++;
            }
            //右边界移动
            r++;
        }

        //如果minLen的长度没有改变，则说明无法覆盖，返回空字符串
        return minLen < s.length() + 1 ? s.substring(start, start + minLen) : "";
    }
}