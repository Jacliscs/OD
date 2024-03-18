package OD200;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 整形数组按个位值排序
 * @date 2024/3/18
 * @level 2
 */

/**
 * 题目描述
 * 给定一个非空数组（列表），其元素数据类型为整型，请按照数组元素十进制最低位从小到大进行排序，十进制最低位相同的元素，相对位置保持不变。
 * <p>
 * 当数组元素为负值时，十进制最低位等同于去除符号位后对应十进制值最低位。
 * <p>
 * 输入描述
 * 给定一个非空数组，其元素数据类型为32位有符号整数，数组长度[1, 1000]
 * <p>
 * 输出描述
 * 输出排序后的数组
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(",");
        //按照最后一位升序排列
        Arrays.sort(str, (o1, o2) -> {
            return o1.charAt(o1.length() - 1) - o2.charAt(o2.length() - 1);
        });
        String result = String.join(",", str);
        System.out.println(result);
    }
}