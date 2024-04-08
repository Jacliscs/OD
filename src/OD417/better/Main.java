package OD417.better;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 浮生
 * @description 火星文计算2
 * @date 2024/4/8
 * @level 6
 * @score 100
 * @url https://hydro.ac/d/HWOD2023/p/OD417
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //读取输入的字符串 由输入保证一定合法
        String str = sc.nextLine();
        //获得结果
        System.out.println(getResult(str));

    }

    //获得火星文计算的结果
    public static long getResult(String str) {
        //#运算优先级更高 x#y = 4*x+3*y+2
        //$运算最后做 x$y = 2*x+y+3

        //输入一定合法，运算符一定会有两个操作数 匹配所有的#运算
        Pattern p = Pattern.compile("(\\d+)#(\\d+)");

        //只要还有#运算，则先把#运算结果计算出来，替换掉原字符串
        while (true) {
            Matcher m = p.matcher(str);
            //如果没有#运算了，则跳出
            if (!m.find()) break;

            //group(0) 表示匹配的整个字符串 如 "123#15"
            String tmp = m.group(0);
            //group(1) 和 group(2) 是正则中的括号()包裹的部分
            long x = Long.parseLong(m.group(1));
            long y = Long.parseLong(m.group(2));

            //先把#运算做了，从左到右依次替换
            str = str.replaceFirst(tmp, 4 * x + 3 * y + 2 + "");
        }

        //现在剩的str部分全是$运算 如 1$23$333
        //从左到右依次计算
        return Arrays.stream(str.split("\\$"))
                .map(Long::parseLong)
                .reduce((x, y) -> 2 * x + y + 3)
                .orElse(0L);
    }
}