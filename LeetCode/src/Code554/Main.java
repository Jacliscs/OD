package Code554;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 砖墙
 * @date 2024/4/7
 * @level 中等
 * @score
 * @url https://leetcode.cn/problems/brick-wall/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        //砖墙的行数
        int n = wall.size();
        //存放裂缝编号及裂缝出现的次数 如(1,2,2,1) 裂缝编号就是(1,3,5) 其中6是墙的最右边，不能穿过
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            //按行遍历
            int sum = 0;
            for (int val : wall.get(i)) {
                sum += val;
                //对应裂缝次数+1
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
            //最后sum=墙的宽度，即最右边的裂缝，不能穿过，要移出
            map.remove(sum);
        }
        //找到最大的裂缝出现次数，用墙的行数-最大裂缝出现的次数=需要穿过的砖的个数
        int max = map.values().stream().max(Integer::compareTo).orElse(0);

        return n - max;
    }
}