package OD216;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 连续出牌数量
 * @date 2024/3/24
 * @level 7
 * @score 200
 */

/**
 * 题目描述
 * 有这么一款单人卡牌游戏，牌面由颜色和数字组成，颜色为红、黄、蓝、绿中的一种，数字为0-9中的一个。游戏开始时玩家从手牌中选取一张卡牌打出，接下来如果玩家手中有和他上一次打出的手牌颜色或者数字相同的手牌，他可以继续将该手牌打出，直至手牌打光或者没有符合条件可以继续打出的手牌。
 * <p>
 * 现给定一副手牌，请找到最优的出牌策略，使打出的手牌最多。
 * <p>
 * 输入描述
 * 输入为两行
 * <p>
 * 第一行是每张手牌的数字，数字由空格分隔，
 * 第二行为对应的每张手牌的颜色，用r y b g这4个字母分别代表4种颜色，字母也由空格分隔。
 * 手牌数量不超过10。
 * <p>
 * 输出描述
 * 输出一个数字，即最多能打出的手牌的数量。
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    //新建一个牌类，记录颜色和牌面
    static class Card {
        int num;
        char color;

        public Card(int num, String color) {
            this.num = num;
            this.color = color.charAt(0);
        }

    }

    //最大出牌数
    static int res = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //牌的数字
        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //牌的颜色 r y b g
        String[] colors = sc.nextLine().split(" ");
        System.out.println(getResult(nums, colors));
    }

    public static int getResult(int[] nums, String[] colors) {
        int n = nums.length;
        //新建卡牌数组 把对应的牌面和颜色填进去
        Card[] cards = new Card[n];
        for (int i = 0; i < n; i++) {
            cards[i] = new Card(nums[i], colors[i]);
        }
        boolean[] isUsed = new boolean[n];
        dfs(cards, null, isUsed, 0);
        return res;
    }

    //回溯算法 求最大出牌数 数字相同或颜色相同都可以出
    public static void dfs(Card[] cards, Card lastCard, boolean[] isUsed, int count) {
        res = Math.max(count, res);
        for (int i = 0; i < cards.length; i++) {
            if (isUsed[i]) continue;
            //当前卡牌
            Card curCard = cards[i];
            //如果上一个不为空，且牌面和数字都不同，则跳过
            if (lastCard != null && lastCard.num != curCard.num && lastCard.color != curCard.color) continue;
            //否则，该牌可以打出
            isUsed[i] = true;
            //递归
            dfs(cards, curCard, isUsed, count + 1);
            //恢复状态
            isUsed[i] = false;
        }
    }


}