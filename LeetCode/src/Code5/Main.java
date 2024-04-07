package Code5;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 最长回文子串
 * @date 2024/4/7
 * @level 中等
 * @score
 * @url https://leetcode.cn/problems/longest-palindromic-substring/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public String longestPalindrome(String s) {
        //如果s为null或者长度为1，则返回自身
        if (s == null || s.length() == 1) {
            return s;
        }

        //dp[i][j]表示s[i....j]是否是回文子串，动态规划
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        //初始化所有单个字符都是回文串 后面l,r不会相等，当r-l<=2时且两端相等，就可以判定是回文串了
        //for (int i = 0; i < n; i++){
        //    dp[i][i] = true;
        //}

        //最大回文串的起点、终点
        int start = 0;
        int end = 0;
        int maxLen = 1;

        //动态规划 dp[i][j]是回文串必须dp[i+1][j-1]也是回文串且s[i]==s[j]
        for (int r = 1; r < n; r++) {
            for (int l = 0; l < r; l++) {
                //2个或者3个字符时，如果左右边界相等，则一定是回文即r-l<=2 大于3个字符则需要用dp[l+1][r-1]
                if (s.charAt(l) == s.charAt(r) && (r - l <= 2 || dp[l + 1][r - 1])) {
                    //标记状态
                    dp[l][r] = true;
                    //如果长度大于当前最大回文串，则更新长度和位置
                    if (r - l + 1 > maxLen) {
                        maxLen = r - l + 1;
                        //[l,r]是回文串，闭区间
                        start = l;
                        end = r;
                    }
                }
            }
        }
        //[l,r]闭区间是最大回文串
        return s.substring(start, end + 1);
    }
}