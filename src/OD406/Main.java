package OD406;

import java.util.*;

/**
 * @author 浮生
 * @description 推荐多样性
 * @date 2024/3/27
 * @level 6
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD406
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //需要输出的窗口数量
        int n = Integer.parseInt(sc.nextLine());
        //每个窗口需要的元素数量
        int k = Integer.parseInt(sc.nextLine());

        //下面不固定行数，用ArrayList方便扩展
        ArrayList<LinkedList<Integer>> lists = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            //是否输入完了
            if (line.length() == 0) break;
            Integer[] nums = Arrays.stream(line.split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
            //添加到列表
            lists.add(new LinkedList<>(Arrays.asList(nums)));
        }

        //最后输出的结果窗口 n列 k行 转为一维数组
        int[] windows = new int[n * k];
        //正在填的索引位置
        int index = 0;
        //正在第几个列表中取值 [0,lists.size()) 左闭右开
        int level = 0;

        //填满了则跳出循环
        while (index < windows.length) {
            //是否向下一行借了数字
            boolean flag = false;
            //每次都要取n个数字，这一行不够则从下一行借
            for (int i = 0; i < n; i++) {
                windows[index++] = lists.get(level).removeFirst();//从第level行取出第一个数字
                //如果还没取到n个就没了，则向下一行取 至少得大于1行才能借
                if (lists.get(level).size() == 0 && lists.size() > 1) {
                    //删除空列表
                    lists.remove(level);
                    //防止下标越界 第0行需要借位则删除第0行，第1行就变成了第0行
                    level %= lists.size();
                    //刷新标志 说明发生了借位
                    flag = true;
                }
            }
            //如果没发生借位，则开始从下一行取 取到最后一行了则回到第一行
            if (!flag) {
                level = (level + 1) % lists.size();
            }
        }
        //windows填满了
        StringJoiner sj = new StringJoiner(" ");
        //遍历窗口
        for (int j = 0; j < n; j++) {//第j列
            for (int i = 0; i < k; i++) {//第j列第i个
                //窗口是从左到右从上到下填入的
                sj.add(windows[i * n + j] + "");
            }
        }
        System.out.println(sj);
    }
}