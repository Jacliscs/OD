package OD402;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 亲子游戏
 * @date 2024/3/27
 * @level 7
 * @score 200
 */

/**
 * https://hydro.ac/d/HWOD2023/p/OD402
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    static int n;//数组大小
    static int[][] box;//地图
    static int[][] candy;//从妈妈位置开始，到达x，y位置的最大糖果数
    static int[][] offsets = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};//偏移量


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //二维矩阵的大小
        n = Integer.parseInt(sc.nextLine());
        box = new int[n][n];
        candy = new int[n][n];
        //记录下一次坐标起点
        LinkedList<int[]> queue = new LinkedList<>();
        //-3 妈妈 -2孩子 -1障碍 >=0是糖果
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //-1表示该位置的糖果还没有被拿到
                candy[i][j] = -1;
                box[i][j] = sc.nextInt();
                //妈妈的位置 起点
                if (box[i][j] == -3) {
                    candy[i][j] = 0;
                    queue.add(new int[]{i, j});
                }
            }
        }
        //记录题解 找到孩子时能获得的最大糖果数
        //如果找不到孩子 则返回-1
        int ans = -1;

        //记录题解：能获得的最大糖果数
        while (!queue.isEmpty()) {
            //记录当前扩散层的点
            LinkedList<int[]> temp = new LinkedList<>();
            //标记是否找到宝宝
            boolean flag = false;
            for (int[] xy : queue) {
                int x = xy[0];
                int y = xy[1];
                //向四个方向扩散
                for (int[] offset : offsets) {
                    int newX = x + offset[0];
                    int newY = y + offset[1];
                    //如果下标越界或遇到障碍物，则不能走
                    if (newX < 0 || newX >= n || newY < 0 || newY >= n || box[newX][newY] == -1) continue;

                    //能走且糖果还没有被拿走，则把当前位置加入到新层
                    if (candy[newX][newY] == -1) {
                        temp.addLast(new int[]{newX, newY});
                    }
                    //更新走到newX，newY位置时能获得的最大糖果数量
                    candy[newX][newY] = Math.max(candy[newX][newY], candy[x][y] + Math.max(box[newX][newY], 0));
                    //如果找到孩子了
                    if (box[newX][newY] == -2) {
                        flag = true;
                        //更新ans
                        ans = Math.max(ans, candy[newX][newY]);
                    }
                }
            }
            //如果已经找到了，则终止
            if (flag) break;
            //否则继续，从下一层开始遍历
            queue = temp;
        }

        //输出
        System.out.println(ans);

    }
}