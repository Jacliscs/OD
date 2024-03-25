package OD389;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 高效货运
 * @date 2024/3/25
 * @level 4
 * @score 200
 */

/**
 * 题目描述
 * 老李是货运公司承运人，老李的货车额定载货重量为 wt。
 * <p>
 * 现有两种货物：
 * <p>
 * 货物 A 单件重量为 wa，单件运费利润为 pa
 * 货物 B 单件重量为 wb，单件运费利润为 pb
 * 老李每次发车时载货总重量刚好为货车额定的载货重量 wt，车上必须同时有货物 A 和货物 B ，货物A、B不可切割。
 * <p>
 * 老李单次满载运输可获得的最高利润是多少？
 * <p>
 * 输入描述
 * 第一列输入为货物 A 的单件重量 wa
 * <p>
 * 0 < wa < 10000
 * 第二列输入为货物 B 的单件重量 wb
 * <p>
 * 0 < wb < 10000
 * 第三列输入为货车的额定载重 wt
 * <p>
 * 0 < wt < 100000
 * 第四列输入为货物 A 的单件运费利润 pa
 * <p>
 * 0 < pa < 1000
 * 第五列输入为货物 B 的单件运费利润 pb
 * <p>
 * 0 < pb < 1000
 * 输出描述
 * 单次满载运输的最高利润
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //A的单件重量
        int wa = sc.nextInt();
        //B的单件重量
        int wb = sc.nextInt();
        //额定载重 每次都必定满载且必须有A和B
        int wt = sc.nextInt();
        //A的单件利润
        int pa = sc.nextInt();
        //B的单件利润
        int pb = sc.nextInt();
        System.out.println(getResult(wa, wb, wt, pa, pb));
    }

    //返回单词满载运输的最高利润
    public static int getResult(int wa, int wb, int wt, int pa, int pb) {
        //找到wa和wb满载wt的组合，乘以对应的利润，得到所有利润，找到最大值
        //存放能整除的集合
        //ArrayList<int[]> list = new ArrayList<>();
        //a最多能取到的个数 如wa=10 wt=40 但是必须包含b，所有wa最多只能有3个
        int max_num_a = wt / wa + (wt % wa == 0 ? -1 : 0);
        int max_profit = 0;
        for (int i = 1; i <= max_num_a; i++) {
            //如果剩下的能整除wb，则可能是一个组合
            if ((wt - wa * i) % wb == 0) {
                int j = (wt - wa * i) / wb;
                max_profit = Math.max(max_profit, i * pa + j * pb);
            }
        }
        ////存放可能的利润值
        //ArrayList<Integer> profit = new ArrayList<>();
        //for (int[] an : list){
        //    int p = an[0]*pa + an[1]*pb;
        //    profit.add(p);
        //}
        //int max_profit = profit.stream().max(Integer::compareTo).orElse(0);
        return max_profit;
    }


}