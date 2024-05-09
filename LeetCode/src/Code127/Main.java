package Code127;

import java.util.*;

/**
 * @author 浮生
 * @description 单词接龙
 * @date 2024/5/7
 * @level 困难
 * @url <a href="https://leetcode.cn/problems/word-ladder/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //转成set，提升效率
        HashSet<String> wordSet = new HashSet<>(wordList);

        //wordList[i].length == beginWord.length
        //如果end不存在，则返回0
        if (!wordSet.contains(endWord)) return 0;

        Deque<String> queue = new LinkedList<>();
        //把begin加入搜索队列
        queue.offer(beginWord);

        //记录从begin转换到key对应的路径长度
        HashMap<String, Integer> map = new HashMap<>();
        //初始化
        map.put(beginWord, 1);

        //bfs 广度优先搜索
        while (!queue.isEmpty()) {
            //取出头字符
            String word = queue.poll();
            //获取当前路径长度
            int curPath = map.get(word);
            //尝试修改word的每个字符，并比较是否在wordList中
            for (int i = 0; i < word.length(); i++) {
                char[] chars = word.toCharArray();
                //将chars[i]分别替换为26个字母
                for (char k = 'a'; k <= 'z'; k++) {
                    //替换
                    chars[i] = k;
                    //转字符串
                    String newWord = new String(chars);
                    //如果转换后刚好是endWord
                    if (newWord.equals(endWord)) return curPath + 1;

                    //如果存在wordList中且还未访问
                    if (wordSet.contains(newWord) && !map.containsKey(newWord)) {
                        //将新字符串加入队列
                        queue.offer(newWord);
                        //记录路径长度
                        map.put(newWord, curPath + 1);
                    }
                }
            }
        }
        //没找到
        return 0;

    }
}