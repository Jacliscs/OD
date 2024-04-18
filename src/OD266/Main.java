package OD266;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * @author Jacliscs
 * @description 考勤信息
 * @date 2024/3/20
 * @level 6
 * @score 100
 */

/**
 * 题目描述
 * 公司用一个字符串来表示员工的出勤信息
 * <p>
 * absent：缺勤
 * late：迟到
 * leaveearly：早退
 * present：正常上班
 * 现需根据员工出勤信息，判断本次是否能获得出勤奖，能获得出勤奖的条件如下：
 * <p>
 * 缺勤不超过一次；
 * 没有连续的迟到/早退；
 * 任意连续7次考勤，缺勤/迟到/早退不超过3次。
 * 输入描述
 * 用户的考勤数据字符串
 * <p>
 * 记录条数 >= 1；
 * 输入字符串长度 < 10000；
 * 不存在非法输入；
 * 输出描述
 * 根据考勤数据字符串，如果能得到考勤奖，输出”true”；否则输出”false”。
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //不存在非法输入
        //考勤条数
        int n = Integer.parseInt(sc.nextLine());
        //考勤记录
        String[][] records = new String[n][];
        for (int i = 0; i < n; i++) {
            records[i] = sc.nextLine().split(" ");
        }
        System.out.println(getResult(records));
    }

    //统计所有行的结果
    public static String getResult(String[][] records) {
        StringJoiner sj = new StringJoiner(" ");
        for (int i = 0; i < records.length; i++) {
            sj.add(isAward(records[i]) + "");
        }
        return sj.toString();
    }


    //判断该行记录是否能得到全勤奖
    public static boolean isAward(String[] records) {
        int absent = 0;//缺勤
        int present = 0;
        String lastRecord = "";
        for (int i = 0; i < records.length; i++) {
            //如果大于7了，要移出左边那个窗口，只影响present数量
            if (i >= 7) {
                //防止空指针异常：常量放前面
                if ("present".equals(records[i - 7])) {
                    present--;
                }
            }
            //当前的考勤记录
            String temp = records[i];
            switch (temp) {
                case "absent":
                    if (++absent > 1) {
                        return false;
                    }
                    //break很重要，不然会进入到下一个case，给present++
                    break;
                case "present":
                    present++;
                    break;
                case "late":
                case "leaveearly":
                    //如果上一个也是迟到早退，则返回false
                    if ("leaveearly".equals(lastRecord) || "late".equals(lastRecord)) {
                        return false;
                    }
                    break;
            }
            //刷新上一个记录
            lastRecord = temp;
            //当前窗口长度
            int windowLength = Math.min(i + 1, 7);
            //如果当前窗口内迟到、早退、缺席>3，则返回false
            if (windowLength - present > 3) {
                return false;
            }
        }
        //上面都没有返回false，则可以拿全勤
        return true;
    }
}