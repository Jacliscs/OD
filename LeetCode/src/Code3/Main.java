package Code3;

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
        if (s.equals("")) {
            return 0;
        }

        //最大长达
        int maxLen = 1;

        for (int i = 0; i < s.length(); i++) {
            //存放字符
            HashSet<String> set = new HashSet<>();
            //把第i位放入
            set.add(s.charAt(i) + "");
            for (int j = i + 1; j < s.length(); j++) {
                //如果不重复，则放入，更新最大长度
                if (!set.contains(s.charAt(j) + "")) {
                    set.add(s.charAt(j) + "");
                    maxLen = Math.max(maxLen, set.size());
                } else {
                    //重复了，更新最大长度，并清空set
                    maxLen = Math.max(maxLen, set.size());
                    break;
                }
            }
        }
        return maxLen;
    }
}