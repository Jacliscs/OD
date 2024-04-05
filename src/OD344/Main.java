package OD344;

import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 找座位
 * @date 2024/3/16
 * @level 6
 * @type 逻辑分析
 * @score 100
 * @url https://hydro.ac/d/HWOD2023/p/OD344
 */

/**
 * 题目描述
 * 在一个大型体育场内举办了一场大型活动，由于疫情防控的需要，要求每位观众的必须间隔至少一个空位才允许落座。
 * <p>
 * 现在给出一排观众座位分布图，座位中存在已落座的观众，请计算出，在不移动现有观众座位的情况下，最多还能坐下多少名观众。
 * <p>
 * 输入描述
 * 一个数组，用来标识某一排座位中，每个座位是否已经坐人。0表示该座位没有坐人，1表示该座位已经坐人。
 * <p>
 * 1 ≤ 数组长度 ≤ 20000
 * 输出描述
 * 整数，在不移动现有观众座位的情况下，最多还能坐下多少名观众
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String seat = sc.nextLine();
            System.out.println(maxSeat(seat));
        }

    }

    /**
     * 返回最大还能坐的人数
     * @create 2024/3/16 17:33
     * @param s
     * @return int
     */
    public static int maxSeat(String s) {
        int count = 0;
        char[] seat = s.toCharArray();
        for (int i = 0; i < seat.length; i++) {
            //如果是0，则判断该位置左边和右边是否有人，都没人则可以坐
            if (seat[i] == '0') {
                //判断左边是否空闲
                boolean isLeftFree = i == 0 || seat[i - 1] == '0';
                //判断右边是否空闲
                boolean isRightFree = i == seat.length - 1 || seat[i + 1] == '0';
                //如果该位置左右都空闲，则count+1，
                if (isLeftFree && isRightFree) {
                    count++;
                    seat[i] = '1';
                    //因为该位置坐人了，则右手紧挨的位置一定不能坐人了，相当于连跳两个位置，减少循环
                    i++;
                }
            }else {
                //如果该位置是1，则右手位置一定不能坐人，则连跳一个位置，减少循环
                i++;
            }
        }
        return count;
    }
}