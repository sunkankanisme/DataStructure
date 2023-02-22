package com.sunk.datastructure.chapter12.floyd;

import java.util.Arrays;

/**
 * 使用弗洛伊德算法解决最短路径问题
 *
 * @author sunk
 * @since 2023/2/22
 */
public class FloydAlgorithm {

    public static void main(String[] args) {
        // 1 创建图
        char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        final int N = 65535;
        int[][] matrix = new int[vertexes.length][vertexes.length];
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};

        final Graph graph = new Graph(vertexes, matrix);
        graph.show();

        // 2 指定弗洛伊德算法
        graph.floyd();
        graph.show();
    }

    static class Graph {
        // 顶点数组
        char[] vertexes;
        // 保存顶点之间的距离关系
        int[][] dis;
        // 保存到达目标顶点的前驱顶点
        int[][] pre;

        /**
         * 构造函数
         *
         * @param matrix   邻接矩阵
         * @param vertexes 顶点数组
         **/
        public Graph(char[] vertexes, int[][] matrix) {
            int length = vertexes.length;
            this.vertexes = vertexes;
            this.dis = matrix;

            // 初始化 pre 数组，存放的是顶点的下标，初始情况下为自身
            this.pre = new int[length][length];
            for (int i = 0; i < pre.length; i++) {
                Arrays.fill(pre[i], i);
            }
        }

        /**
         * 打印信息
         **/
        public void show() {
            // 先输出 pre 数组
            System.out.println("================= PRE ARR =================");
            for (int i = 0; i < dis.length; i++) {
                for (int j = 0; j < dis.length; j++) {
                    System.out.printf("%s\t", vertexes[pre[i][j]]);
                }
                System.out.println();
            }

            // 再输出 dis 数组
            System.out.println("================= DIS ARR =================");
            for (int[] di : dis) {
                for (int j = 0; j < dis.length; j++) {
                    System.out.printf("%5s\t", di[j]);
                }
                System.out.println();
            }
        }

        /**
         * 弗洛伊德算法实现
         **/
        public void floyd() {
            // 保存节点间距离的变量
            int len = 0;

            /*
             * j -> [i] -> k：每一次都表示从 j 出发经过 i 中间节点到达 k 的过程，计算长度并按规则更新表
             * 1 第一层循环，对中间顶点的遍历，i 是中间顶点的下标
             * 2 第二层循环，对出发顶点进行遍历，j 是出发顶点的下标
             * 3 第三层循环，对到达顶点进行遍历，k 是到达顶点的下标
             */
            for (int i = 0; i < vertexes.length; i++) {
                for (int j = 0; j < vertexes.length; j++) {
                    for (int k = 0; k < vertexes.length; k++) {
                        // 计算使用当前中继节点时，从开始节点到达目标节点的权值
                        len = dis[j][i] + dis[i][k];

                        // 使用中继更短时，更新距离表和前驱表
                        if (len < dis[j][k]) {
                            dis[j][k] = len;
                            pre[j][k] = i;
                        }
                    }
                }
            }
        }
    }

}
