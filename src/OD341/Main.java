package OD341;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 模拟目录管理功能
 * @date 2024/3/30
 * @level 6
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD341
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //初始化目录树
        Tree tree = new Tree();

        //记录最后一条命令的输出 除了pwd有输出，其他都输出空
        String command_output = "";

        outer:
        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            String[] tmp = line.split(" ");

            //命令 mkdir cd pwd
            String cmd_key = tmp[0];
            //参数
            //String cmd_val =  tmp[1];//pwd没有参数，可能会越界

            if (cmd_key.equals("pwd")) {
                //pwd不需要参数，有参数的错误输入什么都不需要干
                if (tmp.length != 1) continue;
                //否则，就将当前命令的输出结果保存
                command_output = tree.pwd();
            } else if (cmd_key.equals("mkdir") || cmd_key.equals("cd")) {
                //参数只能有1个
                if (tmp.length != 2) continue;

                //目录名
                String cmd_val = tmp[1];

                //除了 cd .. 不需要检测 其他都需要检测文件名是否合法
                if (!(cmd_key.equals("cd") && cmd_val.equals(".."))) {
                    for (int i = 0; i < cmd_val.length(); i++) {
                        char c = cmd_val.charAt(i);
                        //不合法，直接跳到下一个命令
                        if (c < 'a' || c > 'z') continue outer;
                    }
                }

                //实现操作
                if (cmd_key.equals("mkdir")) {
                    tree.mkdir(cmd_val);

                    //mkdir操作没有输出，清空当前保存的最后输出
                    command_output = "";
                } else {
                    //cd
                    tree.cd(cmd_val);

                    //清空
                    command_output = "";
                }
            }
        }
        //输出最后一条命令的输入，如果是mkdir cd 则没有输出
        System.out.println(command_output);
    }

    //节点 包含父目录 子目录<>
    static class TreeNode {
        String curDicName;
        TreeNode fa;//父目录
        HashMap<String, TreeNode> ch;//子目录可能有多个

        //构造方法
        public TreeNode(String curDicName, TreeNode fa) {
            this.curDicName = curDicName;
            this.fa = fa;
            this.ch = new HashMap<>();
        }
    }

    //目录树
    static class Tree {
        //根节点
        TreeNode root;
        //当前层
        TreeNode cur;

        //默认无参构造方法
        public Tree() {
            //根节点 视为名称为/
            this.root = new TreeNode("/", null);
            this.cur = root;
        }

        //新建目录
        public void mkdir(String dicName) {
            //如果有同名子目录，则不做任务操作
            //文件名 父目录
            this.cur.ch.putIfAbsent(dicName, new TreeNode(dicName + "/", this.cur));
        }

        //进入目录
        public void cd(String dicName) {
            //cd ..
            if (dicName.equals("..")) {
                //如果上层不为空，则进入上层
                if (this.cur.fa != null) {
                    this.cur = this.cur.fa;
                }
                //如果为空，不进行任何操作
            } else {
                //cd dicName
                //有同名子目录才进入
                if (this.cur.ch.containsKey(dicName)) {
                    //进入
                    this.cur = this.cur.ch.get(dicName);
                }
                //没有同名子目录则不做任何操作
            }
        }

        //pwd
        public String pwd() {
            StringBuilder sb = new StringBuilder();

            //从当前层遍历到root，每次插入到头部，即倒序
            TreeNode cur = this.cur;
            while (cur != null) {
                sb.insert(0, cur.curDicName);
                cur = cur.fa;
            }
            return sb.toString();
        }
    }
}