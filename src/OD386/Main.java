package OD386;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 文本统计分析
 * @date 2024/3/19
 * @level 7
 * @score 100
 */


/**
 * 题目描述
 * 有一个文件，包含以一定规则写作的文本，请统计文件中包含的文本数量。
 * <p>
 * 规则如下：
 * <p>
 * 文本以 ";" 分隔，最后一条可以没有 ";" ，但空文本不能算语句，比如
 * <p>
 * COMMAND A; ;
 * <p>
 * 只能算一条语句。 注意，无字符/空白字符/制表符都算作"空"文本；
 * <p>
 * 文本可以跨行，比如下面，是一条文本，而不是三条
 * <p>
 * COMMAND A
 * <p>
 * AND
 * <p>
 * COMMAND B;
 * <p>
 * 文本支持字符串，字符串为成对的单引号(')或者成对的双引号(")，字符串可能出现用转义字符(\)处理的单双引号("your input is\"")和转义字符本身，比如
 * <p>
 * COMMAND A "Say \"hello\"";
 * <p>
 * 支持注释，可以出现在字符串之外的任意位置注释以”--“开头，到换行结束，比如
 * <p>
 * COMMAND A; -- this is comment
 * <p>
 * COMMAND -- comment
 * <p>
 * A AND COMMAND B;
 * <p>
 * 注意字符串内的”--“，不是注释。
 * <p>
 * 输入描述
 * 文本文件
 * <p>
 * 输出描述
 * 包含的文本数量
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> lines = new LinkedList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            //判空，退出循环
            if (line.equals("")) {
                break;
            }
            lines.add(line);
        }
        System.out.println(countText(lines));

    }

    //处理文本 返回有效文本的条数
    public static int countText(List<String> lines) {
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            //按行遍历 将可能造成误会的 如\' \" 等替换为普通文本
            //先把文本中的转义字符\" 替换为普通字符a
            line = line.replaceAll("\\[\"]", "a")
                    //把双引号中的内容替换为普通文本 ?表示非贪婪模式：匹配最短的
                    .replaceAll("\"(.*?)\"", "s")
                    //把单引号括起来的内容替换为普通文本
                    .replaceAll("'(.*?)'", "d")
                    //把注释替换为空
                    .replaceAll("--.+", "")
                    //把空格替换为空
                    .replaceAll("\\s+", "");

            sb.append(line);
        }
        //结尾可以有分号，也可以没有，直接加一个，最后去重
        sb.append(";");
        //去重和去掉开头的分号 如;aaaa;;bbb
        String s = sb.toString().replaceAll(";+", ";").replaceAll("^;", "");
        //现在有效分号个数就是有效文本条数
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ';') {
                count++;
            }
        }
        return count;
    }
}