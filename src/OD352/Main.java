package OD352;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 开源项目热度榜单
 * @date 2024/3/18
 * @level 3
 * @type 多条件排序
 */

/**
 * 题目描述
 * <p>
 * 某个开源社区希望将最近热度比较高的开源项目出一个榜单，推荐给社区里面的开发者。
 * 对于每个开源项目，开发者可以进行关注（watch）、收藏（star）、fork、提issue、提交合并请求（MR）等。
 * 数据库里面统计了每个开源项目关注、收藏、fork、issue、MR的数量，开源项目的热度根据这5个维度的加权求和进行排序。
 * H = W(watch) x #watch + W(star) x #star + W(fork) x #fork + W(issue) x #issue + W(mr) x #mr
 * H 表示热度值
 * W(watch)、W(star)、W(fork)、W(issue)、W(mr) 分别表示5个统计维度的权重
 * #watch、#star、#fork、#issue、#mr 分别表示5个统计维度的统计值
 * 榜单按照热度值降序排序，对于热度值相等的，按照项目名字转换为全小写字母后的字典序排序（'a','b','c',...,'x','y','z'）。
 * <p>
 * 输入描述
 * 第一行输入为N，表示开源项目的个数，0 ＜ N ＜100。
 * <p>
 * 第二行输入为权重值列表，一共 5 个整型值，分别对应关注、收藏、fork、issue、MR的权重，权重取值 0 < W ≤ 50。
 * <p>
 * 第三行开始接下来的 N 行为开源项目的统计维度，每一行的格式为：
 * <p>
 * name nr_watch nr_start nr_fork nr_issue nr_mr
 * <p>
 * 其中 name 为开源项目的名字，由英文字母组成，长度 ≤ 50，其余 5 个整型值分别为该开源项目关注、收藏、fork、issue、MR的数量，数量取值 0 < nr ≤ 1000。
 * <p>
 * 输出描述
 * 按照热度降序，输出开源项目的名字，对于热度值相等的，按照项目名字转换为全小写后的字典序排序（'a' > 'b' > 'c' > ... > 'x' > 'y' > 'z'）。
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //开源项目个数
        int n = Integer.parseInt(sc.nextLine());
        //权重值列表 大小固定为5
        int[] weight = new int[5];
        String[] tempWeight = sc.nextLine().split(" ");
        for (int i = 0; i < 5; i++) {
            weight[i] = Integer.parseInt(tempWeight[i]);
        }
        //存放统计维度  第一项名称，最后一项是总分
        String[][] statistic = new String[n][7];
        for (int i = 0; i < n; i++) {
            String[] temp = sc.nextLine().split(" ");
            //放维度与总维度
            int sum = 0;
            //存名字
            statistic[i][0] = temp[0];
            for (int j = 1; j <= 5; j++) {
                statistic[i][j] = temp[j];
                sum += Integer.parseInt(temp[j]) * weight[j - 1];
            }
            //放总维度的和
            statistic[i][6] = String.valueOf(sum);
        }
        //statistic： 名字 关注 收藏 fork issue MR 总分
        //            0
        Arrays.sort(statistic, (o1, o2) -> {
            //先按总分排序，总分相同按名字转小写字典排序
            if (Integer.parseInt(o1[6]) != Integer.parseInt(o2[6])) {
                return Integer.parseInt(o2[6]) - Integer.parseInt(o1[6]);
            } else {
                return o1[0].toLowerCase().compareTo(o2[0].toLowerCase());
            }
        });
        //输出排序后的名字
        for (int i = 0; i < n; i++) {
            System.out.println(statistic[i][0]);
        }

    }
}