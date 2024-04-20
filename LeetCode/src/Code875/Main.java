package Code875;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 爱吃香蕉的珂珂
 * @date 2024/4/20
 * @level 中等
 * @score
 * @url https://leetcode.cn/problems/koko-eating-bananas/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    /**
     * 返回在h小时吃完所有香蕉的最小速度-二分法
     *
     * @param piles
     * @param h
     * @return int
     */
    public int minEatingSpeed(int[] piles, int h) {
        //最大香蕉数
        int max = Arrays.stream(piles).max().orElse(0);

        //输入控制h>=piles.length 才能吃完
        //如果刚好等于堆数，则必须用最大值才能吃完
        if (h == piles.length) return max;

        //否则，使用二分法
        int min = 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            long cost = getTime(piles, mid);

            //如果花费时间小于h，是可能解，不一定是最小解
            if (cost <= h) {
                max = mid - 1;
            } else {
                //cost>h
                min = mid + 1;
            }
        }

        return min;
    }

    /**
     * 返回用mid速度吃需要的时间
     *
     * @param piles
     * @param mid
     * @return long
     */
    public static long getTime(int[] piles, int mid) {
        long time = 0;

        for (int pile : piles) {
            time += pile / mid + (pile % mid == 0 ? 0 : 1);
        }

        return time;
    }
}