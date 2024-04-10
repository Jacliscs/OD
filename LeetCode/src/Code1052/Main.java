package Code1052;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 爱生气的书店老板
 * @date 2024/4/10
 * @level 中等
 * @score
 * @url https://leetcode.cn/problems/grumpy-bookstore-owner/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    /**
     * 返回最多有多少客户感到满意
     *
     * @param customers
     * @param grumpy
     * @param minutes
     * @return int
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        //满意的客户数
        int ans = 0;
        for (int i = 0; i < customers.length; i++) {
            //老板不生气时顾客才满意
            ans += grumpy[i] == 0 ? customers[i] : 0;
        }

        //1 <= minutes <= n
        //增加的满意顾客数
        int increase = 0;
        //把窗口里面grump[i]=1的顾客数加上，再移动窗口
        //第一个窗口
        for (int i = 0; i < minutes; i++) {
            //如果grump[i] = 1 则加上
            increase += customers[i] * grumpy[i];
        }

        //最大的新增满意客户数
        int maxIncrease = increase;

        //移动窗口
        for (int i = minutes; i < customers.length; i++) {
            //减去左边的，加上右边的
            increase = increase - customers[i - minutes] * grumpy[i - minutes] + customers[i] * grumpy[i];
            //更新
            maxIncrease = Math.max(maxIncrease, increase);
        }

        //最大满意顾客数 = 原来满意 + 新增满意
        return ans + maxIncrease;
    }
}