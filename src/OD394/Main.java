package OD394;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 跳格子3
 * @date 2024/3/26
 * @level 7
 * @score 200
 */

/**
 * url:https://hydro.ac/d/HWOD2023/p/OD394
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //格子总数
        int n = Integer.parseInt(sc.nextLine());
        //每个格子的分数
        int[] score = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //每次最大步长
        int k = Integer.parseInt(sc.nextLine());

    }
}