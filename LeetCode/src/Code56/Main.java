package Code56;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 合并区间
 * @date 2024/4/7
 * @level 中等
 * @score
 * @url https://leetcode.cn/problems/merge-intervals/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public int[][] merge(int[][] intervals) {
        //先按区间start升序排列
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        //一共有多少个区间
        int len = intervals.length;

        //保存不重叠的区间
        LinkedList<int[]> ans = new LinkedList<>();
        //先添加第一个区间
        ans.add(intervals[0]);

        //从第二个区间开始
        for (int i = 1; i < len; i++) {
            int[] tmp = intervals[i];
            int[] pre = ans.getLast();
            //如果有重叠
            if (tmp[0] == pre[0] || tmp[0] <= pre[1]) {
                tmp[0] = Math.min(tmp[0], pre[0]);
                tmp[1] = Math.max(tmp[1], pre[1]);
                //更新
                ans.removeLast();
                ans.addLast(tmp);
            } else {
                //没有重叠,直接加
                ans.addLast(tmp);
            }
        }
        //把LinkedList转为二维数组
        return ans.toArray(new int[ans.size()][]);
    }
}