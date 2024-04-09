package Code1961;

import java.security.spec.EncodedKeySpec;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 检查字符串是否为数组前缀
 * @date 2024/4/9
 * @level 简单
 * @score
 * @url https://leetcode.cn/problems/check-if-string-is-a-prefix-of-array/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public boolean isPrefixString(String s, String[] words) {
        String res = "";

        for (String word : words) {
            res += word;
            if (res.equals(s)) return true;
            else if (res.length() > s.length()) return false;
        }
        //已经拼写完了，但res!=s
        return false;
    }
}