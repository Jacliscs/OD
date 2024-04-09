package OD442;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Jacliscs
 * @description 模拟数据序列化传输
 * @date 2024/3/19
 * @level 8
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD442
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    //编码时：类型 -> 数值
    static HashMap<String, String> typeToNum = new HashMap<String, String>();
    //解码时：数值 -> 类型
    static HashMap<String, String> numToType = new HashMap<String, String>();

    //校验字段
    static Pattern num_RegExp = Pattern.compile("^\\d+$");//以数字开头和结尾的一个或多个数字
    static Pattern str_RegExp = Pattern.compile("^[0-9a-zA-Z\\s]+$");//以数字、字母、空格开头和结尾的一个或多个字符


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //1表示编码 2表示解码
        int command = Integer.parseInt(sc.nextLine());
        //需要编码、解码的字符串
        String str = sc.nextLine();

        //编码时，类型对应的值
        typeToNum.put("Integer", "0");
        typeToNum.put("String", "1");
        typeToNum.put("Compose", "2");

        //解码时，值对应的类型
        numToType.put("0", "Integer");
        numToType.put("1", "String");
        numToType.put("2", "Compose");

        //根据command决定是编码还是解码
        switch (command) {
            case 1:
                try {
                    System.out.println(encode(str));
                } catch (Exception e) {
                    //1
                    //[[1,String,I am Mary]]
                    System.out.println("ENCODE_ERROR");
                }
                break;
            case 2:
                try {
                    System.out.println(decode(str));
                } catch (Exception e) {
                    System.out.println("DECODE_ERROR");
                }
                break;
        }
    }

    /**
     * 整体检验待编码的字符串是否合法
     *
     * @param str
     * @return boolean
     * @create 2024/4/3 19:12
     */
    public static boolean check_encode_str(String str) {
        //检查字符串[]的闭合性是否合法
        LinkedList<Character> stack = new LinkedList<Character>();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == ']') {
                //如果为空，没有对应的[则非法
                if (stack.isEmpty()) {
                    return false;
                }
                //否则，移除最后一个[
                stack.removeLast();
            } else if (c == '[') {
                stack.addLast(c);
            }
        }
        //最后合法的话stack中的[]应该两两抵消
        return stack.isEmpty();
    }

    /**
     * 校验已编码的字符串是否合法
     *
     * @param pos  位置
     * @param type 类型
     * @param data 数据
     * @return boolean
     * @create 2024/4/3 18:22
     */
    public static boolean check_encoded(String pos, String type, String data) {
        //1#1#9#I am Mary
        //如果位置不是以一个或多个数字开头结尾的
        if (!num_RegExp.matcher(pos).find()) {
            return false;
        }

        //如果类型不存在
        if (!typeToNum.containsKey(type)) {
            return false;
        }

        //根据类型字段检验数据字段
        if (type.equals("Integer")) {
            return num_RegExp.matcher(data).find();
        } else if (type.equals("String")) {
            return str_RegExp.matcher(data).find();
        }

        return true;
    }

    /**
     * 字符串编码
     *
     * @param str
     * @return java.lang.String
     * @create 2024/4/3 19:12
     */
    public static String encode(String str) {
        //去除"],[" 变为 "]["
        str = str.replaceAll("],\\[", "][");

        //编码字符串格式校验，整体括号不合法，则返回ENCODE_ERROR
        if (!check_encode_str(str)) {
            return "ENCODE_ERROR";
        }

        //替换待编码字符串中的[]为<> 便于后续使用正则表达式的[]
        str = str.replaceAll("\\[", "<")
                .replaceAll("]", ">");

        //匹配非嵌套类型 即<>中含有一个或多个非<>的字符
        Pattern p = Pattern.compile("<([^<>]+)>");

        while (true) {
            Matcher m = p.matcher(str);

            //会先将嵌套的最内层编码，逐层向外，如果最后匹配不到<>了，则全部编码完成
            if (!m.find()) break;

            //m.group(0) 是匹配到的完整子串 "<位置,类型,值>"
            //m.group(1) 是用于获取第一个正则捕获组 即 "位置,类型,值"
            String[] temp = m.group(1).split(",");

            String pos = temp[0];
            String type = temp[1];
            String data = temp[2];

            //记录编码内容
            String encodeStr = "";

            //如果编码的格式合法，则拼接
            if (check_encoded(pos, type, data)) {
                encodeStr = pos + "#" + typeToNum.get(type) + "#" + data.length() + "#" + data;
            }

            //将匹配到的内容，换为编码后的内容
            str = str.replace(m.group(0), encodeStr);
        }
        return str;
    }

    /**
     * 检测待解码字符串是否合法
     *
     * @param pos  位置
     * @param type 类型
     * @param data 数据
     * @return boolean
     * @create 2024/4/3 19:12
     */
    public static boolean check_decoded(String pos, String type, String data) {
        if (!num_RegExp.matcher(pos).find()) {
            return false;
        }

        if (!numToType.containsKey(type)) {
            return false;
        }

        if (type.equals("0")) {
            return num_RegExp.matcher(data).find();
        } else if (type.equals("1")) {
            return str_RegExp.matcher(data).find();
        }
        return true;
    }

    /**
     * 2解码
     *
     * @param str
     * @return java.lang.String
     * @create 2024/4/3 19:24
     */
    public static String decode(String str) {
        //2
        //1#1#9#I am Mary2#0#2#233#0#3#879

        //各数据区没有分隔符、含有嵌套

        LinkedList<String> queue = new LinkedList<String>();
        //把去除#之后的放进queue
        //1 1 9 I am Mary2 0 2 233 0 3 879
        Collections.addAll(queue, str.split("#"));

        //记录解码后的内容
        StringJoiner res = new StringJoiner(",");

        //如果待解码的队列未空，则循环
        while (!queue.isEmpty()) {
            //默认解码字符串合法，则按顺序是pos type len data
            //非法处理在main方法中
            String pos = queue.removeFirst();//1
            String type = queue.removeFirst();//1
            int len = Integer.parseInt(queue.removeFirst());//9

            //剩余部分重新以#链接
            //I am Mary2#0#2#233#0#3#879
            String remain = String.join("#", queue);
            queue.clear();

            //remain部分的[0,len)部分为上一个数据区的data
            String data = remain.substring(0, len);

            //分割出一个数据区后，剩下的重新按#分隔后入队
            if (remain.length() > len) {
                Collections.addAll(queue, remain.substring(len).split("#"));
            }

            //如果有嵌套的，则把嵌套的data解码
            //4#2#25#1#1#10#I am Kitty data:1#10#I am Kitty
            if (type.equals("2")) {
                data = decode(data);
            }

            //如果位置、类型、数据都合法，则加入结果
            if (check_decoded(pos, type, data)) {
                res.add("[" + pos + "," + numToType.get(type) + "," + data + "]");
            }
        }
        return res.toString();
    }


}