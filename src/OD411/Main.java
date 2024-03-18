package OD411;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 手机APP防沉迷
 * @date 2024/3/18
 * @level 5
 * @score 100
 */

/**
 * 输入描述
 * 第一行表示注册的App数量 N（N ≤ 100）
 * <p>
 * 第二部分包括 N 行，每行表示一条App注册数据
 * <p>
 * 最后一行输入一个时间点，程序即返回该时间点使用的App
 * <p>
 * 2
 * <p>
 * App1 1 09:00 10:00
 * <p>
 * App2 2 11:00 11:30
 * <p>
 * 09:30
 * <p>
 * 数据说明如下：
 * <p>
 * N行注册数据以空格分隔，四项数依次表示：App名称、优先级、起始时间、结束时间
 * 优先级1~5，数字越大，优先级越高
 * 时间格式 HH:MM，小时和分钟都是两位，不足两位前面补0
 * 起始时间需小于结束时间，否则注册不上
 * 注册信息中的时间段包含起始时间点，不包含结束时间点
 * 输出描述
 * 输出一个字符串，表示App名称，或NA表示空闲时间
 * <p>
 * 备注
 * 用例保证时间都介于 00:00 - 24:00 之间；
 * 用例保证数据格式都是正确的，不用考虑数据输入行数不够、注册信息不完整、字符串非法、优先级超限、时间格式不正确的问题；
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //需要注册的App个数
        int n = Integer.parseInt(sc.nextLine());
        //存放需要注册的App，使用List方便排序
        List<App> apps = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            apps.add(new App(sc.next(), Integer.parseInt(sc.next()), timeToInt(sc.next()), timeToInt(sc.next())));
        }
        //需要查询的时间点，转换成分钟数
        int requestTime = timeToInt(sc.next());
        System.out.println(query(apps, requestTime));

    }

    //查询某时间段是哪个App在使用
    public static String query(List<App> apps, int requestTime) {
        //存放已注册的App
        List<App> registeredApps = new ArrayList<>();
        //把apps先按优先级降序，如果后面的App和前面的App冲突，则后面的App不注册
        apps.sort((o1, o2) -> o2.priority - o1.priority);
        //遍历apps，不发生冲突则注册
        //跳出标签，用于跳出内层循环
        outer:
        for (App app : apps) {
            //起始时间>=结束时间，无法注册
            if (app.startTime >= app.endTime) {
                continue;
            }
            //遍历已注册的app，看是否有冲突，有冲突的话先注册的一定是优先级更高的，所以后面的App不注册
            for (App registeredApp : registeredApps) {
                if (isConflict(app, registeredApp)) {
                    //有冲突则开始下一个app与registeredApps中的app比较
                    continue outer;
                }
            }
            //没有冲突，注册
            registeredApps.add(app);
        }
        //如果该时间段没有App，默认返回"NA"
        String result = "NA";

        //在注册列表里遍历
        for (App app : registeredApps) {
            if (requestTime >= app.startTime && requestTime < app.endTime) {
                result = app.name;
                //找到就直接跳出，因为唯一性
                break;
            }
        }
        return result;
    }

    //两个App是否冲突
    public static boolean isConflict(App app1, App app2) {
        int s1 = app1.startTime;
        int e1 = app1.endTime;
        int s2 = app2.startTime;
        int e2 = app2.endTime;
        //时间段是左闭右开区间，所以需要注意边界[100,200)
        if (s1 >= s2 && s1 < e2) {
            return true;
        }
        if (s2 >= s1 && s2 < e1) {
            return true;
        }
        //其他情况则不冲突
        return false;
    }

    //转换时间 全都换成分钟数，类似在一维数组里连续占用多少格子
    public static int timeToInt(String time) {
        String[] times = time.split(":");
        return Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
    }

    //App类
    static class App {
        String name;
        int priority;
        int startTime;
        int endTime;

        //有参构造方法
        public App(String name, int priority, int startTime, int endTime) {
            this.name = name;
            this.priority = priority;
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }
}

