package OD366;

import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 最多购买宝石数目
 * @date 2024/3/21
 * @level 4
 * @score 100
 */

/**
 * 题目描述
 * 橱窗里有一排宝石，不同的宝石对应不同的价格，宝石的价格标记为 gems[i]
 * <p>
 * 0 ≤ i < n
 * n = gems.length
 * 宝石可同时出售0个或多个，如果同时出售多个，则要求出售的宝石编号连续；
 * <p>
 * 例如客户最大购买宝石个数为m，购买的宝石编号必须为：gems[i]，gems[i+1]，...，gems[i+m-1]
 * <p>
 * 0 ≤ i < n
 * m ≤ n
 * 假设你当前拥有总面值为 value 的钱，请问最多能购买到多少个宝石，如无法购买宝石，则返回0。
 * <p>
 * 输入描述
 * 第一行输入n，参数类型为int，取值范围：[0,10^6]，表示橱窗中宝石的总数量。
 * <p>
 * 之后 n 行分别表示从第0个到第n-1个宝石的价格，即 gems[0] 到 gems[n-1] 的价格，类型为int，取值范围：(0,1000]。
 * <p>
 * 之后一行输入v，类型为int，取值范围：[0,10^9]，表示你拥有的钱。
 * <p>
 * 输出描述
 * 输出int类型的返回值，表示最大可购买的宝石数量。
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //宝石数量
        int n = sc.nextInt();
        //如果n=0，则返回0
        int[] gems = new int[n];
        for (int i = 0; i < n; i++) {
            gems[i] = sc.nextInt();
        }
        //拥有的钱
        int v = sc.nextInt();
        System.out.println(maxBuy(gems, v));
    }

    //最多能连续买的宝石数量
    public static int maxBuy(int[] gems, int v) {
        int ans = 0;
        //双指针
        int left = 0;
        int right = 0;
        //双指针范围内宝石的和
        int window_sum = 0;
        outer:
        while (right < gems.length) {
            //先移动右指针
            window_sum += gems[right];
            //如果没有超过v，则r继续右移
            if (window_sum <= v) {
                right++;
            } else {
                //[left,right]范围中的价格超过v，[left,right-1]中的没有超过v，记录
                ans = Math.max(ans, right - left);
                //移动左指针
                while (left < right) {
                    //减去左边的
                    window_sum -= gems[left++];
                    //去掉最左的后，小于v，则right右移
                    if (window_sum <= v) {
                        right++;
                        //跳转到outer，继续右移right
                        continue outer;
                    }
                }
                //如果right位置的价格太高，大于v，则左指针卡在right-1的位置，把两个指针都移动到right+1的位置
                left = ++right;
                //刷新
                window_sum = 0;
            }
        }
        //收尾
        if (window_sum <= v) {
            ans = Math.max(ans, right - left);
        }
        return ans;
    }

}