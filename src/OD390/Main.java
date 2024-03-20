package OD390;

import java.util.*;

/**
 * @author Jacliscs
 * @description 石头剪刀布游戏
 * @date 2024/3/20
 * @level 2
 * @score 200
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, String> map = new HashMap<>();
        //玩家数不固定 1-1000
        while (sc.hasNext()) {
            String[] temp = sc.nextLine().split(" ");
            map.put(temp[0], temp[1]);
            ////本地跳出循环
            //if (sc.nextLine() == "") {
            //    break;
            //}
        }
        //如果ABC都有，则没有赢家
        Set<String> set = new HashSet<>(map.values());
        //存放赢家name
        List<String> list = new ArrayList<>();
        if (set.size() == 3 || set.size() == 1) {
            System.out.println("NULL");
        } else {
            //只有两种手势AB 则A是赢家
            if (!set.contains("C")) {
                map.keySet().stream().forEach(s -> {
                    if (map.get(s).equals("A")) {
                        list.add(s);
                    }
                });

                //for (String key : map.keySet()) {
                //    if (map.get(key).equals("A")) {
                //        list.add(key);
                //    }
                //}
            } else if (!set.contains("B")) {
                for (String key : map.keySet()) {
                    //C是赢家
                    if (map.get(key).equals("C")) {
                        list.add(key);
                    }
                }
            } else {
                //B C ，B是赢家
                for (String key : map.keySet()) {
                    if (map.get(key).equals("B")) {
                        list.add(key);
                    }
                }
            }
            //把赢家id按字符升序排列
            list.sort(String::compareTo);
            for (String s : list) {
                System.out.println(s);
            }

        }
    }
    //输出赢家，没有赢家则输出NULL
}