package OD197;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 欢乐的周末
 * @date 2024/3/23
 * @level 7
 * @score 100
 * @url https://hydro.ac/d/HWOD2023/p/OD197
 */

/**
 * 题目描述
 * 小华和小为是很要好的朋友，他们约定周末一起吃饭。
 * <p>
 * 通过手机交流，他们在地图上选择了多个聚餐地点（由于自然地形等原因，部分聚餐地点不可达），求小华和小为都能到达的聚餐地点有多少个？
 * <p>
 * 输入描述
 * 第一行输入m和n，m代表地图的长度，n代表地图的宽度。
 * <p>
 * 第二行开始具体输入地图信息，地图信息包含：
 * <p>
 * 0 为通畅的道路
 * <p>
 * 1 为障碍物（且仅1为障碍物）
 * <p>
 * 2 为小华或者小为，地图中必定有且仅有2个 （非障碍物）
 * <p>
 * 3 为被选中的聚餐地点（非障碍物）
 * <p>
 * 输出描述
 * 可以被两方都到达的聚餐地点数量，行末无空格。
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {

    //位移偏量
    static int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static int rows;
    private static int cols;
    private static int[][] matrix;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //长 宽
        rows = sc.nextInt();
        cols = sc.nextInt();

        //2是人位置  1是障碍物  3是聚餐地点
        matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        //输出：能共同到达的餐厅数量
        System.out.println(getResult());

    }

    public static int getResult() {
        //并查集
        UnionFindSet ufs = new UnionFindSet(rows * cols);

        //记录小华、小为的位置
        ArrayList<Integer> address = new ArrayList<>();

        //记录餐厅的位置
        ArrayList<Integer> restaurants = new ArrayList<>();

        //遍历每一个起点
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                //把除了障碍物之外的都连通
                if (matrix[i][j] != 1) {
                    //如果不是障碍物，转为一维坐标pos
                    int pos = i * cols + j;

                    //如果是小华、小为
                    if (matrix[i][j] == 2) {
                        address.add(pos);
                    } else if (matrix[i][j] == 3) {
                        restaurants.add(pos);
                    }

                    for (int[] offset : offsets) {
                        int newI = i + offset[0];
                        int newJ = j + offset[1];
                        //如果没越界，且不是障碍物
                        if (newI >= 0 && newI < rows && newJ >= 0 && newJ < cols && matrix[newI][newJ] != 1) {
                            //如果(i,j)和(newI,newJ)的位置都是非1，则合并，都转一维坐标
                            ufs.union(pos, newI * cols + newJ);
                        }
                    }
                }
            }
        }

        //小华所在连通分量的根
        int hua_fa = ufs.find(address.get(0));
        //小为
        int wei_fa = ufs.find(address.get(1));

        //如果不属于同一个分量，则无法去同一家餐厅
        if (hua_fa != wei_fa) {
            return 0;
        }

        //记录题解：共同能到达的餐厅数量
        int ans = 0;

        for (Integer restaurant : restaurants) {
            if (ufs.find(restaurant) == hua_fa) {
                ans++;
            }
        }
        return ans;
    }


}


//实现并查集
class UnionFindSet {
    int[] fa;

    public UnionFindSet(int n) {
        fa = new int[n];
        for (int i = 0; i < n; i++) {
            fa[i] = i;
        }
    }

    public int find(int x) {
        if (x != this.fa[x]) {
            this.fa[x] = this.find(this.fa[x]);
            return this.fa[x];
        }
        return x;
    }

    public void union(int x, int y) {
        int x_fa = this.find(x);
        int y_fa = this.find(y);

        if (x_fa != y_fa) {
            this.fa[y_fa] = x_fa;
        }
    }
}
