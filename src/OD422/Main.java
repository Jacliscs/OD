package OD422;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 反射计数
 * @date 2024/3/30
 * @level 5
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD422
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //宽 高  y  x
        int w = sc.nextInt();
        int h = sc.nextInt();

        //起始位置
        int x = sc.nextInt();
        int y = sc.nextInt();

        //起始方向
        int sx = sc.nextInt();
        int sy = sc.nextInt();

        //经过的时间
        int t = sc.nextInt();

        //路线
        char[][] matrix = new char[h][w];
        for (int i = 0; i < h; i++) {
            matrix[i] = sc.next().toCharArray();
        }

        //记录遇到1的次数
        int ans = 0;

        //剩余步数 把起点也算进去了，所以是走t+1步
        while (t >= 0) {
            //起点也算  宽高对应的是y x
            if (matrix[y][x] == '1') {
                ans++;
            }

            //沿方向位移
            y += sy;
            x += sx;

            //判断x是否越界
            if (x < 0) {
                //碰到左边 x += 2
                x = 1;
                //位移方向
                sx = -sx;
            } else if (x >= w) {
                //碰到右边
                x = w - 2;
                sx = -sx;
            }

            //判断y是否越界
            if (y < 0) {
                y = 1;
                sy = -sy;
            } else if (y >= h) {
                y = h - 2;
                sy = -sy;
            }

            //行走步数减一
            t--;
        }

        //输出经过的1的个数
        System.out.println(ans);
    }
}