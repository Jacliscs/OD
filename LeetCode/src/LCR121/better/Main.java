package LCR121.better;

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

    /**
     * 利用大小规律，从下网上寻找
     *
     * @param plants
     * @param target
     * @return boolean
     */
    public boolean findTargetIn2DPlants(int[][] plants, int target) {
        //从左下角开始遍历，比较target，如果大于target，则这一行一定全部大于，则row--
        //如果小于，则col++；
        int row = plants.length - 1;
        int col = 0;

        while (row >= 0 && col < plants[0].length) {
            if (plants[row][col] > target) row--;
            else if (plants[row][col] < target) col++;
            else return true;
        }
        return false;
    }
}