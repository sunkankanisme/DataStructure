package com.sunk.datastructure.chapter09;

import java.util.Arrays;

/**
 * 创建 HashTable 用来管理多条链表
 *
 * @author sunk
 * @since 2023/1/31
 **/
public class EmpHashTable {
    private final int size;
    private final EmpLinkedList[] empLinkedLists;

    public EmpHashTable(int size) {
        this.size = size;

        // 初始化链表数组
        empLinkedLists = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }


    /*
     * 添加元素
     */
    public void add(Emp emp) {
        // 根据员工的 id 得到该员工应该添加到哪一条链表
        int no = hash(emp.id);

        // 将员工加入到对应的链表中
        empLinkedLists[no].add(emp);
    }

    /*
     * 查找元素
     */
    public Emp findEmpById(int id) {
        return empLinkedLists[hash(id)].findEmpById(id);
    }


    /*
     * 遍历所有的元素
     */
    public void list() {
        for (int i = 0; i < empLinkedLists.length; i++) {
            if (!empLinkedLists[i].isEmpty()) {
                System.out.print(i + "\t");
                empLinkedLists[i].list();
            }
        }
    }

    /*
     * 散列函数
     * - 使用简单地取模法
     */
    public int hash(int id) {
        return id % size;
    }

}
