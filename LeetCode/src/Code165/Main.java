package Code165;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 比较版本号
 * @date 2024/4/20
 * @level 中等
 * @score
 * @url https://leetcode.cn/problems/compare-version-numbers/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {

    /**
     * 如果 version1 > version2 返回 1，
     * 如果 version1 < version2 返回 -1，
     * 除此之外返回 0。
     *
     * @param version1
     * @param version2
     * @return int
     */
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        int len1 = v1.length;
        int len2 = v2.length;

        //从前比较到后，遇到大于或者小于直接返回，遇到相同比较下一位
        int i = 0, j = 0;
        //题解

        while (i < len1 || j < len2) {
            //要忽略前导0，转整型 超过范围的默认为0
            int n1 = i < len1 ? Integer.parseInt(v1[i]) : 0;
            int n2 = j < len2 ? Integer.parseInt(v2[j]) : 0;

            if (n1 == n2) {
                i++;
                j++;
            } else if (n1 > n2) {
                return 1;
            } else {
                //n1 < n2
                return -1;
            }
        }

        return 0;
    }
}