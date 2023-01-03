package com.sunk.datastructure.chapter04;

public class Main {

    public static void main(String[] args) {
        testSingleLinkedList2();
    }

    public static void testSingleLinkedList() {
        // 进行测试
        // 1 创建节点
        final SingleLinkedList.HeroNode node1 = new SingleLinkedList.HeroNode(1, "宋江", "及时雨");
        final SingleLinkedList.HeroNode node2 = new SingleLinkedList.HeroNode(2, "卢俊义", "玉麒麟");
        final SingleLinkedList.HeroNode node3 = new SingleLinkedList.HeroNode(3, "吴用", "智多星");
        final SingleLinkedList.HeroNode node4 = new SingleLinkedList.HeroNode(4, "林冲", "豹子头");

        // 2 创建链表
        final SingleLinkedList linkedList = new SingleLinkedList();
        linkedList.add(node1);
        linkedList.add(node3);
        linkedList.add(node4);
        linkedList.add(node2);

        // 3 打印链表
        linkedList.list();
    }

    public static void testSingleLinkedList2() {
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
    }

}
