package OD223;

import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 数组连续和
 * @date 2024/3/24
 * @level 6
 * @score 100
 */

/**
 * 题目描述
 * 给定一个含有N个正整数的数组, 求出有多少个连续区间（包括单个正整数）, 它们的和大于等于x。
 * <p>
 * 输入描述
 * 第一行两个整数N x（0 < N <= 100000, 0 <= x <= 10000000)
 * <p>
 * 第二行有N个正整数（每个正整数小于等于100)。
 * <p>
 * 输出描述
 * 输出一个整数，表示所求的个数。
 * <p>
 * 注意：此题对效率有要求，暴力解法通过率不高，请考虑高效的实现方式。
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //数组个数
        int n = sc.nextInt();
        //需要大于等于x
        int x = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        System.out.println(getResult(nums, x));

    }

    //有多少个连续数组和大于等于x
    public static long getResult(int[] nums, int x) {
        long len = nums.length;
        long count = 0;
        //因为有效率要求，不能每次都求和，用前缀和来表示
        long[] preSum = new long[(int) (len + 1)];
        //preSum[i]表示前面i个数的和
        for (int i = 1; i <= len; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        //方法一：双重for循环
        //for (int i=0;i<len;i++){
        //    for (int j=i+1;j<=len;j++){
        //        long sum = preSum[j]-preSum[i];
        //        if (sum>=x){
        //            //如果此时[i,j]的和大于sum，则后续大于j小于等于len的都会大于sum
        //            count += len-j+1;
        //            break;
        //        }
        //    }
        //}
        //方法二：while循环
        int l = 0;
        int r = 1;
        while (r <= len) {
            long sum = preSum[r] - preSum[l];
            if (sum >= x) {
                count += len - r + 1;
                //窗口滑动
                l++;
                r = l + 1;
            } else {
                r++;
            }
        }

        return count;
    }

}