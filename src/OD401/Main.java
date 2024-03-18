package OD401;

import java.util.*;

/**
 * @author Jacliscs
 * @description 堆内存申请
 * @date 2024/3/18
 * @level 6
 * @score 100
 */

/**
 * 题目描述
 * 有一个总空间为100字节的堆，现要从中新申请一块内存，内存分配原则为：优先紧接着前一块已使用内存，分配空间足够且最接近申请大小的空闲内存。
 * <p>
 * 输入描述
 * 第1行是1个整数，表示期望申请的内存字节数
 * <p>
 * 第2到第N行是用空格分割的两个整数，表示当前已分配的内存的情况，每一行表示一块已分配的连续内存空间，每行的第1和第2个整数分别表示偏移地址和内存块大小，如：
 * <p>
 * 0 1
 * <p>
 * 3 2
 * <p>
 * 表示 0 偏移地址开始的 1 个字节和 3 偏移地址开始的 2 个字节已被分配，其余内存空闲。
 * <p>
 * 输出描述
 * 若申请成功，输出申请到内存的偏移；
 * <p>
 * 若申请失败，输出 -1。
 * <p>
 * 备注
 * 若输入信息不合法或无效，则申请失败
 * 若没有足够的空间供分配，则申请失败
 * 堆内存信息有区域重叠或有非法值等都是无效输入
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //总内存可以看做是大小为100的一维数组
        int[] memory = new int[100];
        //标记每个位置是否被申请过
        boolean[] used = new boolean[100];
        //申请内存
        int request = sc.nextInt();
        //存放开始地址和申请长度
        List<int[]> list = new ArrayList<>();
        while (sc.hasNextInt()) {
            //本地不能跳出循环，OJ环境读取到输入数据文件末尾时，会自动带上EOF标志
            //Java的hasNext，或者hasNextLine会识别到这个标志，然后自动结束
            list.add(new int[]{sc.nextInt(), sc.nextInt()});
        }
        //填数据
        for (int i = 0; i < list.size(); i++) {
            //填入失败
            if (!fill(memory, used, list.get(i)[0], list.get(i)[1])) {
                System.out.println(-1);
                //终止进程
                System.exit(0);
            }
        }

        System.out.println(find(memory, used, request));
    }

    //返回区间最短且满足申请长度的第一个区间下标
    public static int find(int[] memory, boolean[] used, int request) {
        //申请内存大小非法
        if (request <= 0 || request > 100) {
            return -1;
        }
        //默认的下标起始位置
        int ans = 0;
        int min = 100;
        //开始的左边界
        int left = 0;
        while (left < 100) {
            //找到第一个空闲的格子
            while (left < 100 && used[left]) {
                left++;
            }
            //找到空闲区间的右边届
            int right = left + 1;
            while (right < 100 && !used[right]) {
                right++;
            }
            //此时的空闲内存大小
            int free = right - left;
            //空闲内存大小要大于申请的大小，且更新最小值
            if (free >= request && free < min) {
                min = free;
                ans = left;
            }
            //寻找下一个空闲内存
            left = right + 1;
        }
        return ans;
    }


    //填充数组，返回
    public static boolean fill(int[] memory, boolean[] used, int start, int count) {
        for (int i = start; i < start + count; i++) {
            //合法情况
            if (!used[i] && i < 100 && i >= 0 && start + count <= 100) {
                used[i] = true;
            } else {
                return false;
            }
        }
        //全都能合法填入
        return true;
    }
}