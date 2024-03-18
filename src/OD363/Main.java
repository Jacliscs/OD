package OD363;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 内存冷热标记
 * @date 2024/3/18
 * @level 4
 */

/**
 * 题目描述
 * <p>
 * 现代计算机系统中通常存在多级的存储设备，针对海量 workload 的优化的一种思路是将热点内存页优先放到快速存储层级，这就需要对内存页进行冷热标记。
 * 一种典型的方案是基于内存页的访问频次进行标记，如果统计窗口内访问次数大于等于设定阈值，则认为是热内存页，否则是冷内存页。
 * 对于统计窗口内跟踪到的访存序列和阈值，现在需要实现基于频次的冷热标记。内存页使用页框号作为标识。
 * <p>
 * 输入描述
 * 第一行输入为 N，表示访存序列的记录条数，0 < N ≤ 10000。
 * <p>
 * 第二行为访存序列，空格分隔的 N 个内存页框号，页面号范围 0 ~ 65535，同一个页框号可能重复出现，出现的次数即为对应框号的频次。
 * <p>
 * 第三行为热内存的频次阈值 T，正整数范围 1 ≤ T ≤ 10000。
 * <p>
 * 输出描述
 * 第一行输出标记为热内存的内存页个数，如果没有被标记的热内存页，则输出 0 。
 * <p>
 * 如果第一行 > 0，则接下来按照访问频次降序输出内存页框号，一行一个，频次一样的页框号，页框号小的排前面。
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //序列条数
        int n = Integer.parseInt(sc.nextLine());//在下一行有nextLine()时可以有效防止换行符
        //访问序列
        int[] list = new int[n];
        for (int i = 0; i < n; i++) {
            list[i] = sc.nextInt();
        }
        //阈值
        int t = sc.nextInt();
        if (n == 0) {
            System.out.println(0);
        } else {
            markHot(list, t);
        }

    }

    //标记过热内存的个数
    public static void markHot(int[] list, int t) {
        //存放次数
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < list.length; i++) {
            map.put(list[i], map.getOrDefault(list[i], 0) + 1);
        }
        //把小于阈值的给移出
        map.keySet().removeIf(s -> map.get(s) < t);
        //输出大于阈值的个数即当前map的大小
        System.out.println(map.size());
        //排序：先按频次降序，频次相同按页码升序
        map.entrySet().stream().sorted((a, b) ->
                a.getValue() == b.getValue() ? a.getKey() - b.getKey() : b.getValue() - a.getValue()
        ).forEach(s -> System.out.println(s.getKey()));


        ////超过阈值的个数
        //int count = 0;
        ////存放超过阈值的页面 <页框号,频次>
        //Map<Integer, Integer> hot = new TreeMap<>();
        ////遍历map
        //for (int key : map.keySet()) {
        //    if (map.get(key) >= t) {
        //        count++;
        //        hot.put(key, map.get(key));
        //    }
        //}
        ////如果没有超过阈值的，返回0
        //if (count == 0) {
        //    System.out.println(0);
        //} else {
        //    System.out.println(count);
        //    //超过阈值的:先按频次降序，频次一样的按页框号升序
        //    hot.keySet().stream().sorted((a, b) -> {
        //        //频次不同，则按频次降序
        //        if (hot.get(a) != hot.get(b)) {
        //            return hot.get(b) - hot.get(a);
        //        }else {
        //            //频次相同，按页框号升序
        //            return a - b;
        //        }
        //    }).forEach(s -> System.out.println(s));
        //}
    }
}