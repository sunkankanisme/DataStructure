package com.sunk.datastructure.chapter12;

/**
 * 背包问题
 *
 * @author sunk
 * @since 2023/2/15
 */
public class BackpackProblem {

    public static void main(String[] args) {
        // 物品的重量
        int[] w = {1, 4, 3};
        // 物品的价值
        int[] val = {1500, 3000, 2000};
        // 背包的容量
        int m = 4;
        // 物品的个数
        int n = val.length;

        // 二位数组，动态规划表
        // v[i][j] 表示在前 i 个物品中能够装入容量为 j 的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];

        // 为了记录商品存放的类型，定义一个二维数组
        String[][] path = new String[n + 1][m + 1];

        // 初始化第一行和第一列，这里可以不写具体逻辑，因为默认是 0

        // 打印当前动态表的情况
        // 0	0	0	0	0
        // 0	0	0	0	0
        // 0	0	0	0	0
        // 0	0	0	0	0
        printArr(v);

        // 根据前面得到的公式动态处理
        // 跳过第一行，从 i=1 行开始处理
        for (int i = 1; i < v.length; i++) {
            // 跳过第一列，从 j=1 列开始处理
            for (int j = 1; j < v[i].length; j++) {
                // 套用公式，数组下标从 0 开始，所以从重量 w 和价值 val 数组中取元素需要 -1
                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    // 为了记录路径，此处修改代码为 if...else... 形式
                    // v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);

                    final int v1 = v[i - 1][j];
                    final int v2 = val[i - 1] + v[i - 1][j - w[i - 1]];

                    // 根据情况，更新动态规划表和路径表
                    if (v1 > v2) {
                        v[i][j] = v1;
                        path[i][j] = v1 + "";
                    } else {
                        v[i][j] = v2;
                        path[i][j] = val[i - 1] + "," + v[i - 1][j - w[i - 1]];
                    }
                }
            }
        }

        // 打印当前动态表的情况
        // 0	0	    0   	0   	0
        // 0	1500	1500	1500	1500
        // 0	1500	1500	1500	3000
        // 0	1500	1500	2000	3500
        printArr(v);

        // 打印路径表
        // null	null	null	null	null
        // null	1500,0	1500,0	1500,0	1500,0
        // null	null	null	null	3000,0
        // null	null	null	2000,0	2000,1500
        printArr(path);

    }

    public static void printArr(int[][] arr) {
        for (int[] ints : arr) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }

            System.out.println();
        }
    }

    public static void printArr(String[][] arr) {
        for (String[] strings : arr) {
            for (String string : strings) {
                System.out.print(string + "\t");
            }

            System.out.println();
        }
    }

}
