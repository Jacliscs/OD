package OD265;

import java.util.Scanner;
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
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //预期瑕疵度
        int n = Integer.parseInt(sc.nextLine());
        //字符串
        String str = sc.nextLine();
        System.out.println(getMaxVowel(str, n));
    }


    //寻找一个字符串中满足预期瑕疵度的最长元音子串，返回最长元音子串的长度
    public static int getMaxVowel(String s, int flaw) {
        //如果长度为1
        if (s.length() == 1) {
            if (s.charAt(0) == 'a' || s.charAt(0) == 'e' || s.charAt(0) == 'i' || s.charAt(0) == 'o' || s.charAt(0) == 'u' && getFlaw(s) == flaw) {
                return 1;
            } else {
                return 0;
            }
        }
        //双指针
        Pattern p = Pattern.compile("^[AEIOUaeiou].*[AEIOUaeiou]$");
        for (int i = 0; i < s.length(); i++) {
            for (int j = s.length(); j > i; j--) {
                String temp = s.substring(i, j);
                //一找到就返回，一定是最大长度
                if (p.matcher(temp).find() && getFlaw(temp) == flaw) {
                    return temp.length();
                }
            }
        }
        //没找到的话
        return 0;
    }

    //返回一个元音字符串中的瑕疵度
    public static long getFlaw(String s) {
        //开头和末尾一定是元音
        String newStr = s.replaceAll("[AEIOUaeiou]", "");
        return newStr.length();
    }
}