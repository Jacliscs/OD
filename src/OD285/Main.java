package OD285;

import java.util.*;

/**
 * @author Jacliscs
 * @description 数组去重和排序
 * @date 2024/3/17
 * @level 6
 */

/**
 * 题目描述
 * 给定一个乱序的数组，删除所有的重复元素，使得每个元素只出现一次，并且按照出现的次数从高到低进行排序，相同出现次数按照第一次出现顺序进行先后排序。
 * <p>
 * 输入描述
 * 一个数组
 * <p>
 * 输出描述
 * 去重排序后的数组
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //读取数组
        String[] nums = sc.nextLine().split(",");
        String result = sort(nums);
        System.out.println(result);
    }

    //去重并排序数组
    public static String sort(String[] nums) {
        //存放 数组元素——出现次数，并按出现次数降序
        Map<String, Integer> map = new LinkedHashMap<>();
        //存放去重后的元素和该元素第一次出现的下标
        Map<String, Integer> indexMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            indexMap.putIfAbsent(nums[i], i);
        }
        StringJoiner sj = new StringJoiner(",");
        //根据value值降序排序
        //s为indexMap中的每个值，排序后添加到sj中
        indexMap.keySet().stream().sorted((o1, o2) -> {
            //出现次数
            int o1Count = map.get(o1);
            int o2Count = map.get(o2);
            //如果次数不相等，则降序排列
            if (o1Count != o2Count) {
                return o2Count - o1Count;
            } else {
                //如果相等，则按出现顺序排列
                return indexMap.get(o1) - indexMap.get(o2);
            }
        }).forEach(sj::add);

        return sj.toString();
    }

}