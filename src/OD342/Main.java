package OD342;

import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 最富裕的小家庭
 * @date 2024/3/16
 * @level 6
 */

/**
 * 题目描述
 * <p>
 * 在一颗树中，每个节点代表一个家庭成员，节点的数字表示其个人的财富值，一个节点及其直接相连的子节点被定义为一个小家庭。
 * 现给你一颗树，请计算出最富裕的小家庭的财富和。
 * <p>
 * 输入描述
 * <p>
 * 第一行为一个数 N，表示成员总数，成员编号 1~N。1 ≤ N ≤ 1000
 * 第二行为 N 个空格分隔的数，表示编号 1~N 的成员的财富值。0 ≤ 财富值 ≤ 1000000
 * 接下来 N -1 行，每行两个空格分隔的整数（N1, N2），表示 N1 是 N2 的父节点。
 * <p>
 * 输出描述
 * <p>
 * 最富裕的小家庭的财富和
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            //成员个数
            int n = sc.nextInt();
            //成员财富，从1开始
            int[]  wealth = new int[n+1];
            //该成员的家庭财富：自身+子节点
            int[]  familyWealth = new int[n+1];
            for (int i = 1; i <= n; i++) {
                wealth[i] = sc.nextInt();
                //初始化家庭财富
                familyWealth[i] = wealth[i];
            }
            //依次读取成员关系，并直接将对应子节点的值加到其对应的父节点的famliyWealth中
            for(int i = 0; i < n-1; i++){
                int parent = sc.nextInt();
                int child = sc.nextInt();
                familyWealth[parent] += wealth[child];
            }
            //遍历找到最大的famliyWealth
            int  max = 0;
            for(int i : familyWealth){
                max = Math.max(max, i);
            }
            System.out.println(max);
        }
        sc.close();
    }
}