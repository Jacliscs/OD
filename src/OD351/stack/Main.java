package OD351.stack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 转盘寿司-单调栈做法
 * @date 2024/4/12
 * @level
 * @score
 * @url
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //寿司价格
        int[] prices = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        //输出获得优惠后的价格
        int[] ans = getResult(prices);
        Arrays.stream(ans).forEach(price -> System.out.print(price + " "));
    }

    public static int[] getResult(int[] prices) {
        int length = prices.length;

        //题解：每个寿司加上第一个比自己价格低的寿司后的价格
        //先初始化为每个寿司的价格
        int[] ans = Arrays.copyOf(prices, length);

        //单调栈：栈底到栈顶单调递增，压栈元素是栈顶元素在nums顺序后面的值
        //每当压栈时，比较栈顶元素 > 压栈元素 ？ 若是，则说明找到了栈顶元素的下一个更小值，此时弹栈，压栈元素继续和新栈顶元素比较大小，直到栈顶元素 <= 压栈元素，则停止比较，执行压栈
        LinkedList<Integer> stack = new LinkedList<>();

        //因为遍历到末尾可以回到开头，所以可以遍历两圈
        for (int i = 0; i < length * 2; i++) {
            //防止下标越界 i是压栈元素对应的元素下标
            int price_i = prices[i % length];

            while (stack.size() > 0) {
                //j是栈顶元素对应的下标
                int j = stack.getLast();

                //如果压栈元素price_i < 栈顶元素price[j]，则说明j元素找到第一个比自己小的
                if (price_i < prices[j]) {
                    //栈顶元素 j 已经找到比自己小的，移出
                    stack.removeLast();

                    //把第一个比自己小的元素price_i加入到ans
                    ans[j] += price_i;
                } else {
                    //跳出while循环
                    break;
                }
            }

            //第一圈才执行入栈
            if (i < length) stack.addLast(i);
        }

        return ans;

    }
}