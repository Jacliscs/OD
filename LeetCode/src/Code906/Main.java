package Code906;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 超级回文数
 * @date 2024/5/9
 * @level 困难
 * @url <a href="https://leetcode.cn/problems/super-palindromes/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public int superpalindromesInRange(String left, String right) {
        //左右边界
        long L = Long.parseLong(left);
        long R = Long.parseLong(right);


        //P<10^18 P自身是回文数且也是一个回文数M的平方，M<10^9
        //假设M=12344321 或 1234321
        //令K=M的前半部分 1234，暴力i~[1,k],拼接后再平方，看是否在范围内
        int K = 100000;
        int ans = 0;

        //如果是奇数个数
        for (int i = 1; i < K; i++) {
            StringBuilder sb = new StringBuilder(Integer.toString(i));

            //i是前半部分，拼接后半部分1234 321
            for (int j = sb.length() - 2; j >= 0; j--) {
                //拼接后半部分
                sb.append(sb.charAt(j));
            }

            long tmp = Long.parseLong(sb.toString());
            //平方
            tmp *= tmp;

            //超过范围了则跳出，后面的都会超出范围
            if (tmp > R) break;

            //大于左边界，且平方数是回文数
            if (tmp >= L && check(tmp)) ans++;
        }

        //如果是偶数个数
        for (int i = 0; i < K; i++) {
            StringBuilder sb = new StringBuilder(Integer.toString(i));

            //i是前半部分，拼接后半部分1234 4321
            for (int j = sb.length() - 1; j >= 0; j--) {
                //拼接后半部分
                sb.append(sb.charAt(j));
            }

            long tmp = Long.parseLong(sb.toString());
            //平方
            tmp *= tmp;

            //超过范围了则跳出，后面的都会超出范围
            if (tmp > R) break;

            //大于左边界，且平方数是回文数
            if (tmp >= L && check(tmp)) ans++;
        }

        return ans;
    }

    /**
     * 判断num是否是回文数
     *
     * @param num
     * @return boolean
     */
    public boolean check(Long num) {
        String str = Long.toString(num);

        StringBuilder sb = new StringBuilder(str);

        return sb.reverse().toString().equals(str);
    }
}