package OD436;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 简易内存池
 * @date 2024/4/3
 * @level 200
 * @score 6
 * @url https://hydro.ac/d/HWOD2023/p/OD436
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //命令个数
        int n = Integer.parseInt(sc.nextLine());

        //n条命令
        String[][] cmds = new String[n][2];
        for (int i = 0; i < n; i++) {
            cmds[i] = sc.nextLine().split("=");
        }

        //输出结果
        getResult(n, cmds);

    }


    public static void getResult(int n, String[][] cmds) {
        //使用used记录已占用的内存
        LinkedList<int[]> used = new LinkedList<>();
        //使用[100,101]限制右边界不能大于100，左边界是从0开始，不用[-2,-1]限制
        used.add(new int[]{100, 101});

        //按行读取命令
        for (String[] cmd : cmds) {
            String key = cmd[0];
            String val = cmd[1];

            //申请命令 常量在前面防止空指针异常
            if ("REQUEST".equals(key)) {
                //需要分配的内存大小
                int size = Integer.parseInt(val);

                //异常处理：申请的大小为0
                if (size == 0) {
                    System.out.println("error");
                    //继续执行下一行命令
                    continue;
                }

                //默认从start=0开始申请内存
                int start = 0;

                //标志是否成功分配
                boolean flag = false;

                //used.size()是已分配的地址块
                for (int i = 0; i < used.size(); i++) {
                    //申请地址的结束下标
                    int end = start + size - 1;
                    //要申请的内存区间
                    int[] range = new int[]{start, end};

                    //如果申请的内存与已分配的内存不冲突，则添加进已分配内存列表
                    if (!hasIntersection(range, used.get(i))) {
                        //分配到内存 插入到第i块地址前
                        used.add(i, range);
                        //成功分配内存
                        flag = true;
                        //打印起始位置
                        System.out.println(start);
                        break;
                    } else {
                        //range与used.get(i)有交叉，则改变起始位置到used.get(i)结束位置的后一位
                        start = used.get(i)[1] + 1;
                    }
                }
                //遍历完，还是没成功分配内存
                if (!flag) {
                    System.out.println("error");
                }
            }
            //释放命令
            else {
                //当cmd[0] = "RELEASE"时，cmd[1]是要释放的起始地址
                int addr = Integer.parseInt(val);

                //越界检查
                if (addr >= 100) {
                    System.out.println("error");
                    //继续下一条命令
                    continue;
                }

                //标记是否释放成功
                boolean flag = false;

                //遍历比较used中的启示地址，与addr相同则移除
                for (int i = 0; i < used.size(); i++) {
                    if (used.get(i)[0] == addr) {
                        used.remove(i);
                        flag = true;
                        break;
                    }
                }

                //如果没有释放成功，或没有该起始地址的内存
                if (!flag) {
                    System.out.println("error");
                }
            }
        }


    }

    /**
     * 判断两个区间是否有交集
     *
     * @param range1
     * @param range2
     * @return boolean
     * @create 2024/4/3 17:42
     */
    public static boolean hasIntersection(int[] range1, int[] range2) {
        int s1 = range1[0];
        int e1 = range1[1];

        int s2 = range2[0];
        int e2 = range2[1];

        if (s1 == s2) {
            return true;
        } else if (s1 < s2) {
            return e1 >= s2;
        } else {
            //s1>s2
            return e2 >= s1;
        }
    }

}