package com.sunk.datastructure.chapter04;

/*
 * 单向环形链表
 */
public class CircleSingleLinkedList {
    // 创建一个 first 节点
    private Boy first = null;

    /*
     * 添加节点，构建环形的链表
     */
    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("nums 的值不正确");
            return;
        }

        // 创建辅助节点，帮助构建环形链表
        Boy currBoy = null;

        // 使用循环创建环形链表
        for (int i = 1; i <= nums; i++) {
            // 根据编号创建 Boy 节点
            final Boy boy = new Boy(i);

            if (i == 1) {
                // 如果是第一个节点
                first = boy;
                first.setNext(first);

                // 初始化辅助节点
                currBoy = first;
            } else {
                // 如果不是第一个节点
                currBoy.setNext(boy);
                boy.setNext(first);

                // 移动辅助节点
                currBoy = boy;
            }
        }
    }

    /*
     * 遍历当前环形链表
     */
    public void list() {
        // 判断链表是否为空
        if (first == null) {
            System.out.println("链表为空");
            return;
        }

        // 使用辅助节点进行遍历
        Boy curr = first;

        do {
            System.out.println(curr);
            curr = curr.next;
        } while (curr != first);
    }


    static class Boy {
        private int no;

        // 指向下一个节点
        private Boy next;

        public Boy(int no) {
            this.no = no;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public void setNext(Boy next) {
            this.next = next;
        }

        public Boy getNext() {
            return next;
        }

        @Override
        public String toString() {
            return "Boy{" +
                    "no=" + no +
                    '}';
        }
    }

}
