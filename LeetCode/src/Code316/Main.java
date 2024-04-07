package Code316;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 去除重复字母
 * @date 2024/4/7
 * @level 中等
 * @score
 * @url https://leetcode.cn/problems/remove-duplicate-letters/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public String removeDuplicateLetters(String s) {
        char[] ss = s.toCharArray();
        //存储每个字符还剩余的次数 remain[0]即'a'的次数
        int[] remain = new int[26];
        for (char c : ss) {
            remain[c - 'a']++;
        }

        //存放结果
        StringBuilder ans = new StringBuilder();
        //记录字符是否已经添加到结果中
        boolean[] inAns = new boolean[26];
        for (char c : ss) {
            //剩余次数-1
            remain[c - 'a']--;
            //如果该字符已经在ans中，则跳过
            if (inAns[c - 'a']) continue;

            //如果c小于ans中最后一个字符x，如果后面x还会出现，则可以去掉x
            while (!ans.isEmpty() && c < ans.charAt(ans.length() - 1) && remain[ans.charAt(ans.length() - 1) - 'a'] > 0) {
                //将x移出ans，状态标记为false
                inAns[ans.charAt(ans.length() - 1) - 'a'] = false;
                ans.deleteCharAt(ans.length() - 1);
            }
            //添加c到末尾
            ans.append(c);
            //修改c的状态 标记c已经在ans中
            inAns[c - 'a'] = true;
        }
        return ans.toString();
    }
}