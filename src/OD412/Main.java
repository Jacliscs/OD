package OD412;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 字符串拼接
 * @date 2024/3/29
 * @level 6
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD412
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {

    private static String str;
    private static int n;
    //private static int count = 0; //会超时

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //字符串
        str = sc.next();
        //结果字符串的长度
        n = sc.nextInt();
        System.out.println(getResult());
    }

    /**
     * 返回满足条件的最多子串个数
     *
     * @param
     * @return int
     * @create 2024/3/29 16:58
     */
    public static int getResult() {
        //异常输入：如果子串的长度大于原字符串的长度 或者 不是a-z的字符
        char[] chs = str.toCharArray();
        if (n > chs.length) {
            return 0;
        }
        for (char c : chs) {
            if (c < 'a' || c > 'z') {
                return 0;
            }
        }

        //排序，方便树层去重
        Arrays.sort(chs);

        //返回count
        return dfs(chs, -1, 0, new boolean[chs.length], 0);
    }

    /**
     * dfs求满足条件的全排列
     *
     * @param chs   需要做全排列的数组
     * @param pre   上一个排列元素在数组中的索引
     * @param len   当前排列的长度
     * @param used  记录元素是否被使用
     * @param count 统计满足条件的个数
     * @create 2024/3/29 17:02
     */
    public static int dfs(char[] chs, int pre, int len, boolean[] used, int count) {
        //当排列的长度满足条件时，加1
        if (len == n) {
            return ++count;
        }
        //遍历
        for (int i = 0; i < chs.length; i++) {
            //如果被使用过
            if (used[i]) continue;

            //条件：相邻元素不能相同
            if (pre >= 0 && chs[i] == chs[pre]) continue;

            //树层去重
            if (i > 0 && chs[i] == chs[i - 1] && !used[i - 1]) continue;

            //使用该数字
            used[i] = true;
            //递归
            count = dfs(chs, i, len + 1, used, count);
            //恢复
            used[i] = false;
        }
        return count;

    }

}