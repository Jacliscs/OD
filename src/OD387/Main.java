package OD387;

import java.util.Scanner;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Jacliscs
 * @description 数据单元的变化替换
 * @date 2024/3/19
 * @level 6
 * @score 100
 */

/**
 * 题目描述
 * 将一个 csv 格式的数据文件中包含有单元格引用的内容替换为对应单元格内容的实际值。
 * <p>
 * comma separated values(CSV) 逗号分隔值，csv 格式的数据文件使用逗号 "," 作为分隔符将各单元的内容进行分隔。
 * <p>
 * 输入描述
 * 输入只有一行数据，用逗号分隔每个单元格，行尾没有逗号。最多26个单元格，对应编号A~Z。
 * <p>
 * 每个单元格的内容包含字母和数字，以及使用 '<>' 分隔的单元格引用，例如：<A>表示引用第一个单元的值。
 * <p>
 * 每个单元格的内容，在替换前和替换后均不超过100个字符。
 * <p>
 * 引用单元格的位置不受限制，允许排在后面的单元格被排在前面的单元格引用。
 * <p>
 * 不存在循环引用的情况，比如下面这种场景是不存在的：
 * <p>
 * A单元恪：aCd<B>8U
 * <p>
 * B单元格：KAy<A>uZq0
 * <p>
 * 不存在多重 '<>' 的情况，一个单元只能引用一个其他单元格。比如下面这种场景是不存在的：
 * <p>
 * A单元格：aCdOu
 * <p>
 * B单元格：kAydzco
 * <p>
 * C单元格：y<<A><B>>d
 * <p>
 * 输出描述
 * 输出替换后的结果
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    static String[] arr;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        arr = sc.nextLine().split(",");
        System.out.println(getResult());
    }

    //
    public static String getResult() {
        //最多26个单元格
        if (arr.length > 26) {
            return "-1";
        }

        //存放结果
        StringJoiner sj = new StringJoiner(",");
        for (int i = 0; i < arr.length; i++) {
            //替换失败
            if (!change(i)) {
                return "-1";
            }
            //每个单元格内容不超过100字符
            if (arr[i].length() > 100) {
                return "-1";
            }
            //每个单元格只应包含字母和数字
            if (!arr[i].matches("^[a-zA-Z0-9]*$")) {
                return "-1";
            }
            //上面没有返回，则替换成功
            sj.add(arr[i]);
        }
        //把所有格子都替换完了
        return sj.toString();
    }


    //需要递归替换 因为没有返回元素，所以需要全局变量arr
    public static boolean change(int index) {
        //正则匹配 <A> <B> 使用非贪婪模式，在while中判断是否正确，否则没匹配到也会默认正确，忽略了23<AB1>2这种非法格式
        //Pattern p = Pattern.compile("<[A-Z]>");
        Pattern p = Pattern.compile("<.*?>");
        Matcher m = p.matcher(arr[index]);
        //如果找到了，则说明该位置需要替换
        while (m.find()) {
            //记录引用字符串 如<A>
            String reference = m.group(0);
            //引用的长度只能是3
            if (reference.length() != 3) {
                return false;
            }

            //引用单元格的编号
            char reference_name = reference.charAt(1);
            //当前单元格编号 A->0  B->1
            char current_name = (char) (index + 'A');
            //当前单元格编号合法，且不能自己引用自己
            if (reference_name == current_name || reference_name < 'A' || reference_name > 'Z') {
                return false;
            }
            //引用单元格的索引 A->0  B->1
            int reference_index = reference_name - 'A';
            //引用索引长过数组
            if (reference_index >= arr.length) {
                return false;
            }

            //如果引用的那个单元格也需要引用，且递归失败
            if (!change(reference_index)) {
                return false;
            }
            //经过上一步，引用的那个单元格已经成功递归，不存在<>了，现在直接替换
            arr[index] = arr[index].replace(reference, arr[reference_index]);
            //刷新 正则匹配不到则跳出循环
            m = p.matcher(arr[index]);
        }
        return true;
    }

}