package OD385;

import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 结队编程
 * @date 2024/3/25
 * @level 6
 * @score 200
 */

/**
 * 题目描述
 * 某部门计划通过结队编程来进行项目开发，
 * <p>
 * 已知该部门有 N 名员工，每个员工有独一无二的职级，每三个员工形成一个小组进行结队编程，结队分组规则如下：
 * <p>
 * 从部门中选出序号分别为 i、j、k 的3名员工，他们的职级分贝为 level[i]，level[j]，level[k]，
 * <p>
 * 结队小组满足 level[i] < level[j] < level[k] 或者 level[i] > level[j] > level[k]，
 * <p>
 * 其中 0 ≤ i < j < k < n。
 * <p>
 * 请你按上述条件计算可能组合的小组数量。同一员工可以参加多个小组。
 * <p>
 * 输入描述
 * 第一行输入：员工总数 n
 * <p>
 * 第二行输入：按序号依次排列的员工的职级 level，中间用空格隔开
 * <p>
 * 限制：
 * <p>
 * 1 ≤ n ≤ 6000
 * 1 ≤ level[i] ≤ 10^5
 * 输出描述
 * 可能结队的小组数量
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //员工总数
        int n = sc.nextInt();
        //各员工职级
        int[] levels = new int[n];
        for (int i = 0; i < n; i++) {
            levels[i] = sc.nextInt();
        }
        //按序号，职级是升序或者降序才能组队，可以参加多个组队，暴力枚举
        System.out.println(solution(levels));
    }

    /**
     * 使用左右升序降序排列和
     *
     * @param levels
     * @return int
     * @create 2024/3/25 17:32
     */
    public static long solution(int[] levels) {
        //寻找三人组中间那个值
        //如果该点左边有2个比自己小的值，右边有三个比自己大的值，则组成升序就会有2*3=6中可能，降序同理
        long count = 0;
        int n = levels.length;

        //双重for循环 找到第i个人左右分别比自己大或者小的个数
        for (int i = 1; i < n - 1; i++) {
            //找左边比自己大或者小的个数
            long left_smaller = 0;
            long left_bigger = 0;
            for (int j = 0; j < i; j++) {
                if (levels[j] < levels[i]) {
                    left_smaller++;
                } else {
                    left_bigger++;
                }
            }
            //找右边比自己大的数
            long right_smaller = 0;
            long right_bigger = 0;
            for (int k = n - 1; k > i; k--) {
                if (levels[k] < levels[i]) {
                    right_smaller++;
                } else {
                    right_bigger++;
                }
            }
            //left_smaller*right_bigger 是升序组合个数 left_bigger*right_smaller是降序组合个数
            count += left_smaller * right_bigger + left_bigger * right_smaller;
        }
        return count;
    }
}