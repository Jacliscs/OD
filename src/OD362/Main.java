package OD362;

import java.util.*;

/**
 * @author Jacliscs
 * @description 智能成绩表
 * @date 2024/3/16
 * @level 5
 */

/**
 * 输入描述
 * 第 1 行输入两个整数，学生人数 n 和科目数量 m。
 * <p>
 * 0 < n < 100
 * 0 < m < 10
 * 第 2 行输入 m 个科目名称，彼此之间用空格隔开。
 * <p>
 * 科目名称只由英文字母构成，单个长度不超过10个字符。
 * 科目的出现顺序和后续输入的学生成绩一一对应。
 * 不会出现重复的科目名称。
 * 第 3 行开始的 n 行，每行包含一个学生的姓名和该生 m 个科目的成绩（空格隔开）
 * <p>
 * 学生不会重名。
 * 学生姓名只由英文字母构成，长度不超过10个字符。
 * 成绩是0~100的整数，依次对应第2行种输入的科目。
 * 第n+2行，输入用作排名的科目名称。若科目不存在，则按总分进行排序。
 * <p>
 * 输出描述
 * 输出一行，按成绩排序后的学生名字，空格隔开。成绩相同的按照学生姓名字典顺序排序。
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //学生人数
        int n = sc.nextInt();
        //科目数量
        int m = sc.nextInt();
        List<String> subject = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            //科目名称
            subject.add(sc.next());
        }


        //存放学生姓名、科目成绩
        //如 姓名 语文 数学 英语
        //   张三 100  90  80
        String[][] student = new String[n][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m + 1; j++) {
                student[i][j] = sc.next();
            }
        }
        //按某科目排序
        String sort = sc.next();
        //排序并打印输出
        solution(student, subject, sort);

    }

    //按照科目名字从高到低排序
    public static void solution(String[][] student, List<String> subject, String sort) {
        //新数组，用编号代替名字
        int[][] temp = new int[student.length][student[0].length + 1];
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < student.length; i++) {
            temp[i][0] = i;
            //各学科 和 总分
            for (int j = 1; j < student[0].length; j++) {
                temp[i][j] = Integer.parseInt(student[i][j]);
                temp[i][student[0].length] += temp[i][j];
            }
            //存放名字
            map.put(i, student[i][0]);
        }

        //如果科目sort不存在subject中，则按总分排序
        if (!subject.contains(sort)) {
            int index = temp[0].length - 1;
            Arrays.sort(temp, (o1, o2) -> o2[index] - o1[index] == 0 ? map.get(o1[0]).compareTo(map.get(o2[0])) : o2[index] - o1[index]);
        } else {
            //学科下标0 对应student中下标1
            int index = subject.indexOf(sort) + 1;
            //二维数组按照第i列降序排列
            Arrays.sort(temp, (o1, o2) -> o2[index] - o1[index] == 0 ? map.get(o1[0]).compareTo(map.get(o2[0])) : o2[index] - o1[index]);
        }

        //打印输出
        for (int i = 0; i < temp.length; i++) {
            System.out.print(map.get(temp[i][0]) + " ");
        }
    }
}