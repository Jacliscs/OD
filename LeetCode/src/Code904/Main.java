package Code904;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 水果成篮
 * @date 2024/4/20
 * @level 中等
 * @score
 * @url https://leetcode.cn/problems/fruit-into-baskets/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}


class Solution {
    public int totalFruit(int[] fruits) {
        //只能有两个种类,记录当前能采摘的两种的数量
        HashMap<Integer, Integer> map = new HashMap<>();

        //双指针
        int left = 0;

        //题解
        int ans = 0;

        for (int right = 0; right < fruits.length; right++) {
            //存放right的种类
            map.put(fruits[right], map.getOrDefault(fruits[right], 0) + 1);

            //此时如果超过了两种，则需要左指针右移，知道只剩两种
            while (map.size() > 2) {
                //把左指针代表的种类数量-1，如果数量为0，则移出
                map.put(fruits[left], map.get(fruits[left]) - 1);
                if (map.get(fruits[left]) == 0) map.remove(fruits[left]);
                //左指针右移
                left++;
            }

            //此时[left,right]中只有两种水果
            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}