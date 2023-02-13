package com.sunk.datastructure.chapter11;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 使用邻接矩阵实现图
 */
public class Graph {

    public static void main(String[] args) {
        // 节点的个数
        int n = 5;
        String[] vertexValue = {"A", "B", "C", "D", "E"};

        // 创建图
        final Graph graph = new Graph(n);
        for (String s : vertexValue) {
            graph.insertVertex(s);
        }

        // 添加边
        // A-B A-C
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        // B-C B-D B-E
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);

        // 显示邻接矩阵
        graph.showGraph();

        // 测试 dfs
        // 0 1 2 3 4
        // A B C D E
        graph.dfs2(0);

    }

    // 存储顶点的集合
    private final ArrayList<String> vertexList;

    // 存储 图 对应的邻接矩阵
    private final int[][] edges;

    // 存储边的数量
    private int numOfEdges;

    // 定义一个数组，记录某个节点是否被访问过
    private final boolean[] isVisited;

    /**
     * 构建图
     *
     * @param n 图顶点个数
     **/
    public Graph(int n) {
        // 初始化矩阵和 list
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        isVisited = new boolean[n];
    }

    /**
     * 获得指定节点的第一个邻接节点的下标
     *
     * @param index 传入的节点
     * @return 返回传入节点的邻接节点下标，不存在的情况下返回-1
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }

        return -1;
    }

    /*
     * 根据前一个邻接节点的下标获取下一个邻接节点
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }

        return -1;
    }

    /*
     * 深度优先遍历算法
     */
    public void dfs(int i) {
        // 访问初始结点v，并标记结点v为已访问
        System.out.print(getValueByIndex(i) + " -> ");
        isVisited[i] = true;

        // 查找节点 v 的第一个邻接节点 w
        int w = getFirstNeighbor(i);

        while (w != -1) {
            if (!isVisited[w]) {
                dfs(w);
            }

            // 如果 w 节点已经被访问过，则查找邻接节点的下一个节点
            w = getNextNeighbor(i, w);
        }
    }

    /*
     * 对 dfs 进行重载，遍历所有的节点，进行 dfs
     */
    public void dfs() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(i);
            }
        }
    }

    /**
     * 深度优先访问 DFS
     * 1 打印当前节点，标记当前节点已经访问
     * <p>
     * 2 循环找到当前节点连连接的，未被访问的子节点
     * <p>
     * 3 针对每一个子节点执行 DFS
     * <p>
     * 4 若当前节点已经没有未遍历的子节点则表示已经完成搜索
     * <p>
     * 注：如果存在独立的节点（不与任何节点相邻的节点）则需要循环传入每一个节点进行查找类似于 dfs()
     */
    public void dfs2(int i) {
        System.out.print(vertexList.get(i) + " ");
        isVisited[i] = true;

        while (true) {
            final int unVisitorNeighbor = getUnVisitorNeighbor(i);

            if (unVisitorNeighbor != -1) {
                dfs2(unVisitorNeighbor);
            } else {
                break;
            }
        }
    }

    // 获取指定节点未被访问的子节点
    public int getUnVisitorNeighbor(int i) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[i][j] != 0 && !isVisited[j]) {
                return j;
            }
        }

        return -1;
    }


    /*
     * 插入顶点
     */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     *
     * @param v1     点 1 的下标
     * @param v2     点 2 的下标
     * @param weight 边的权值
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    /*
     * 返回节点的个数
     */
    public int getNumOfVertex() {
        return vertexList.size();
    }

    /*
     * 得到边的数量
     */
    public int getNumOfEdges() {
        return numOfEdges;
    }

    /*
     * 返回节点 i 的值
     */
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    /*
     * 返回 v1 - v2 的权值
     */
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    /*
     * 显示图对应的矩阵
     */
    public void showGraph() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }
}
