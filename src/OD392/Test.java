package OD392;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Test {
    // 分数
    static class Fractions {
        int fa; // 分母
        int ch; // 分子

        public Fractions() {
        }

        public Fractions(int fa, int ch) {
            this.fa = fa;
            this.ch = ch;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(getResult(sc.nextLine()));
    }

    // 操作数栈
    static LinkedList<Fractions> oper_num = new LinkedList<>();
    // 操作符栈
    static LinkedList<Character> oper_sign = new LinkedList<>();

    public static String getResult(String s) {
        // +,-,*,/ 运算符优先级
        HashMap<Character, Integer> priority = new HashMap<>();
        priority.put('+', 1);
        priority.put('-', 1);
        priority.put('*', 2);
        priority.put('/', 2);

        // 操作数的字符缓存容器
        StringBuilder numStr = new StringBuilder();

        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);

            // 遇到数字字符
            if (c >= '0' && c <= '9') {
                // 则将该数字所在操作数的剩余数字字符一次性探索完
                while (c >= '0' && c <= '9') {
                    numStr.append(c);
                    if (i + 1 >= s.length()) break;
                    i++;
                    c = s.charAt(i);
                }
                // 探索完后，将操作数缓存容器中记录的字符，变为分数后，压入操作数栈
                oper_num.addLast(new Fractions(1, Integer.parseInt(numStr.toString())));
                // 注意清空操作数缓存容器
                numStr = new StringBuilder();
            }

            // 遇到运算符
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                // 只要栈顶运算符的优先级 >= 当前运算符，就需要不停出栈运算
                while (oper_sign.size() > 0
                        && oper_sign.getLast() != '('
                        && priority.get(c) <= priority.get(oper_sign.getLast())) {
                    calc();
                }
                oper_sign.addLast(c);
            } else if (c == ')') {
                // 遇到')', 需要将操作符栈中靠近栈顶的'('后面的运算都出栈做了
                while (oper_sign.getLast() != '(') {
                    calc();
                }
                // 最后将')'对应的'('出栈
                oper_sign.removeLast();
            } else if (c == '(') {
                // 遇到'('，则直接压倒操作符栈
                oper_sign.add(c);
            }

            i++;
        }

        // oper_num栈中还有2个以上的数，则还需要进行运算
        while (oper_num.size() > 1) {
            calc();
        }

        // oper_num栈中只剩一个数时，该数就是表达式结果
        Fractions result = oper_num.removeLast();

        // 如果结果的分母为0（除数为0），则不合法
        if (result.fa == 0) {
            return "ERROR";
        }

        // 求分子、分母的最大公约数，并进行约份，求得最简格式的分子，分母
        int k = getMaxCommonDivisor(result.fa, result.ch);
        result.fa /= k;
        result.ch /= k;

        // 求计算结果的符号，这里用乘法是为了避免 分母小，分子大，除法结果为0的情况，这样会丢失符号信息
//        String sign = result.fa * result.ch < 0 ? "-" : "";
        String sign = (result.fa > 0 && result.ch < 0) || (result.fa < 0 && result.ch > 0) ? "-" : "";

        int fa = Math.abs(result.fa);
        int ch = Math.abs(result.ch);

        if (fa == 1) {
            // 如果分母为1，则直接输出分子
            return sign + ch;
        } else {
            // 如果分母不为1，则输出 分子 / 分母
            return sign + ch + "/" + fa;
        }
    }

    // 取出oper_num栈顶两个操作数进行运算
    public static void calc() {
        // 操作数顺序会对运算产生影响
        Fractions b = oper_num.removeLast(); // 栈顶元素是运算符右边的操作数
        Fractions a = oper_num.removeLast(); // 栈顶倒数第二个元素是运算符左边的操作数

        // 运算符
        char op = oper_sign.removeLast();

        // 记录运算结果
        Fractions result = new Fractions();

        switch (op) {
            case '+':
                result.fa = a.fa * b.fa;
                result.ch = a.ch * b.fa + b.ch * a.fa;
                break;
            case '-':
                result.fa = a.fa * b.fa;
                result.ch = a.ch * b.fa - b.ch * a.fa;
                break;
            case '*':
                result.fa = a.fa * b.fa;
                result.ch = a.ch * b.ch;
                break;
            case '/':
                result.fa = a.fa * b.ch;
                result.ch = a.ch * b.fa;
                break;
        }

        oper_num.add(result);
    }

    // 辗转相除法，求两个数的最大公约数
    public static int getMaxCommonDivisor(int x, int y) {
        while (y != 0) {
            int tmp = y;
            y = x % y;
            x = tmp;
        }
        return x;
    }
}