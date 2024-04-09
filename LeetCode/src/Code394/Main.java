package Code394;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 浮生
 * @description 字符串解码
 * @date 2024/4/9
 * @level 中等
 * @score
 * @url https://leetcode.cn/problems/decode-string/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public String decodeString(String s) {
        s = s.replaceAll("\\[", "<")
                .replaceAll("]", ">");

        //从最内层开始替换
        Pattern p = Pattern.compile("(\\d+)<([^<>]+)>");
        Matcher m = p.matcher(s);
        while (m.find()) {
            //整体需要替换的
            String replace = m.group(0);

            //重复次数
            int num = Integer.parseInt(m.group(1));
            //重复内容
            String tmp = m.group(2);
            //替换内容
            StringBuilder sb = new StringBuilder();
            while (num > 0) {
                sb.append(tmp);
                num--;
            }

            //把原字符串中的替换
            s = s.replace(replace, sb.toString());

            //刷新正则匹配
            m = p.matcher(s);
        }

        return s;
    }
}