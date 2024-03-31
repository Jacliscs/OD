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
 * @score 100
 * @url https://hydro.ac/d/HWOD2023/p/OD251
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
        System.out.println(getResult(str, k));

    }

    public static int getResult(String s, int k) {
        //异常处理
        if (k <= 0) return -1;

        //给s末尾拼接一个0，方便后续比较前后字符是否相同，存入最后一个字符的状态
        s += "0";

        //找出连续字母的最大长度
        char[] chs = s.toCharArray();

        //存放不同字符的最大长度
        HashMap<Character, Integer> map = new HashMap<>();

        //初始状态 上一字符及该字符对应的最大长度
        char pre = chs[0];
        int len = 1;

        for (int i = 1; i < chs.length; i++) {
            //当前字符
            char cur = chs[i];
            if (cur == pre) {
                len++;
            } else {
                //如果当前字符跟上一个不相等，则判断map中是否不存在、或已存在但长度不是最长
                if (!map.containsKey(pre) || map.containsKey(pre) && len > map.get(pre)) {
                    map.put(pre, len);
                }
                //刷新状态
                pre = cur;
                len = 1;
            }
        }

        //只需要用到字符出现的次数排序 降序
        Integer[] arr = map.values().stream().sorted((a, b) -> b - a).toArray(Integer[]::new);

        //无效输出
        if (k > arr.length) return -1;
        else return arr[k - 1];
    }
}