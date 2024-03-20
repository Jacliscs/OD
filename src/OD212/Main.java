package OD212;

import java.util.*;

/**
 * @author Jacliscs
 * @description 寻找众数和中位数
 * @date 2024/3/20
 * @level 3
 * @score 100
 */

/**
 * 题目描述
 * 众数是指一组数据中出现次数量多的那个数，众数可以是多个。
 * <p>
 * 中位数是指把一组数据从小到大排列，最中间的那个数，如果这组数据的个数是奇数，那最中间那个就是中位数，如果这组数据的个数为偶数，那就把中间的两个数之和除以2，所得的结果就是中位数。
 * <p>
 * 查找整型数组中元素的众数并组成一个新的数组，求新数组的中位数。
 * <p>
 * 输入描述
 * 输入一个一维整型数组，数组大小取值范围 0<N<1000，数组中每个元素取值范围 0<E<1000
 * <p>
 * 输出描述
 * 输出众数组成的新数组的中位数
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //没有限制数组大小，则读取一行转换为数组
        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //输出众数组成的数组的中位数
        System.out.println(getResult(nums));

    }

    //输出众数组成的数组的中位数
    public static int getResult(int[] nums) {
        //先统计每个数字出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        //找到最大出现次数max
        //int max = 0;
        //for (int key : map.keySet()) {
        //    if (map.get(key) > max) {
        //        max = map.get(key);
        //    }
        //}
        int max = map.values().stream().max(Integer::compareTo).orElse(0);

        //存放众数
        List<Integer> list = new ArrayList<>();
        //把出现次数max的数字，添加到众数数组，可以有多个数字都出现次数max
        for (int key : map.keySet()) {
            if (map.get(key) == max) {
                list.add(key);
            }
        }
        //寻找list中的中位数 如果是奇数个，则中位数下标为(list.size()+1)/2
        // 如果是偶数个，中位数为
        Collections.sort(list);
        return list.size() % 2 != 0 ? list.get((list.size() - 1) / 2) : (list.get(list.size() / 2) + list.get(list.size() / 2 - 1)) / 2;
    }
}