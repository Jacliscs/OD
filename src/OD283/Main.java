package OD283;

import java.util.*;

/**
 * @author Jacliscs
 * @description 最大N个数与最小N个数的和
 * @date 2024/3/17
 * @level 6
 */

/**
 * 题目描述
 * 给定一个数组，编写一个函数来计算它的最大N个数与最小N个数的和。你需要对数组进行去重。
 * <p>
 * 说明：
 * <p>
 * 数组中数字范围[0, 1000]
 * 最大N个数与最小N个数不能有重叠，如有重叠，输入非法返回-1
 * 输入非法返回-1
 * 输入描述
 * 第一行输入M， M标识数组大小
 * 第二行输入M个数，标识数组内容
 * 第三行输入N，N表达需要计算的最大、最小N个数
 * 输出描述
 * 输出最大N个数与最小N个数的和
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //数字个数
        int m = Integer.parseInt(sc.nextLine());
        int[] arr = new int[m];
        for (int i = 0; i < m; i++) {
            arr[i] = sc.nextInt();
        }
        //最大最小个数
        int n = sc.nextInt();
        System.out.println(getSum(arr, n));
    }

    //返回数组中最大最小n个数的和，有重叠返回-1
    public static int getSum(int[] arr, int n) {
        if (arr == null || arr.length == 0 || n <= 0) {
            return -1;
        }
        //数字范围为0-1000
        Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            if (num < 0 || num > 1000) {
                return -1;
            }
            set.add(num);
        }

        //去重后如果set的大小小于2n，则一定会重叠
        if (set.size() < 2 * n) {
            return -1;
        }
        //排序set中的数
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        //最大最小n个数的和
        int ans = 0;
        //左指针
        int l = 0;
        //右指针
        int r = list.size() - 1;
        while (n > 0) {
            ans += list.get(l) + list.get(r);
            l++;
            r--;
            n--;
        }

        return ans;
    }
}