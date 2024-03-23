package OD372;

import java.util.*;

/**
 * @author Jacliscs
 * @description 虚拟理财游戏
 * @date 2024/3/23
 * @level 6
 * @score 100
 */

/**
 * 题目描述
 * 在一款虚拟游戏中生活，你必须进行投资以增强在虚拟游戏中的资产以免被淘汰出局。
 * <p>
 * 现有一家Bank，它提供有若干理财产品 m 个，风险及投资回报不同，你有 N（元）进行投资，能接收的总风险值为X。
 * <p>
 * 你要在可接受范围内选择最优的投资方式获得最大回报。
 * <p>
 * 备注：
 * <p>
 * 在虚拟游戏中，每项投资风险值相加为总风险值；
 * 在虚拟游戏中，最多只能投资2个理财产品；
 * 在虚拟游戏中，最小单位为整数，不能拆分为小数；
 * 投资额*回报率=投资回报
 * 输入描述
 * 第一行：
 * <p>
 * 产品数（取值范围[1,20]）
 * 总投资额（整数，取值范围[1, 10000]）
 * 可接受的总风险（整数，取值范围[1,200]）
 * 第二行：产品投资回报率序列，输入为整数，取值范围[1,60]
 * <p>
 * 第三行：产品风险值序列，输入为整数，取值范围[1, 100]
 * <p>
 * 第四行：最大投资额度序列，输入为整数，取值范围[1, 10000]
 * <p>
 * 输出描述
 * 每个产品的投资额序列
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //产品数 总投资额 可接受的总风险
        int[] line1 = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int m = line1[0];//[1,20]
        int n = line1[1];//[1,10000]
        int x = line1[2];//[1,200]
        //投资回报率序列
        int[] backs = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //产品风险
        int[] risks = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //最大投资额度序列
        int[] invests = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //最大投资回报
        int max_invest_back = 0;
        //最小风险值
        int min_invest_risk = Integer.MAX_VALUE;
        //存放最后选择的iD和对应投资额
        Map<Integer, Integer> select = new HashMap<>();
        for (int i = 0; i < m; i++) {
            //如果当前产品风险大于最大风险，跳过
            if (risks[i] > x) {
                continue;
            }
            //如果当前产品风险小于最大风险
            if (risks[i] <= x) {
                //产品i的投资额
                int invest_i = Math.min(invests[i], n);
                //产品i的投资回报
                int back_i = invest_i * backs[i];
                //如果只投资i产品，投资回报高于当前的最优策略，或者等于最优但风险值更小
                if (back_i > max_invest_back || back_i == max_invest_back && risks[i] < min_invest_risk) {
                    //更新最大投资回报
                    max_invest_back = back_i;
                    //更新最小风险
                    min_invest_risk = risks[i];
                    //把之前选择清空，重新选择
                    select.clear();
                    select.put(i, invest_i);
                }
            }
            //购买两个产品
            for (int j = i + 1; j < m; j++) {
                //如果总风险不超过risk_all 则都投资
                if (risks[i] + risks[j] <= x) {
                    //产品i j分别的投资额
                    int invest_i;
                    int invest_j;
                    //如果投资回报率不相同，则谁回报率高，谁多投
                    if (backs[i] != backs[j]) {
                        if (backs[i] > backs[j]) {
                            //总和不能超过n
                            invest_i = Math.min(invests[i], n);
                            invest_j = Math.min(invests[j], n - invest_i);
                        } else {
                            invest_j = Math.min(invests[j], n);
                            invest_i = Math.min(invests[i], n - invest_j);
                        }
                    } else {
                        //产品回报率相同，谁风险小，谁多投
                        if (risks[i] < risks[j]) {
                            invest_i = Math.min(invests[i], n);
                            invest_j = Math.min(invests[j], n - invest_i);
                        } else {
                            invest_j = Math.min(invests[j], n);
                            invest_i = Math.min(invests[i], n - invest_j);
                        }
                    }
                    //投资两个产品的总投资回报
                    int invest_back = invest_i * backs[i] + invest_j * backs[j];
                    //总风险
                    int invest_risk = risks[i] + risks[j];
                    //如果大于当前最优投资回报 或等于但风险更小，则更新
                    if (invest_back > max_invest_back || invest_back == max_invest_back && invest_risk < min_invest_risk) {
                        max_invest_back = invest_back;
                        min_invest_risk = invest_risk;
                        //清空当前最优策略
                        select.clear();
                        //添加当前的最优策略 id 投资额
                        if (invest_i > 0) {
                            select.put(i, invest_i);
                        }
                        if (invest_j > 0) {
                            select.put(j, invest_j);
                        }
                    }
                }
            }
        }
        //存放结果
        StringJoiner sj = new StringJoiner(" ");
        for (int k = 0; k < m; k++) {
            //如果id在select中，则添加对应的投资额
            if (select.containsKey(k)) {
                sj.add(String.valueOf(select.get(k)));
            } else {
                sj.add("0");
            }
        }
        System.out.println(sj);
    }
}