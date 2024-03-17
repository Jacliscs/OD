package OD413;

import java.util.*;

/**
 * @author Jacliscs
 * @description 测试用例执行计划
 * @date 2024/3/17
 */

/**
 * 题目描述
 * 某个产品当前迭代周期内有 N 个特性（F1,F2,......FN）需要进行覆盖测试，每个特性都被评估了对应的优先级，特性使用其 ID 作为下标进行标识。
 * <p>
 * 设计了 M 个测试用例（T1,T2,......,TM），每个测试用例对应一个覆盖特性的集合，测试用例使用其 ID 作为下标进行标识，测试用例的优先级定义为其覆盖的特性的优先级之和。
 * <p>
 * 在开展测试之前，需要制定测试用例的执行顺序，规则为：优先级大的用例先执行，如果存在优先级相同的用例，用例 ID 小的先执行。
 * <p>
 * 输入描述
 * 第一行输入为 N 和 M，
 * <p>
 * N 表示特性的数量，0 < N ≤ 100
 * M 表示测试用例的数量，0 < M ≤ 100
 * 之后 N 行表示特性 ID=1 到特性 ID=N 的优先级，
 * <p>
 * 再接下来 M 行表示测试用例 ID=1 到测试用例 ID=M 关联的特性的 ID 的列表。
 * <p>
 * 输出描述
 * 按照执行顺序（优先级从大到小）输出测试用例的 ID，每行一个ID。
 * <p>
 * 测试用例覆盖的 ID 不重复
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //特性数量
        //int n = sc.nextInt();
        ////测试用例数量
        //int m = sc.nextInt();
        int[] temp = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = temp[0];
        int m = temp[1];
        //存放单个特性id 及 对应优先级 下标从1开始
        int[] priority = new int[n + 1];
        for (int i = 1; i <= n; i++){
            priority[i] = Integer.parseInt(sc.nextLine());
        }

        //测试用例 方便对应，从下标1开始
        String[][]  testCase = new String[m+1][];
        //存放测试用例id 和 优先级
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= m; i++){
            testCase[i] = sc.nextLine().split(" ");
            //优先级求和
            int sum = 0;
            for (int j = 0; j < testCase[i].length; j++){
                sum += priority[Integer.parseInt(testCase[i][j])];
            }
            //存放用例id 及 优先级
            map.put(i, sum);
        }
        StringJoiner sj = new StringJoiner(" ");
        //对map按优先级降序排列，优先级相同时按出现顺序排列
        map.entrySet().stream().sorted((o1, o2) -> {
            //如果优先级不同，则降序排列
            if (o1.getValue() != o2.getValue()){
                return o2.getValue() - o1.getValue();
            }else {
                //按照id顺序排列
                return o1.getKey() - o2.getKey();
            }
        }).forEach(entry -> sj.add(entry.getKey() + ""));

        for(String s : sj.toString().split(" ")){
            System.out.println(s);
        }
    }
}