package OD351;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 转盘寿司
 * @date 2024/3/16
 * @level 5
 * @type 数据结构、栈、单调栈
 */

/**
 * 题目描述
 * 寿司店周年庆，正在举办优惠活动回馈新老客户。
 * <p>
 * 寿司转盘上总共有 n 盘寿司，prices[i] 是第 i 盘寿司的价格，
 * <p>
 * 如果客户选择了第 i 盘寿司，寿司店免费赠送客户距离第 i 盘寿司最近的下一盘寿司 j，前提是 prices[j] < prices[i]，如果没有满足条件的 j，则不赠送寿司。
 * <p>
 * 每个价格的寿司都可无限供应。
 * <p>
 * 输入描述
 * 输入的每一个数字代表每盘寿司的价格，每盘寿司的价格之间使用空格分隔，例如:
 * <p>
 * 3 15 6 14
 * <p>
 * 表示：
 * <p>
 * 第 0 盘寿司价格 prices[0] 为 3
 * 第 1 盘寿司价格 prices[1] 为 15
 * 第 2 盘寿司价格 prices[2] 为 6
 * 第 3 盘寿司价格 prices[3] 为 14
 * 寿司的盘数 n 范围为：1 ≤ n ≤ 500
 * <p>
 * 每盘寿司的价格 price 范围为：1 ≤ price ≤ 1000
 * <p>
 * 输出描述
 * 输出享受优惠后的一组数据，每个值表示客户选择第 i 盘寿司时实际得到的寿司的总价格。使用空格进行分隔，例如：
 * <p>
 * 3 21 9 17
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //将接受到的数据用空格分隔，并转为整型数组
        int[] prices = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] newPrices = getDiscount(prices);
        for (int i: newPrices){
            System.out.print(i + " ");
        }
    }

    //返回享受一轮优惠后的寿司价格
    public static int[] getDiscount(int[] prices) {
        int[] newPrices = new int[prices.length];
        //每一个往后遍历，遇到第一个价格比自己低的，则+=
        for (int i = 0; i < prices.length; i++) {
            newPrices[i] = prices[i];
        }

        for (int i = 0; i < prices.length; i++) {
            int flag = 0;
            //先从i+1往后遍历
            for (int j = i + 1; j < prices.length; j++) {
                //从i往后遍历，找到第一个比自己价格低的
                if (prices[j] < prices[i] && flag==0) {
                    newPrices[i] += prices[j];
                    flag = 1;
                    break;
                }
            }
            //如果往后遍历没找到，则从开头往i-1遍历
            if (flag == 0) {
                for (int k = 0; k < i; k++) {
                    if (prices[k] < prices[i]) {
                        newPrices[i] += prices[k];
                        flag = 1;
                        break;
                    }
                }
            }
        }
        return newPrices;
    }
}