package OD320;

import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 计算面积、绘图机器
 * @date 2024/3/20
 * @level 6
 * @score 100
 */

/**
 * 题目描述
 * 绘图机器的绘图笔初始位置在原点(0,0)机器启动后按照以下规则来进行绘制直线。
 * <p>
 * 尝试沿着横线坐标正向绘制直线直到给定的终点E
 * 期间可以通过指令在纵坐标轴方向进行偏移，offsetY为正数表示正向偏移,为负数表示负向偏移
 * 给定的横坐标终点值E 以及若干条绘制指令，
 * <p>
 * 请计算绘制的直线和横坐标轴以及x=E的直线组成的图形面积。
 * <p>
 * 输入描述
 * 首行为两个整数 N 和 E
 * <p>
 * 表示有N条指令,机器运行的横坐标终点值E
 * <p>
 * 接下来N行 每行两个整数表示一条绘制指令x offsetY
 * <p>
 * 用例保证横坐标x以递增排序的方式出现
 * <p>
 * 且不会出现相同横坐标x
 * <p>
 * 取值范围
 * <p>
 * 0<N<=10000
 * 0<=x<=E<=20000
 * -10000<=offsetY<=10000
 * 输出描述
 * 一个整数表示计算得到的面积 用例保证结果范围在0到4294967295之内。
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //n条指令
        int n = sc.nextInt();
        //横坐标终点值
        int end_X = sc.nextInt();
        //面积和
        int area = 0;
        //上一个点的坐标
        int last_X = 0;
        int last_Y = 0;
        for (int i = 0; i < n; i++) {
            //当前点的x坐标
            int cur_X = sc.nextInt();
            //当前点在y轴上的偏移量
            int offset_Y = sc.nextInt();
            //把该点与上一个点与x轴围成的面积算出来 不分正负
            area += (cur_X - last_X) * Math.abs(last_Y);

            //更新上一个点的坐标
            last_X = cur_X;
            last_Y = last_Y + offset_Y;
        }
        //因为要保证沿着x正向划线，所以前面的点坐标不可能超过end_X
        if (end_X > last_X) {
            area += (end_X - last_X) * Math.abs(last_Y);
        }
        System.out.println(area);
    }
}