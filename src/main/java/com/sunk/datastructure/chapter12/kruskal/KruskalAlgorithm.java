package com.sunk.datastructure.chapter12.kruskal;

import java.util.Arrays;

/**
 * 使用克鲁斯卡尔算法创建最小生成树，解决修公交车站的问题
 *
 * @author sunk
 * @since 2023/2/20
 */
public class KruskalAlgorithm {

    public static void main(String[] args) {
        /*
         * 1 构建图
         */
        char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {0, 12, INF, INF, INF, 16, 14},
                /*B*/ {12, 0, 10, INF, INF, 7, INF},
                /*C*/ {INF, 10, 0, 3, 5, 6, INF},
                /*D*/ {INF, INF, 3, 0, 4, INF, INF},
                /*E*/ {INF, INF, 5, 4, 0, 2, 8},
                /*F*/ {16, 7, 6, INF, 2, 0, 9},
                /*G*/ {14, INF, INF, INF, 8, 9, 0}
        };

        final KruskalAlgorithm kru = new KruskalAlgorithm(vertexes, matrix);

        //          0	        12	2147483647	2147483647	2147483647	        16	        14
        //         12	         0	        10	2147483647	2147483647	         7	2147483647
        // 2147483647	        10	         0	         3	         5	         6	2147483647
        // 2147483647	2147483647	         3	         0	         4	2147483647	2147483647
        // 2147483647	2147483647	         5	         4	         0	         2	         8
        //         16	         7	         6	2147483647	         2	         0	         9
        //         14	2147483647	2147483647	2147483647	         8	         9	         0
        kru.print();

        // 打印边信息
        // [Data [A->B=12], Data [A->F=16], Data [A->G=14], Data [B->C=10], Data [B->F=7], Data [C->D=3], Data [C->E=5], Data [C->F=6], Data [D->E=4], Data [E->F=2], Data [E->G=8], Data [F->G=9]]
        final Data[] edges = kru.getEdges();
        System.out.println(Arrays.toString(edges));

        // 对边进行排序
        // [Data [E->F=2], Data [C->D=3], Data [D->E=4], Data [C->E=5], Data [C->F=6], Data [B->F=7], Data [E->G=8], Data [F->G=9], Data [B->C=10], Data [A->B=12], Data [A->G=14], Data [A->F=16]]
        kru.sortEdge(edges);
        System.out.println(Arrays.toString(edges));

