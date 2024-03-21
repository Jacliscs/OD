package OD258;

import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 字符串序列判定
 * @date 2024/3/21
 * @level 6
 * @score 100
 */

/**
 * 题目描述
 * 输入两个字符串S和L，都只包含英文小写字母。S长度<=100，L长度<=500,000。
 * <p>
 * 判定S是否是L的有效子串。
 * <p>
 * 判定规则：
 * <p>
 * S中的每个字符在L中都能找到（可以不连续），且S在Ｌ中字符的前后顺序与S中顺序要保持一致。
 * <p>
 * （例如，S=”ace”是L=”abcde”的一个子序列且有效字符是a、c、e，而”aec”不是有效子序列，且有效字符只有a、e）
 * <p>
 * 输入描述
 * 输入两个字符串S和L，都只包含英文小写字母。S长度<=100，L长度<=500,000。
 * <p>
 * 先输入S，再输入L，每个字符串占一行。
 * <p>
 * 输出描述
 * S串最后一个有效字符在L中的位置。（首位从0开始计算，无有效字符返回-1）
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //字符串s
        String s = sc.nextLine();
        //字符串l
        String l = sc.nextLine();
        System.out.println(getResult(s, l));

    }

    //返回字符串s最后一个有效字符在字符串l中的下标
    public static int getResult(String s, String l) {
        char[] ss = s.toCharArray();
        char[] ls = l.toCharArray();
        //双指针
        int ans = -1;
        int i = 0;
        int j = 0;
        while (i < ss.length && j < ls.length) {
            //如果相同，则i++ j++
            if (ss[i] == ls[j]) {
                ans = j;
                i++;
                j++;
            } else {
                j++;
            }
        }

        return ans;
    }

}