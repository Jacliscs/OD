package OD395;

import java.util.HashSet;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 员工派遣
 * @date 2024/3/26
 * @level 7
 * @score 200
 */

/**
 * 题目描述
 * 某公司部门需要派遣员工去国外做项目。
 * <p>
 * 现在，代号为 x 的国家和代号为 y 的国家分别需要 cntx 名和 cnty 名员工。
 * <p>
 * 部门每个员工有一个员工号（1,2,3,......），工号连续，从1开始。
 * <p>
 * 部长派遣员工的规则：
 * <p>
 * 规则1：从 [1, k] 中选择员工派遣出去
 * 规则2：编号为 x 的倍数的员工不能去 x 国，编号为 y 的倍数的员工不能去 y 国。
 * 问题：
 * <p>
 * 找到最小的 k，使得可以将编号在 [1, k] 中的员工分配给 x 国和 y 国，且满足 x 国和 y 国的需求。
 * <p>
 * 输入描述
 * 四个整数 x，y，cntx，cnty。
 * <p>
 * 2 ≤ x < y ≤ 30000
 * x 和 y 一定是质数
 * 1 ≤ cntx, cnty < 10^9
 * cntx + cnty ≤ 10^9
 * 输出描述
 * 满足条件的最小的k
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {

    private static long x;
    private static long y;
    private static long cnt_x;
    private static long cnt_y;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //国家代号 x y  x<y
        x = sc.nextInt();
        y = sc.nextInt();
        //俩国家需要的人数 cntx cnty
        cnt_x = sc.nextLong();
        cnt_y = sc.nextLong();
        //输出能满足的最小k  [1,k]中间取值
        System.out.println(getResult());

    }

    /**
     * 二分法求k值
     *
     * @return long
     * @create 2024/3/26 17:39
     */
    public static long getResult() {
        //最少派遣cny_x+cnt_y个
        long min = cnt_x + cnt_y;
        //最多派遣
        //long max = Long.MAX_VALUE;//机考通过率低
        long max = 1000000000L;
        //记录题解
        long ans = max;
        //二分法
        while (min <= max) {
            long mid = (min + max) / 2;
            //如果当前mid能满足，则是一个可能解
            if (check(mid)) {
                ans = Math.min(ans, mid);
                //缩小
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return ans;
    }

    /**
     * 当前k值能否满足要求
     *
     * @param k
     * @return boolean
     * @create 2024/3/26 17:44
     */
    public static boolean check(long k) {
        long div_x = k / x;//1-k中能被x整除的数量 包含div_xy
        long div_y = k / y;//1-k中能被y整除的数量 包含div_xy
        long div_xy = k / (x * y);//1-k中能被x*y整除的数量
        //把是y倍数但不是x倍速的给x公司 x还需要的人数
        long need_x = Math.max(0, cnt_x - (div_y - div_xy));
        //把是x倍数但不是y倍数的给y公司 y还需要的人数
        long need_y = Math.max(0, cnt_y - (div_x - div_xy));
        //剩余既不是x倍数也不是y倍数的有 k-div_x-div_y+div_xy个人 两个公司都能去
        //只要满足剩余人数大于等于两家公司还需要的人数即可
        return need_x + need_y <= k - div_x - div_y + div_xy;
    }

}