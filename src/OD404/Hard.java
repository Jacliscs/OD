package OD404;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 使用二维数组填充
 * @date 2024/3/18
 * @level
 * @score
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Hard {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //第一行
        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(s -> Integer.parseInt(s)).toArray();
        //第二行
        int[] pos = Arrays.stream(sc.nextLine().split(" ")).mapToInt(s -> Integer.parseInt(s)).toArray();

        //创建二维数组
        int[][] arr = new int[nums[0]][nums[1]];
        //标记该位置是否被填充
        boolean[][] visit = new boolean[nums[0]][nums[1]];
        for (int i = 2; i < nums.length; i += 2) {
            //要填充的数字
            int num = nums[i];
            //要填充的长度
            int len = nums[i + 1];
            //填充
            fill(arr, visit, num, len);
        }
        //结果
        int result = arr[pos[0]][pos[1]];
        System.out.println(result);

    }

    //将数字按从左到右，从上到下的顺序填充在数组中
    public static void fill(int[][] arr, boolean[][] visit, int num, int len) {
        //已填充长度
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                //如果没有填充过，且还没填完
                if (!visit[i][j] && count < len) {
                    //更新状态
                    visit[i][j] = true;
                    arr[i][j] = num;
                    count++;
                }
            }
        }
    }
}