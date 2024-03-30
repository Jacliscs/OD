package OD424;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * @author 浮生
 * @description 加密算法、特殊的加密算法
 * @date 2024/3/30
 * @level 6
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD424
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {

    private static int n;
    private static int[] datas;
    private static int m;
    private static int[][] secrets;
    private static ArrayList<Integer> starts;
    static int[][] offsets = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //明文个数
        n = sc.nextInt();

        //明文组成的序列
        datas = new int[n];
        for (int i = 0; i < n; i++) {
            datas[i] = sc.nextInt();
        }

        //记录密码本中与明文第一个相同的元素位置 二维转一维
        starts = new ArrayList<>();

        //密文的宽高
        m = sc.nextInt();
        //密文
        secrets = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                secrets[i][j] = sc.nextInt();

                //如果和明文第一个数字相同，则加入到起始位置
                if (datas[0] == secrets[i][j]) {
                    starts.add(i * m + j);
                }
            }
        }

        //得到结果，如果没有找到明文序列，则返回error
        System.out.println(getResult());
    }

    //输出结果
    public static String getResult() {
        //遍历开始位置 起点是按顺序添加的，只要一找到符合要求的，则这个起点就是最优的
        for (int start : starts) {
            //起点
            int x = start / m;
            int y = start % m;

            //是否已经使用过
            boolean[][] used = new boolean[m][m];
            //出发点已使用
            used[x][y] = true;

            //记录结果路径各节点位置
            LinkedList<String> path = new LinkedList<>();

            //先将出发点加入
            path.add(x + " " + y);

            //执行深度搜索dfs
            if (dfs(x, y, 1, path, used)) {
                //如果能找到符合要求的
                StringJoiner sj = new StringJoiner(" ");
                for (String s : path) sj.add(s);
                return sj.toString();
            }
        }
        //所有起点位置都遍历了找不到符合要求的，则返回error
        return "error";

    }

    /**
     * 深度搜索从起点位置能否找到符合要求的路径
     *
     * @param x     当前位置坐标
     * @param y     当前位置坐标
     * @param index 将要匹配的明文数字datas[index]
     * @param path  已经走过的路径
     * @param used  标记密码是否被使用过
     * @return boolean
     * @create 2024/3/30 21:50
     */
    public static boolean dfs(int x, int y, int index, LinkedList<String> path, boolean[][] used) {
        //index是从1开始的 如果已经找到明文的最后一位，则说明符合要求
        if (index == n) {
            return true;
        }

        //因为找到相同路径后需要按字典排序
        //此处按照上、左、右、下四个方向按顺序遍历，只要一找到路径，就是最优的
        for (int[] offset : offsets) {
            int newX = x + offset[0];
            int newY = y + offset[1];

            //越界、不是明文的下一位、已使用 则跳过
            if (newX < 0 || newX >= m || newY < 0 || newY >= m || used[newX][newY] || secrets[newX][newY] != datas[index])
                continue;

            //否则当前这个位置就是明文 且是最优的
            path.add(newX + " " + newY);
            //标记
            used[newX][newY] = true;

            //如果该路径继续能找到所有明文，则返回
            if (dfs(newX, newY, index + 1, path, used)) {
                return true;
            }

            //否则，回溯
            path.removeLast();
            used[newX][newY] = false;
        }

        //如果遍历不能返回true，则说明这个x,y的不可以找到符合要求的
        return false;
    }

}