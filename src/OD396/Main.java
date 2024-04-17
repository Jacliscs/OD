package OD396;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * @author 浮生
 * @description 文件缓存系统
 * @date 2024/4/4
 * @level 6
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD396
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    //共用时间
    static int time = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //缓存总大小
        int maxSize = Integer.parseInt(sc.nextLine());
        //初始化
        MyFileSystem myFileSystem = new MyFileSystem(maxSize);

        //命令的条数
        int n = Integer.parseInt(sc.nextLine());
        //命令列表
        for (int i = 0; i < n; i++) {
            String[] cmd = sc.nextLine().split(" ");

            //如果是get操作
            if (cmd.length == 2) {
                myFileSystem.get(cmd[1]);
            }

            //如果是put操作
            if (cmd.length == 3) {
                //如果单文件大于最大缓存，则输出NONE
                if (Integer.parseInt(cmd[2]) > maxSize) {
                    System.out.println("NONE");
                    return;
                }
                myFileSystem.put(cmd[1], Integer.parseInt(cmd[2]));
            }
        }

        //如果缓存中没有文件，则输出NONE
        if (myFileSystem.files.isEmpty()) {
            System.out.println("NONE");
            return;
        }

        //把缓存中文件名字升序排序
        StringJoiner sj = new StringJoiner(",");

        myFileSystem.files.stream().sorted((Comparator.comparing(o -> o.name))).forEach(file -> sj.add(file.name));

        //输出
        System.out.println(sj);

    }

    //文件类
    static class MyFile {
        //文件名
        String name;
        //大小
        int size;
        //最近访问时间
        int lastTime;
        //访问次数
        int count;

        //新建文件初始化访问时间为当前time，并将time+1防止重复
        public MyFile(String name, int size) {
            this.name = name;
            this.size = size;
            this.lastTime = time++;
            this.count = 1;
        }
    }

    //文件操作类
    static class MyFileSystem {
        //总缓存大小
        int maxSize;
        //已使用缓存大小
        int usedSize;
        //已缓存的文件列表 优先队列，访问次数最少的优先级最高，访问次数一样的最近访问时间最小的优先级更高
        PriorityQueue<MyFile> files = new PriorityQueue<>(((o1, o2) -> {
            if (o1.count != o2.count) {
                return o1.count - o2.count;
            } else {
                return o1.lastTime - o2.lastTime;
            }
        }));

        //初始化
        public MyFileSystem(int maxSize) {
            this.maxSize = maxSize;
        }

        //put操作
        public void put(String name, int size) {
            //如果已经缓存有同名文件，则不做任何操作
            for (MyFile file : files) {
                if (file.name.equals(name)) {
                    return;
                }
            }

            //如果内存不够，则从优先队列队首依次移出
            while (maxSize - usedSize < size) {
                MyFile discard_file = files.poll();
                //更新已使用大小
                if (discard_file != null) {
                    usedSize -= discard_file.size;
                }
            }

            //内容足够放进缓存了
            MyFile newFile = new MyFile(name, size);

            //添加进优先队列
            files.add(newFile);
            //更新已使用的缓存大小
            usedSize += size;
        }

        //get操作 使文件访问次数+1 且更新最近访问时间
        public void get(String name) {
            //如果不存在该文件，则不做任何操作
            for (MyFile file : files) {
                if (file.name.equals(name)) {
                    //更新最近访问时间和访问次数
                    file.lastTime = time++;
                    file.count++;

                    //重新插入优先队列排序
                    files.remove(file);
                    files.add(file);
                    break;
                }
            }
        }
    }
}