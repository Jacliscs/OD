package Code522;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 最长特殊序列II
 * @date 2024/5/1
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/longest-uncommon-subsequence-ii/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    /**
     * 找最长的一个不能被当成子串的字符串
     *
     * @param strs
     * @return int
     */
    public int findLUSlength(String[] strs) {
        int n = strs.length;

        //题解：最长特殊序列的长度
        int ans = -1;

        //双重循环 每次用i去比较j，i是否是j的子串，如果i不是其他所有字符串的子串，则i本身是一个特殊序列
        for (int i = 0; i < n; i++) {
            //当前字符串长度
            int len = strs[i].length();

            //如果当前字符串长度已经小于当前的特殊序列长度，则不用遍历i
            if (len <= ans) continue;

            //i是否是特殊序列
            boolean isSpecial = true;

            //与其他字符比较
            for (int j = 0; j < n; j++) {
                //跳过自身
                if (j == i) continue;

                //j长度<i长度 则i一定不是j的子串
                if (strs[j].length() < len) continue;

                //如果i是j的子序列，则i不是特殊序列
                if (check(strs[i], strs[j])) {
                    isSpecial = false;
                    //结束本轮循环，比较下一个i
                    break;
                }
            }

            //如果i是特殊序列，则更新ans
            if (isSpecial) {
                ans = Math.max(ans, len);
            }
        }

        return ans;

    }

    /**
     * 判断s1是否是s2的子序列
     *
     * @param s1
     * @param s2
     * @return boolean
     */
    public static boolean check(String s1, String s2) {
        int i = 0, j = 0;
        while (i < s1.length() && j < s2.length()) {
            if (s1.charAt(i) == s2.charAt(j)) {
                i++;
            }
            j++;
        }
        //如果s1是s2的子序列，则i遍历完最后一个字符后，i=s1.length
        return i == s1.length();

    }

}