package OD428;

import java.text.NumberFormat;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 运输时间
 * @date 2024/3/20
 * @level 6
 * @score 200
 */

/**
 * 题目描述
 * M（1 ≤ M ≤ 20）辆车需要在一条不能超车的单行道到达终点，起点到终点的距离为 N（1 ≤ N ≤ 400）。
 * <p>
 * 速度快的车追上前车后，只能以前车的速度继续行驶，求最后一辆车到达目的地花费的时间。
 * <p>
 * 注：每辆车固定间隔 1 小时出发，比如第一辆车 0 时出发，第二辆车 1 时出发，依次类推
 * <p>
 * 输入描述
 * 第一行两个数字：M N，分别代表车辆数和到终点的距离，以空格分隔
 * <p>
 * 接下来 M 行，每行一个数字 S，代表每辆车的速度。0 < S < 30
 * <p>
 * 输出描述
 * 最后一辆车到达目的地花费的时间
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //车辆数
        int m = sc.nextInt();
        //终点距离
        int n = sc.nextInt();
        //初始化上一辆车到达目的地时间
        double arrived = 0;
        for (int i = 0; i < m; i++) {
            //当前车的速度
            double speed = sc.nextInt();
            //当前车到达目的地的时间 i是等待出发的时间，n/speed是第i辆车正常行使需要的时间，后车不可能比前车更早到达
            arrived = Math.max(arrived, n / speed + i);
        }
        //最后一辆车花费的时间 到达时间-出发时刻
        double cost = arrived - (m - 1);
        //实际考试不需要考虑精度
        System.out.println(cost);

        //格式化打印小数
        //没有小数时输出整数，有小数时保留三位
        //NumberFormat nf = NumberFormat.getNumberInstance();
        //nf.setMinimumFractionDigits(0);//最少保留0位小数
        //nf.setMaximumFractionDigits(3);//最大保留3位小数
        //System.out.println(nf.format(cost));
    }
}