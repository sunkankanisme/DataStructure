package com.sunk.datastructure.chapter10.avl;

public class AVLTreeTest {

    public static void main(String[] args) {
        // int[] arr = {4, 3, 6, 5, 7, 8};  // 左旋测试
        // int[] arr = {10, 12, 8, 9, 7, 6};  // 右旋测试
        int[] arr = {10, 11, 7, 6, 8, 9};

        // 创建 AVL 树
        final AVLTree avlTree = new AVLTree();

        // 添加节点
        for (int value : arr) {
            avlTree.add(value);
        }

        // 中序遍历
        avlTree.infixOrder();

        /*
         * 左平衡测试
         * - 处理前：4 0 3
         * - 处理后：3 2 2
         * 右平衡测试
         * - 处理前：4 3 1
         * - 处理后：3 2 2
         * 双旋转测试
         * - 处理后：3 2 2
         */
        printTreeHeight(avlTree);

    }

    public static void printTreeHeight(AVLTree tree) {
        System.out.println("树高度为：" + tree.getRoot().height());
        System.out.println("左高度为：" + tree.getRoot().leftHeight());
        System.out.println("右高度为：" + tree.getRoot().rightHeight());
    }

}
