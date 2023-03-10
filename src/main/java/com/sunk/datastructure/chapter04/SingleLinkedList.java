package com.sunk.datastructure.chapter04;

import java.util.Stack;

/*
 * 定义一个链表来管理英雄任务
 */
public class SingleLinkedList {

    // 1 先初始化一个头结点，头结点不要动，不存放具体的数据
    private final HeroNode head = new HeroNode(0, "", "");

    /*
     * 添加元素到尾部
     * - 当不考虑编号的顺序时找到当前链表的最后节点，将最后这个节点的 next 指向新的节点
     */
    public void add(HeroNode node) {
        // 因为 head 节点不能动，所以需要一个辅助变量 temp
        HeroNode temp = head;

        // 遍历链表找到最后
        while (true) {
            // 当节点后面没有节点时，则找到最后了
            if (temp.next == null) {
                break;
            }

            // 如果不是最后的节点则后移
            temp = temp.next;
        }

        // 将新的节点挂载在最后一个节点上
        temp.next = node;
    }

    /*
     * 按照 HeroID 进行元素的插入
     */
    public void addByOrder(HeroNode node) {
        // temp 应该是位于添加位置的前一个节点
        HeroNode temp = head;

        // 标识添加的编号是否存在
        boolean flag = false;

        while (true) {
            // 说明已经到最后一个元素了
            if (temp.next == null) {
                break;
            }

            if (temp.next.no > node.no) {
                // 元素应该插在 temp 的后面，temp.next 前面
                break;
            } else if (temp.next.no == node.no) {
                // 元素已经存在，抛出异常
                flag = true;
                break;
            }

            temp = temp.next;
        }

        // 判断 flag
        if (flag) {
            // 编号已经存在，不能添加
            System.out.printf("准备插入的英雄 [%s] 已经存在，不能加入 \n", node);
        } else {
            // 插入到链表中
            // - 新节点的 next = temp.next
            // - temp.next = 新的节点
            node.next = temp.next;
            temp.next = node;
        }
    }

    /*
     * 完成修改节点的信息
     * - 根据编号进行修改（编号不能修改）
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
     */
    public void deleteNode(HeroNode node) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        HeroNode temp = head;
        boolean flag = false;

        while (true) {
            if (temp.next == null) {
                break;
            }

            // 找到需要删除的节点的前一个节点
            if (temp.next.no == node.no) {
                flag = true;
                break;
            }

            temp = temp.next;
        }

        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("没有找到编号为 [%s] 的节点 \n", node.no);
        }
    }

    /*
     * 获取单链表有效节点的个数（不计入头结点）
     */
    public int length() {
        // 空链表返回 0
        if (head.next == null) {
            return 0;
        }

        int length = 0;
        // 定义辅助节点
        HeroNode curr = head.next;
        while (curr != null) {
            length++;
            curr = curr.next;
        }

        return length;
    }

    /*
     * 找倒数第 index 个元素
     */
    public HeroNode findLastIndexNode(int index) {
        // 空链表返回null
        if (head.next == null) {
            return null;
        }

        // 第一次得到链表的长度
        int length = length();

        // 第二次遍历到 length - index 个元素
        if (index <= 0 || index > length) {
            return null;
        } else {
            // 定义辅助变量
            HeroNode temp = head.next;

            // 向后遍历指定次数
            for (int i = 0; i < (length - index); i++) {
                temp = temp.next;
            }

            return temp;
        }
    }

    /*
     * 实现链表的反转
     */
    public void reverse() {
        // 当前链表如果为空，或者仅有一个节点则直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }

        // 当前节点的变量
        HeroNode curr = head.next;
        // 当前节点的下一个节点的变量
        HeroNode next = null;
        // 新链表的头结点
        HeroNode reverseHead = new HeroNode(0, "", "");

        // 遍历当前链表，并完成链表的反转
        while (curr != null) {
            // 将下一个节点保存起来，防丢失
            next = curr.next;

            // 将当前节点插入到新链表的第一个位置
            curr.next = reverseHead.next;
            reverseHead.next = curr;

            // 将当前节点指向下一个节点
            curr = next;
        }

        // 将新链表的数据赋给原先的 Head 上
        head.next = reverseHead.next;
    }

    /*
     * 逆序打印链表，使用栈
     */
    public void reverseList() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        // 创建一个栈
        final Stack<HeroNode> stack = new Stack<>();
        HeroNode tmp = head.next;

        // 将链表的所有节点压入栈
        while (tmp != null) {
            stack.push(tmp);
            tmp = tmp.next;
        }

        // 打印中的元素
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    public void reverseListIter(HeroNode curr) {
        if (curr == null) {
            return;
        }

        reverseListIter(curr.next);
        System.out.println(curr);
    }

    public HeroNode firstNode() {
        return head.next;
    }


    /*
     * 显示链表的元素
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
     * 定义一个节点类
     */
    static class HeroNode {
        public int no;
        public String name;
        public String nickName;

        public HeroNode next;

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




