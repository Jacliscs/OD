package OD226;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 求最多可以派出多少支团队
 * @date 2024/3/24
 * @level 5
 * @score 100
 */

/**
 * 题目描述
 * 用数组代表每个人的能力，一个比赛活动要求参赛团队的最低能力值为N，每个团队可以由1人或者2人组成，且1个人只能参加1个团队，计算出最多可以派出多少只符合要求的团队。
 * <p>
 * 输入描述
 * 第一行代表总人数，范围1-500000
 * 第二行数组代表每个人的能力
 * 数组大小，范围1-500000
 * 元素取值，范围1-500000
 * 第三行数值为团队要求的最低能力值，范围1-500000
 * 输出描述
 * 最多可以派出的团队数量
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //代表总人数
        int n = sc.nextInt();
        //每个人的能力值
        int[] power = new int[n];
        for (int i = 0; i < n; i++) {
            power[i] = sc.nextInt();
        }
        //团队要求最低能力值
        int minPower = sc.nextInt();
        System.out.println(getResult(power, minPower));
    }

    //最多可以派出多少团队 能一个人满足的就一个人组队
    public static int getResult(int[] power, int minPower) {
        int n = power.length;
        //升序排列
        Arrays.sort(power);

        //双指针
        int l = 0;
        int r = n - 1;
        int count = 0;

        //先记录单人组队
        while (r >= l && power[r] >= minPower) {
            count++;
            r--;
        }

        //把小于<=minPower的两两分组 尽可能多
        while (l < r) {
            int sum = power[l] + power[r];
            //如果此时无法组队，则l位置的不可能组队成功
            if (sum < minPower) {
                l++;
            } else {
                //组队成功
                count++;
                l++;
                r--;
            }
        }

        return count;

    }
}