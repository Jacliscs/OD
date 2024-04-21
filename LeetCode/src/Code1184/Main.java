package Code1184;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 公交站间的距离
 * @date 2024/4/21
 * @level 简单
 * @url <a href="https://leetcode.cn/problems/distance-between-bus-stops/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        //题解：最短距离
        int ans = 0;

        int n = distance.length;

        //顺时针的距离
        int dis1 = 0;
        //逆时针=总路程-顺时针
        int all_dist = Arrays.stream(distance).sum();


        //顺时针
        while (start != destination) {
            dis1 += distance[start++];

            //防止越界
            start %= n;
        }

        ans = Math.min(dis1, all_dist - dis1);

        return ans;

    }
}