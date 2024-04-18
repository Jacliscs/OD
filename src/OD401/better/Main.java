package OD401.better;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 堆内存申请
 * @date 2024/4/18
 * @level
 * @score
 * @url
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {

    private static int malloc_size;
    private static ArrayList<int[]> used;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //要申请的内存数
        malloc_size = sc.nextInt();

        //内存地址
        used = new ArrayList<>();

        //不限制输入行数
        while (sc.hasNextInt()) {
            int start = sc.nextInt();
            int count = sc.nextInt();
            int end = start + count - 1;
            //加入内存
            used.add(new int[]{start, end});
        }

        //输出结果：成功申请则返回开始地址，申请失败则返回-1
        System.out.println(getResult());

    }

    public static int getResult() {
        //申请内存大小非法
        if (malloc_size < 0 || malloc_size > 100) {
            return -1;
        }

        //数组对应内存使用情况，0为空闲，1为占用
        int[] memory = new int[100];

        //取出已分配的内存
        for (int[] range : used) {
            int start = range[0];
            int end = range[1];

            //开始地址非法或结束地址非法
            if (start < 0 || start > 100 || end < 0 || end > 100) {
                return -1;
            }

            //分配内存，遇到重叠的则非法
            for (int i = start; i <= end; i++) {
                //如果该内存已被占用，则非法
                if (memory[i] == 1) return -1;
                    //否则，分配内存
                else memory[i] = 1;
            }
        }

        //现在内存已经分配好， 去获取分配malloc_size的开始地址
        return getAns(memory);
    }

    /**
     * 返回申请内存的开始地址
     *
     * @param memory
     * @return int
     */
    public static int getAns(int[] memory) {
        //记录最佳起始位置
        int ans = -1;

        //记录满足申请内存的空闲区间的最小值
        int min_size = Integer.MAX_VALUE;

        //左指针
        int l = 0;
        while (l < 100) {
            //找到空闲区间的左边界
            while (l < 100 && memory[l] == 1) l++;

            //补充逻辑
            if (l >= 100) break;

            //找空闲区间的右边届
            int r = l + 1;
            while (r < 100 && memory[r] == 0) r++;

            //此时空闲区间长度
            int free_size = r - l;

            //大于申请内存且长度更优，则更新
            if (free_size >= malloc_size && free_size < min_size) {
                min_size = free_size;
                //更新题解：申请内存的左边界
                ans = l;
            }

            //找下一个左边界
            l = r + 1;
        }
        return ans;
    }
}