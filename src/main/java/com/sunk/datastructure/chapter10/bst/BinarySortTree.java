package com.sunk.datastructure.chapter10.bst;

/**
 * 二叉排序树
 **/
public class BinarySortTree {

    private Node root;

    /*
     * 添加元素
     */
    public void add(int value) {
        if (root == null) {
            this.root = new Node(value);
        } else {
            root.add(new Node(value));
        }
    }

    /*
     * 中序遍历
     */
    public void infixOrder() {
        if (this.root == null) {
            return;
        }

        root.infixOrder();
    }


    /*
     * 内部节点类
     */
    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }

        /*
         * 添加节点
         * - 递归的形式添加节点
         */
        public void add(Node node) {
            if (node == null) {
                return;
            }

            // 判断传入的节点的值，与当前子树的根节点的值得关系
            if (node.value < this.value) {
                if (this.left == null) {
                    // 当前节点的左子节点为空，直接挂载在左边
                    this.left = node;
                } else {
                    // 递归的向左子节点添加
                    this.left.add(node);
                }
            } else {
                if (this.right == null) {
                    this.right = node;
                } else {
                    // 递归的向右子节点添加
                    this.right.add(node);
                }
            }
        }

        /*
         * 中序遍历二叉树
         */
        public void infixOrder() {
            if (this.left != null) {
                this.left.infixOrder();
            }

            System.out.println(this);

            if (this.right != null) {
                this.right.infixOrder();
            }
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }

}
