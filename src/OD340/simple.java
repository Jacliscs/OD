package OD340;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Jacliscs
 * @description 简单版
 * @date 2024/3/18
 * @level 7
 * @score 100
 * @type 双指针、正则表达式、栈
 */

/**
 * 题目描述
 * 提取字符串中的最长合法简单数学表达式，字符串长度最长的，并计算表达式的值。如果没有，则返回 0
 * <p>
 * 简单数学表达式只能包含以下内容：
 * <p>
 * 0-9数字，符号+-*
 * 说明：
 * <p>
 * 所有数字，计算结果都不超过long
 * 如果有多个长度一样的，请返回第一个表达式的结果
 * 数学表达式，必须是最长的，合法的
 * 操作符不能连续出现，如 +--+1 是不合法的
 * 输入描述
 * 字符串
 * <p>
 * 输出描述
 * 表达式值
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class simple {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        //简单合法表达式为只有两个操作数 如 1+1 -1+1 +1+1都是合法的
        //正则包含三部分：开头^(含有0个或1个符号的数字1个到多个) (操作符+-*) (数字1个到多个)$结尾
        Pattern pattern = Pattern.compile("^([+-]?\\d+)([+*-])(\\d+)$");
        int maxLength = 0;
        long sum = 0;
        //遍历
        for (int i = 0; i < str.length(); i++) {
            for (int j = i; j < str.length(); j++) {
                String sub = str.substring(i, j + 1);
                Matcher matcher = pattern.matcher(sub);
                //如果匹配到且长度大于当前保存的长度，则更新
                if (matcher.find() && sub.length() > maxLength) {
                    maxLength = sub.length();
                    //求解 使用matcher.group(1)和matcher.group(3)分别获取两个数字
                    long num1 = Long.parseLong(matcher.group(1));
                    long num2 = Long.parseLong(matcher.group(3));
                    //操作符
                    String sign = matcher.group(2);
                    switch (sign) {
                        case "+":
                            sum = num1 + num2;
                            break;
                        case "-":
                            sum = num1 - num2;
                            break;
                        case "*":
                            sum = num1 * num2;
                            break;
                    }

                }
            }
        }
        System.out.println(sum);
    }
}