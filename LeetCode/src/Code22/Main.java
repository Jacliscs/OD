package Code22;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 括号生成
 * @date 2024/4/9
 * @level 中等
 * @score
 * @url https://leetcode.cn/problems/generate-parentheses/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 3;
        Solution solution = new Solution();
        List<String> res = solution.generateParenthesis(n);
        res.forEach(System.out::println);
    }
}

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        dfs("", n, n, res);
        return res;
    }

    /**
     * 生成括号组合
     *
     * @param curStr 当前的结果
     * @param left   剩余的左括号个数
     * @param right  剩余的右括号个数
     * @return void
     */
    public static void dfs(String curStr, int left, int right, List<String> res) {
        //返回标志
        if (left == 0 && right == 0) {
            res.add(curStr);
            return;
        }

        //异常情况：一定是先消耗一个左括号，才能再消耗一个右括号
        //如果右括号数量严格少于左括号，则不能组成
        if (right < left) return;

        //必须先消耗左括号
        if (left > 0) {
            //结果第一个是"((()))"
            dfs(curStr + "(", left - 1, right, res);
        }

        //消耗右括号
        if (right > 0) {
            dfs(curStr + ")", left, right - 1, res);
        }

    }
}