        // 生成最小生成树
        final Data[] results = kru.kruskal();
        System.out.println(Arrays.toString(results));
    }


    // 边的个数
    private int edgeNum;

    // 顶点数组
    private final char[] vertexes;

    // 邻接矩阵
    private final int[][] matrix;

    // 使用 int 型最大值，表示两个顶点之间无法连通
    private static final int INF = Integer.MAX_VALUE;

    /**
     * 初始化图
     *
     * @param vertexes 顶点数据
     * @param matrix   边数组
     **/
    public KruskalAlgorithm(char[] vertexes, int[][] matrix) {
        int vlen = vertexes.length;

        // 使用 copy 的方式初始化，可以保障多次运行不会互相干扰
        // 初始化顶点数组
        this.vertexes = new char[vlen];
        System.arraycopy(vertexes, 0, this.vertexes, 0, vlen);

        // 初始化边
        this.matrix = new int[vlen][vlen];
        System.arraycopy(matrix, 0, this.matrix, 0, vlen);

        // 统计边
        for (int i = 0; i < vlen; i++) {
            for (int j = i + 1; j < vlen; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    /**
     * 使用克鲁斯卡尔算法生成最小生成树
     */
    public Data[] kruskal() {
        // 表示最后结果数组的索引
        int index = 0;

        // 用于保存已有 "最小生成树" 中的每个顶点的【终点顶点下标】，初始为 0
        int[] ends = new int[edgeNum];

        // 创建结果数组，最小生成树的边的集合
        Data[] results = new Data[edgeNum];

        // 获取图中所有边的集合, 一共 12 条边
        final Data[] allEdges = getEdges();

        // 按照边的权值大小进行排序
        // [Data [E->F=2], Data [C->D=3], Data [D->E=4], Data [C->E=5], Data [C->F=6], Data [B->F=7], Data [E->G=8], Data [F->G=9], Data [B->C=10], Data [A->B=12], Data [A->G=14], Data [A->F=16]]
        sortEdge(allEdges);

        // 从小到大遍历所有的边，添加到结果集中，但是需要保证不产生回路
        for (final Data currentEdge : allEdges) {
            // 处理每一条边
            // 获取边的开始点, 终止点的位置
            // ===== 第一条边为 [E->F=2]，start 为 4 end 为 5
            final int start = getPosition(currentEdge.start);
            final int end = getPosition(currentEdge.end);

            // 获取 start 点在已有的最小生成树的终点
            // ===== 第一条边为 [E->F=2]，因为 ends 所有初始元素为 0，m->4，n->5
            int m = getEnd(ends, start);
            int n = getEnd(ends, end);

            // 判断是否构成回路
            if (m != n) {
                // 设置 m 在终点集合中的值，即 m 的终点是 n
                // ===== 第一条边为 [E->F=2]，此时 ends 数组更新状态，下标为 4 号顶点的顶点的【终点顶点下标】更新为 5 号顶点，即 E 的终点是 F
                // ends 初始：[0,0,0,0,0,0,0,0,0,0,0,0]
                // ends 此时：[0,0,0,0,5,0,0,0,0,0,0,0]
                ends[m] = n;
                results[index++] = currentEdge;
            }
        }

        return results;
    }

    public void print() {
        for (int i = 0; i < vertexes.length; i++) {
            for (int j = 0; j < vertexes.length; j++) {
                System.out.printf("%10d\t", matrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 使用冒泡对边进行排序
     *
     * @param edges 边的集合
     **/
    private void sortEdge(Data[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    Data tmp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * 返回顶点的下标
     *
     * @param c 传入的顶点的值
     **/
    private int getPosition(char c) {
        for (int i = 0; i < vertexes.length; i++) {
            if (c == vertexes[i]) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 获取图中的边，放到 Data[] 数组中，后面需要遍历该数组，通过 matrix 邻接矩阵来获得
     * <p>
     * Data[] -> [Data['A','B',12], Data['B','F',7], ...]
     **/
    private Data[] getEdges() {
        int index = 0;
        final Data[] edges = new Data[edgeNum];

        for (int i = 0; i < vertexes.length; i++) {
            // 连接矩阵关于中心轴对称
            for (int j = i + 1; j < vertexes.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new Data(vertexes[i], vertexes[j], matrix[i][j]);
                }
            }
        }

        return edges;
    }

    /**
     * 功能描述，获取下标为 i 的顶点的终点，用于判断两点顶点的终点是否相同（是否会产生回路）
     *
     * @param ends 记录了各个顶点对应的【终点顶点的下标】，此数据是在遍历过程中逐步形成的
     * @param i    传入的顶点下标
     * @return 下边为 i 的顶点的对应终点的下标
     **/
    private int getEnd(int[] ends, int i) {
        // ===== 第一条边为 [E->F=2]，此时 ends 数组更新状态，下标为 4 号顶点的顶点的【终点顶点下标】更新为 5 号顶点，即 E 的终点是 F
        // ends 初始：[0,0,0,0,0,0,0,0,0,0,0,0]
        // ends 此时：[0,0,0,0,5,0,0,0,0,0,0,0]
        // 1 取终点时，E(4) -> F；F(5) -> F 因为 ends[5] ==0 所以直接返回当前下标
        // 2 表示如果这个顶点在 ends 数据中更新过，那么就取更新过的值，否则返回自身的下标
        // 3 while 循环的作用是，按顺序依次取终点，因为这个顶点的下一个可能只是中继节点，需要继续向下找终点
        while (ends[i] != 0) {
            i = ends[i];
        }

        return i;
    }

    /*
     * 边的对象
     */
    static class Data {
        // 边的开始点
        char start;
        // 边的结束点
        char end;
        // 边的权值
        int weight;

        public Data(char start, char end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Data [" + start + "->" + end + "=" + weight + "]";
        }
    }
}
