package com.sunk.datastructure.chapter09;

/**
 * @author sunk
 * @since 2023/1/31
 **/
public class EmpLinkedList {

    // 头指针指向第一个元素，此链表的 head 是有效的
    private Emp head;


    /*
     * 添加元素
     * - 添加元素时将元素添加到尾部（假定 ID 是自增的）
     */
    public void add(Emp emp) {
        // 添加首个元素
        if (head == null) {
            head = emp;
            return;
        }

        // 添加到尾部
        Emp curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }

        curr.next = emp;
    }

    /*
     * 查找元素
     */
    public Emp findEmpById(int id) {
        if (head == null) {
            return null;
        }

        Emp emp = head;

        while (emp != null && emp.id != id) {
            emp = emp.next;
        }

        return emp;
    }

    /*
     * 遍历链表
     */
    public void list() {
        // 链表为空
        if (head == null) {
            return;
        }

        // 遍历元素
        Emp curr = head;
        while (curr != null) {
            System.out.printf("ID: %s, NAME: %s\t", curr.id, curr.name);
            curr = curr.next;
        }

        System.out.println();
    }

    public boolean isEmpty() {
        return head == null;
    }

}
