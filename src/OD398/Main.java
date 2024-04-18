package OD398;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 精准核酸检测
 * @date 2024/4/3
 * @level 6
 * @score 100
 * @url https://hydro.ac/d/HWOD2023/p/OD398
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {

    private static int n;
    private static int[] confirmed;
    private static int[][] matrix;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //n行n列
        n = Integer.parseInt(sc.nextLine());

        //初始确诊病例编号
        confirmed = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();

        //人员接触matrix[i][j]==1 表示编号i与编号j有接触 对角线对称
        matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            matrix[i] = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(getResult());

    }

    public static int getResult() {
        //初始化所有点的根节点是自己
        UnionFindSet ufs = new UnionFindSet(n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //有过接触的人进行合并 matrix[i][j] = 1表示编号i与编号j的人有接触，则合并他俩的根
                if (matrix[i][j] == 1) {
                    ufs.union(i, j);
                }
            }
        }

        //统计每个接触群体<连通分量>中的人数
        int[] cnts = new int[n];
        for (int i = 0; i < n; i++) {
            int fa = ufs.find(i);
            //根节点为fa的连通的接触人数+1
            cnts[fa]++;
        }

        //记录已统计过的感染群体
        HashSet<Integer> confirmed_fa = new HashSet<>();

        //将所有感染者的接触群体的人数统计
        int ans = 0;
        for (int i : confirmed) {
            //感染源是i的根节点，所有跟i有接触或者间接接触的根都是fa cnts[fa]则是这条感染链的接触人数
            int fa = ufs.find(i);

            //则跳过
            if (confirmed_fa.contains(fa)) continue;

            //添加到统计列表
            confirmed_fa.add(fa);

            //增加接触人数
            ans += cnts[fa];
        }

        //感染者不需要做核酸
        return ans - confirmed.length;
    }


}

//并查集实现
class UnionFindSet {
    //fa[5]表示5的根节点
    int[] fa;

    public UnionFindSet(int n) {
        this.fa = new int[n];
        //初始化，每个人的根节点是自己
        for (int i = 0; i < n; i++) {
            fa[i] = i;
        }
    }

    //查找x的根节点
    public int find(int x) {
        //如果他自己不是根节点
        if (x != this.fa[x]) {
            //递归查找连通集的根
            this.fa[x] = this.find(this.fa[x]);
            return this.fa[x];
        }
        return x;
    }

    //合并
    public void union(int x, int y) {
        //x的根节点
        int x_fa = this.find(x);
        //y的根节点
        int y_fa = this.find(y);

        //如果两个不同根，则合并根
        if (x_fa != y_fa) {
            this.fa[y_fa] = x_fa;
        }

    }
}