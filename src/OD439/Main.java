package OD439;

import java.util.*;

/**
 * @author Jacliscs
 * @description 中文分词模拟器
 * @date 2024/3/25
 * @level 7
 * @score 200
 */

/**
 * 题目描述
 * 给定一个连续不包含空格的字符串，该字符串仅包含英文小写字母及英文标点符号（逗号、分号、句号），同时给定词库，对该字符串进行精确分词。
 * <p>
 * 说明：
 * <p>
 * 精确分词：字符串分词后，不会出现重叠。即"ilovechina"，不同词库可分割为"i,love,china"，"ilove,china"，不能分割出现重叠的"i,ilove,china"，i 出现重叠
 * <p>
 * 标点符号不成词，仅用于断句
 * <p>
 * 词库：根据外部知识库统计出来的常用词汇例：dictionary = ["i", "love", "china", "lovechina", "ilove"]
 * <p>
 * 分词原则：采用分词顺序优先且最长匹配原则
 * <p>
 * "ilovechina"，假设分词结果 [i,ilove,lo,love,ch,china,lovechina]，则输出 [ilove,china]
 * <p>
 * 错误输出：[i,lovechina]，原因："ilove" > 优先于 "lovechina" 成词
 * <p>
 * 错误输出：[i,love,china]，原因："ilove" > "i"遵循最长匹配原则
 * <p>
 * 输入描述
 * 第一行输入待分词语句 "ilovechina"
 * <p>
 * 字符串长度限制：0 < length < 256
 * 第二行输入中文词库 "i,love,china,ch,na,ve,lo,this,is,this,word"
 * <p>
 * 词库长度限制：1 < length < 100000
 * 输出描述
 * 按顺序输出分词结果 "i,love,china"
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //待分词语句 用 , . ; 分隔
        String[] sentences = sc.nextLine().split("[,.;]");
        //词库
        String[] words = sc.nextLine().split("[,.;]");
        System.out.println(getResult(sentences, words));

    }

    //返回分词后的结果
    public static String getResult(String[] sentences, String[] words) {
        //存放待分词语句 从第一个位置取出，匹配后将剩下的部分再添加到第一个
        LinkedList<String> sentence_list = new LinkedList<>(Arrays.asList(sentences));
        //存放词库 优先顺序优先最长遍历 每个词库只能用一次
        LinkedList<String> word_list = new LinkedList<>(Arrays.asList(words));
        //HashSet<String> word_list = new HashSet<>(Arrays.asList(words));
        //存放分词后的结果
        LinkedList<String> result_list = new LinkedList<>();

        while (!sentence_list.isEmpty()) {
            //从左到右取出第一个
            String sentence = sentence_list.removeFirst();
            int r = sentence.length();
            //从[0,r)截取 实现优先最长匹配
            for (; r > 0; r--) {
                String subString = sentence.substring(0, r);
                //如果匹配到了，则是最长的，把剩余部分添加回sentence_list，跳出
                if (word_list.contains(subString)) {
                    //将该分组加入结果
                    result_list.addLast(subString);
                    //将word_list中对应的删除
                    word_list.remove(subString);
                    //如果有剩余部分 则需要把包含i后面的部分添加回待拆分的句子
                    if (r < sentence.length()) {
                        sentence_list.addFirst(sentence.substring(r));
                    }
                    //添加回后，跳出
                    break;
                }
            }
            //如果没有匹配到对应的，则拆分首字母，并把剩余添加回
            if (r == 0) {
                result_list.add(sentence.charAt(0) + "");
                //如果有剩余，把1后面的加回
                if (sentence.length() > 1) {
                    sentence_list.addFirst(sentence.substring(1));
                }
            }
        }
        //打印结果
        StringJoiner joiner = new StringJoiner(",");
        //把result_list中的插入到joiner中
        result_list.forEach(s -> joiner.add(s));
        return joiner.toString();
    }

}