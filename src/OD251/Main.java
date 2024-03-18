package OD251;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * @author Jacliscs
 * @description 连续字母长度
 * @date 2024/3/17
 * @level 6
 */

/**
 * 题目描述
 * 给定一个字符串，只包含大写字母，求在包含同一字母的子串中，长度第 k 长的子串的长度，相同字母只取最长的那个子串。
 * <p>
 * 输入描述
 * 第一行有一个子串(1<长度<=100)，只包含大写字母。
 * <p>
 * 第二行为 k的值
 * <p>
 * 输出描述
 * 输出连续出现次数第k多的字母的次数。
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //输入字符串
        String str = sc.nextLine();
        //读取第k长的字符
        int k = Integer.parseInt(sc.nextLine());
        System.out.println(getKth(str, k));
    }

    //输出连续出现次数第k多的字母的次数
    public static int getKth(String str, int k) {
        if (str == null || str.isEmpty() || k < 1) {
            return -1;
        }
        //找到每个字母的连续值，并降序排列
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            //默认出现1次
            int count = 1;
            for (int j = i + 1; j < chars.length; j++) {
                if (chars[j] == c) {
                    count++;
                } else {
                    break;
                }
            }
            //如果当前没有该字母或该字母出现的次数小于当前字母出现的次数，则更新
            if (!map.containsKey(c) || map.get(c) < count) {
                map.put(c, count);
            }
        }
        //map里存放的key是字母，value是连续出现的次数，按出现次数从大到小排序
        StringJoiner sj = new StringJoiner(",");
        map.values().stream().sorted((a, b) -> b - a).forEach(s -> sj.add(String.valueOf(s)));
        String[] strings = sj.toString().split(",");
        if (k > strings.length) {
            return -1;
        } else {
            return Integer.parseInt(strings[k - 1]);
        }
    }
}