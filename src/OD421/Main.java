package OD421;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 最小矩阵宽度
 * @date 2024/3/29
 * @level 7
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD421
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {

    private static int n;
    private static int m;
    private static int[][] matrix;
    private static int k;
    private static int[] cnts;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //矩阵行数
        n = sc.nextInt();
        //矩阵列数
        m = sc.nextInt();
        //矩阵
        matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        //目标矩阵的长度
        k = sc.nextInt();
        //目标矩阵中所需目标数字的个数，默认为0
        //比如目标矩阵是[1,1,4] 则cnts[1] = 2,cnts[4] = 1
        //输入的数字[1,1000]
        cnts = new int[1000];

        for (int i = 0; i < k; i++) {
            int num = sc.nextInt();
            cnts[num]++;
        }

        System.out.println(getResult());

    }

    public static int getResult() {
        //未完成匹配的元素个数
        int total = k;

        //最小子矩阵的宽度
        int minLen = Integer.MAX_VALUE;

        //初始矩阵的左边界(列)
        int l = 0;
        int r = 0;

        while (r < m) {
            //将第r列的元素纳入子矩阵
            for (int i = 0; i < n; i++) {
                int num = matrix[i][r];

                //if (cnts[num] > 0){//如果是目标内的num，且当前对num的需求量大于0
                //    cnts[num]--;//匹配到一个就减一个
                //    total--;//总需求-1
                //}else {
                //    //就算不是目标num，也要减1，这样后续左边界l右移的时候，对非目标num++也不会让cnts[num]>0,
                //    cnts[num]--;
                //}

                //如果num是需要的数字，此时cnts[num]一定>0 如cnts[1] = 2,变为cnts[1]=1,然后将total-1
                //如果num不是需要的数字，初始cnts[num]=0,不满足cnts[num]>0，同时cnts[num]也要-1，便于后续移动左边界后，不会让cnts[num]>0最多=0
                if (cnts[num]-- > 0) {
                    total--;
                }

            }

            //当前l-r列包含了目标数组中的所有数字，但不一定是最小宽度
            while (total == 0) {
                minLen = Math.min(minLen, r - l + 1);
                //将左边界l右移
                for (int i = 0; i < n; i++) {
                    int num = matrix[i][l];
                    //此时所有非目标num的cnts[num]都应该小于0
                    if (cnts[num] >= 0) {
                        //目标num
                        cnts[num]++;
                        total++;
                    } else {
                        //非目标
                        cnts[num]++;
                    }

                    //如果移出的
                    //if (cnts[num]++ >= 0){
                    //    total++;
                    //}
                }
                l++;
            }
            //r右移
            r++;
        }

        //如果没有找到能满足目标数组的矩阵，则不会去修改minLen
        if (minLen == Integer.MAX_VALUE) {
            return -1;
        } else {
            return minLen;
        }

    }
}