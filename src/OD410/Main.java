package OD410;

import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 螺旋数字矩阵
 * @date 2024/3/15
 * @level 5
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //需要填入的数字n
        int n = sc.nextInt();
        //行数
        int m = sc.nextInt();
        //需要的最小列数，向上取整
        int k = (int) Math.ceil(n * 1.0 / m);

        //矩阵，顺时针螺旋填入1-n数字，用*号填充
        //默认为0
        int[][] matrix = new int[m][k];

        //当前需要填入的值
        int temp = 1;
        //要填入的值的位置
        int x = 0;
        int y = 0;

        //循环填入数字
        while (temp <= n) {
            //先顺序填入第一行
            while (y < k && temp <= n && matrix[x][y] == 0) {
                matrix[x][y++] = temp++;
            }
            //填完第一行后，y=k，x=0，x++，y--
            y -= 1;
            x += 1;

            //从最后一列，第二行开始往下顺序填数
            while (x < m && temp <= n && matrix[x][y] == 0) {
                matrix[x++][y] = temp++;
            }
            //填完最后一列后，x=m，y=k-1，x--，y--
            x -= 1;
            y -= 1;

            //最后一行，从右到左填数
            while (y >= 0 && temp <= n && matrix[x][y] == 0) {
                matrix[x][y--] = temp++;
            }
            //填完最后一行后，x=m-1，y=-1，x--，y++
            y += 1;
            x -= 1;

            //第一列向上填充
            while (x >= 0 && temp <= n && matrix[x][y] == 0) {
                matrix[x--][y] = temp++;
            }
            //填完第一列后，x=0，y=-1，x++，y++
            y += 1;
            x += 1;

            //此时，x=1，y=0，第二次则从第二行第一列循环填数，最多填到n为止，剩下的用*号填充

            }
        //打印螺旋矩阵
        for (int i = 0; i < m; i++){
            for (int j = 0; j < k; j++){
                if (matrix[i][j] == 0){
                    System.out.print("*" + " ");
                }else {
                    System.out.print(matrix[i][j] + " ");
                }
            }
            System.out.println();
        }
        }
    }