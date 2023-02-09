package com.sunk.datastructure.chapter10.bst;

public class BinarySortTreeTest {

    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9};
        final BinarySortTree binarySortTree = new BinarySortTree();

        // 添加元素
        for (int i : arr) {
            binarySortTree.add(i);
        }

        // 中序遍历
        binarySortTree.infixOrder();


    }

}
