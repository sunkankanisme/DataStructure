package com.sunk.datastructure.chapter12.prim;

import java.util.Arrays;

/**
 * 使用普里姆（prim）算法创建最小生成树，解决修路问题
 *
 * @author sunk
 * @since 2023/2/17
 */
public class PrimAlgorithm {

    public static void main(String[] args) {
        // 创建图
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertexNum = data.length;

        // 使用二维数组表示邻接矩阵关系
        // 注：使用 10000 表示两个点之间不连通
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}
        };

        // 生成图对象
        final MGraph mGraph = new MGraph(vertexNum);
        final MinTree minTree = new MinTree();
        minTree.createGraph(mGraph, vertexNum, data, weight);
        minTree.showGraph(mGraph);

        // 生成最小生成树
        minTree.prim(mGraph, 0);
    }

    /*
     * 最小生成树类
     */
    static class MinTree {

        /**
         * 构建图
         *
         * @param graph     图对象
         * @param vertexNum 图对应的顶点个数
         * @param data      图的各个顶点的值
         * @param weight    图的邻接矩阵
         **/
        public void createGraph(MGraph graph, int vertexNum, char[] data, int[][] weight) {
            for (int k = 0; k < vertexNum; k++) {
                graph.data[k] = data[k];

                for (int l = 0; l < vertexNum; l++) {
                    graph.weight[k][l] = weight[k][l];
                }
            }
        }

        // 打印图邻接矩阵
        public void showGraph(MGraph graph) {
            for (int i = 0; i < graph.vertexNum; i++) {
                System.out.println(Arrays.toString(graph.weight[i]));
            }
        }

        /**
         * 编写 prim 算法生成最小生成树
         *
         * @param graph 图
         * @param v     从第几个顶点开始创建
         **/
        public void prim(MGraph graph, int v) {
            // 表示已经访问过的节点
            // 注：Java 数据元素默认为 0，其他语言需要进行初始化操作
            final int[] visited = new int[graph.vertexNum];

            // 将当前节点标记为已访问
            visited[v] = 1;

            // 使用 h1 和 h2 表示两个顶点的下标
            int h1 = -1;
            int h2 = -1;

            // 将 minWeight 初始化为较大的值，后面在遍历过程中会更新
            int minWeight = Integer.MAX_VALUE;

            // 有 graph.vertexNum 个顶点，会存在 graph.vertexNum - 1 条边
            // 这层循环指定遍历的次数
            for (int i = 1; i < graph.vertexNum; i++) {
                // 从【最小生成树中】某个已访问的顶点遍历它的所有【未访问邻接节点】找到权值最小的
                // 注：这里可以保证所有的已访问节点的未访问邻接节点都可达到，从中取出权值最小的节点，加入到最小生成树中
                for (int j = 0; j < graph.vertexNum; j++) {
                    // 优化：一，将 visited[j] == 1 放在此处更好；二，判断 j != k 无需和自身进行比较
                    for (int k = 0; k < graph.vertexNum; k++) {
                        // 取出权值最小的节点的权值，并记录节点信息
                        if (visited[j] == 1 && visited[k] == 0 && graph.weight[j][k] < minWeight) {
                            minWeight = graph.weight[j][k];
                            h1 = j;
                            h2 = k;
                        }
                    }
                }

                // 将找到的边打印出来，并将找到的节点标记为已访问，相当于加入到最小生成树中
                System.out.println("边 <" + graph.data[h1] + " - " + graph.data[h2] + "> 权值 " + minWeight);
                visited[h2] = 1;
                minWeight = Integer.MAX_VALUE;
            }
        }

    }

    /*
     * 图类
     */
    static class MGraph {
        // 表示图的节点个数
        int vertexNum;

        // 存放节点数据
        char[] data;

        // 定义邻接矩阵存放边数据
        int[][] weight;

        public MGraph(int vertexNum) {
            this.vertexNum = vertexNum;
            data = new char[vertexNum];
            weight = new int[vertexNum][vertexNum];
        }
    }

}
