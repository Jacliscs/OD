package OD377;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 攀登者1
 * @date 2024/3/20
 * @level 3
 * @score 100
 */

/**
 * 题目描述
 * 攀登者喜欢寻找各种地图，并且尝试攀登到最高的山峰。
 * <p>
 * 地图表示为一维数组，数组的索引代表水平位置，数组的元素代表相对海拔高度。其中数组元素0代表地面。
 * <p>
 * 例如：[0,1,2,4,3,1,0,0,1,2,3,1,2,1,0]，代表如下图所示的地图，地图中有两个山脉位置分别为 1,2,3,4,5 和 8,9,10,11,12,13，最高峰高度分别为 4,3。最高峰位置分别为3,10。
 * <p>
 * 一个山脉可能有多座山峰(高度大于相邻位置的高度，或在地图边界且高度大于相邻的高度)。
 * <p>
 * image
 * <p>
 * 登山者想要知道一张地图中有多少座山峰。
 * <p>
 * 输入描述
 * 输入为一个整型数组，数组长度大于1。
 * <p>
 * 输出描述
 * 输出地图中山峰的数量。
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] mountains = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        //山峰数量
        int count = 0;
        //统计山峰个数：比两边山高，或者在边界比旁边的高
        for (int i = 0; i < mountains.length; i++) {
            //控制左右，防止越界
            int left = i == 0 ? 0 : mountains[i - 1];
            int right = i == mountains.length - 1 ? 0 : mountains[i + 1];
            if (mountains[i] > left && mountains[i] > right) {
                count++;
            }

        }
        System.out.println(count);
    }
}