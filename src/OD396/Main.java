package OD396;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author Jacliscs
 * @description 文件缓存系统
 * @date 2024/3/26
 * @level 6
 * @score 200
 */

/**
 * https://hydro.ac/d/HWOD2023/p/OD396
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    //文件访问次数
    private static final TreeMap<String, Integer> file_times = new TreeMap<>();
    //文件大小
    private static final LinkedHashMap<String, Integer> file_sizes = new LinkedHashMap<>();
    private static int m;
    private static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //缓存最大值 整数，取值范围为 0 < m ≤ 52428800
        m = Integer.parseInt(sc.nextLine());
        //文件操作系列个数 [0,300000]
        n = Integer.parseInt(sc.nextLine());
        //文件操作 op file_name file_size
        String[][] ops = new String[n][3];
        for (int i = 0; i < n; i++) {
            ops[i] = sc.nextLine().split(" ");
        }

    }

    public static String getResult(String[][] ops) {
        //最近访问
        LinkedList<String> last_file = new LinkedList<>();

        //新put的，则次数初始化1
        for (String[] file : ops) {
            String op = file[0];
            String file_name = file[1];
            int file_size = Integer.parseInt(file[2]);

            switch (op) {
                case "put":
                    //先看是否已存在文件 不存在则添加，存在则什么也不做
                    if (!file_times.containsKey(file_name)) {
                        //检查是否内存足够
                        int sum = 0;
                        for (int val : file_sizes.values()) {
                            sum += val;
                        }
                        //足够放下file的大小
                        if (file_size <= (m - sum)) {
                            //初始化访问次数1 按序添加
                            file_times.put(file_name, 1);
                            //添加内存
                            file_sizes.put(file_name, file_size);
                            //添加到访问时间的头部 表示最新 尾部表示最晚
                            //先删掉原来file的访问顺序，添加到头部
                            if (last_file.contains(file_name)) {
                                last_file.remove(file_name);
                                last_file.addFirst(file_name);
                            } else {
                                //如果之前访问顺序没有，则添加到头部
                                last_file.addFirst(file_name);
                            }
                        } else {
                            //内存不够，先删访问次数最少的，访问次数一样就删最远访问的
                            while (m - sum < file_size) {


                            }

                        }


                    } else {
                        //如果不存在，则什么也不做

                    }
                    break;
                case "get":
                    //如果存在的话

            }
        }
        return null;
    }

}