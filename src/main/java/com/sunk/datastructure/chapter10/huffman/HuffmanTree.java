package com.sunk.datastructure.chapter10.huffman;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author sunk
 * @since 2023/2/7
 **/
public class HuffmanTree {

    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};

        final Node huffmanTree = createHuffmanTree(arr);
        huffmanTree.preOrder();
    }

    /*
     * 创建赫夫曼树的方法
     */
    public static Node createHuffmanTree(int[] arr) {
        Node finalNode = null;

        // 1 将数组转换为 Node 集合
        final ArrayList<Node> nodes = new ArrayList<>();
        for (int i : arr) {
            nodes.add(new Node(i));
        }

        while (nodes.size() >= 2) {
            // 2 将集合中的元素进行排序
            Collections.sort(nodes);

            // 3 取出权值最小的两个元素
            final Node nodeLeft = nodes.remove(0);
            final Node nodeRight = nodes.remove(0);

            // 4 构建新树并存入到原集合中
            final Node newNode = new Node(nodeLeft.value + nodeRight.value);
            newNode.left = nodeLeft;
            newNode.right = nodeRight;

            nodes.add(newNode);
            finalNode = newNode;

            System.out.println("NODES: " + nodes);
        }

        return finalNode;
    }

}
