package OD352;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 开源项目热度榜单
 * @date 2024/3/18
 * @level 3
 * @type 多条件排序
 * @score 100
 * @url https://hydro.ac/d/HWOD2023/p/OD352
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
    //自定义类 项目名 总热度
    static class Project {
        String name;
        int hot;

        public Project(String name, int hot) {
            this.name = name;
            this.hot = hot;
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //开源项目个数
        int n = Integer.parseInt(sc.nextLine());
        //权重值列表 大小固定为5
        int[] weights = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Project[] projects = new Project[n];

        //项目名 和各项分 最后统计总分
        for (int i = 0; i < n; i++) {
            String name = sc.next();
            //总热度值
            int hot = 0;
            for (int j = 0; j < 5; j++) {
                hot += sc.nextInt() * weights[j];
            }
            projects[i] = new Project(name, hot);
        }

        //排序：先按总热力值降序，相同则按转小写后的项目名字排序
        Arrays.sort(projects, (a, b) -> {
            if (a.hot != b.hot) return b.hot - a.hot;
            else return a.name.toLowerCase().compareTo(b.name.toLowerCase());
        });

        //打印
        for (Project project : projects) {
            System.out.println(project.name);
        }
    }
}