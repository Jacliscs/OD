package OD371;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 万能字符单词拼写
 * @date 2024/3/20
 * @level 4
 * @score 100
 */

/**
 * 题目描述
 * 有一个字符串数组 words 和一个字符串 chars。
 * <p>
 * 假如可以用 chars 中的字母拼写出 words 中的某个“单词”（字符串），那么我们就认为你掌握了这个单词。
 * <p>
 * words 的字符仅由 a-z 英文小写字母组成，例如 "abc"
 * <p>
 * chars 由 a-z 英文小写字母和 "?" 组成。其中英文 "?" 表示万能字符，能够在拼写时当作任意一个英文字母。例如："?" 可以当作 "a" 等字母。
 * <p>
 * 注意：每次拼写时，chars 中的每个字母和万能字符都只能使用一次。
 * <p>
 * 输出词汇表 words 中你掌握的所有单词的个数。没有掌握任何单词，则输出0。
 * <p>
 * 输入描述
 * 第一行：输入数组 words 的个数，记作N。
 * <p>
 * 第二行 ~ 第N+1行：依次输入数组words的每个字符串元素
 * <p>
 * 第N+2行：输入字符串chars
 * <p>
 * 输出描述
 * 输出一个整数，表示词汇表 words 中你掌握的单词个数
 * <p>
 * 备注
 * 1 ≤ words.length ≤ 100
 * 1 ≤ words[i].length, chars.length ≤ 100
 * 所有字符串中都仅包含小写英文字母、英文问号
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //单词数组个数
        int n = Integer.parseInt(sc.nextLine());
        //单词数组
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = sc.nextLine();
        }
        //掌握的字符
        String control = sc.nextLine();
        //输出能拼写出来的单词个数
        System.out.println(getResult(words, control));
    }

    //输出能用掌握的单词拼写出单词的个数
    public static int getResult(String[] words, String control) {
        int count = 0;
        for (int i = 0; i < words.length; i++) {
            if (success(words[i], control)) {
                count++;
            }
        }
        return count;
    }

    //判断word能否被已掌握的字符拼写出
    public static boolean success(String word, String control) {
        //统计control中?的个数
        char[] chars = control.toCharArray();
        int n = chars.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (chars[i] == '?') {
                count++;
            }
        }
        char[] words = word.toCharArray();
        int m = words.length;
        for (int i = 0; i < m; i++) {
            //如果字符不在已掌握的字符里或没有多余?号，则返回false
            if (!control.contains(words[i] + "")) {
                //判断是否还有?
                if (count > 0) {
                    count--;
                } else {
                    return false;
                }
            } else {
                //每个字符只能用一次，所以需要把匹配到的字符去掉
                control = control.replaceFirst(words[i] + "", "");
            }
        }
        return true;
    }

}