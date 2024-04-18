package OD301.better;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 按身高体重排序
 * @date 2024/4/18
 * @level
 * @score
 * @url
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //学生个数
        int n = Integer.parseInt(sc.nextLine());
        Student[] students = new Student[n];

        //身高
        int[] heights = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        //体重
        int[] weights = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        ;

        for (int i = 0; i < n; i++) {
            //编号从1开始
            students[i] = new Student(i + 1, heights[i], weights[i]);
        }

        //排序：优先身高升序、身高相同按体重升序、都相同按编号升序

        Arrays.sort(students, (s1, s2) -> {
            if (s1.height != s2.height) {
                return s1.height - s2.height;
            }
            if (s1.weight != s2.weight) {
                return s1.weight - s2.weight;
            }
            return s1.id - s2.id;
        });

        //输出
        for (Student student : students) {
            System.out.print(student.id + " ");
        }
    }

    static class Student {
        //学生编号
        int id;
        //身高
        int height;
        //体重
        int weight;

        public Student(int id, int height, int weight) {
            this.id = id;
            this.height = height;
            this.weight = weight;
        }
    }

}