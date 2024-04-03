package OD380;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * @author 浮生
 * @description 找城市
 * @date 2024/4/3
 * @level 7
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD380
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {

    private static int n;
    private static int[][] relations;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //节点个数
        n = sc.nextInt();
        //城市之间的连通
        relations = new int[n - 1][2];
        for (int i = 0; i < n - 1; i++) {
            relations[i][0] = sc.nextInt();
            relations[i][1] = sc.nextInt();
        }
        //输出结果
        System.out.println(getResult());

    }

    public static String getResult() {
        //记录最小dpi
        int minDp = Integer.MAX_VALUE;

        //记录最小dpi对应的切断城市
        ArrayList<Integer> city = new ArrayList<>();

        //遍历切断城市i
        for (int i = 1; i <= n; i++) {
            //并查集 对城市进行关联
            UnionFindSet ufs = new UnionFindSet(n + 1);

            for (int[] relation : relations) {
                int c1 = relation[0];
                int c2 = relation[1];
                //如果有一个是切断城市，则不进行合并操作
                if (c1 == i || c2 == i) continue;

                //否则，则连接c1 c2
                ufs.union(c1, c2);
            }

            //对于切断城市i后，每个城市的dp
            int[] dps = new int[n + 1];
            for (int j = 1; j <= n; j++) {
                //城市j的连通的根
                int fa = ufs.find(j);
                //根fa下的相连城市+1
                dps[fa]++;
            }

            //城市i的聚集度是切断后的最大聚集度
            //DPi = max（城市群1的城市个数，城市群2的城市个数，…城市群m 的城市个数）。
            int dp = Arrays.stream(dps).max().orElse(0);

            //更新较小值
            if (dp < minDp) {
                minDp = dp;
                //清空之前的city列表
                city.clear();
                city.add(i);
            } else if (dp == minDp) {
                city.add(i);
            }
            //dp>minDp则不操作
        }
        //遍历完所有切断城市，现在city里保存的就是最小聚集度的切断城市列表
        //如果有多个，则升序排序
        city.sort((a, b) -> a - b);
        StringJoiner sj = new StringJoiner(" ");
        for (Integer c : city) {
            sj.add(c + "");
        }
        return sj.toString();
    }
}

//并查集实现
class UnionFindSet {
    int[] fa;

    public UnionFindSet(int n) {
        this.fa = new int[n];
        for (int i = 0; i < n; i++) {
            fa[i] = i;
        }
    }

    public int find(int x) {
        if (x != this.fa[x]) {
            return (this.fa[x] = this.find(this.fa[x]));
        }
        return x;
    }

    //合并
    public void union(int x, int y) {
        int x_fa = this.find(x);
        int y_fa = this.find(y);

        if (x_fa != y_fa) {
            //合并
            this.fa[x_fa] = y_fa;
        }
    }
}