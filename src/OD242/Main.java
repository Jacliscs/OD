package OD242;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long[] arr = Arrays.stream(String.valueOf(n).split("")).mapToLong(Long::parseLong).toArray();

        System.out.println(getResult(arr));
    }

    public static long getResult(long[] arr) {
        int correct = 0;

        for (int i = 0; i < arr.length; i++) {
            long fault = arr[i];
            if (fault > 4) fault--;

            //到达该位置需要经历j次 9进制
            for (int j = arr.length - i - 1; j > 0; j--) {
                fault *= 9;
            }

            correct += fault;
        }

        return correct;
    }
}