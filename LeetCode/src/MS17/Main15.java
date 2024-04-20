package MS17;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 最长单词
 * @date 2024/4/20
 * @level 中等
 * @score
 * @url https://leetcode.cn/problems/longest-word-lcci/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main15 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    /**
     * 给定一组单词words，编写一个程序，找出其中的最长单词，且该单词由这组单词中的其他单词组合而成。
     * 若有多个长度相同的结果，返回其中字典序最小的一项，若没有符合要求的单词则返回空字符串。
     *
     * @param words
     * @return java.lang.String
     */
    public String longestWord(String[] words) {
        //先排序，长度降序，长度相同则字典序升序
        Arrays.sort(words, (a, b) -> a.length() == b.length() ? a.compareTo(b) : b.length() - a.length());

        //使用set存放单词
        HashSet<String> set = new HashSet<>(Arrays.asList(words));

        //从第一个开始找，找到的第一个即是最优的：长度最长或长度相同字典序更小
        for (String word : words) {
            //移出自身，然后看其余set能否组成自己
            set.remove(word);

            //如果能组成，则返回
            if (find(set, word)) return word;
        }

        //无法找到，返回空字符串
        return "";
    }

    /**
     * word能否被set中的单词组成
     *
     * @param set
     * @param word
     * @return boolean
     */
    public static boolean find(HashSet<String> set, String word) {
        //如果为空字符串，则返回true
        if (word.length() == 0) return true;

        //
        for (int i = 0; i < word.length(); i++) {
            //word的[0,i]能否被找到，递归后面部分也能找到，则返回true
            if (set.contains(word.substring(0, i + 1)) && find(set, word.substring(i + 1))) {
                return true;
            }
        }
        //否则，无法组成
        return false;
    }
}