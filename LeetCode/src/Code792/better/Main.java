package Code792.better;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 匹配子序列的单词数
 * @date 2024/4/24
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/number-of-matching-subsequences/solutions/1975527/by-lcbin-gwyj/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    //存放s中各个字符出现的下标集合
    public static List<Integer>[] pos = new List[26];

    public int numMatchingSubseq(String s, String[] words) {
        //初始化
        for (int i = 0; i < 26; i++) {
            pos[i] = new ArrayList<>();
        }

        //添加下标
        for (int i = 0; i < s.length(); i++) {
            pos[s.charAt(i) - 'a'].add(i);
        }

        //题解
        int ans = 0;

        //遍历words
        for (String word : words) {
            //如果word是s的子序列
            if (check(word)) {
                ans++;
            }
        }

        return ans;
    }

    public static boolean check(String word) {
        //指向s中的字符
        int p = -1;
        for (int i = 0; i < word.length(); i++) {
            int j = word.charAt(i) - 'a';
            //在当前字符列表里找第一个比p大的下标，返回该下标的索引
            int idx = search(pos[j], p);
            //如果idx==pos[j].size 说明j列表里所有下标都比p小
            if (idx == pos[j].size()) return false;

            //更新p，找下一个字符
            p = pos[j].get(idx);
        }

        return true;
    }

    /**
     * 二分查找：返回list中第一个比i大的下标，如果没找到
     *
     * @param list
     * @param i
     * @return int
     */
    public static int search(List<Integer> list, int i) {
        int left = 0;
        int right = list.size();
        int ans = right;
        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) > i) {
                //找最小的满足list.get(mid)>i的数
                ans = Math.min(ans, mid);
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }
}