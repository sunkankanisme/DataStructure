package com.sunk.datastructure.chapter10.huffman;

/**
 * 赫夫曼树节点
 * <p>
 * 为了让类支持工具类的排序方法，需要实现 Comparable 接口
 *
 * @author sunk
 * @since 2023/2/7
 **/
public class Node implements Comparable<Node> {

    // 节点权值
    int value;

    // 左右指针
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }

    public void preOrder() {
        System.out.println(this);

        if (this.left != null) {
            this.left.preOrder();
        }

        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
