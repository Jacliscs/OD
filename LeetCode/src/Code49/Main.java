package Code49;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 浮生
 * @description 字母异位词分组
 * @date 2024/4/9
 * @level 中等
 * @score
 * @url https://leetcode.cn/problems/group-anagrams/description/?envType=study-plan-v2&envId=top-100-liked
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {

        //排序后字符串相同，则是异位词
        Map<String, List<String>> map = Arrays.stream(strs).collect(Collectors.groupingBy(s -> {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            //如果排序后chars相同，则会把s加入到chars对应的List<String>中
            return new String(chars);
        }));

        return new ArrayList<>(map.values());

    }
}