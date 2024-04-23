package Code202;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author 浮生
 * @description 快乐数
 * @date 2024/4/23
 * @level 简单
 * @url <a href="https://leetcode.cn/problems/happy-number/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {

    /**
     * 判断一个数是否是快乐数
     *
     * @param n
     * @return boolean
     */
    public boolean isHappy(int n) {
        //存放已经出现过的数字，如果重复，则说明会循环，不是快乐数
        Set<Integer> set = new HashSet<>();

        //只要不是1且没重复
        while (n != 1 && !set.contains(n)) {
            //把该值添加进集合
            set.add(n);
            //计算下一个值
            n = getNext(n);
        }

        //是否变为1
        return n == 1;

    }

    private static int getNext(int n) {
        int next = 0;
        while (n != 0) {
            //每次取最后一位
            int d = n % 10;
            n /= 10;
            next += d * d;
        }
        return next;
    }
}