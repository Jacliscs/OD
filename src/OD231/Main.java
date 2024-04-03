package OD231;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 图像物体的边界
 * @date 2024/4/3
 * @level 7
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD231
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {

    private static int m;
    private static int n;
    private static int[][] matrix;
    //坐标偏移
    private static int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();

        matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        //输出
        System.out.println(getResult());

    }

    public static int getResult() {
        //记录所有边界位置
        ArrayList<Integer[]> brand = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //如果是像素5，则扩散
                if (matrix[i][j] == 5) {
                    for (int[] offset : offsets) {
                        int newI = i + offset[0];
                        int newJ = j + offset[1];

                        //如果新位置不越界且为像素1，则是边界
                        if (newI >= 0 && newI < m && newJ >= 0 && newJ < n && matrix[newI][newJ] == 1) {
                            brand.add(new Integer[]{newI, newJ});
                        }
                    }
                }
            }
        }

        int k = brand.size();
        //并查集，对所有边界位置进行合并
        UnionFindSet ufs = new UnionFindSet(k);
        for (int i = 0; i < k; i++) {
            int x1 = brand.get(i)[0];
            int y1 = brand.get(i)[1];

            for (int j = i + 1; j < k; j++) {
                int x2 = brand.get(j)[0];
                int y2 = brand.get(j)[1];

                //如果两个边界像素1的位置横向、纵向距离均小于1，则相邻，合并
                //即所有绕格子5的一圈格子1是一个连通集，边界归于1，如果两个5的一圈边界有相邻，则会归于一个
                if (Math.abs(x1 - x2) <= 1 && Math.abs(y1 - y2) <= 1) {
                    //合并一次则边界-1
                    ufs.union(i, j);
                }
            }
        }

        //返回边界数量
        return ufs.count;
    }


}

//并查集实现
class UnionFindSet {
    //根节点
    int[] fa;
    //边界个数
    int count;

    public UnionFindSet(int n) {
        this.count = n;
        this.fa = new int[n];
        //初始化根节点是自己
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

        //如果两个不同根，则合并
        if (x_fa != y_fa) {
            this.fa[x_fa] = y_fa;
            //边界-1
            this.count--;
        }
    }
}