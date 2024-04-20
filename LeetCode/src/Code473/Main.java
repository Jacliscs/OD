package Code473;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 火柴拼正方形
 * @date 2024/4/20
 * @level 中等
 * @score
 * @url https://leetcode.cn/problems/matchsticks-to-square/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {

    private static int side;

    public boolean makesquare(int[] matchsticks) {
        //要用到所有的火柴，总周长必须能被4整除
        int circumference = Arrays.stream(matchsticks).sum();

        //如果不能被4整除
        if (circumference % 4 != 0) return false;

        //边长
        side = circumference / 4;

        //升序排列
        Arrays.sort(matchsticks);

        //四条边的组成
        int[] edges = new int[4];

        //逆序放入火柴
        return dfs(matchsticks.length - 1, matchsticks, edges);
    }

    public static boolean dfs(int index, int[] mat, int[] edges) {
        //返回标志，因为是逆序排列，所以当index=-1时返回true
        if (index == -1) return true;

        //放入四条边里
        outer:
        for (int i = 0; i < 4; i++) {
            //剪枝优化:如果前面的边的长度跟当前edges[i]一样，则说明当前情况已经循环过
            for (int j = 0; j < i; j++) {
                if (edges[j] == edges[i]) continue outer;
            }

            //当前index的火柴长度
            int len = mat[index];

            //如果放进edges[i]边后会超过最大边长，则跳出
            if (edges[i] + len > side) continue;

            //否则，放入当前边
            edges[i] += len;

            //递归，当前策略是否最终能放完所有火柴
            if (dfs(index - 1, mat, edges)) return true;

            //如果不能返回true，则说明index不该放在edges[i]，回溯
            edges[i] -= len;
        }

        //递归没有返回true，则说明不能
        return false;

    }


}