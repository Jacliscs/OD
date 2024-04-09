package Code605;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 种花问题
 * @date 2024/4/9
 * @level 简单
 * @score
 * @url https://leetcode.cn/problems/can-place-flowers/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n == 0) return true;

        //种花必须左右相邻不能有花
        for (int i = 0; i < flowerbed.length; i++) {
            //左右是否有花
            boolean is_left_free = i == 0 || flowerbed[i - 1] == 0;
            boolean is_right_free = i == flowerbed.length - 1 || flowerbed[i + 1] == 0;
            //如果该位置是0且左右没有花，则可以种
            if (is_left_free && is_right_free && flowerbed[i] == 0) {
                //种花
                flowerbed[i] = 1;
                n--;
                if (n == 0) break;
                //相邻位置肯定不能种花，减少循环
                i++;
            } else if (flowerbed[i] == 1) {
                //相邻位置不能种花
                i++;
            }

        }

        return n == 0;
    }
}