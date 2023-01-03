package com.sunk.datastructure.chapter04;

public class Main {

    public static void main(String[] args) {
        testSingleLinkedList();

        System.out.println("\n\n\n----------\n\n\n");

        doubleLinkedListTest();
    }

    /*
     * 单向链表测试
     */
    public static void testSingleLinkedList() {
        // 进行测试
        // 1 创建节点
        final SingleLinkedList.HeroNode node1 = new SingleLinkedList.HeroNode(1, "宋江", "及时雨");
        final SingleLinkedList.HeroNode node2 = new SingleLinkedList.HeroNode(2, "卢俊义", "玉麒麟");
        final SingleLinkedList.HeroNode node3 = new SingleLinkedList.HeroNode(3, "吴用", "智多星");
        final SingleLinkedList.HeroNode node4 = new SingleLinkedList.HeroNode(4, "林冲", "豹子头");

        // 2 创建链表
        final SingleLinkedList linkedList = new SingleLinkedList();
        linkedList.addByOrder(node1);
        linkedList.addByOrder(node3);
        linkedList.addByOrder(node4);
        linkedList.addByOrder(node2);

        // 测试修改元素
        final SingleLinkedList.HeroNode newNode = new SingleLinkedList.HeroNode(4, "林冲2", "豹子头2");
        linkedList.update(newNode);

        // 测试删除元素
        final SingleLinkedList.HeroNode deleteNode = new SingleLinkedList.HeroNode(5, "", "");
        linkedList.deleteNode(deleteNode);

        // 测试链表长度
        final int length = linkedList.length();
        System.out.println(length);

        // 测试：查找单链表中的倒数第 k 个节点
        final SingleLinkedList.HeroNode lastIndexNode = linkedList.findLastIndexNode(3);
        System.out.println("lastIndexNode: " + lastIndexNode);


        // 3 打印链表
        System.out.println("====================");
        linkedList.list();

        // 测试反转链表
        System.out.println("====================");
        linkedList.reverse();
        linkedList.list();

        // 测试打印反转链表
        System.out.println("==================== 反转1");
        linkedList.reverseList();
        System.out.println("==================== 反转2");
        linkedList.reverseListIter(linkedList.firstNode());
    }


    public static void doubleLinkedListTest() {
        // 进行测试
        final DoubleLinkedList.HeroNode node1 = new DoubleLinkedList.HeroNode(1, "宋江", "及时雨");
        final DoubleLinkedList.HeroNode node2 = new DoubleLinkedList.HeroNode(2, "卢俊义", "玉麒麟");
        final DoubleLinkedList.HeroNode node3 = new DoubleLinkedList.HeroNode(3, "吴用", "智多星");
        final DoubleLinkedList.HeroNode node4 = new DoubleLinkedList.HeroNode(4, "林冲", "豹子头");

        // 创建双向链表
        final DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        doubleLinkedList.add(node1);
        doubleLinkedList.add(node2);
        doubleLinkedList.add(node3);
        doubleLinkedList.add(node4);

        // 打印链表
        System.out.println("============================");
        doubleLinkedList.list();

        // 修改测试
        final DoubleLinkedList.HeroNode nodeUpdate = new DoubleLinkedList.HeroNode(4, "公孙胜", "入云龙");
        doubleLinkedList.update(nodeUpdate);
        System.out.println("============================");
        doubleLinkedList.list();

        // 删除测试
        System.out.println("============================");
        doubleLinkedList.deleteNode(3);
        doubleLinkedList.list();
    }

}
