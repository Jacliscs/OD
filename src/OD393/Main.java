package OD393;

import jdk.swing.interop.LightweightFrameWrapper;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * @author 浮生
 * @description 根据IP查找城市
 * @date 2024/4/4
 * @level 7
 * @score 200
 * @url https://hydro.ac/d/HWOD2023/p/OD393
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {

    public static long ip_to_dec(String ip) {
        String[] blocks = ip.split("\\.");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < blocks.length; i++) {
            //转二进制
            String binary = Integer.toBinaryString(Integer.parseInt(blocks[i]));
            //补足8位
            while (binary.length() < 8) {
                binary = "0" + binary;
            }
            //拼接
            sb.append(binary);
        }
        //转为10进制
        return Long.parseLong(sb.toString(), 2);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Range> ranges = new ArrayList<>();

        //城市ip列表
        String[] cities = sc.nextLine().split(";");
        //请求的ip列表
        String[] queryIps = sc.nextLine().split(",");

        for (String city : cities) {
            String[] tmp = city.split("[=,]");
            ranges.add(new Range(tmp[0], tmp[1], tmp[2]));
        }

        StringJoiner sj = new StringJoiner(",");

        //遍历待查询的ip
        for (String ip : queryIps) {
            //转换成整数
            long ipDec = ip_to_dec(ip);

            //记录匹配城市，就算没匹配到也要输出""
            String city = "";

            //记录匹配到的最优字段，如果有多个地址段匹配到，返回最短的
            long minLen = Long.MAX_VALUE;

            for (Range range : ranges) {
                //包含在range的地址段
                if (ipDec >= range.startIpDec && ipDec <= range.endIpDec) {
                    //如果当前匹配到的字段更短，或字段长度与最小长度一样，取city字典更大的
                    if (range.ipLen < minLen || range.ipLen == minLen && range.city.compareTo(city) > 0) {
                        //更新
                        city = range.city;
                        minLen = range.ipLen;
                    }
                }

            }
            //如果没匹配到，输出""
            sj.add(city);
        }
        System.out.println(sj);

    }

    //记录城市名、开始地址、结束地址、字段长度
    static class Range {
        String city;
        long startIpDec;
        long endIpDec;
        long ipLen;

        public Range(String city, String startIpDec, String endIpDec) {
            this.city = city;
            this.startIpDec = ip_to_dec(startIpDec);
            this.endIpDec = ip_to_dec(endIpDec);
            this.ipLen = this.endIpDec - this.startIpDec + 1;
        }


    }
}