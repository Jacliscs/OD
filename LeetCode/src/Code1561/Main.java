package Code1561;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 你可以获得的最大硬币数目
 * @date 2024/5/1
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/maximum-number-of-coins-you-can-get/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {

    /**
     * 把硬币分为3n堆，每次自己拿中间值，返回所有结果中最大的
     *
     * @param piles
     * @return int
     */
    public int maxCoins(int[] piles) {
        //n堆硬币 相当于拿n次第二大的硬币
        int n = piles.length / 3;

        //A每次拿最大的，自己拿第二大，让C拿最小的
        Arrays.sort(piles);

        //自己拿的下标，从第二大开始拿，每次减2
        int index = piles.length - 2;

        //题解：自己能获得的最多硬币
        int ans = 0;
        for (int i = 0; i < n; i++) {
            //每次拿第二大的
            ans += piles[index];
            index -= 2;
        }

        return ans;
    }


}