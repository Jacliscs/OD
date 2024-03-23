package OD153;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 求满足条件的最长子串的长度
 * @date 2024/3/22
 * @level 6
 * @score 100
 */

/**
 * 题目描述
 * 给定一个字符串，只包含字母和数字，按要求找出字符串中的最长（连续）子串的长度，字符串本身是其最长的子串，子串要求：
 * <p>
 * 1、 只包含1个字母(a~z, A~Z)，其余必须是数字；
 * <p>
 * 2、 字母可以在子串中的任意位置；
 * <p>
 * 如果找不到满足要求的子串，如全是字母或全是数字，则返回-1。
 * <p>
 * 输入描述
 * 字符串(只包含字母和数字)
 * <p>
 * 输出描述
 * 子串的长度
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //读取输入字符串
        String str = sc.nextLine();
        System.out.println(getResult(str));

    }

    //寻找只包含一个数字的最长子串长度，没有则返回-1
    public static int getResult(String s) {
        int n = s.length();
        //存放数字的索引
        //双指针：从0开始，右指针往右移动，遇到字母就+1，并记录下标，左指针移动到上一个字母下标+1处
        int left = 0;
        int right = 0;
        int maxLen = -1;
        LinkedList<Integer> lastLetter = new LinkedList<>();
        int countLetter = 0;
        while (right < n) {
            char c = s.charAt(right);
            //如果是字母，则记录下标
            if (Character.isLetter(c)) {
                countLetter++;
                lastLetter.add(right);
                //如果超过1个字母，则左指针移动
                if (countLetter > 1) {
                    left = lastLetter.removeFirst() + 1;
                    countLetter--;
                }
                if (left == right) {
                    right++;
                    continue;
                }
            }
            //此时left到right之间一定最多一个字母
            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }
        //如果没有字母，则返回-1
        if (countLetter == 0) {
            return -1;
        }
        //否则返回maxLen
        return maxLen;
    }
}