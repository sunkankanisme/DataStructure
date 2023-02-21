package com.sunk.datastructure.chapter12.dijkstra;

import java.util.Arrays;

/**
 * 使用迪杰斯特拉算法解决最短路径问题
 *
 * @author sunk
 * @since 2023/2/21
 */
public class DijkstraAlgorithm {

    public static void main(String[] args) {
        // 顶点集合
        char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        // 邻接矩阵
        int[][] matrix = new int[vertexes.length][vertexes.length];
        // 表示不可以连接
        final int N = 65535;
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};

        // 构建图
        // 65535	    5	    7	65535	65535	65535	    2
        //     5	65535	65535	    9	65535	65535	    3
        //     7	65535	65535	65535	    8	65535	65535
        // 65535	    9	65535	65535	65535	    4	65535
        // 65535	65535	    8	65535	65535	    5	    4
        // 65535	65535	65535	    4	    5	65535	    6
        //     2	    3	65535	65535	    4	    6	65535
        final Graph graph = new Graph(vertexes, matrix);
        graph.showGraph();

        // 以 6 号顶点为初始节点，寻找每个节点的最短路径
        int start = 6;
        graph.dij(start);
        graph.printDis(start);
    }

    static class Graph {
        // 顶点集合
        private final char[] vertexes;

        // 邻接矩阵
        private final int[][] matrix;

        // 添加三个数组类的属性
        private VisitedVertex vv;

        // 构造函数
        public Graph(char[] vertexes, int[][] matrix) {
            this.vertexes = vertexes;
            this.matrix = matrix;
        }

        // 显示图
        public void showGraph() {
            for (int[] ints : matrix) {
                for (int anInt : ints) {
                    System.out.printf("%5s\t", anInt);
                }
                System.out.println();
            }
        }

        /**
         * 迪杰斯特拉算法实现
         *
         * @param index 出发顶点对应的下标
         **/
        public void dij(int index) {
            // 初始化三个数组对象
            vv = new VisitedVertex(vertexes.length, index);
            vv.printStatus();

            // 更新从 index 顶点到周围顶点的距离，和前驱节点信息
            update(index);
            vv.printStatus();

            // 选择下一个节点进行遍历
            for (int i = 1; i < vertexes.length; i++) {
                index = vv.chooseNext();
                update(index);
                vv.printStatus();
            }

            vv.printStatus();
        }


        /**
         * 更新 index 下标顶点到周围顶点的距离和周围顶点的前驱节点
         *
         * @param index 指定节点的下标
         **/
        public void update(int index) {
            int len = 0;

            // 更新已访问节点为 1
            vv.already_arr[index] = 1;

            // 遍历 index 节点的邻接矩阵关系
            for (int j = 0; j < matrix[index].length; j++) {
                // 当前距离 + 前驱节点的距离
                len = vv.getDis(index) + matrix[index][j];

                // 如果当前节点没有被访问过，并且距离更短
                if (!vv.isVisited(j) && len < vv.getDis(j)) {
                    // 更新遍历到的节点到初始节点的距离
                    vv.updateDis(j, len);

                    // 更新前驱节点信息，前驱节点为 index 当前节点为 j，因为是从 index 广度遍历找到的 j
                    vv.updatePre(index, j);
                }
            }
        }

        /**
         * 打印结果信息，指定开始节点到各个节点的距离
         */
        public void printDis(int index) {
            final char curr = vertexes[index];

            for (int i = 0; i < vv.dis.length; i++) {
                System.out.println(curr + " -> " + vertexes[i] + " = " + vv.getDis(i));
            }
        }
    }

    static class VisitedVertex {
        // 记录每个节点是否访问过，1 表示访问过，0 表示未访问
        public int[] already_arr;
        // 记录访问的顶点
        public int[] pre_visited;
        // 记录从出发点到其他所有节点的距离
        public int[] dis;

        /**
         * @param length 元素个数
         * @param index  从哪个节点开始访问，即出发顶点
         **/
        public VisitedVertex(int length, int index) {
            this.already_arr = new int[length];
            this.pre_visited = new int[length];
            this.dis = new int[length];

            // 初始化 dis，与自身距离为 0，与其他节点置为最大值
            Arrays.fill(dis, 65535);
            dis[index] = 0;
        }

        /**
         * 判断指定下标的顶点是否被访问过
         *
         * @param index 顶点下标
         **/
        public boolean isVisited(int index) {
            return already_arr[index] == 1;
        }

        /**
         * 更新出发顶点到某个节点的距离
         *
         * @param index 某个顶点的下标
         * @param len   距离
         **/
        public void updateDis(int index, int len) {
            dis[index] = len;
        }

        /**
         * 更新顶点的前驱节点，下标为 index 的前驱节点设置为下标为 preIndex 的节点
         *
         * @param preIndex 需要更新的节点的前驱节点
         * @param index    需要更新的节点下标
         **/
        public void updatePre(int preIndex, int index) {
            pre_visited[index] = preIndex;
        }

        /**
         * 返回出发顶点到 index 顶点的距离
         *
         * @param index 目标顶点
         **/
        public int getDis(int index) {
            return dis[index];
        }


        /**
         * 选择下一个进行访问的节点，例如 G 节点访问完之后，选择 A 作为新的访问节点
         * <p>
         * - TODO 感觉应在与 G 连接的顶点中选择？
         */
        public int chooseNext() {
            int min = 65535;
            int index = 0;

            // 在所有未访问的节点, 寻找最小距离的节点继续进行广度遍历
            for (int i = 0; i < already_arr.length; i++) {
                if (already_arr[i] == 0 && dis[i] < min) {
                    min = dis[i];
                    index = i;
                }
            }

            return index;
        }

        /**
         * 显示最后的结果
         */
        public void printStatus() {
            System.out.println("\n ===== CURRENT STATUS ");
            System.out.println("ALR" + Arrays.toString(already_arr));
            System.out.println("PRE" + Arrays.toString(pre_visited));
            System.out.println("DIS" + Arrays.toString(dis));
        }
    }

}
