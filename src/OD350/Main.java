package OD350;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Jacliscs
 * @description 分配土地
 * @date 2024/3/9
 * @level 3
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    //静态类，记录每个点最小最大坐标
    static class Rect {
        int minRow = Integer.MAX_VALUE;
        int maxRow = Integer.MIN_VALUE;
        int minCol = Integer.MAX_VALUE;
        int maxCol = Integer.MIN_VALUE;
        /*
         * 更新该点最大最小横坐标
         * @create 2024/3/9 23:11
         * @param row
         * @return void
         */
        private void setRow(int row){
            this.minRow = Math.min(this.minRow,row);
            this.maxRow = Math.max(this.maxRow,row);
        }

        /*
         * 更新该点最大最小纵坐标
         * @create 2024/3/9 23:12
         * @param col
         * @return void
         */
        private void setCol(int col){
            this.minCol = Math.min(this.minCol,col);
            this.maxCol = Math.max(this.maxCol,col);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            //几行
            int m = sc.nextInt();
            //几列
            int n = sc.nextInt();
            HashMap<Integer,Rect> map = new HashMap<>();
            for (int i =0;i<m;i++){
                for (int j = 0;j<n;j++){
                    int num = sc.nextInt();
                    if (num>0){
                        //若没有该num则新建一个Rect对象，修改当前数字所表示的最大最小坐标
                        map.putIfAbsent(num,new Rect());
                        map.get(num).setRow(i);
                        map.get(num).setCol(j);
                    }
                }
            }
            int maxArea = 0;
            //遍历
            for (int num : map.keySet()){
                Rect rect = map.get(num);
                maxArea = Math.max(maxArea,(rect.maxRow-rect.minRow+1)*(rect.maxCol-rect.minCol+1));
            }
            System.out.println(maxArea);
        }

    }
}