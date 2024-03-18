package OD154;

import java.util.Scanner;
import java.util.StringJoiner;

/**
 * @author Jacliscs
 * @description 字符串分隔（二）
 * @date 2024/3/17
 * @level 6
 */

/**
 * 题目描述
 * 给定一个非空字符串S，其被N个‘-’分隔成N+1的子串，给定正整数K，要求除第一个子串外，其余的子串每K个字符组成新的子串，并用‘-’分隔。
 * <p>
 * 对于新组成的每一个子串，如果它含有的小写字母比大写字母多，则将这个子串的所有大写字母转换为小写字母；
 * <p>
 * 反之，如果它含有的大写字母比小写字母多，则将这个子串的所有小写字母转换为大写字母；大小写字母的数量相等时，不做转换。
 * <p>
 * 输入描述
 * 输入为两行，第一行为参数K，第二行为字符串S。
 * <p>
 * 输出描述
 * 输出转换后的字符串。
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //分隔子串的长度
        int n = Integer.parseInt(sc.nextLine());
        //原始子串
        String s = sc.nextLine();
        System.out.println(solution(s, n));
    }


    //将字符串按k长度分隔，中间添加"-"，并转换大小写
    public static String solution(String s, int k) {
        String[] strs = s.split("-");
        StringBuilder sb = new StringBuilder();
        StringJoiner  sj = new StringJoiner("-");
        //第一个直接加
        sj.add(strs[0]);

        //把剩下的字符串合并成一个新字符串
        for (int i = 1; i < strs.length; i++) {
            sb.append(strs[i]);
        }
        String newStr = sb.toString();
        //把剩下的字符串按k个分组，最后一组是到末尾结束
        for (int i = 0; i < newStr.length(); i += k) {
            String sub = newStr.substring(i, Math.min(i + k, newStr.length()));
            //转换大小写后添加进sj
            sj.add(change(sub));
        }

        return sj.toString();
    }

    //将字符串根据大小写数量转换大小写
    public static String change(String s) {
        int upper = 0;
        int lower = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
                upper++;
            }else if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                lower++;
            }
        }
        //如果大写多
        if (upper > lower) {
            return s.toUpperCase();
        }else if (lower > upper) {
            return s.toLowerCase();
        }else {
            return s;
        }
    }
}