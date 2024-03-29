package OD343;

import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 最长子字符串的长度（一）
 * @date 2024/3/16
 * @level 6
 * @type 逻辑分析
 */

/**
 * 题目描述
 * 给你一个字符串 s，首尾相连成一个环形，请你在环中找出 'o' 字符出现了偶数次最长子字符串的长度。
 * <p>
 * 输入描述
 * 输入是一个小写字母组成的字符串
 * <p>
 * 输出描述
 * 输出是一个整数
 * <p>
 * 备注
 * 1 ≤ s.length ≤ 500000
 * s 只包含小写英文字母
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 读入数据
        String s = sc.next();
        System.out.println(getMaxString(s));
    }

    /**
     * 返回字符串中包含偶数个o的最长子字符串的长度
     * @create 2024/3/16 17:31
     * @param s
     * @return int
     */
    public static int getMaxString(String s) {
        // 字符串长度
        int n = s.length();
        // 统计字符串中'o'的个数
        int count = 0;
        //遍历字符，统计o的个数
        for(int i = 0; i < n; i++){
            if(s.charAt(i) == 'o'){
                count++;
            }
        }
        //如果是偶数，则本身就是最大
        if(count % 2 == 0){
            return n;
        }else {
            return n - 1;
        }
    }
}