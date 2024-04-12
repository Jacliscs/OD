package OD345;

import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 密码输入检测
 * @date 2024/3/16
 * @level 6
 * @type 数据结构、栈
 */

/**
 * 题目描述
 * 给定用户密码输入流 input，输入流中字符 '<' 表示退格，可以清除前一个输入的字符，请你编写程序，输出最终得到的密码字符，并判断密码是否满足如下的密码安全要求。
 * <p>
 * 密码安全要求如下：
 * <p>
 * 密码长度 ≥ 8；
 * 密码至少需要包含 1 个大写字母；
 * 密码至少需要包含 1 个小写字母；
 * 密码至少需要包含 1 个数字；
 * 密码至少需要包含 1 个字母和数字以外的非空白特殊字符；
 * 注意空串退格后仍然为空串，且用户输入的字符串不包含 '<' 字符和空白字符。
 * <p>
 * 输入描述
 * 用一行字符串表示输入的用户数据，输入的字符串中 '<' 字符标识退格，用户输入的字符串不包含空白字符，例如：
 * <p>
 * ABC<c89%000<
 * <p>
 * 输出描述
 * 输出经过程序处理后，输出的实际密码字符串，并输出改密码字符串是否满足密码安全要求。两者间由 ',' 分隔， 例如：
 * <p>
 * ABc89%00,true
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(check(s));
    }

    // 判断输入的密码是否合法
    public static String check(String s) {
        int n = s.length();
        StringBuilder  sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != '<'){
                sb.append(s.charAt(i));
            }else {
                //遇到"<"时，删除最后一个字符
                if (sb.isEmpty()){
                    continue;
                }else {
                    //删除最后一位
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
        String newPassword = sb.toString();
        //需要检测的长度、大小写、数字、符号
        int len = newPassword.length();
        int upper = 0;
        int lower = 0;
        int num = 0;
        int other = 0;
        //遍历
        for (int i = 0; i < len; i++) {
            char a = newPassword.charAt(i);
            if (a >= 'A' && a <= 'Z'){
                upper++;
            }else if (a >= 'a' && a <= 'z'){
                lower++;
            }else if (a >= '0' && a <= '9'){
                num++;
            }else {
                other++;
            }
        }
        if (upper >= 1 && lower >= 1 && num >= 1 && other >= 1 && len >= 8) {
            return newPassword + ",true";
        }else {
            return newPassword + ",false";
        }
    }
}