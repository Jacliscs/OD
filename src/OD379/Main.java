package OD379;

import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 密码解密
 * @date 2024/3/17
 */

/**
 * 题目描述
 * 给定一段“密文”字符串 s，其中字符都是经过“密码本”映射的，现需要将“密文”解密并输出。
 * <p>
 * 映射的规则（'a' ~ 'i'）分别用（'1' ~ '9'）表示；（'j' ~ 'z'）分别用（"10*" ~ "26*"）表示。
 * <p>
 * 约束：映射始终唯一。
 * <p>
 * 输入描述
 * “密文”字符串
 * <p>
 * 输出描述
 * 明文字符串
 * <p>
 * 备注
 * 翻译后的文本长度在100以内
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(code(s));
    }

    //映射密码
    public static String code(String s) {
        //必须逆序从26*开始，到1结束，不然会出现错误
        for (int i = 26; i >= 1; i--) {
            String key = i + (i < 10 ? "" : "\\*");
            String value = String.valueOf((char) ('a' + i - 1));
            s = s.replaceAll(key, value);
        }
        return s;
    }
}