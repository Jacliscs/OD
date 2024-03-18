package OD404;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 灰度图存储
 * @date 2024/3/18
 * @level 3
 * @score 100
 */

/**
 * 题目描述
 * 黑白图像常采用灰度图的方式存储，即图像的每个像素填充一个灰色阶段值，256阶灰图是一个灰阶值取值范围为 0~255 的灰阶矩阵，0表示全黑，255表示全白，范围内的其他值表示不同的灰度。
 * <p>
 * 但在计算机中实际存储时，会使用压缩算法，其中一个种压缩格式描述如如下：
 * <p>
 * 10 10 255 34 0 1 255 8 0 3 255 6 0 5 255 4 0 7 255 2 0 9 255 21
 * <p>
 * 所有的数值以空格分隔；
 * 前两个数分别表示矩阵的行数和列数；
 * 从第三个数开始，每两个数一组，每组第一个数是灰阶值，第二个数表示该灰阶值从左到右，从上到下（可理解为二维数组按行存储在一维矩阵中）的连续像素个数。比如题目所述的例子， “255 34” 表示有连续 34 个像素的灰阶值是 255。
 * 如此，图像软件在打开此格式灰度图的时候，就可以根据此算法从压缩数据恢复出原始灰度图矩阵。
 * <p>
 * 请从输入的压缩数恢复灰度图原始矩阵，并返回指定像素的灰阶值。
 * <p>
 * 输入描述
 * <p>
 * 10 10 255 34 0 1 255 8 0 3 255 6 0 5 255 4 0 7 255 2 0 9 255 21
 * <p>
 * 3 4
 * <p>
 * 输入包行两行：
 * <p>
 * 第一行是灰度图压缩数据
 * 第二行表示一个像素位置的行号和列号，如 0 0 表示左上角像素
 * 输出描述
 * 0
 * <p>
 * 输出数据表示的灰阶矩阵的指定像素的灰阶值。
 * <p>
 * 备注
 * 系保证输入的压缩数据是合法有效的，不会出现数据起界、数值不合法等无法恢复的场景
 * 系统保证输入的像素坐标是合法的，不会出现不在矩阵中的像素
 * 矩阵的行和列数范图为:(0,100]
 * 灰阶值取值范图:[0,255]
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //方法一：因为是把二维矩阵从左到右，从上到下按顺序填充，故可以转为在一维数组中从左到右填充
        //第一行读取 行列值 和相对应的填充颜色
        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //第二行数据，表示要查询的行列值 把行列值转为一维数组下标
        int[] pos = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //总共有多少格
        int[] graph = new int[nums[0] * nums[1]];
        //开始填充的起点
        int start = 0;
        //由输入保证合法、不会出现越界
        for (int i = 2; i < nums.length; i += 2) {
            //要填的数字
            int num = nums[i];
            //该数字占几格
            int len = nums[i + 1];
            //填充Arrays.fill
            Arrays.fill(graph, start, start + len, num);
            //更新起点
            start += len;
        }
        //要获取的二维位置转为一维位置
        int target = pos[0] * nums[1] + pos[1];
        System.out.println(graph[target]);
    }
}