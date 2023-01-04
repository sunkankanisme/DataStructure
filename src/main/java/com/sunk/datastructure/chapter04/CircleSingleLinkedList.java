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

    /**
     * 根据用户的输入，计算出圈的顺序
     *
     * @param startNo 从第几个小孩开始数数
     * @param countNo 数几下
     * @param nums    最初有几个小孩
     */
    public void countBoy(int startNo, int countNo, int nums) {
        // 先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误，请重新输入");
            return;
        }

        // 1 创建辅助指针，将 helper 指向环形链表最后的节点
        Boy helper = first;
        while (helper.next != first) {
            helper = helper.next;
        }

        // 2 报数前，先数 k 个元素，代码中循环 k - 1 次，因为第一个小孩也会报一次数
        for (int i = 0; i < startNo - 1; i++) {
            first = first.next;
            helper = helper.next;
        }

        // 3 从当前位置开始报数，然后出圈，只剩最后一个终止
        while (first.next != first) {
            for (int i = 0; i < countNo - 1; i++) {
                first = first.next;
                helper = helper.next;
            }

            // 4 此时 first 指向的就是出圈的节点
            System.out.printf("小孩 %d 出圈\n", first.no);
            first = first.next;
            helper.next = first;
        }

        // 5 将最后一个节点出圈
        System.out.printf("小孩 %d 出圈\n", first.no);
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
