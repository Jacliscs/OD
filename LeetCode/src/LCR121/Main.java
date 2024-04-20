package LCR121;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 寻找目标值-二维数组
 * @date 2024/4/20
 * @level 中等
 * @score
 * @url https://leetcode.cn/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public boolean findTargetIn2DPlants(int[][] plants, int target) {
        //如果为空
        if (plants == null || plants.length == 0 || plants[0].length == 0) return false;


        //可能会越界
        int m = plants.length;
        int n = plants[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (plants[i][j] == target) return true;
            }
        }

        return false;

    }
}