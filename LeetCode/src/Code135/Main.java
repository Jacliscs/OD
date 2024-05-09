package Code135;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 分发糖果
 * @date 2024/5/9
 * @level 困难
 * @url <a href="">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;

        //左遍历
        int[] left = new int[n];
        for (int i = 0; i < n; i++) {
            //如果比左边的评分高，就多一个
            if (i > 0 && ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                //初始为1
                left[i] = 1;
            }
        }
        int ans = 0;

        //右遍历
        int[] right = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            //如果比右边的评分高，就多一个
            if (i < n - 1 && ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else {
                //初始为1
                right[i] = 1;
            }

            //最少的糖果，要同时满足左右遍历
            ans += Math.max(left[i], right[i]);
        }
        return ans;
    }
}