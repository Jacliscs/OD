package OD301;

import java.util.*;

/**
 * @author Jacliscs
 * @description 按身高和体重排队
 * @date 2024/3/17
 * @level 6
 */

/**
 * 题目描述
 * 某学校举行运动会，学生们按编号(1、2、3…n)进行标识，现需要按照身高由低到高排列，对身高相同的人，按体重由轻到重排列；对于身高体重都相同的人，维持原有的编号顺序关系。请输出排列后的学生编号。
 * <p>
 * 输入描述
 * 两个序列，每个序列由n个正整数组成（0 < n <= 100）。第一个序列中的数值代表身高，第二个序列中的数值代表体重。
 * <p>
 * 输出描述
 * 排列结果，每个数值都是原始序列中的学生编号，编号从1开始
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //学生数量
        int n = sc.nextInt();
        //身高 <学生编号，身高>
        Map<Integer, Integer> height = new HashMap<>();
        for (int i = 0; i < n; i++) {
            height.put(i + 1, sc.nextInt());
        }
        //体重 <学生编号，身高>
        Map<Integer, Integer> weight = new HashMap<>();
        for (int i = 0; i < n; i++) {
            weight.put(i + 1, sc.nextInt());
        }
        //先按身高排序，身高相同按体重，体重相同按编号，结果放进StringJoiner中
        StringJoiner sj = new StringJoiner(" ");
        height.keySet().stream().sorted((o1, o2) -> {
            int h1 = height.get(o1);
            int h2 = height.get(o2);
            int w1 = weight.get(o1);
            int w2 = weight.get(o2);
            if(h1 != h2){
                return h1 - h2;
            } else if (h1 == h2 && w1 != w2) {
                return w1 - w2;
            }else {
                return o1 - o2;
            }
        }).forEach(s -> {
            sj.add(Integer.toString(s));
        });

        //打印结果
        System.out.println(sj.toString());
    }
}