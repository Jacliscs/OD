package OD407;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 压缩数组解法
 * @date 2024/3/28
 * @level
 * @score
 * @url
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Better {

    private static String a;
    private static String b;
    private static int m;
    private static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        a = sc.next();
        b = sc.next();

        m = b.length();
        n = a.length();

        System.out.println(getResult());
    }

    public static int getResult() {
        //preRow 表示上一行的各点到(0,0)点的距离
        //初始化时表示第一行(0,1)~(0,n)到(0,0)的距离
        int[] preRow = new int[n + 1];
        for (int j = 0; j <= n; j++) {
            preRow[j] = j;
        }

        //初始时curRow表示第二行 i从1开始
        int[] curRow = new int[n + 1];

        for (int i = 1; i <= m; i++) {
            //每一行的curRow[0] 表示的是第一列到(0,0)的最短距离
            curRow[0] = i;
            for (int j = 1; j <= n; j++) {
                //如果字符相同，则可以走斜线
                if (a.charAt(j - 1) == b.charAt(i - 1)) {
                    curRow[j] = preRow[j - 1] + 1;
                } else {
                    //如果不能走斜线，则从当前点的上方或者左方选择更小的+1
                    curRow[j] = Math.min(preRow[j], curRow[j - 1]) + 1;
                }
            }

            //压缩数组 把当前行作为上一行
            System.arraycopy(curRow, 0, preRow, 0, n + 1);
        }
        //最后的curRow是最后一行到达(0,0)位置的最短距离
        return curRow[n];
    }
}