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
    }
}
