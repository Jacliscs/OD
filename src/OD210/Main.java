package OD210;

import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 停车场车辆统计
 * @date 2024/3/20
 * @level 5
 * @score 100
 */

/**
 * 题目描述
 * 特定大小的停车场，数组cars[]表示，其中1表示有车，0表示没车。
 * <p>
 * 车辆大小不一，小车占一个车位（长度1），货车占两个车位（长度2），卡车占三个车位（长度3）。
 * <p>
 * 统计停车场最少可以停多少辆车，返回具体的数目。
 * <p>
 * 输入描述
 * 整型字符串数组cars[]，其中1表示有车，0表示没车，数组长度小于1000。
 * <p>
 * 输出描述
 * 整型数字字符串，表示最少停车数目。
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //取出所有，号 如1110010011
        String str = sc.nextLine().replaceAll(",", "");
        System.out.println(countMin(str));
    }

    //计算最少停车数 001001000111001
    public static int countMin(String str) {
        int count = 0;
        str = str.replaceAll("111", "c")
                .replaceAll("11", "c")
                .replaceAll("1", "c");
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'c') {
                count++;
            }
        }

        ////上一次长度
        //int preLength = str.length();
        ////先替换三个位置的111
        //str = str.replaceAll("111","");
        //count += (preLength-str.length())/3;
        //preLength = str.length();
        ////替换占2个位置的11
        //str =  str.replaceAll("11","");
        //count +=  (preLength-str.length())/2;
        //preLength = str.length();
        ////替换占1个位置的
        //str =  str.replaceAll("1","");
        //count +=  preLength-str.length();
        return count;
    }
}