package OD403;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 项目排期
 * @date 2024/3/27
 * @level 7
 * @score 200
 */

/**
 * https://hydro.ac/d/HWOD2023/p/OD403
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {

    private static int[] requirements;
    private static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //每个需求需要的天数
        requirements = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //项目组人员数量
        n = sc.nextInt();
        System.out.println(getResult());
    }

    //二分法
    public static int getResult() {
        //将需求排序
        int len = requirements.length;
        Arrays.sort(requirements);
        //每人最少需要多少天
        int min = requirements[len - 1];
        //最多需要多少天
        int max = Arrays.stream(requirements).sum();

        //题解
        int ans = max;
        //二分法
        while (min <= max) {
            int mid = (min + max) / 2;
            if (check(0, new int[n], mid)) {
                //mid是一个可能解，不一定是最优解
                ans = Math.min(ans, mid);
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return ans;
    }

    /**
     * 最少天数为limit的情况下，n个人能否完成所有需求
     *
     * @param index  分配的需求索引
     * @param person 每个人分配多少任务
     * @param limit  最少的天数
     * @return boolean
     * @create 2024/3/27 17:04
     */
    public static boolean check(int index, int[] person, int limit) {
        //返回条件 如果能完成最后一个需求，则返回true
        if (index == requirements.length) return true;

        //当前要被分配的天数
        int selected = requirements[index];

        //遍历
        for (int i = 0; i < person.length; i++) {
            //剪枝优化
            if (i > 0 && person[i] == person[i - 1]) continue;

            //如果当前这个人分配了需求后，没有超过最大天数，则可以分配给他
            if (selected + person[i] <= limit) {
                person[i] += selected;
                //递归 分配下一个任务天数
                if (check(index + 1, person, limit)) {
                    return true;
                }
                //如果这种策略不能分配完需求，则回溯
                person[i] -= selected;
            }
        }
        return false;
    }


}