package OD437;

import javax.naming.spi.DirObjectFactory;
import java.security.Key;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * @author 浮生
 * @description 找单词
 * @date 2024/4/3
 * @level 7
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD437
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {

    private static int n;
    private static char[][] matrix;
    private static String word;
    private static boolean[][] used;
    //保存结果路径
    public static LinkedList<String> path = new LinkedList<>();

    public static void main(String[] args) {
        //将输入分隔符改为 "," 和换行符\n
        Scanner sc = new Scanner(System.in).useDelimiter("[,\n]");
        //n行n列的单词矩阵
        n = Integer.parseInt(sc.nextLine());
        matrix = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //上面修改了，next()遇到","或\n就会返回
                matrix[i][j] = sc.next().charAt(0);
            }
        }

        //要查找的单词
        word = sc.next();

        //word的某一位是否被使用
        used = new boolean[n][n];

        System.out.println(getResult());

    }

    //输入查找的单词的坐标
    public static String getResult() {
        //遍历，找到第一个字符相同的才会开始
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //matrix[i][j]与word.charAt(0)相同才会开始递归，如果最后返回true，则说明找到路径了
                if (dfs(i, j, 0)) {
                    StringJoiner sj = new StringJoiner(",");
                    for (String pos : path) sj.add(pos);
                    return sj.toString();
                }
            }
        }
        //如果没找到
        return "N";
    }

    /**
     * 遍历查找指定单词在矩阵matrix中的位置
     *
     * @param i matrix[i][]
     * @param j matrix[][j]
     * @param k word.charAt(k)
     * @return boolean
     * @create 2024/4/3 16:51
     */
    public static boolean dfs(int i, int j, int k) {
        //检查越界
        if (i < 0 || i >= n || j < 0 || j >= n || word.charAt(k) != matrix[i][j] || used[i][j]) {
            return false;
        }

        //能走，则先添加到路径中
        path.add(i + "," + j);

        //如果已经找完了，则返回true
        if (path.size() == word.length()) {
            return true;
        }

        //没找完，则遍历上下左右四个方向
        used[i][j] = true;
        boolean res = dfs(i - 1, j, k + 1) || dfs(i + 1, j, k + 1) || dfs(i, j - 1, k + 1) || dfs(i, j + 1, k + 1);

        //只要有一个方向能找到下一个字符，则会进入递归

        //如果都找不到，则恢复状态
        if (!res) {
            used[i][j] = false;
            path.removeLast();
        }

        //当前路径是否能找到所有字符
        return res;
    }


}