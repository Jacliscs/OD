package Code120;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 三角形最小路径和
 * @date 2024/5/1
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/triangle/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {

    public int minimumTotal(List<List<Integer>> triangle) {
        //三角形行数
        int row = triangle.size();

        //minLen[i]表示自底向上到达每行第i个位置的最小和 第row行有row+1个数字，所以大小为row+1
        int[] minLen = new int[row + 1];

        //从最后一行开始
        for (int level = row - 1; level >= 0; level--) {
            //第level行有level+1个数字
            for (int i = 0; i < level + 1; i++) {
                //到达本行第i个位置的最小和，由更下面的行更新的minLen来更替，第i个位置只能从下面行的第i、i+1走上来
                minLen[i] = Math.min(minLen[i], minLen[i + 1]) + triangle.get(level).get(i);
            }
        }
        //最后返回从底到顶最小和
        return minLen[0];
    }
}