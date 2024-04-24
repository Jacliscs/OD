package Code318.better;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 最大单词长度乘积
 * @date 2024/4/24
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/maximum-product-of-word-lengths/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution2 {
    /**
     * 位运算表示每个单词中字母出现的情况
     *
     * @param words
     * @return int
     */
    public int maxProduct(String[] words) {
        int len = words.length;

        //mask[i]表示word[i]中单词出现的情况
        int[] mask = new int[len];

        for (int i = 0; i < len; i++) {
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                // |= 将mask[i]与右边的二进制异或运算，有1则为1，否则为0
                mask[i] |= 1 << (word.charAt(j) - 'a');
                //如word是abc，则对应的mask='00..000111'
            }
        }

        //最大长度
        int maxLen = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                //如果两个单词没有相同的字母，则可以相乘
                // & 按位与运算，两个都为1则为1，否则为0
                if ((mask[i] & mask[j]) == 0) {
                    maxLen = Math.max(maxLen, words[i].length() * words[j].length());
                }
            }
        }

        return maxLen;
    }
}