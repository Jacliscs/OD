package Code2105;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 给植物浇水II
 * @date 2024/5/9
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/watering-plants-ii/description/?envType=daily-question&envId=2024-05-09">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public int minimumRefill(int[] plants, int capacityA, int capacityB) {
        //补水次数
        int ans = 0;

        //Alice、Bob分别剩余的水
        int a = capacityA, b = capacityB;

        //浇水位置
        int i = 0, j = plants.length - 1;

        //相遇前
        while (i < j) {
            //A给位置i浇水
            if (a < plants[i]) {
                //补水
                ans++;
                //剩余水装满
                a = capacityA;
            }
            //浇水 并且移动到下一个位置
            a -= plants[i++];

            //B给位置j浇水
            if (b < plants[j]) {
                //补水
                ans++;
                //剩余水装满
                b = capacityB;
            }
            //浇水 并且移动到下一个位置
            b -= plants[j--];
        }

        //如果i=j 相遇了，剩余更多的人都不够的话，就需要补水
        if (i == j && Math.max(a, b) < plants[i]) ans++;

        return ans;
    }
}