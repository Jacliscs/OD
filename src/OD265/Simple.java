package OD265;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author Jacliscs
 * @description 最长的指定瑕疵度的元音子串
 * @date 2024/3/21
 * @level 6
 * @score 100
 */

/**
 * 题目描述
 * 开头和结尾都是元音字母（aeiouAEIOU）的字符串为元音字符串，其中混杂的非元音字母数量为其瑕疵度。比如:
 * <p>
 * “a” 、 “aa”是元音字符串，其瑕疵度都为0
 * “aiur”不是元音字符串（结尾不是元音字符）
 * “abira”是元音字符串，其瑕疵度为2
 * 给定一个字符串，请找出指定瑕疵度的最长元音字符子串，并输出其长度，如果找不到满足条件的元音字符子串，输出0。
 * <p>
 * 子串：字符串中任意个连续的字符组成的子序列称为该字符串的子串。
 * <p>
 * 输入描述
 * 首行输入是一个整数，表示预期的瑕疵度flaw，取值范围[0, 65535]。
 * <p>
 * 接下来一行是一个仅由字符a-z和A-Z组成的字符串，字符串长度(0, 65535]。
 * <p>
 * 输出描述
 * 输出为一个整数，代表满足条件的元音字符子串的长度。
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Simple {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //预期瑕疵度
        long n = Integer.parseInt(sc.nextLine());
        //字符串
        String str = sc.nextLine();
        System.out.println(getMaxVowel(str, n));
    }


    //寻找一个字符串中满足预期瑕疵度的最长元音子串，返回最长元音子串的长度
    public static long getMaxVowel(String s, long flaw) {
        //所有元音字母
        char[] vowels = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        //添加到set中
        Set<Character> vowelSet = new HashSet<>();
        for (char c : vowels) {
            vowelSet.add(c);
        }
        //存放字符串s中元音字符的下标
        ArrayList<Integer> vowelIndex = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //如果set中含有c，则记录该元音字母的下标
            if (vowelSet.contains(c)) {
                vowelIndex.add(i);
            }
        }
        //保存瑕疵度相同的最长元音子串长度
        int maxLen = 0;
        int n = vowelIndex.size();
        //双指针
        int left = 0;
        int right = 0;
        while (right < n) {
            //瑕疵度 后一个元音字母在s中的下标-前一个元音字母在s中的下标 - left与right中间的元音字母数量
            int diff = vowelIndex.get(right) - vowelIndex.get(left) - (right - left);
            //判断瑕疵度
            if (diff < flaw) {
                right++;
            } else if (diff > flaw) {
                left++;
            } else {
                //与预期瑕疵度相等
                maxLen = Math.max(maxLen, vowelIndex.get(right) - vowelIndex.get(left) + 1);
                //right++有可能瑕疵度也相等，但长度+1
                right++;
            }
        }
        return maxLen;
    }

}