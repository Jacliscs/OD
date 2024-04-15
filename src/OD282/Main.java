package OD282;

import java.util.*;

/**
 * @author Jacliscs
 * @description 查找接口成功率最优时间段
 * @date 2024/3/24
 * @level 6
 * @score 100
 */

/**
 * 题目描述
 * 服务之间交换的接口成功率作为服务调用关键质量特性，某个时间段内的接口失败率使用一个数组表示，
 * <p>
 * 数组中每个元素都是单位时间内失败率数值，数组中的数值为0~100的整数，
 * <p>
 * 给定一个数值(minAverageLost)表示某个时间段内平均失败率容忍值，即平均失败率小于等于minAverageLost，
 * <p>
 * 找出数组中最长时间段，如果未找到则直接返回NULL。
 * <p>
 * 输入描述
 * 输入有两行内容，第一行为{minAverageLost}，第二行为{数组}，数组元素通过空格(” “)分隔，
 * <p>
 * minAverageLost及数组中元素取值范围为0~100的整数，数组元素的个数不会超过100个。
 * <p>
 * 输出描述
 * 找出平均值小于等于minAverageLost的最长时间段，输出数组下标对，格式{beginIndex}-{endIndx}(下标从0开始)，
 * <p>
 * 如果同时存在多个最长时间段，则输出多个下标对且下标对之间使用空格(” “)拼接，多个下标对按下标从小到大排序。
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //minAverageLost
        int minAverageLost = Integer.parseInt(sc.nextLine());
        int[] arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(arr, minAverageLost));
    }


    //找出平均值小于等于n的最长时间段，输出对应下标 如0-1 3-4 多个下标对按从小到大排序
    public static String getResult(int[] arr, int minAverageLost) {
        //存放最长时间段 可以有多个
        List<int[]> list = new ArrayList<>();
        //初始化最大长度
        int maxLen = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                //区间[i,j]的和
                int sum = getSum(arr, i, j);
                int len = j - i + 1;
                int lost = len * minAverageLost;
                if (sum <= lost) {
                    //如果len>maxLen 则清空list，并添加最新的，重置maxLen
                    //如果len=maxLen，则直接添加进list
                    if (len >= maxLen) {
                        if (len > maxLen) {
                            //清空之前maxLen长度的list
                            list.clear();
                        }
                        //然后再添加
                        list.add(new int[]{i, j});
                        maxLen = len;
                    }
                }
            }
        }
        //未找到则返回NULL
        if (list.isEmpty()) {
            return "NULL";
        }
        //按开始下标升序排序
        list.sort(Comparator.comparingInt(a -> a[0]));
        //添加进结果
        StringJoiner sj = new StringJoiner(" ");
        list.forEach(t -> sj.add(t[0] + "-" + t[1]));
        //for (int[] temp : list) {
        //    sj.add(temp[0] + "-" + temp[1]);
        //}
        return sj.toString();
    }

    //从数组中start加到end位置的和
    public static int getSum(int[] arr, int start, int end) {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += arr[i];
        }
        return sum;
    }
}