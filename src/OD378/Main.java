package OD378;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 攀登者2
 * @date 2024/3/24
 * @level 7
 * @score 200
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
 * 登山时会消耗登山者的体力(整数)，
 * <p>
 * 上山时，消耗相邻高度差两倍的体力
 * 下山时，消耗相邻高度差一倍的体力
 * 平地不消耗体力
 * 登山者体力消耗到零时会有生命危险。
 * <p>
 * 例如，上图所示的山峰：
 * <p>
 * 从索引0，走到索引1，高度差为1，需要消耗 2 * 1 = 2 的体力，
 * 从索引2，走到索引3，高度差为2，需要消耗 2 * 2 = 4 的体力。
 * 从索引3，走到索引4，高度差为1，需要消耗 1 * 1 = 1 的体力。
 * 攀登者想要评估一张地图内有多少座山峰可以进行攀登，且可以安全返回到地面，且无生命危险。
 * <p>
 * 例如上图中的数组，有3个不同的山峰，登上位置在3的山可以从位置0或者位置6开始，从位置0登到山顶需要消耗体力 1 * 2 + 1 * 2 + 2 * 2 = 8，从山顶返回到地面0需要消耗体力 2 * 1 + 1 * 1 + 1 * 1 = 4 的体力，按照登山路线 0 → 3 → 0 需要消耗体力12。攀登者至少需要12以上的体力（大于12）才能安全返回。
 * <p>
 * 输入描述
 * 第一行输入为地图一维数组
 * <p>
 * 第二行输入为攀登者的体力
 * <p>
 * 输出描述
 * 确保可以安全返回地面，且无生命危险的情况下，地图中有多少山峰可以攀登。
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //山峰高度
        int[] heights = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        //体力 必须大于上山下山消耗的体力和
        int maxStrength = sc.nextInt();
        System.out.println(getResult(heights, maxStrength));

    }

    //考试为核心代码模式

    /**
     * 返回最多能攀登的山峰个数
     *
     * @param heights     山峰高度数组
     * @param maxStrength 最大体力值
     * @return int
     * @create 2024/3/24 21:24
     */
    public static int getResult(int[] heights, int maxStrength) {
        //最后返回可以攀登的山峰数量 存放可以攀登的山峰下标，防止逆序后重复
        HashSet<Integer> result = new HashSet<>();

        //正向爬山
        climb(heights, maxStrength, result, true);
        //反转数组：数组->list.reverse()->数组   或自己实现双指针
        reverse(heights);
        //逆向爬山
        climb(heights, maxStrength, result, false);

        //正向 逆向 将能攀登的山的下标添加到set中，不会重复
        return result.size();
    }

    /**
     * 攀登山峰，并将可以攀登的山峰序列保存进result
     *
     * @param heights     山峰高度列表
     * @param maxStrength 最大体力值
     * @param result      存放可以攀登的山峰序列
     * @param isDirection 是否是正向攀爬 逆向时需要转换下标
     * @return void
     * @create 2024/3/24 21:08
     */
    public static void climb(int[] heights, int maxStrength, HashSet<Integer> result, boolean isDirection) {
        //从左到右 找到第一个地面
        int i = 0;
        while (i < heights.length && heights[i] != 0) {
            i++;
        }
        //此时heights[i]=0 即第一个地面位置 必须从地面开始攀登
        //上下山消耗的总体力
        int cost = 0;
        //开始爬山
        for (int j = i + 1; j < heights.length; j++) {
            //遇到新的地面，则刷新，从新的地面开始走
            if (heights[j] == 0) {
                cost = 0;
                continue;
            }
            //高度差
            int diff = heights[j] - heights[j - 1];
            //如果右边高
            if (diff > 0) {
                //只考虑单向上下 即 0->1->0 上去消耗2倍体力，下山消耗1倍体力
                cost += diff * 3;
                //如果heights[j] > heights[j+1] 或i在边界 则i是山峰
                if (j + 1 >= heights.length || heights[j] > heights[j + 1]) {
                    //此时cost包含上山下山的体力和 小于最大体力即可 不能等于
                    if (cost < maxStrength) {
                        //可以攀登 判断方向 改变对应山峰的下标
                        if (isDirection) {
                            //是正向攀登，则直接放下标
                            result.add(j);
                        } else {
                            //逆向攀登 下标0对应正向的heights.length-1
                            result.add(heights.length - j - 1);
                        }
                    }
                }
            } else if (diff < 0) {
                //正向爬到某个山顶，体力充沛的话，前面可能已经经过一个山顶了
                cost -= diff * 3;
            }
        }
    }

    /**
     * 反转数组
     *
     * @param heights
     * @return void
     * @create 2024/3/24 21:23
     */
    public static void reverse(int[] heights) {
        int l = 0;
        int r = heights.length - 1;
        while (l < r) {
            int temp = heights[r];
            heights[r] = heights[l];
            heights[l] = temp;
            l++;
            r--;
        }
    }
}