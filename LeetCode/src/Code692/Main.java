package Code692;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 前k个高频单词
 * @date 2024/4/23
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/top-k-frequent-words/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        //统计出现次数
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        //题解：
        List<String> ans = new ArrayList<>();

        //排序，首先按出现次数降序，出现次数一样的按字典顺序排序
        map.keySet().stream().sorted((a, b) -> {
            //出现次数不同
            if (map.get(a) != map.get(b)) {
                return map.get(b) - map.get(a);
            } else {
                //出现次数相同，按字典排序
                return a.compareTo(b);
            }
        }).forEach(ans::add);

        //只保留k个
        while (ans.size() > k) {
            ans.remove(ans.size() - 1);
        }

        return ans;
    }
}