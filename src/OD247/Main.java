package OD247;

import java.util.*;

/**
 * @author Jacliscs
 * @description 寻找身高相近的小朋友
 * @date 2024/3/20
 * @level 4
 * @score 100
 * @url https://hydro.ac/d/HWOD2023/p/OD247
 */

/**
 * 题目描述
 * 小明今年升学到了小学1年级来到新班级后，发现其他小朋友身高参差不齐，然后就想基于各小朋友和自己的身高差，对他们进行排序，请帮他实现排序。
 * <p>
 * 输入描述
 * 第一行为正整数 h和n，0<h<200 为小明的身高，0<n<50 为新班级其他小朋友个数。
 * <p>
 * 第二行为n个正整数，h1 ~ hn分别是其他小朋友的身高，取值范围0<hi<200，且n个正整数各不相同。
 * <p>
 * 输出描述
 * 输出排序结果，各正整数以空格分割，
 * <p>
 * 和小明身高差绝对值最小的小朋友排在前面，
 * <p>
 * 和小明身高差绝对值最大的小朋友排在后面，
 * <p>
 * 如果两个小朋友和小明身高差一样，则个子较小的小朋友排在前面。
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //小明身高
        int h = sc.nextInt();
        //小朋友个数
        int n = sc.nextInt();
        //小朋友
        Integer[] heights = new Integer[n];
        for (int i = 0; i < n; i++) {
            heights[i] = sc.nextInt();
        }
        System.out.println(sortHeight(heights, h));
    }

    //按与小明身高差的绝对值从小到大排序
    public static String sortHeight(Integer[] heights, int h) {
        //排序 要使用Integer[]才能直接做“-“法
        Arrays.sort(heights, (a, b) -> {
            //身高差的绝对值
            int absA = Math.abs(a - h);
            int absB = Math.abs(b - h);

            //绝对值升序
            if (absA != absB) return absA - absB;
                //绝对值相同，按身高排序
            else return a - b;
        });
        StringJoiner sj = new StringJoiner(" ");
        for (Integer height : heights) {
            sj.add(height + "");
        }
        return sj.toString();
    }

}