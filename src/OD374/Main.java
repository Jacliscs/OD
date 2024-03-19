package OD374;

import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 分披萨
 * @date 2024/3/19
 * @level 7
 * @score 100
 * @type 递归
 */

/**
 * 题目描述
 * "吃货"和"馋嘴"两人到披萨店点了一份铁盘（圆形）披萨，并嘱咐店员将披萨按放射状切成大小相同的偶数个小块。但是粗心的服务员将披萨切成了每块大小都完全不同奇数块，且肉眼能分辨出大小。
 * <p>
 * 由于两人都想吃到最多的披萨，他们商量了一个他们认为公平的分法：从"吃货"开始，轮流取披萨。除了第一块披萨可以任意选取外，其他都必须从缺口开始选。
 * <p>
 * 他俩选披萨的思路不同。"馋嘴"每次都会选最大块的披萨，而且"吃货"知道"馋嘴"的想法。
 * <p>
 * 已知披萨小块的数量以及每块的大小，求"吃货"能分得的最大的披萨大小的总和。
 * <p>
 * 输入描述
 * 第 1 行为一个正整数奇数 N，表示披萨小块数量。
 * <p>
 * 3 ≤ N < 500
 * 接下来的第 2 行到第 N + 1 行（共 N 行），每行为一个正整数，表示第 i 块披萨的大小
 * <p>
 * 1 ≤ i ≤ N
 * 披萨小块从某一块开始，按照一个方向次序顺序编号为 1 ~ N
 * <p>
 * 每块披萨的大小范围为 [1, 2147483647]
 * 输出描述
 * "吃货"能分得到的最大的披萨大小的总和。
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    //披萨
    static int[] pizza;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //披萨块数 输入保证一定合法且为奇数 ,3<=n<500
        int n = sc.nextInt();
        pizza = new int[n];
        for (int i = 0; i < n; i++) {
            pizza[i] = sc.nextInt();
        }
        //吃货吃的最多披萨数
        long sum = 0;
        //第一块是吃货选，遍历每一种结果
        for (int i = 0; i < n; i++) {
            sum = Math.max(sum, getMaxOfPizza(check(i - 1), check(i + 1)) + pizza[i]);
        }
        System.out.println(sum);

    }

    //获得当前缺口下吃货能吃的最大披萨大小，递归  没有缓存优化，会超时
    public static long getMaxOfPizza(int left, int right) {
        // 吃货吃完该馋嘴吃
        // 馋嘴一定会吃当前缺口更大的那一块
        if (pizza[left] > pizza[right]) {
            left = check(left - 1);
        } else {
            right = check(right + 1);
        }
        //如果只剩一块披萨了，这块披萨一定是吃货吃
        //这也是结束递归条件
        if (left == right) {
            return pizza[left];//返回pizza[right]一样
        } else {
            //如果还剩多块披萨，则递归返回吃左边、吃右边的结果中的最大值
            return Math.max(getMaxOfPizza(check(left - 1), right) + pizza[left], getMaxOfPizza(left, check(right + 1)) + pizza[right]);
        }

    }

    //保证缺口下标不越界
    public static int check(int index) {
        //如果小于0，则跳到最后一块披萨
        if (index < 0) {
            return pizza.length - 1;
        } else if (index >= pizza.length) {
            return 0;
        }
        return index;
    }

}