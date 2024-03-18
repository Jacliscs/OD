package OD368;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 最大坐标值、小明的幸运数
 * @date 2024/3/18
 * @level 3
 * @score 100
 */

/**
 * 题目描述
 * <p>
 * 小明在玩一个游戏，游戏规则如下：
 * 在游戏开始前，小明站在坐标轴原点处（坐标值为0）.
 * 给定一组指令和一个幸运数，每个指令都是一个整数，小明按照指令前进指定步数或者后退指定步数。前进代表朝坐标轴的正方向走，后退代表朝坐标轴的负方向走。
 * 幸运数为一个整数，如果某个指令正好和幸运数相等，则小明行进步数+1。
 * <p>
 * 例如：
 * <p>
 * 幸运数为3，指令为[2,3,0,-5]
 * <p>
 * 指令为2，表示前进2步；
 * <p>
 * 指令为3，正好和幸运数相等，前进3+1=4步；
 * <p>
 * 指令为0，表示原地不动，既不前进，也不后退。
 * <p>
 * 指令为-5，表示后退5步。
 * <p>
 * 请你计算小明在整个游戏过程中，小明所处的最大坐标值。
 * <p>
 * 输入描述
 * 第一行输入1个数字，代表指令的总个数 n（1 ≤ n ≤ 100）
 * <p>
 * 第二行输入1个数字，代表幸运数m（-100 ≤ m ≤ 100）
 * <p>
 * 第三行输入n个指令，每个指令的取值范围为：-100 ≤ 指令值 ≤ 100
 * <p>
 * 输出描述
 * 输出在整个游戏过程中，小明所处的最大坐标值。异常情况下输出：12345
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //指令总数
        int n = sc.nextInt();
        //幸运数
        int luck = sc.nextInt();
        //指令序列
        int[] command = new int[n];
        for (int i = 0; i < n; i++) {
            command[i] = sc.nextInt();
        }
        System.out.println(getMaxCoordinate(command, luck));
    }

    //返回最大坐标，异常返回12345
    public static int getMaxCoordinate(int[] command, int luck) {
        //异常
        if (luck < -100 || luck > 100 || command == null || command.length == 0 || command.length > 100) {
            return 12345;
        }
        //指令范围：-100~100
        for (int i : command) {
            if (i < -100 || i > 100) {
                return 12345;
            }
        }

        List<Integer> list = new ArrayList<>();
        list.add(0);
        int now = 0;
        //步数分正负，负数幸运值的话就是多—1
        for (int i = 0; i < command.length; i++) {
            if (command[i] == luck && luck > 0) {
                now += command[i] + 1;
                list.add(now);
            } else if (command[i] == luck && luck < 0) {
                now += command[i] - 1;
                list.add(now);
            } else {
                //包括command[i]=luck=0的情况，不用多走一步
                now += command[i];
                list.add(now);
            }
        }
        list.sort((a, b) -> b - a);
        return list.get(0);
    }
}