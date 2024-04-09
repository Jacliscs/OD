package Code14;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 最长公共前缀
 * @date 2024/4/9
 * @level 简单
 * @score
 * @url https://leetcode.cn/problems/longest-common-prefix/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public String longestCommonPrefix(String[] strs) {
        //最长公共前缀
        String ans = "";

        //第一个元素
        String s1 = strs[0];

        for (int i = 0; i < s1.length(); i++) {
            String tmp = ans + s1.charAt(i);
            //如果有字符不以tmp为前缀，则返回ans
            for (String str : strs) {
                if (!str.startsWith(tmp)) {
                    return ans;
                }
            }
            //否则，更新前缀长度
            ans = tmp;
        }
        return ans;
    }
}