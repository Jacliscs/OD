package OD232;

import java.util.*;

/**
 * @author Jacliscs
 * @description 英文输入法
 * @date 2024/3/17
 */

/**
 * 题目描述
 * 主管期望你来实现英文输入法单词联想功能。
 * <p>
 * 需求如下：
 * <p>
 * 依据用户输入的单词前缀，从已输入的英文语句中联想出用户想输入的单词，按字典序输出联想到的单词序列，
 * 如果联想不到，请输出用户输入的单词前缀。
 * 注意：
 * <p>
 * 英文单词联想时，区分大小写
 * 缩略形式如”don’t”，判定为两个单词，”don”和”t”
 * 输出的单词序列，不能有重复单词，且只能是英文单词，不能有标点符号
 * 输入描述
 * 输入为两行。
 * <p>
 * 首行输入一段由英文单词word和标点符号组成的语句str；
 * <p>
 * 接下来一行为一个英文单词前缀pre。
 * <p>
 * 0 < word.length() <= 20
 * 0 < str.length <= 10000
 * 0 < pre <= 20
 * 输出描述
 * 输出符合要求的单词序列或单词前缀，存在多个时，单词之间以单个空格分割
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //读取输入
        String s = sc.nextLine();
        //前缀匹配
        String pre = sc.nextLine();
        System.out.println(match(pre, s));

    }

    //根据前缀匹配单词
    public static String match(String pre, String str) {
        //把单词用非字母分隔开
        String[] words = str.split("[^a-zA-Z]");
        int n = words.length;
        List<String> sj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (words[i].startsWith(pre)) {
                sj.add(words[i]);
            }
        }
        if (sj.isEmpty()) {
            return pre;
        } else {
            //按字典排序
            sj.sort(String::compareTo);
            StringJoiner result = new StringJoiner(" ");
            for (String s1 : sj) {
                //不能有重复的
                if (!result.toString().contains(s1)) {
                    result.add(s1);
                }
            }
            return result.toString();
        }
    }
}