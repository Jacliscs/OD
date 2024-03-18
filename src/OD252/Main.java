package OD252;

import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 拼接URL
 * @date 2024/3/17
 * @level 6
 */

/**
 * 题目描述
 * 给定一个url前缀和url后缀,通过,分割 需要将其连接为一个完整的url
 * <p>
 * 如果前缀结尾和后缀开头都没有/，需要自动补上/连接符
 * 如果前缀结尾和后缀开头都为/，需要自动去重
 * 约束：不用考虑前后缀URL不合法情况
 * <p>
 * 输入描述
 * url前缀(一个长度小于100的字符串)，url后缀(一个长度小于100的字符串)
 * <p>
 * 输出描述
 * 拼接后的url
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //读取字符串、
        String[] url = sc.nextLine().split(",");
        //可能会出现 ","->[]   "1,"->["1"]  ",1"->["","1"]
        String prefix = url.length > 0 ? url[0] : "";
        String suffix = url.length > 1 ? url[1] : "";
        System.out.println(joinUrl(prefix, suffix));
    }

    //拼接url
    public static String joinUrl(String prefix, String suffix) {
        //把前缀后面的一个或多个/去掉 $表示末尾 +表示一个或多个
        prefix = prefix.replaceAll("/+$", "");
        //把后缀前面的一个或多个/去掉 ^表示开头
        suffix = suffix.replaceAll("^/+", "");
        //用字符/连接
        return prefix + "/" + suffix;
    }
}