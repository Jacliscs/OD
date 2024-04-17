package OD434;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 最长子字符串的长度（二）
 * @date 2024/3/30
 * @level 8
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD434
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //字符串
        String s = sc.nextLine();
        System.out.println(getResult(s));
    }


    /**
     * 返回子字符串中出现o l x都为偶数次时的最大子字符串长度
     *
     * @param s
     * @return int
     * @create 2024/3/30 22:21
     */
    public static int getResult(String s) {
        //二进制 000 第一位表示l 第二位表示o 第三位表示x
        //0表示偶数 1表示奇数 初始时都是0次，偶数
        int status = 0b000;

        //map.get(i)记录状态i出现的所有位置 3个字母 最多有2^3=8种状态
        ArrayList<LinkedList<Integer>> map = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            map.add(new LinkedList<>());
        }

        //000对应的十进制为0 最早出现与还没遍历的时候，即-1
        map.get(0).add(-1);

        //l o x都为偶数时的最长子串长度
        int maxLen = 0;

        //可以循环遍历
        for (int i = 0; i < s.length() * 2; i++) {
            //防止下标越界
            char c = s.charAt(i % s.length());
            switch (c) {
                case 'l':
                    //改变第一位的奇偶性 status与二进制100进行异或运算 相同为0 不同为1
                    status ^= 0b100;
                    break;
                case 'o':
                    status ^= 0b010;
                    break;
                case 'x':
                    status ^= 0b001;
                    break;
            }
            //第一轮时，还没回到起点
            if (i < s.length()) {
                //记录状态 和 该状态出现过的所有位置 getFirst()是最早位置
                map.get(status).add(i);
            }

            while (!map.get(status).isEmpty()) {
                //状态status最早出现的位置
                int earliest = map.get(status).getFirst();

                //i是当前status的位置 可能会在s.length - 2*s.length中，截出的子串长度可能会超出s
                if (i - earliest > s.length()) {
                    //如果[earliest,i]的长度超过s的长度，则说明earliest左越界
                    map.get(status).removeFirst();
                } else {
                    maxLen = Math.max(maxLen, i - earliest);
                    break;
                }
            }
        }
        return maxLen;
    }

}