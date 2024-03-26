package OD399;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 贪吃的猴子
 * @date 2024/3/26
 * @level 7
 * @score 200
 */

/**
 * https://hydro.ac/d/HWOD2023/p/OD399
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    private static int[] numbers;
    private static int len;
    private static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //数组的长度
        len = Integer.parseInt(sc.nextLine());
        //每个数字
        numbers = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //能吃的次数
        n = sc.nextInt();
        System.out.println(getResult());

    }

    public static int getResult() {
        //左右滑动窗口共N个，必连续，左边x个，右边就是N-x个
        //初始右边0个
        int right = 0;
        //初始左边N个
        int left = 0;
        for (int i = 0; i < n; i++) {
            left += numbers[i];
        }
        //如果N=n，全吃
        if (n == len) {
            return left;
        }

        //记录左右两边的和，并利用双指针移动 左- 右+
        int sum = left + right;
        //题解，找最大的
        int ans = sum;

        //指向左边最右的指针 将要失去的
        int l = n - 1;
        //指向右边开始的指针，后续往左移动
        int r = len - 1;
        while (l >= 0) {
            sum += numbers[r--] - numbers[l--];
            ans = Math.max(ans, sum);
        }
        return ans;
    }
}