package OD268;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 字符串变换最小字符串
 * @date 2024/3/17
 */

/**
 * 题目描述
 * 给定一个字符串s，最多只能进行一次变换，返回变换后能得到的最小字符串（按照字典序进行比较）。
 * <p>
 * 变换规则：交换字符串中任意两个不同位置的字符。
 * <p>
 * 输入描述
 * 一串小写字母组成的字符串s
 * <p>
 * 输出描述
 * 一串小写字母组成的字符串s
 * <p>
 * 备注
 * s是都是小写字符组成
 * 1 ≤ s.length ≤ 1000
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(transform(sc.next()));
    }

    //做一次变换，返回变换后的字符串
    public static String transform(String s) {
        char[] minStr = s.toCharArray();
        Arrays.sort(minStr);

        String temp = new String(minStr);
        if (temp.equals(s)) {
            //自身就是最小值
            return s;
        }
        //如 afcbbb 变换为 abcbbf 把第一个跟minStr出现的不同字符变换到最后一个出现b的index处
        char[] result = s.toCharArray();

        for (int i = 0; i < s.length(); i++) {
            if (result[i] != minStr[i]) {
                //把第一个非最小排序的值暂存
                char tempChar = result[i];
                //把该位置变为排序后最小时对应的值
                result[i] = minStr[i];
                //找到该值出现在原字符串中的最后索引
                int index = s.lastIndexOf(minStr[i]);
                //把temp交换到该值最后出现的位置上
                result[index] = tempChar;
                //交换一次就跳出
                break;
            }
        }
        return new String(result);

    }
}