package OD356;

import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 分隔均衡字符串
 * @date 2024/3/20
 * @level 4
 * @score 100
 */

/**
 * 题目描述
 * 均衡串定义：字符串中只包含两种字符，且这两种字符的个数相同。
 * <p>
 * 给定一个均衡字符串，请给出可分割成新的均衡子串的最大个数。
 * <p>
 * 约定：字符串中只包含大写的 X 和 Y 两种字符。
 * <p>
 * 输入描述
 * 输入一个均衡串。
 * <p>
 * 字符串的长度：[2， 10000]。
 * 给定的字符串均为均衡字符串
 * 输出描述
 * 输出可分割成新的均衡子串的最大个数。
 * <p>
 * 备注
 * 分割后的子串，是原字符串的连续子串
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //原始均衡串 X Y个数相同
        String str = sc.nextLine();
        int countX = 0;
        int countY = 0;
        //可分割的最多子均衡串 每遇到一次XY数量相等，就是一个子均衡串
        int ans = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'X') {
                countX++;
            } else {
                countY++;
            }
            if (countX == countY) {
                ans++;
            }
        }
        System.out.println(ans);

    }
}