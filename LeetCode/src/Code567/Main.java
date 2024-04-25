package Code567;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 字符串的排列
 * @date 2024/4/25
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/permutation-in-string/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = "ab";
        String s2 = "eidbaooo";
        Solution solution = new Solution();
        System.out.println(solution.checkInclusion(s1, s2));
    }
}

class Solution {
    /**
     * 判断s1的排列之一是s2的子串
     *
     * @param s1
     * @param s2
     * @return boolean
     */
    public boolean checkInclusion(String s1, String s2) {
        //储存s1的全排列
        List<String> res = new ArrayList<>();
        boolean[] used = new boolean[s1.length()];
        dfs(s1, new StringBuilder(), used, res);

        for (String s : res) {
            //如果s是s2的子串，则返回true
            if (s2.contains(s)) return true;
        }

        //否则返回false
        return false;
    }

    public static void dfs(String s, StringBuilder path, boolean[] used, List<String> res) {
        //返回条件
        if (path.length() == s.length()) {
            res.add(path.toString());
            return;
        }

        //遍历
        for (int i = 0; i < s.length(); i++) {
            //如果该字符已经使用
            if (used[i]) continue;

            //去重
            if (i > 0 && s.charAt(i) == s.charAt(i - 1) && !used[i - 1]) continue;

            //否则拼接
            path.append(s.charAt(i));
            used[i] = true;

            //递归
            dfs(s, path, used, res);

            //恢复
            path.deleteCharAt(path.length() - 1);
            used[i] = false;
        }
    }
}