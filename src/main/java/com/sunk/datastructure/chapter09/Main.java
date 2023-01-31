package com.sunk.datastructure.chapter09;

/**
 * @author sunk
 * @since 2023/1/31
 **/
public class Main {

    public static void main(String[] args) {
        final EmpHashTable empHashTable = new EmpHashTable(7);

        final Emp zhangsan = new Emp(1, "zhangsan");
        final Emp lisi = new Emp(102, "lisi");
        final Emp wangwu = new Emp(8, "wangwu");

        // 测试添加
        empHashTable.add(zhangsan);
        empHashTable.add(lisi);
        empHashTable.add(wangwu);

        empHashTable.list();

        // 测试查找
        System.out.println(empHashTable.findEmpById(101));
        System.out.println(empHashTable.findEmpById(102));

    }

}
