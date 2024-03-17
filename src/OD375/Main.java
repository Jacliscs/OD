package OD375;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * @author Jacliscs
 * @description 机场航班调度程序
 * @date 2024/3/17
 */

/**
 * 题目描述
 * XX市机场停放了多架飞机，每架飞机都有自己的航班号CA3385，CZ6678，SC6508等，航班号的前2个大写字母（或数字）代表航空公司的缩写，后面4个数字代表航班信息。
 * 但是XX市机场只有一条起飞跑道，调度人员需要安排目前停留在机场的航班有序起飞。
 * 为保障航班的有序起飞，调度员首先按照航空公司的缩写（航班号前2个字母）对所有航班进行排序，同一航空公司的航班再按照航班号的后4个数字进行排序，最终获得安排好的航班的起飞顺序。
 * 请编写一段代码根据输入的航班号信息帮助调度员输出航班的起飞顺序。
 * <p>
 * 说明：
 * <p>
 * 航空公司缩写排序按照从特殊符号$ & *，0~9，A~Z排序；
 * <p>
 * 输入描述
 * 第一行输入航班信息，多个航班号之间用逗号 "," 分隔，输入的航班号不超过100个。
 * <p>
 * 例如：
 * <p>
 * CA3385,CZ6678,SC6508,DU7523,HK4456,MK0987
 * <p>
 * 备注：
 * <p>
 * 航班号为6为长度，后4位位纯数字，不考虑存在后4位重复的场景。
 * <p>
 * 输出描述
 * CA3385,CZ6678,DU7523,HK4456,MK0987,SC6508
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] flights = sc.nextLine().split(",");
        System.out.println(schedule(flights));

    }

    //机场航班调度
    public static String schedule(String[] flights) {
        //直接排序，并储存结果到StringJoiner中
        StringJoiner sj = new StringJoiner(",");
        Arrays.stream(flights).sorted((o1, o2) -> {
            String n1 = o1.substring(0, 2);
            String n2 = o2.substring(0, 2);
            String v1 = o1.substring(2);
            String v2 = o2.substring(2);
            //先按前两位排序
            if (n1.compareTo(n2) != 0) {
                return n1.compareTo(n2);
            } else {
                //前两位相同，则按后4位排序
                return v1.compareTo(v2);
            }
        })
                //将排序后的添加进sj
                .forEach(sj::add);

        return sj.toString();
    }
}