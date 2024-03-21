package OD440;

import java.util.*;

/**
 * @author Jacliscs
 * @description 数字排列
 * @date 2024/3/21
 * @level 3
 * @score 200
 */

/**
 * 题目描述
 * 小明负责公司年会，想出一个趣味游戏：
 * <p>
 * 屏幕给出 1 ~ 9 中任意 4 个不重复的数字，大家以最快时间给出这几个数字可拼成的数字从小到大排列位于第 N 位置的数字，其中 N 为给出数字中最大的（如果不到这么多数字则给出最后一个即可）。
 * <p>
 * 注意：
 * <p>
 * 2 可以当作 5 来使用，5 也可以当作 2 来使用进行数字拼接，且屏幕不能同时给出 2 和 5；
 * 6 可以当作 9 来使用，9 也可以当作 6 来使用进行数字拼接，且屏幕不能同时给出 6 和 9。
 * 如给出：1，4，8，7，则可以拼接的数字为：
 * <p>
 * 1，4，7，8，14，17，18，41，47，48，71，74，78，81，84，87，147，148，178 ... (省略后面的数字)
 * <p>
 * 那么第 N （即8）个的数字为 41。
 * <p>
 * 输入描述
 * 输入以逗号分隔的 4 个 int 类型整数的字符串。
 * <p>
 * 输出描述
 * 输出为这几个数字可拼成的数字从小大大排列位于第 N （N为输入数字中最大的数字）位置的数字，
 * <p>
 * 如果输入的数字不在范围内或者有重复，则输出-1。
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] num = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(num));
    }

    /**
     * 返回数字排列后第max位置的数
     *
     * @param num
     * @return int
     * @create 2024/3/21 17:40
     */
    public static int getResult(int[] num) {
        //异常处理
        Set<Integer> set = new HashSet<>();
        //最大数
        int max = 0;
        for (int i : num) {
            if (i < 1 || i > 9) {
                return -1;
            }
            set.add(i);
            max = Math.max(max, i);
        }
        //如果有重复，返回-1
        if (set.size() != 4) {
            return -1;
        }
        //如果25 69同时出现
        if (set.contains(2) && set.contains(5) || set.contains(6) && set.contains(9)) {
            return -1;
        }

        //存放映射关系
        Map<Integer, Integer> map = new HashMap<>();
        map.put(2, 5);
        map.put(5, 2);
        map.put(6, 9);
        map.put(9, 6);
        //标记该数是否被使用过
        boolean[] visit = new boolean[num.length];
        //记录排列组合
        String path = "";
        //存放结果
        List<Integer> res = new ArrayList<>();
        //排列，结果放进res，无序
        dfs(num, visit, path, map, res);
        //升序排列
        res.sort(Comparator.comparingInt(a -> a));
        //如果max超过res.size，则返回最后一个
        max = Math.min(max, res.size());
        //输出第max位置的 下标为max-1
        return res.get(max - 1);
    }

    /**
     * 递归求排列
     *
     * @param nums  需要进行排列的数组
     * @param visit 标记数字是否被使用
     * @param path  记录当前路径
     * @param map   存放映射关系
     * @param res   存放总结果
     * @return void
     * @create 2024/3/21 17:53
     */
    public static void dfs(int[] nums, boolean[] visit, String path, Map<Integer, Integer> map, List<Integer> res) {
        //如果当前排列不为空，则添加
        if (!path.isEmpty()) {
            res.add(Integer.parseInt(path));
        }
        //如果长度已经等于nums.length 则不需要继续递归
        if (path.length() == nums.length) {
            return;
        }
        //遍历数组 每次都从一个位置开始，然后排列到nums.length的长度，刷新数字状态，从下一个位置开始
        for (int i = 0; i < nums.length; i++) {
            //如果该数字被使用过，则跳过
            if (visit[i]) {
                continue;
            }
            //如果没被使用，则刷新
            visit[i] = true;
            //递归传递
            dfs(nums, visit, path + nums[i], map, res);
            //如果存在25 69，则转换对应关系，再递归
            if (map.containsKey(nums[i])) {
                dfs(nums, visit, path + map.get(nums[i]), map, res);
            }
            //第i位数字开头的排列用完，开始第i+1个位置，恢复状态
            visit[i] = false;
        }

    }

}