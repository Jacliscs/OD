package OD419;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 贪心歌手
 * @date 2024/3/30
 * @level 6
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD419
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {

    private static int t;
    private static int n;
    private static int costTime;
    private static int[][] city;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //总天数
        t = sc.nextInt();
        //n个城市
        n = sc.nextInt();

        //花费的时间
        costTime = 0;
        for (int i = 0; i <= n; i++) {
            costTime += sc.nextInt();
        }
        //城市第一天赚的钱 和 递减的钱
        city = new int[n][2];
        for (int i = 0; i < n; i++) {
            city[i][0] = sc.nextInt();//第一天花费
            city[i][1] = sc.nextInt();//递减
        }

        //最多可以赚多少钱
        System.out.println(getResult());

    }

    public static int getResult() {
        //能用于赚钱的时间
        int remain = t - costTime;

        //如果没剩，就不唱了
        if (remain <= 0) {
            return 0;
        }
        //创建优先队列，把钱赚的最少的放前面，如果后续remain满了，就把后续需要添加的与第一位比较
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
        for (int[] tmp : city) {
            int profit = tmp[0];
            int decrease = tmp[1];

            //每一个城市的钱都赚完
            while (profit > 0) {
                if (pq.size() >= remain) {
                    //pq只能赚remain天数的钱，大于的要与第一位判断，如果比第一位大，就更换，否则就跳过
                    if (profit > pq.peek()) {
                        pq.poll();
                    } else {
                        break;
                    }
                }

                //pq还没有达到remain上限 或者 把pq里面更小的弹出
                pq.add(profit);

                //递减
                profit -= decrease;
            }
        }
        return pq.stream().reduce(Integer::sum).orElse(0);
    }

}