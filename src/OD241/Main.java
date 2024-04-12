package OD241;

import java.util.*;

/**
 * @author Jacliscs
 * @description 用连续自然数之和来表达整数
 * @date 2024/3/24
 * @level 5
 * @score 100
 */

/**
 * 题目描述
 * 一个整数可以由连续的自然数之和来表示。
 * <p>
 * 给定一个整数，计算该整数有几种连续自然数之和的表达式，且打印出每种表达式
 * <p>
 * 输入描述
 * 一个目标整数T (1 <=T<= 1000)
 * <p>
 * 输出描述
 * 该整数的所有表达式和表达式的个数。
 * <p>
 * 如果有多种表达式，输出要求为：自然数个数最少的表达式优先输出，每个表达式中按自然数递增的顺序输出，具体的格式参见样例。
 * <p>
 * 在每个测试数据结束时，输出一行”Result:X”，其中X是最终的表达式个数。
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        //存放结果
        ArrayList<String> result = getResult(n);
        result.forEach(System.out::println);
    }

    public static ArrayList<String> getResult(int t) {
        //存放结果
        ArrayList<String> result = new ArrayList<>();
        //创建一个从1-n的数组，使用滑动窗口
        int[] arr = new int[t];
        for (int i = 0; i < t; i++) {
            arr[i] = i + 1;
        }
        int left = 0;
        int right = 1;
        //初始化和
        int sum = arr[left];
        //存放步骤和
        ArrayList<int[]> ans = new ArrayList<>();
        //当左指针到达数组末尾，结束循环
        while (left < t) {
            //如果小于n，则右指针移动
            if (sum > t) {
                sum -= arr[left++];
            } else if (sum == t) {
                //记录此时的数组元素 包括left 不包含right
                ans.add(Arrays.copyOfRange(arr, left, right));
                //窗口往右移动
                sum -= arr[left++];
                //此时right有可能已经到达数组末尾
                if (right >= t) {
                    break;
                }
                //没有到达边界则往右移动
                sum += arr[right++];

            } else {
                //sum<n 右指针往右移动
                sum += arr[right++];

            }
        }
        //排序 按长度升序
        ans.sort(Comparator.comparing(a -> a.length));
        ans.forEach(a -> {
            StringJoiner sj = new StringJoiner("+", t + "=", "");//前缀是n= 连接符是+ 后置是""
            Arrays.stream(a).forEach(i -> sj.add(i + ""));
            result.add(sj.toString());
        });

        result.add("Result:" + ans.size());
        return result;
    }
}