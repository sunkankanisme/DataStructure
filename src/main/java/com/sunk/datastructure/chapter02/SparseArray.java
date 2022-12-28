package com.sunk.datastructure.chapter02;

/*
 * 稀疏数组案例
 */
public class SparseArray {

    public static void main(String[] args) {
        // 创建原始的二维数组 11*11
        // 0：表示没有棋子，1：表示黑子，2：表示蓝子
        int[][] chessArr = new int[11][11];
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;

        // 输出
        System.out.println("=============================");
        for (int[] row : chessArr) {
            for (int item : row) {
                System.out.printf("%d\t", item);
            }

            System.out.println();
        }

        // 将数组转换为稀疏数组
        // 1 先遍历原始数组，得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                if (chessArr[i][j] != 0) {
                    sum++;
                }
            }
        }

        // 2 创建对应的稀疏数组
        System.out.println("=============================");
        final int[][] sparseArr = new int[sum + 1][3];
        // 给稀疏数组赋值
        sparseArr[0][0] = chessArr.length;
        sparseArr[0][1] = chessArr[0].length;
        sparseArr[0][2] = sum;

        // 遍历二维数组，将非0的值存放到稀疏数组中
        int index = 1;
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                if (chessArr[i][j] != 0) {
                    sparseArr[index][0] = i;
                    sparseArr[index][1] = j;
                    sparseArr[index][2] = chessArr[i][j];
                    index++;
                }
            }
        }

        // 打印稀疏数组
        for (int[] ints : sparseArr) {
            for (int anInt : ints) {
                System.out.printf("%d\t", anInt);
            }

            System.out.println();
        }

        // 3 从稀疏数组恢复原数组
        System.out.println("=============================");
        // 先读取稀疏数组第一行，构建出原始的数组
        final int rowSize = sparseArr[0][0];
        final int colSize = sparseArr[0][1];
        int[][] chessArray2 = new int[rowSize][colSize];
        // 读取数据，并赋值给原始数组
        for (int i = 1; i < sparseArr.length; i++) {
            final int[] ints = sparseArr[i];
            chessArray2[ints[0]][ints[1]] = ints[2];
        }

        // 打印恢复的数组
        for (int[] ints : chessArray2) {
            for (int anInt : ints) {
                System.out.printf("%d\t", anInt);
            }

            System.out.println();
        }

    }
}
