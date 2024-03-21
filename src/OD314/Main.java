package OD314;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Jacliscs
 * @description 解密犯罪时间
 * @date 2024/3/21
 * @level 5
 * @score 100
 */

/**
 * 题目描述
 * 警察在侦破一个案件时，得到了线人给出的可能犯罪时间，形如 “HH:MM” 表示的时刻。
 * <p>
 * 根据警察和线人的约定，为了隐蔽，该时间是修改过的，
 * <p>
 * 解密规则为：利用当前出现过的数字，构造下一个距离当前时间最近的时刻，则该时间为可能的犯罪时间。
 * <p>
 * 每个出现数字都可以被无限次使用。
 * <p>
 * 输入描述
 * 形如HH:SS字符串，表示原始输入。
 * <p>
 * 输出描述
 * 形如HH:SS的字符串，表示推理处理的犯罪时间。
 * <p>
 * 备注
 * 1.可以保证现任给定的字符串一定是合法的。
 * <p>
 * 例如，“01:35”和“11:08”是合法的，“1:35”和“11:8”是不合法的。
 * <p>
 * 2.最近的时刻可能在第二天。
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] times = sc.nextLine().split(":");
        String hour = times[0];
        String minute = times[1];
        System.out.println(getResult(hour, minute));

    }

    //返回下一个距离最近的时间
    public static String getResult(String hour, String minute) {
        //存放时间字符，去重
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < hour.length(); i++) {
            set.add(hour.charAt(i));
        }
        for (int i = 0; i < minute.length(); i++) {
            set.add(minute.charAt(i));
        }
        //把字符转为char[]
        Character[] arr = set.toArray(new Character[0]);
        //存放满足时间格式的结果
        ArrayList<String> res = new ArrayList<>();
        //存放当前排列结果
        LinkedList<Character> path = new LinkedList<>();

        dfs(arr, path, res);

        //把满足时间格式的结果升序排列
        res.sort(String::compareTo);
        //找到当前时间 在res里面的下标
        int index = res.indexOf(hour + minute);
        String recentTime = "";
        //如果当前的时间是当前组合的最后一个，则下一个时间是第二天的开头
        if (index == res.size() - 1) {
            recentTime = res.get(0);
        } else {
            recentTime = res.get(index + 1);
        }
        //插入 ：
        recentTime = recentTime.substring(0, 2) + ":" + recentTime.substring(2);
        return recentTime;
    }

    //递归，求当前时间的全排列，每个数字可以无限次使用

    /**
     * 返回当前时间的全排列
     *
     * @param arr  组成当前时间的字符列表
     * @param path 当前的排列
     * @param res  结构的排列
     * @return void
     * @create 2024/3/21 19:24
     */
    public static void dfs(Character[] arr, LinkedList<Character> path, ArrayList<String> res) {
        //返回上一层标志 path.length=4
        if (path.size() == 4) {
            StringBuilder sb = new StringBuilder();
            for (Character c : path) {
                sb.append(c);
            }
            String timeStr = sb.toString();
            //判断该排列是否是合法时间
            Pattern p = Pattern.compile("([01][0-9]|2[0-3])([0-5][0-9])");
            Matcher m = p.matcher(timeStr);
            if (m.find()) {
                res.add(timeStr);
            }
            //返回上一层
            return;
        }

        //全排列
        for (Character c : arr) {
            //第i个字符开头，后面全排列，字符可以重复使用
            path.add(c);
            dfs(arr, path, res);
            //因为数字可以无限次使用，所有开始下一个字符的时候，需要清除上一个
            path.removeLast();
        }


    }

}