package OD400;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * @author Jacliscs
 * @description 多段线数据压缩
 * @date 2024/3/18
 * @level 6
 * @score 100
 */

/**
 * 下图中，每个方块代表一个像素，每个像素用其行号和列号表示。
 * 为简化处理，多线段的走向只能是水平、竖直、斜向45度。
 * <p>
 * 上图中的多线段可以用下面的坐标串表示：(2,8),(3,7),(3,6),(3,5),(4,4),(5,3),(6,2),(7,3),(8,4),(7,5)。
 * <p>
 * 但可以发现，这种表示不是最简的，其实只需要存储6个蓝色的关键点即可，它们是线段的起点、拐点、终点，而剩下4个点是冗余的。
 * <p>
 * 现在，请根据输入的包含有冗余数据的多线段坐标列表，输出其最简化的结果。
 * <p>
 * 输入描述
 * 2 8 3 7 3 6 3 5 4 4 5 3 6 2 7 3 8 4 7 5
 * <p>
 * 所有数字以空格分隔，每两个数字一组，第一个数字是行号，第二个数字是列号；
 * 行号和列号范围 为 [0, 64)，用例输入保证不会越界，考生不必检查；
 * 输入数据至少包含两个坐标点
 * 输出描述
 * 2 8 3 7 3 5 6 2 8 4 7 5
 * <p>
 * 压缩后的最简化坐标列表，和输入数据的格式相同。
 * <p>
 * 备注
 * 输出的坐标相对顺序不能变化
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String path = sc.nextLine();
        System.out.println(getSimplePath(path));
    }

    //获得最简路径：起点、拐点、终点
    public static String getSimplePath(String path) {
        int[] nums = Arrays.stream(path.split(" ")).mapToInt(Integer::parseInt).toArray();
        //存放结果
        StringJoiner sj = new StringJoiner(" ");
        //起点x坐标
        int preX = nums[0];
        //起点y坐标
        int preY = nums[1];
        //上一次运动方向，默认为0
        double preDirectionX = 0;
        double preDirectionY = 0;
        //从第二个点开始遍历
        for (int i = 2; i < nums.length; i += 2) {
            //当前点坐标
            int curX = nums[i];
            int curY = nums[i + 1];

            //坐标偏移量
            int offsetX = curX - preX;
            int offsetY = curY - preY;

            //化简基
            int base = Math.max(Math.abs(offsetX), Math.abs(offsetY));
            //本次运动方向
            double curDirectionX = offsetX / (double) base;
            double curDirectionY = offsetY / (double) base;

            //如果与上一次方向不同，则[上一个点]是拐点，需要添加进结果，起点一定会被添加
            if (curDirectionX != preDirectionX || curDirectionY != preDirectionY) {
                //preX与preY是int类型，转换为String类型
                //sj.add(String.valueOf(preX)).add(String.valueOf(preY));
                sj.add(preX + " " + preY);
            }
            //更新
            preX = curX;
            preY = curY;
            preDirectionX = curDirectionX;
            preDirectionY = curDirectionY;
        }
        //遍历结束后，还差最后一个点没添加
        sj.add(preX + " " + preY);
        return sj.toString();
    }
}