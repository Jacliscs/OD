package OD381;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 考古学家
 * @date 2024/4/1
 * @level 6
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD381
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {

    //需要排列的个数
    private static int n;
    private static String[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //n块石碑 全排列 去重
        n = Integer.parseInt(sc.nextLine());
        arr = sc.nextLine().split(" ");

        getResult();
    }

    public static void getResult() {
        //升序排列 相同元素相邻，方便后续去重
        Arrays.sort(arr);

        //标记是否被使用
        boolean[] used = new boolean[n];
        //已经加入的排列
        LinkedList<String> path = new LinkedList<>();
        //树层去重仍然会有相同结果出来，使用set防重复
        HashSet<String> res = new HashSet<>();

        dfs(path, used, res);

        //需要按字典升序排列
        res.stream().sorted(String::compareTo).forEach(System.out::println);
    }

    /**
     * 递归求全排列
     *
     * @param path 已经加入的排列 当size为n时，拼接path中的路径，添加到res中
     * @param used 标记是否使用
     * @param res  保留结果
     * @return void
     * @create 2024/4/1 20:29
     */
    public static void dfs(LinkedList<String> path, boolean[] used, HashSet<String> res) {
        //如果已经排列了n个
        if (path.size() == n) {
            StringBuilder sb = new StringBuilder();
            path.forEach(sb::append);
            //排列结果加入到res
            res.add(sb.toString());
            return;
        }

        for (int i = 0; i < n; i++) {
            if (used[i]) continue;

            //树层去重
            if (i > 0 && arr[i].equals(arr[i - 1]) && !used[i - 1]) continue;

            //修改状态
            used[i] = true;
            path.addLast(arr[i]);

            //dfs递归
            dfs(path, used, res);

            //恢复状态
            used[i] = false;
            path.removeLast();
        }


    }


}