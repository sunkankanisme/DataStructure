package com.sunk.datastructure.chapter10;

/**
 * 以数组的方式存储二叉树，并完成遍历
 *
 * @author sunk
 * @since 2023/2/2
 **/
public class ArrayTreeDemo {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        final ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);

        arrayBinaryTree.preOrder();

    }

    static class ArrayBinaryTree {
        // 存储数据节点的数组
        private final int[] arr;

        public ArrayBinaryTree(int[] arr) {
            this.arr = arr;
        }

        public void preOrder() {
            preOrder(0);
        }

        /**
         * 实现顺序存储二叉树的前序遍历（根据数组模拟的规律）
         *
         * @param index 数组的下标
         */
        public void preOrder(int index) {
            // 如果数组为空则退出
            if (arr == null) {
                return;
            }

            // 输出当前元素
            System.out.println(arr[index]);

            // 向左递归遍历
            if (2 * index + 1 < arr.length) {
                preOrder(2 * index + 1);
            }

            // 向右递归遍历
            if (2 * index + 2 < arr.length) {
                preOrder(2 * index + 2);
            }
        }
    }

}
