package OD347;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 学生重新排队
 * @date 2024/4/5
 * @level 9
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD347
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    //分块：即连续的相同组的小朋友个数
    static class Block {
        //组号
        int groupId;
        //连续的人数[1,3]
        int count;

        //默认初始化
        public Block(int groupId, int count) {
            this.groupId = groupId;
            this.count = count;
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //初始化排队顺序
        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = nums.length;

        //序号 -> 组号的对应关系
        int[] group = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            //每三个为一组 整除3
            group[num] = i / 3;
        }

        //输出最多需要排多少次
        System.out.println(getResult(nums, group));

    }

    /**
     * 按组调整需要的最小调整次数
     *
     * @param nums
     * @param group
     * @return int
     */
    public static int getResult(int[] nums, int[] group) {
        //相邻相同组号合并
        LinkedList<Block> queue = new LinkedList<>();

        for (int num : nums) {
            int groupId = group[num];
            //如果与上一个组号不同，则新加入
            if (queue.isEmpty() || queue.getLast().groupId != groupId) {
                queue.addLast(new Block(groupId, 1));
            } else {
                //与前一个组号相同
                queue.getLast().count++;
            }
        }
        //此时queue中形如：[11][222][33][1][3]

        //记录调整次数
        int moved_count = 0;
        //每遇到一个相同组号且个数为3时，消去，类似于连连看，最后队列清空
        while (!queue.isEmpty()) {
            Block first = queue.removeFirst();

            //如果第一组只有一个连续的人，如
            // 1 x[] 1 1 y z
            // 1 x[] 1 y 1 z
            if (first.count == 1) {
                Block x = queue.getFirst();
                //如果x是完整组，则消去，且不消耗步数
                while (x.count == 3) {
                    queue.removeFirst();
                    x = queue.getFirst();
                }

                //如果现在x与first刚好是同组，且加起来是3个人，则消去，步数+1，相当于把first插入到x[11]前面
                if (x.groupId == first.groupId && x.count + first.count == 3) {
                    moved_count++;
                    queue.removeFirst();
                } else {
                    // 情况如下：中间间隔其他组
                    // 1 x[2 2] 1 1
                    // 1 x[2] 1 2 1
                    // 将后面的两个1移动到开头
                    moved_count += 2;

                    //删去first.groupId后，合并剩下组
                    queue = confirm(queue, first.groupId);
                }
            } else if (first.count == 2) {
                // 当first.count == 2 时，情况如下：
                // 1 1 x 1 y z
                //把后面的那个1拿到前面来
                moved_count++;
                //删去first.groupId后，合并剩下组
                queue = confirm(queue, first.groupId);
            }
            //first.count = 3 则不需要操作，直接被removeFirst()，开始下一组

        }
        //返回操作次数
        return moved_count;
    }

    /**
     * 把queue中组号为confirmed_groupId的删去后合并剩下的组
     *
     * @param queue
     * @param confirmed_groupId
     * @return java.util.LinkedList<OD347.Main.Block>
     */
    public static LinkedList<Block> confirm(LinkedList<Block> queue, int confirmed_groupId) {
        LinkedList<Block> back_queue = new LinkedList<>();
        while (!queue.isEmpty()) {
            Block first = queue.removeFirst();

            if (first.groupId == confirmed_groupId) {
                continue;
            }
            if (back_queue.isEmpty() || back_queue.getLast().groupId != first.groupId) {
                //添加新的
                back_queue.addLast(new Block(first.groupId, first.count));
            } else {
                //次数加1
                back_queue.getLast().count += first.count;
            }
        }

        return back_queue;
    }

}