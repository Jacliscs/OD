package OD218;

import java.util.*;

/**
 * @author Jacliscs
 * @description 字符串筛选排序
 * @date 2024/3/17
 */

/**
 * 题目描述
 * 输入一个由N个大小写字母组成的字符串
 * <p>
 * 按照ASCII码值从小到大进行排序
 * <p>
 * 查找字符串中第K个最小ASCII码值的字母(k>=1)
 * <p>
 * 输出该字母所在字符串中的位置索引(字符串的第一个位置索引为0)
 * <p>
 * k如果大于字符串长度则输出最大ASCII码值的字母所在字符串的位置索引
 * <p>
 * 如果有重复字母则输出字母的最小位置索引
 * <p>
 * 输入描述
 * 第一行输入一个由大小写字母组成的字符串
 * <p>
 * 第二行输入k ，k必须大于0 ，k可以大于输入字符串的长度
 * <p>
 * 输出描述
 * 输出字符串中第k个最小ASCII码值的字母所在字符串的位置索引
 * <p>
 * k如果大于字符串长度则输出最大ASCII码值的字母所在字符串的位置索引
 * <p>
 * 如果第k个最小ASCII码值的字母存在重复 则输出该字母的最小位置索引
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //读取字符串
        String str = sc.nextLine();
        //从小到大排序第k个ASCII码
        int k = Integer.parseInt(sc.nextLine());
        System.out.println(getIndex(str, k));

    }

    //输出字符串中第k个最小ASCII码值的字母所在字符串的位置索引
    public static int getIndex(String str, int k) {
        char[] chars = str.toCharArray();
        //按ASCII码排序
        Arrays.sort(chars);
        if (k > chars.length) {
            k = chars.length;
        }
        //第k个字符是
        char target = chars[k - 1];
        //返回该字符在str中第一次出现的索引
        return str.indexOf(target);
    }
}