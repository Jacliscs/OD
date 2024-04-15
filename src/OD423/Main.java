package OD423;

import java.util.*;

/**
 * @author 浮生
 * @description 启动多任务排序
 * @date 2024/3/29
 * @level 6
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD423
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //输入不定的多个依赖对 a->b c->d
        String[][] relations = Arrays.stream(sc.nextLine().split(" ")).map(s -> s.split("->")).toArray(String[][]::new);
        //对应任务的入度 入度越低越先执行
        HashMap<String, Integer> inDegree = new HashMap<>();
        //存放任务的后续任务列表，即依赖于该任务的任务，A->B 即A依赖于B，A在B的后续任务列表中
        HashMap<String, ArrayList<String>> next = new HashMap<>();

        for (String[] relation : relations) {
            //取出每一对依赖 a->b
            String a = relation[0];
            String b = relation[1];

            //b的入度不变，a的入度+1
            inDegree.put(b, inDegree.getOrDefault(b, 0));
            inDegree.put(a, inDegree.getOrDefault(a, 0) + 1);

            //把a添加到b的后续任务列表
            next.putIfAbsent(b, new ArrayList<>());//如果不存在则创建新列表
            next.get(b).add(a);

            //a的后续任务列表不变，如果没有则创建新的
            next.putIfAbsent(a, new ArrayList<>());
        }

        //queue收集第一层最先执行的任务
        ArrayList<String> queue = new ArrayList<>();
        for (String key : inDegree.keySet()) {
            if (inDegree.get(key) == 0) {
                queue.add(key);
            }
        }

        //记录任务执行的顺序
        StringJoiner ans = new StringJoiner(" ");
        //
        while (queue.size() > 0) {
            //同一层优先级的任务按字母顺序排序
            queue.sort(String::compareTo);

            //用于记录下一层 并刷新上一层
            ArrayList<String> nextQueue = new ArrayList<>();

            //执行任务
            for (String fa : queue) {
                //执行了，就添加到ans
                ans.add(fa);

                //遍历fa的后续任务列表，将其后续任务的入度-1
                for (String ch : next.get(fa)) {
                    inDegree.put(ch, inDegree.get(ch) - 1);

                    //如果入度减小后为0，则会在下一层执行
                    if (inDegree.get(ch) == 0) {
                        nextQueue.add(ch);
                    }
                }
            }
            //把当前层queue执行完了，刷新下一层
            queue = nextQueue;
        }
        //打印任务列表
        System.out.println(ans);

    }
}