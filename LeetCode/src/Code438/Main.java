package Code438;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 找到字符串中所有字母异位词
 * @date 2024/4/27
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/find-all-anagrams-in-a-string/description/?envType=study-plan-v2&envId=top-100-liked">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        //s p仅包含小写字母 cnt[]用于存放p中字母出现次数
        int[] cnt_p = new int[26];
        for (char c : p.toCharArray()) {
            cnt_p[c - 'a']++;
        }

        //cnt_s用于存放s中滑动窗口内字母出现次数
        int[] cnt_s = new int[26];
        int left = 0;

        List<Integer> res = new ArrayList<>();
        for (int right = 0; right < s.length(); right++) {
            //将右边界的字母加入滑动窗口
            cnt_s[s.charAt(right) - 'a']++;
            //当滑动窗口内字母数量大于等于p中字母数量时，将左边界的字母从滑动窗口中移除
            while (right - left + 1 > p.length()) {
                cnt_s[s.charAt(left) - 'a']--;
                left++;
            }
            //判断滑动窗口内字母是否为p中字母的排列组合
            if (Arrays.equals(cnt_p, cnt_s)) {
                res.add(left);
            }
        }
        return res;

    }
}