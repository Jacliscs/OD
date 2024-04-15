package OD415;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * @author 浮生
 * @description 伐木工
 * @date 2024/3/29
 * @level 7
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD415
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //木头长度
        int length = sc.nextInt();

        //输出结果
        System.out.println(getResult(length));

    }

    /**
     * 返回木头长度为length时的最大收益下的切割状态
     *
     * @param length
     * @return int
     * @create 2024/3/29 17:42
     */
    public static String getResult(int length) {
        Wood[] dp = new Wood[length + 1];

        //初始化 将不切割的状态设为默认
        for (int i = 0; i <= length; i++) {
            dp[i] = new Wood();
            dp[i].price = i;
            dp[i].slice.add(i);
        }

        //从长度2开始切割 比较
        for (int i = 2; i <= length; i++) {
            //遍历从1到i 看是否有更大收益的组合 将i长度的木头切割为i-j和j
            for (int j = 1; j < i; j++) {
                int new_price = dp[j].price * dp[i - j].price;

                //如果切割后收益更大，或收益相同的情况下切割更少次数，则更新dp[i]
                if (new_price > dp[i].price ||
                        (new_price == dp[i].price && dp[j].slice.size() + dp[i - j].slice.size() < dp[i].slice.size())) {
                    dp[i].price = new_price;
                    //先清空原来的切割方案
                    dp[i].slice.clear();
                    //添加新切割的方案
                    dp[i].slice.addAll(dp[j].slice);
                    dp[i].slice.addAll(dp[i - j].slice);
                }
            }
        }
        //长度为length的木头的最大收益和切割方案已经递归出来了，需要升序排列
        dp[length].slice.sort((a, b) -> a - b);
        //储存结果
        StringJoiner sj = new StringJoiner(" ");
        dp[length].slice.forEach(s -> sj.add(s + ""));
        //for (int i : dp[length].slice) {
        //    sj.add(i + "");
        //}
        return sj.toString();
    }

    //建立木头类 记录木头长度为i是最大收益与最佳切割方案
    static class Wood {
        int price;
        ArrayList<Integer> slice = new ArrayList<>();
    }

}