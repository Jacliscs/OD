package OD365.easy;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 传递悄悄话-逆向
 * @date 2024/4/13
 * @level
 * @score
 * @url
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //输入二叉树层序遍历
        int[] tree = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        //获得最大接受悄悄话的时间
        System.out.println(getResult(tree));

    }

    public static int getResult(int[] tree) {
        //记录题解
        int maxTime = 0;

        //子节点收到悄悄话的时间=父节点传递到自己花费时间+父节点收到悄悄话的时间
        //子节点索引i 父节点(i-1)/2
        int len = tree.length;

        //如果只有一个头结点
        if (len == 1) {
            return tree[0];
        } else {
            for (int i = 1; i < len; i++) {
                //父节点
                int p = (i - 1) / 2;
                //自己收到悄悄话的时间=父节点传递给自己需要的时间+父节点收到悄悄话的时间
                tree[i] = tree[p] + tree[i];
                //更新最大接受时间
                maxTime = Math.max(maxTime, tree[i]);
            }

        }

        return maxTime;
    }
}