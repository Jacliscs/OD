package OD334;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 信道分配
 * @date 2024/4/5
 * @level 9
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD334
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //信道的最大阶数
        int R = sc.nextInt();

        //0-R阶信道的个数
        int[] N = new int[R + 1];
        for (int i = 0; i <= R; i++) {
            N[i] = sc.nextInt();
        }

        //单个传输需要的数据量
        int D = sc.nextInt();

        //输出最多可以供多少用户传输数据
        System.out.println(getResult(R, N, D));

    }

    public static int getResult(int R, int[] N, int D) {
        //将D转为二进制并逆序 如30 -> 二进制：11110 -> 逆序：01111 N[]=[10,5,0,1,3,2]
        //表示需要花费对应信道的个数，其中N[5]对应的1个就可以满足一个用户
        int[] subtrahend = Arrays.stream(new StringBuilder(Integer.toBinaryString(D)).reverse().toString().split(""))
                .mapToInt(Integer::parseInt).toArray();

        //能满足的用户数量
        int count = 0;

        //大于subtrahend的信道数量,一个就可以满足一个用户
        for (int i = R; i >= subtrahend.length; i--) {
            count += N[i];
        }

        //剩余的信道数量，去掉大于subtrahend的信道数量，位数相同[0,subtrahend.length)
        int[] minuend = Arrays.copyOfRange(N, 0, subtrahend.length);

        //进行二进制减法，只要结果大于0，则说明可以再满足一个用户
        while (binary_sub(minuend, subtrahend)) {
            count++;
        }

        return count;

    }

    /**
     * minuend[]剩余信道个数，是否还能承载一个subtrahend[]
     *
     * @param minuend
     * @param subtrahend
     * @return boolean
     */
    public static boolean binary_sub(int[] minuend, int[] subtrahend) {
        //减法逻辑，从高位开始
        for (int i = minuend.length - 1; i >= 0; i--) {
            //如果对应位置的信道足够，直接相减
            if (minuend[i] >= subtrahend[i]) {
                minuend[i] -= subtrahend[i];
            } else {
                //不够，则要么向高位借1，要么向低位欠2
                //需要看minuend的[0,i]部分能承载的信道是否大于subtrahend的[0,i]部分，如果不能，只能向高位借
                if (cals_bin(Arrays.copyOfRange(minuend, 0, i + 1)) < cals_bin(Arrays.copyOfRange(subtrahend, 0, i + 1))) {
                    //向高位借 只要能借到一个，就能承载[0,i]部分，返回true
                    int j = i + 1;
                    //高一位不够，则再高一位，不能超过minuend.length
                    while (j < minuend.length) {
                        //如果能借，则-1，返回true
                        if (minuend[j] > 0) {
                            minuend[j]--;
                            return true;
                        } else {
                            //j位不够借，到j+1位借
                            j++;
                        }
                    }
                    //j一直借到最高位都没有借的，则剩下部分不足以再分配给一个用户了
                    return false;
                } else {
                    //低位能承载 minuend[i]<subtrahend[i] 向低位借
                    //此时minuend[i]为负数，表示欠债
                    minuend[i] -= subtrahend[i];

                    //将欠债加到低位，低位需要承担双倍
                    minuend[i - 1] += minuend[i] * 2;

                    //i位置的欠债清空
                    minuend[i] = 0;
                }
            }
        }
        //默认结果大于0，表示可以承载一个用户
        return true;
    }

    /**
     * 返回bin[]信道能承载的信道总数
     *
     * @param bin
     * @return int
     */
    public static int cals_bin(int[] bin) {
        int ans = 0;
        for (int i = 0; i < bin.length; i++) {
            ans += (int) (bin[i] * Math.pow(2, i));
        }
        return ans;
    }


}