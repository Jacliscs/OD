package OD358;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 围棋的气
 * @date 2024/3/20
 * @level 4
 * @score 100
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //黑棋坐标
        int[] black = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //白棋坐标
        int[] white = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //棋盘
        int[][] board = new int[19][19];
        //取黑棋
        for (int i = 0; i < black.length; i += 2) {
            int x = black[i];
            int y = black[i + 1];
            //把该位置置为1
            board[x][y] = 1;
        }
        //取白棋
        for (int i = 0; i < white.length; i += 2) {
            int x = white[i];
            int y = white[i + 1];
            //把白棋置为2
            board[x][y] = 2;
        }
        //统计黑白棋的气
        int countBlack = 0;
        int countWhite = 0;
        //上下左右操作
        int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        //遍历棋盘，寻找可能是黑白棋的气的点：该点为0，且上下左右存在1或者2
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                if (board[i][j] == 0) {
                    //该点是否是黑棋的气
                    boolean isBlack = false;
                    boolean isWhite = false;
                    //遍历询问：该点是否是黑、白的气
                    for (int[] offset : offsets) {
                        int newI = i + offset[0];
                        int newJ = j + offset[1];
                        //如果下标越界，跳出
                        if (newI < 0 || newI >= 19 || newJ < 0 || newJ >= 19) continue;
                        isBlack = isBlack || board[newI][newJ] == 1;
                        isWhite = isWhite || board[newI][newJ] == 2;
                    }
                    //如果是黑棋的气，则黑棋气+1 因为是按顺序遍历的空白格，故同一个气只会被算作一次
                    if (isBlack) countBlack++;
                    if (isWhite) countWhite++;
                }
            }
        }
        System.out.println(countBlack + " " + countWhite);
    }
}