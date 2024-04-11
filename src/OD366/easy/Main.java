package OD366.easy;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 最多购买宝石数目-前缀和
 * @date 2024/4/11
 * @level 4
 * @score 100
 * @url https://hydro.ac/d/HWOD2023/p/OD366
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //宝石数目
        int n = sc.nextInt();

        //购买0-i个宝石的价格和
        int[] gemPriceSum = new int[n + 1];

        for (int i = 0; i < n; i++) {
            gemPriceSum[i + 1] = gemPriceSum[i] + sc.nextInt();
        }

        //拥有的钱
        int money = sc.nextInt();

        //能购买[left,right]窗口内的宝石
        int left = 0;
        int right = 1;

        //题解：最大购买数量
        int ans = 0;
        //gems = [1,2,3,100,4,5,6] money = 10
        //gemPriceSum = [0,1,3,6,106,110,115,121]
        while (right < gemPriceSum.length) {
            //购买当前区间内的宝石需要多少钱
            int cost = gemPriceSum[right] - gemPriceSum[left];

            //如果能买的起
            if (cost <= money) {
                ans = Math.max(ans, right - left);
                right++;
            }
            //如果窗口内宝石的价格和超过money，则移动左指针
            if (cost > money) {
                left++;
            }
        }
        //最后窗口的长度就是能买的最大宝石数量
        System.out.println(ans);

    }
}