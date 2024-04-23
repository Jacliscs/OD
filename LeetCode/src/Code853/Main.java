package Code853;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 车队
 * @date 2024/4/23
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/car-fleet/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    //车类
    static class Car {
        //起始位置
        int pos;
        //在没有干预情况下到达终点的时间
        double arrive_time;

        //构造函数
        public Car(int pos, double arrive_time) {
            this.pos = pos;
            this.arrive_time = arrive_time;
        }
    }


    public int carFleet(int target, int[] position, int[] speed) {
        //车的数量
        int n = position.length;

        //车
        Car[] cars = new Car[n];

        //由输入保证position各不相同
        for (int i = 0; i < n; i++) {

            //计算到达终点的时间
            double arrive_time = (double) (target - position[i]) / speed[i];

            //构造车
            cars[i] = new Car(position[i], arrive_time);
        }

        //把车按起始位置排序
        Arrays.sort(cars, Comparator.comparingInt(a -> a.pos));

        //题解：有多少车队
        int ans = 0;
        int i = n - 1;

        while (i > 0) {
            //如果前一辆车比后一辆车更早到达终点，则后一辆车追不上前一辆车，前一辆车单独算一个车队
            if (cars[i].arrive_time < cars[i - 1].arrive_time) ans++;
                //前一辆车被后一辆车追上，后一辆车保持前车速度，更新到达时间
            else cars[i - 1].arrive_time = cars[i].arrive_time;
            i--;
        }

        //不管是if还是else，都没算最后一辆车的车队
        return ans + 1;
    }
}