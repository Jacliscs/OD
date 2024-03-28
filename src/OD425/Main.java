package OD425;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 田忌赛马
 * @date 2024/3/28
 * @level 6
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD425
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    //尽可能多的使a[i]>b[i] 默认是0
    private static int maxBiggerCount = 0;
    //满足条件的a数组的排列个数
    private static int ans = 0;
    private static int[] a;
    private static int[] b;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        a = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        b = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        //a排序 减少复杂度 方便去重
        Arrays.sort(a);
        //全排列 比较
        dfs(0, new boolean[a.length], 0);
        System.out.println(ans);

    }

    public static void dfs(int index, boolean[] used, int biggerCount) {
        //返回上一层标志
        if (index >= a.length) {
            if (biggerCount > maxBiggerCount) {
                //刷新
                maxBiggerCount = biggerCount;
                ans = 1;
            } else if (biggerCount == maxBiggerCount) {
                ans++;
            }
            return;
        }

        //遍历比较 a[i]与b[index]
        for (int i = 0; i < a.length; i++) {
            if (used[i]) continue;
            //去重 需要先将a排序
            if (i > 0 && a[i] == a[i - 1] && !used[i - 1]) continue;
            used[i] = true;
            //递归
            dfs(index + 1, used, biggerCount + ((a[i] > b[index]) ? 1 : 0));
            //恢复
            used[i] = false;
        }
    }
}