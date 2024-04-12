package Code239;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 滑动窗口最大值
 * @date 2024/4/12
 * @level 困难
 * @score
 * @url https://leetcode.cn/problems/sliding-window-maximum/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        //存放结果
        int[] ans = new int[nums.length - k + 1];

        //单调队列 存放的是当前窗口内的数字下标，按nums[i]对应的大小降序
        Deque<Integer> queue = new LinkedList<>();

        //滑窗形成过程 把该滑窗内的最大值放在queue队首，降序排列
        for (int i = 0; i < k; i++) {
            while (!queue.isEmpty() && nums[i] >= nums[queue.peekLast()]) {
                //队尾出栈
                queue.pollLast();
            }
            //保证queue单调,此时队尾没有比nums[i]更大的
            queue.offerLast(i);
        }

        //把第一个滑窗内的最大值加入ans
        ans[0] = nums[queue.peekFirst()];

        //滑窗移动过程
        for (int i = k; i < nums.length; i++) {
            while (!queue.isEmpty() && nums[i] >= nums[queue.peekLast()]) {
                //队尾出栈
                queue.pollLast();
            }
            //保证queue单调,此时队尾没有比nums[i]更大的
            queue.offerLast(i);

            //如果此时队列的最大值不在当前窗口内，则移出
            while (queue.peekFirst() <= i - k) {
                queue.pollFirst();
            }
            //把当前窗口内的最大值加入ans
            ans[i - k + 1] = nums[queue.peekFirst()];
        }
        return ans;

    }
}