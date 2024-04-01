package OD418;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 最大社交距离
 * @date 2024/4/1
 * @level 7
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD418
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //座位个数
        int seatNum = Integer.parseInt(sc.nextLine());
        //座位进出情况
        String tmp = sc.nextLine();
        int[] seatOrLeave = Arrays.stream(tmp.substring(1, tmp.length() - 1).split(", ")).mapToInt(Integer::parseInt).toArray();
        //返回最后一个人坐的位置 如果位置已满，则返回-1
        System.out.println(getResult(seatOrLeave, seatNum));

    }

    public static int getResult(int[] seatOrLeave, int seatNum) {
        //已经已经坐人座位的序号 [0,n-1]
        ArrayList<Integer> seatIdx = new ArrayList<>();

        //记录题解：最后一个人坐的座位编号
        int last_seat_id = -1;

        //遍历座位的进出顺序
        for (int info : seatOrLeave) {
            //如果是负数，则是对应位置的人离开座位
            if (info < 0) {
                int leave_id = -info;
                //转Integer类型
                seatIdx.remove(Integer.valueOf(leave_id));
                continue;
            }

            //1 表示进场

            //如果没有空座位
            if (seatIdx.size() == seatNum) {
                //假设当前就是最后一个人
                last_seat_id = -1;
                continue;
            }

            //初始化，第一个人只能坐0，第二个人只能坐seatNum-1
            if (seatIdx.size() == 0) {
                seatIdx.add(0);
                last_seat_id = 0;
            } else if (seatIdx.size() == 1) {
                //第二个人只能坐最右边
                seatIdx.add(seatNum - 1);
                last_seat_id = seatNum - 1;
            } else {
                //第三个人及以上，找最大社交距离，同样大社交距离则取索引小的
                //记录最大社交距离的座位号
                int bestId = -1;
                //记录最大社交距离
                int bestDis = -1;

                //遍历每一个连续空位置区间
                int left = seatIdx.get(0);//初始左边界
                for (int i = 1; i < seatIdx.size(); i++) {
                    //右边界
                    int right = seatIdx.get(i);

                    //左右边界中间空闲长度
                    int dis = right - left - 1;

                    //必须要大于0才能坐人，比较当前区域的最大社交距离
                    if (dis > 0) {
                        //当前区域能产生的最大社交距离：离自己最近的人的最大距离
                        int curSeatDis = dis / 2 - (dis % 2 == 0 ? 1 : 0);
                        //当前区域内具有最大社交距离的位置
                        int curSeatIdx = left + curSeatDis + 1;

                        //保留最优解
                        if (curSeatDis > bestDis) {
                            bestDis = curSeatDis;
                            bestId = curSeatIdx;
                        }
                    }

                    //左边界往右递加
                    left = right;
                }

                //如果最后一个位置没人，比如后面-9 离开了
                if (seatIdx.get(seatIdx.size() - 1) < seatNum - 1) {
                    //此时社交距离就是最后一个位置到目前坐的最后一个位置的距离
                    int curSeatDis = seatNum - 1 - seatIdx.get(seatIdx.size() - 1) - 1;
                    int curSeatIdx = seatNum - 1;

                    //保留最优解
                    if (curSeatDis > bestDis) {
                        bestDis = curSeatDis;
                        bestId = curSeatIdx;
                    }
                }

                //bestId位置能坐人，则加入到座位列表
                if (bestId > 0) {
                    seatIdx.add(bestId);
                    //升序排序
                    seatIdx.sort((a, b) -> a - b);
                }

                //假设此时就是最后一个人，无论能否坐进去，都更新
                last_seat_id = bestId;
            }
        }
        return last_seat_id;
    }
}