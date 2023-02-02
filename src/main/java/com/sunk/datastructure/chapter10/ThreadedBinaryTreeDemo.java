package com.sunk.datastructure.chapter10;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author sunk
 * @since 2023/2/2
 **/
public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {
        // 测试中序线索化二叉树
        final HeroNode2 root = new HeroNode2(1, "a");
        final HeroNode2 node2 = new HeroNode2(3, "b");
        final HeroNode2 node3 = new HeroNode2(6, "c");
        final HeroNode2 node4 = new HeroNode2(8, "d");
        final HeroNode2 node5 = new HeroNode2(10, "e");
        final HeroNode2 node6 = new HeroNode2(14, "f");

        /*
         * 挂载节点
         *
         *                  root
         *         node2         node3
         *     node4   node5  node6
         */
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;

        final ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree(root);
        threadedBinaryTree.threadedNodes();

        // 测试
        System.out.println(node5.getLeft());
        System.out.println(node5.getRight());

        // 测试遍历 - 使用线索化的方式
        System.out.println("======================");
        threadedBinaryTree.threadedList();
    }


    static class ThreadedBinaryTree {

        private final HeroNode2 root;

        // 为了实现线索化，需要创建一个当前节点指向前驱节点的引用
        // 在递归进行线索化时，pre 总是保持指向前一个节点
        HeroNode2 pre = null;

        public ThreadedBinaryTree(HeroNode2 root) {
            this.root = root;
        }

        public void threadedNodes() {
            threadedNodes(root);
        }

        /**
         * 编写对二叉树进行中序线索化的方法
         *
         * @param node 当前需要线索化的节点
         */
        public void threadedNodes(HeroNode2 node) {
            if (node == null) return;

            // 1 线索化左子树
            threadedNodes(node.getLeft());

            // 2 线索化自身
            // 处理左指针
            if (node.getLeft() == null) {
                node.setLeft(pre);
                node.leftType = 1;
            }

            // 处理右指针
            if (pre != null && pre.getRight() == null) {
                pre.setRight(node);
                pre.setRightType(1);
            }

            pre = node;

            // 3 线索化右子树
            threadedNodes(node.getRight());
        }

        /*
         * 遍历中序线索化二叉树
         */
        public void threadedList() {
            // 定义一个变量，存储当前遍历的节点，从 root 开始
            HeroNode2 node = root;

            while (node != null) {
                // 找到 left type = 1 的节点，即中序遍历的第一个节点
                // 后面随着遍历而变化，因为 leftType = 1 时，说明该节点是按照线索化处理后的有效节点
                while (node.getLeftType() == 0) {
                    node = node.left;
                }

                // 打印当前节点
                System.out.println(node);

                // 如果当前节点的右指针是后继节点，则一直输出
                while (node.getRightType() == 1) {
                    final HeroNode2 next = node.right;
                    System.out.println(next);

                    node = node.right;
                }

                // 当右指针不存在后继节点的时候，替换遍历的节点
                node = node.right;
            }
        }
    }
}
