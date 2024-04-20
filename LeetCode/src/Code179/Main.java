package Code179;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 最大数
 * @date 2024/4/20
 * @level 中等
 * @url https://leetcode.cn/problems/largest-number/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public String largestNumber(int[] nums) {
        int n = nums.length;
        String[] ss = new String[n];
        //把int转为String，方便比较字典大小
        for (int i = 0; i < n; i++) {
            ss[i] = nums[i] + "";
        }

        //排序ss，拼接后字典序降序
        //这里利用了传递性 ab > ba  bc>cb 则abc最大
        Arrays.sort(ss, (a, b) -> (b + a).compareTo(a + b));

        //拼接
        StringBuilder sb = new StringBuilder();
        //使用增强for循环比forEach快
        for (String s : ss) {
            sb.append(s);
        }

        //处理前导0 最多保留一位
        int k = 0;
        while (k < sb.length() - 1 && sb.charAt(k) == '0') k++;

        return sb.substring(k);


    }
}