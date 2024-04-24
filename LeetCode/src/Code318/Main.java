package Code318;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 最大单词长度乘积
 * @date 2024/4/24
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/maximum-product-of-word-lengths/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public int maxProduct(String[] words) {
        //按单词长度降序
        Arrays.sort(words, (a, b) -> b.length() - a.length());

        //数组长度
        int n = words.length;

        //最大长度
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                //如果不含相同字母且长度乘积更大，则更新
                if (isDiff(words[i], words[j]) && words[i].length() * words[j].length() > maxLen) {
                    maxLen = words[i].length() * words[j].length();
                }
            }
        }

        return maxLen;

    }


    /**
     * 判断两个字符串是否含有相同字符 仅小写
     *
     * @param s1
     * @param s2
     * @return boolean
     */
    public static boolean isDiff(String s1, String s2) {
        for (int i = 0; i < s1.length(); i++) {
            if (s2.contains(s1.charAt(i) + "")) {
                return false;
            }
        }
        return true;
    }
}