package OD349;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 游戏分组
 * @date 2024/3/23
 * @level 7
 * @score 100
 * @type 回溯算法
 */

/**
 * 题目描述
 * 部门准备举办一场王者荣耀表演赛，有 10 名游戏爱好者参与，分为两队，每队 5 人。
 * <p>
 * 每位参与者都有一个评分，代表着他的游戏水平。为了表演赛尽可能精彩，我们需要把 10 名参赛者分为示例尽量相近的两队。
 * <p>
 * 一队的实力可以表示为这一队 5 名队员的评分总和。
 * <p>
 * 现在给你 10 名参与者的游戏水平评分，请你根据上述要求分队，最后输出这两组的实力差绝对值。
 * <p>
 * 例：10 名参赛者的评分分别为：5 1 8 3 4 6 7 10 9 2，分组为（1 3 5 8 10）和（2 4 6 7 9），两组实力差最小，差值为1。有多种分法，但是实力差的绝对值最小为1。
 * <p>
 * 输入描述
 * 10个整数，表示10名参与者的游戏水平评分。范围在 [1, 10000] 之间。
 * <p>
 * 输出描述
 * 1个整数，表示分组后两组实力差绝对值的最小值。
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //10名玩家的评分
        int[] scores = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(scores));
    }

    //找到两队游戏水平最小绝对值差值
    public static int getResult(int[] scores) {
        //先按升序排序
        Arrays.sort(scores);
        //存放所有5人组合可能的战斗力之和
        ArrayList<Integer> res = new ArrayList<>();
        //dfs求10选5的去重组合，并将5人战斗力之和加入res
        dfs(scores, 0, 0, 0, res);
        //战斗力总和
        int sum = Arrays.stream(scores).sum();
        //res 中存放的是所有5人战斗力的集合，假如1队战斗力为sum1,另一队战斗力就是sum-sum1 差值的绝对值就是Math.abs(sum-2*sum1)
        //然后求差值的最小值
        int min = res.stream().map(sum1 -> Math.abs(sum - 2 * sum1)).min((a, b) -> a - b).orElse(0);
        return min;
    }

    //10选5去重组合

    /**
     * 10选5去重组合，并将5人组合的战斗力之和加入res
     *
     * @param scores 战斗力数组
     * @param index  开始的下标
     * @param level  树的层级
     * @param sum    当前的战斗力之和
     * @param res    存放结果
     * @return void
     * @create 2024/3/23 15:30
     */
    public static void dfs(int[] scores, int index, int level, int sum, ArrayList<Integer> res) {
        //返回上一层标记，当level=5时，则已经添加了5人
        if (level == 5) {
            res.add(sum);
            return;
        }
        //没满5人，则添加，如果战斗力一样则去重
        for (int i = index; i < scores.length; i++) {
            //遍历到i后面的时候，如果跟前一个人战斗力一样，则去重，因为最后res求的是总和
            if (i > index && scores[i] == scores[i - 1]) {
                continue;
            }
            dfs(scores, i + 1, level + 1, sum + scores[i], res);
        }

    }

}