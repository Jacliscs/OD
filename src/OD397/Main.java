package OD397;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 小朋友来自多少小区
 * @date 2024/3/23
 * @level 7
 * @score 100
 */

/**
 * 题目描述
 * 幼儿园组织活动，老师布置了一个任务：
 * <p>
 * 每个小朋友去了解与自己同一个小区的小朋友还有几个。
 * <p>
 * 我们将这些数量汇总到数组 garden 中。
 * <p>
 * 请根据这些小朋友给出的信息，计算班级至少有多少个小朋友
 * <p>
 * 输入描述
 * 输入：garden[] = {2, 2, 3}
 * <p>
 * 输出描述
 * 输出：7
 * <p>
 * 备注
 * garden 数组长度最大为 999
 * 每个小区的小朋友数量最多 1000 人，也就是 garden[i] 的范围为 [0, 999]
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getMin(nums));

    }

    //计算至少有多少个小朋友
    public static int getMin(int[] nums) {
        //x说有y个人同小区，那么这个小区至少有y+1人  z声明有y个人同小区，x，z有可能是同小区的
        //nums[i] 是小朋友反馈自己同小区的人数 cnts[nums]是有相同反馈的小朋友数量
        HashMap<Integer, Integer> cnts = new HashMap<>();
        for (int num : nums) {
            cnts.put(num, cnts.getOrDefault(num, 0) + 1);
        }

        //记录题解
        int ans = 0;
        for (int key : cnts.keySet()) {
            //key是反馈同小区的人数，那么该小区至少有key+1个人
            int total = key + 1;
            //Math.ceil(cnts.get(key) * 1.0/total) 是向上取整至少含有key+1人数的小区个数
            ans += (int) Math.ceil(cnts.get(key) * 1.0 / total) * total;
        }
        return ans;
    }

}