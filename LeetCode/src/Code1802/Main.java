package Code1802;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 有界数组中指定下标处的最大值
 * @date 2024/4/21
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/maximum-value-at-a-given-index-in-a-bounded-array/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    /**
     * 返回长度为cnt，最大值为max的数组的和
     *
     * @param max
     * @param cnt
     * @return long
     */
    public static long getSum(long max, long cnt) {
        //数组是等差数列:首项为max，等差是-1，当减到1之后保持1不变
        //当max >= cnt 时，不会有多余的1，完全等差数列
        //尾项是 max + (cnt-1)*-1 = max - cnt +1;
        //数列和=(首项+尾项)*cnt/2 = (max + max - cnt + 1)*cnt / 2;
        //当max < cnt 时，等差数列项数为max 尾项为1 并多余了 cnt-max个1
        //数列和= (max+1)*max/2 + cnt-max;
        return max < cnt ? (max + 1) * max / 2 + cnt - max : (max + max - cnt + 1) * cnt / 2;

    }

    public int maxValue(int n, int index, int maxSum) {
        //当确定下标处的值后，这个数组的最小maxSum就确定了，左右两边依次减1，减到1之后保持1不变
        //数组值是正整数
        int min = 1;
        int max = maxSum;

        //题解
        int ans = 0;

        while (min <= max) {
            int mid = (min + max) / 2;
            //如果index处的值为mid，左右两边依次递减到1，数组和不超过maxSum，则是可能解
            //mid值为左区间内最大的 左边区间个数为index+1
            //mid-1是右区间最大的，个数为n-index-1;
            if (getSum(mid, index + 1) + getSum(mid - 1, n - index - 1) <= maxSum) {
                ans = Math.max(ans, mid);
                //index处的值可以更大
                min = mid + 1;
            } else {
                //index的值更小
                max = mid - 1;
            }

        }

        return ans;
    }

}