package OD441;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 音乐小说内容重复识别
 * @date 2024/4/5
 * @level 9
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD441
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {

    private static String str1;
    private static String str2;
    private static ArrayList<HashSet<String>> similar;

    //存放相似结果
    private static ArrayList<String[]> isSimilar = new ArrayList<>();
    //存放第一个不相似的结果
    private static ArrayList<String[]> notSimilar = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //原始字符串
        str1 = sc.nextLine();

        //相似字符串
        str2 = sc.nextLine();

        //储存相似字符组
        similar = new ArrayList<>();


        //相似字符组
        while (sc.hasNextLine()) {
            String[] tmp = sc.nextLine().split(" ");
            //添加到相似字符数组

        }


    }

    /**
     * 比较两字符串是否相似，并将相似\不相似的结果加入对应列表
     *
     * @param
     * @return java.lang.String
     */
    public static String getResult() {


        return "True";
    }


}