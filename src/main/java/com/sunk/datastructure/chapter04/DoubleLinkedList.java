package com.sunk.datastructure.chapter04;


/*
 * 双向链表
 */
public class DoubleLinkedList {

    // 1 先初始化一个头结点，头结点不要动，不存放具体的数据
    private final HeroNode head = new HeroNode(0, "", "");

    /*
     * 遍历双向链表
     */
    public void list() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        // 遍历链表
        HeroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
            System.out.println(temp);
        }
    }

    /*
     * 添加元素
     */
    public void add(HeroNode node) {
        HeroNode temp = head;

        // 遍历链表找到最后，当节点后面没有节点时，则找到最后了
        while (temp.next != null) {
            // 如果不是最后的节点则后移
            temp = temp.next;
        }

        // 将新的节点挂载在最后一个节点上
        temp.next = node;
        node.pre = temp;
    }

    /*
     * 修改节点的内容
     */
    public void update(HeroNode newNode) {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        // 找到需要修改的节点
        HeroNode temp = head;
        // 标识是否找到该节点
        boolean flag = false;

        while (true) {
            // 找到最后了
            if (temp == null) {
                break;
            }

            // 找到了元素
            if (temp.no == newNode.no) {
                flag = true;
                break;
            }

            temp = temp.next;
        }

        // 根据 flag 判断是否找到
        if (flag) {
            temp.name = newNode.name;
            temp.nickName = newNode.nickName;
        } else {
            System.out.printf("没有找到编号为 [%s] 的节点 \n", newNode.no);
        }
    }

    /*
     * 删除节点
     * - 对于双向链表，可以直接找到要删除的这个节点，然后自我删除即可
     */
    public void deleteNode(int no) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        HeroNode temp = head.next;
        boolean flag = false;

        while (temp != null) {
            // 找到需要删除的节点的前一个节点
            if (temp.no == no) {
                flag = true;
                break;
            }

            temp = temp.next;
        }

        if (flag) {
            // 执行双向链表的删除
            temp.pre.next = temp.next;

            // 如果是最后一个节点就不需要下面这个
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("没有找到编号为 [%s] 的节点 \n", no);
        }
    }


    /*
     * 定义一个节点类
     */
    static class HeroNode {
        public int no;
        public String name;
        public String nickName;

        // 前后节点的指针
        public HeroNode next;
        public HeroNode pre;

        public HeroNode(int hNo, String hName, String hNickName) {
            this.no = hNo;
            this.name = hName;
            this.nickName = hNickName;
        }

        @Override
        public String toString() {
            return "HeroNode{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    ", nickName='" + nickName +
                    '}';
        }
    }
}




