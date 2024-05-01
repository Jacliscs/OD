package Code131;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 分割回文串
 * @date 2024/5/1
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/palindrome-partitioning/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    private final List<List<String>> ans = new ArrayList<>();
    private final List<String> path = new ArrayList<>();
    private String s;


    /**
     * 使每个子串都是回文串返回 s 所有可能的分割方案。
     *
     * @param s
     * @return java.util.List<java.util.List < java.lang.String>>
     */
    public List<List<String>> partition(String s) {
        this.s = s;
        //dfs递归
        dfs(0);
        //返回结果
        return ans;
    }

    private void dfs(int i) {
        //返回条件，已经分割完子串了，i遍历到末尾
        if (i == s.length()) {
            //将path添加进结果
            ans.add(new ArrayList<>(path));
            return;
        }

        //递归，当前轮次从i开始作为开始
        for (int j = i; j < s.length(); j++) {
            //如果是回文串，则添加进path，递归，然后恢复现场继续下一个
            if (check(i, j)) {
                //加入路径，进入递归
                path.add(s.substring(i, j + 1));
                dfs(j + 1);
                //恢复现场
                path.remove(path.size() - 1);
            }
        }
    }

    /**
     * 判断子串是否是回文串
     *
     * @param i
     * @param j
     * @return boolean
     */
    private boolean check(int i, int j) {
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) return false;
        }
        return true;
    }
}