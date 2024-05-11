package Code93;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * @author 浮生
 * @description 复原IP地址
 * @date 2024/4/25
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/restore-ip-addresses/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    //记录结果
    List<String> ans = new ArrayList<>();
    //路径
    List<String> path = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        //题解
        int length = s.length();

        if (length > 12 || length < 4) return ans;

        dfs(s, 0);

        return ans;
    }


    /**
     * 把s截取为4段
     *
     * @param s
     * @param index
     * @return void
     */
    public void dfs(String s, int index) {
        //返回标志:遍历到s最后一个字符且有4段
        if (index == s.length() && path.size() == 4) {
            ans.add(String.join(".", path));
            return;
        }

        //每段只能有1-3位
        for (int i = index; i < Math.min(s.length(), index + 3); i++) {
            //剪枝1：如果加起来凑不到4段了
            if (path.size() + (s.length() - index) < 4) break;

            //剪枝2：如果已经满了4段，但是没有遍历完s
            if (path.size() == 4 && index != s.length()) break;

            //截取该段字符串，作为某段地址
            String sub = s.substring(index, i + 1);

            //如果该段字符为有效地址段
            if (isValid(sub)) {

                //将该段地址加入路径
                path.add(sub);

                //递归下一段
                dfs(s, i + 1);

                //回溯
                path.remove(path.size() - 1);
            }
        }


    }

    /**
     * 判断某段地址是否合法
     *
     * @param ip
     * @return boolean
     */
    public boolean isValid(String ip) {
        int ip_num = Integer.parseInt(ip);

        //要么是单独0，要么没有前导0且在0-255之间
        return "0".equals(ip) || (ip.charAt(0) != '0' && ip_num >= 0 && ip_num <= 255);
    }
}