package com.sunk.datastructure.chapter10;

/**
 * @author sunk
 * @since 2023/2/1
 **/
public class Main {

    public static void main(String[] args) {
        // 创建二叉树
        final BinaryTree tree = new BinaryTree();

        // 创建节点
        final BinaryTree.HeroNode node1 = new BinaryTree.HeroNode(1, "宋江");
        final BinaryTree.HeroNode node2 = new BinaryTree.HeroNode(2, "吴用");
        final BinaryTree.HeroNode node3 = new BinaryTree.HeroNode(3, "卢俊义");
        final BinaryTree.HeroNode node4 = new BinaryTree.HeroNode(4, "林冲");

        /*
         * 挂载节点
         *
         *          node1(root)
         *      node2       node3
         *     -     -     -    node4
         */
        tree.setRoot(node1);
        node1.setLeft(node2);
        node1.setRight(node3);
        node3.setRight(node4);

        // 测试
        System.out.println("====== 前序遍历");
        tree.preOrder();                // 1,2,3,4
        System.out.println("====== 中序遍历");
        tree.infixOrder();              // 2,1,3,4
        System.out.println("====== 后序遍历");
        tree.postOrder();               // 2,3,4,1

        // 查找
        final BinaryTree.HeroNode node5 = new BinaryTree.HeroNode(5, "关胜");
        node3.setLeft(node5);
        System.out.println("====== 前序遍历查找");
        System.out.println(tree.preOrderSearch(5));
        System.out.println("====== 中序遍历查找");
        System.out.println(tree.infixOrderSearch(5));
        System.out.println("====== 后序遍历查找");
        System.out.println(tree.postOrderSearch(5));


        // 删除
        System.out.println("====== 删除前");
        tree.preOrder();                        // 1,2,3,5,4
        tree.delNode(5);
        System.out.println("====== 删除后");
        tree.preOrder();                        // 1,2,3,4

        // 删除 2
        System.out.println("====== 删除前 2");
        tree.preOrder();
        tree.delNode2(3);
        System.out.println("====== 删除后 2");
        tree.preOrder();
    }

}
