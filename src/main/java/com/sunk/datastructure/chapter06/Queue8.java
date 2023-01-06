package com.sunk.datastructure.chapter06;

/**
 * @author sunk
 * @since 2023/1/6
 **/
public class Queue8 {

    // 表示有多少个皇后
    static int max = 8;

    // 定义 array ，保存皇后防止的位置
    static int[] array = new int[max];

    public static void main(String[] args) {
        check(0);
    }

    /*
     * 编写一个方法，放置第 n 个皇后
     */
    public static void check(int n) {
        if (n == max) {
            // n = 8 的时候表示前面的 8 个皇后已经放好了
            printArr();
            return;
        }

        /*
         * 依次在某一行放入皇后并判断是否冲突
         * - array[n]：表示在第几行放入该皇后
         * - i = [0-7]：表示在这一行的那一列放入皇后
         */
        for (int i = 0; i < max; i++) {
            // 先把当前皇后 n，放到改行的第一列
            array[n] = i;

            if (judge(n)) {
                // 不冲突的情况下，接着放下一个皇后
                check(n + 1);
            } else {
                // 一旦冲突则继续循环，将皇后放到下一列，再进行测试
                continue;
            }
        }
    }


    /*
     * 判断皇后是否冲突
     * - 当放置第 n 个皇后的时候，检查与前面 n-1 个皇后是否冲突
     * - array[i] == array[n] -> 在同一列
     * - Math.abs(n - i) == Math.abs(array[n] - array[i]) -> 在同一斜线
     */
    public static boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }

        return true;
    }


    /*
     * 将皇后摆放的位置输出，为实际在棋盘上的位置
     */
    public static void printArr() {
        for (int i : array) {
            System.out.print(i + 1 + " ");
        }

        System.out.println();
    }
}
