package Code3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 无重复字符的最长子串
 * @date 2024/4/7
 * @level
 * @score
 * @url
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(new Solution().lengthOfLongestSubstring(s));

    }
}

class Solution {
    /**
     * 返回无重复字符的最长子串长度
     *
     * @param s
     * @return int
     */
    public int lengthOfLongestSubstring(String s) {
        //存放字符以及出现的下标
        HashMap<Character, Integer> map = new HashMap<>();
        //开始的左边界
        int left = -1;
        //题解
        int ans = 0;
        int len = s.length();
        for (int right = 0; right < len; right++) {
            //[left,right]中无重复字符
            if (map.containsKey(s.charAt(right))) {
                //更新左边界
                left = Math.max(left, map.get(s.charAt(right)));
            }
            //存放右边届
            map.put(s.charAt(right), right);
            //更新长度
            ans = Math.max(ans, right - left);
        }
        return ans;
    }
}