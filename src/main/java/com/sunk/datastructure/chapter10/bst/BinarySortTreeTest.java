package com.sunk.datastructure.chapter10.bst;

public class BinarySortTreeTest {

    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        final BinarySortTree binarySortTree = new BinarySortTree();

        // 添加元素
        for (int i : arr) {
            binarySortTree.add(i);
        }

        // 中序遍历
        binarySortTree.infixOrder();

        // 测试删除叶子节点
        // System.out.println("=== 测试删除叶子节点");
        // binarySortTree.deleteNode(7);
        // binarySortTree.infixOrder();

        // 测试删除单子树节点
        // System.out.println("=== 测试删除单子树节点");
        // binarySortTree.deleteNode(1);
        // binarySortTree.infixOrder();

        // 测试删除双子树节点
        System.out.println("=== 测试删除双子树节点");
        binarySortTree.deleteNode(3);
        binarySortTree.infixOrder();

    }

}
