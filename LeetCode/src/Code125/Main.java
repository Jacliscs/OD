package Code125;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 验证回文串
 * @date 2024/4/30
 * @level 简单
 * @url <a href="https://leetcode.cn/problems/valid-palindrome/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    /**
     * 去掉非字母数字字符后的字符串是否是回文串
     *
     * @param s
     * @return boolean
     */
    public boolean isPalindrome(String s) {
        //所有大写转小写，去掉所有非字母数字字符
        s = s.replaceAll("[^0-9a-zA-Z]", "").toLowerCase();

        //判断是否是回文
        return check(s);
    }


    /**
     * 判断s是否是回文串
     *
     * @param s
     * @return boolean
     */
    public boolean check(String s) {
        //空字符串算回文
        if ("".equals(s) || s.isEmpty()) return true;

        StringBuilder newS = new StringBuilder(s);

        return s.contentEquals(newS.reverse());
    }

}