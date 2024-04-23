package Code6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author 浮生
 * @description Z字型变换
 * @date 2024/4/23
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/zigzag-conversion/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    /**
     * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
     *
     * @param s
     * @param numRows
     * @return java.lang.String
     */
    public String convert(String s, int numRows) {
        //如果只有一行或者一列
        if (numRows == 1 || numRows >= s.length()) return s;

        //按行添加，最后从第一行拼接到最后一行
        List<StringBuilder> rows = new ArrayList<>(numRows);
        //初始化每行
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }

        //标志当前字符应该添加到哪一行，默认从第一行开始
        int index = 0;

        //控制index上下移动，默认向下即+1 但第一行和最后一行要反转方向，故初始化为-1
        int flag = -1;

        //遍历字符串
        for (char c : s.toCharArray()) {
            //添加到第i行
            rows.get(index).append(c);

            //如果是最后一行或者第一行，则反转方向
            if (index == 0 || index == numRows - 1) {
                flag = -flag;
            }

            //更新index
            index += flag;
        }

        //把每一行的数据拼接
        StringBuilder ans = new StringBuilder();

        for (StringBuilder row : rows) {
            ans.append(row);
        }

        return ans.toString();
    }
}