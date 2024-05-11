package Code792.bucket;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 分桶做法
 * @date 2024/5/11
 * @level
 * @url <a href="">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public int numMatchingSubseq(String s, String[] words) {
        //根据26个首字母分桶
        Deque<String>[] bucket = new Deque[26];

        //初始化
        for (int i = 0; i < 26; i++) {
            bucket[i] = new ArrayDeque<>();
        }

        //把words里面的单词，放在对应的桶内
        for (String word : words) {
            //根据首字母分桶
            bucket[word.charAt(0) - 'a'].add(word);
        }

        //题解：子序列个数
        int ans = 0;

        //遍历s，每次到对应的桶位置查找，如果刚好长度为1，说明是子序列，否则去掉首字母后重新放到对应的桶
        for (char c : s.toCharArray()) {
            //c首字母所对应的桶
            Deque<String> deque = bucket[c - 'a'];
            //遍历该桶
            for (int i = deque.size(); i > 0; i--) {
                //取出
                String tmp = deque.pollFirst();

                //如果长度为1，则找到一个子序列
                if (tmp.length() == 1) ans++;
                    //不为1，则去掉首字母后重新放置
                else bucket[tmp.charAt(1) - 'a'].offer(tmp.substring(1));
            }
        }

        return ans;
    }
}