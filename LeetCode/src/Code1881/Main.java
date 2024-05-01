package Code1881;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 插入后的最大值
 * @date 2024/5/1
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/maximum-value-after-insertion/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public String maxValue(String n, int x) {
        //如果n是整数 则x插入到第一个小于等于x的前面
        //如果n是负数 则x插入到第一个大于等于x的前面
        //没找到就都插入到最后
        StringBuilder sb = new StringBuilder();

        //插入位置
        int insert = 0;

        if (n.charAt(0) == '-') {
            //如果是负数，则从1开始遍历n
            insert++;

            //找到第一个大于x的数字，插入到他前面，没找到的话insert = n.length
            while (insert < n.length() && n.charAt(insert) - '0' <= x) {
                insert++;
            }
        } else {
            //正数从0开始遍历
            //找到第一个小于x的，插入到他前面，没找到的话insert = n.length
            while (insert < n.length() && n.charAt(insert) - '0' >= x) {
                insert++;
            }
        }

        //x插入到n的insert位置
        sb.append(n, 0, insert);
        sb.append(x);
        sb.append(n.substring(insert));

        return sb.toString();

    }
}