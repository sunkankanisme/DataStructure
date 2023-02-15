package com.sunk.datastructure.chapter12;

/**
 * 使用分治算法完成汉诺塔解题
 *
 * @author sunk
 * @since 2023/2/15
 */
public class HanoiTower {

    public static void main(String[] args) {
        run(64, 'A', 'B', 'C');
    }

    /**
     * 汉诺塔
     *
     * @param num 需要移动的盘子的数量
     * @param a   左塔，初始时盘子所在的塔
     * @param b   中间塔，初始时没有盘子
     * @param c   右塔，初始时没有盘子的塔，最终需要负载所有盘子的塔
     **/
    public static void run(int num, char a, char b, char c) {
        if (num == 1) {
            // 只有一个盘的情况下，直接移动
            System.out.println("第 " + num + " 个盘从 " + a + " -> " + c);
        } else {
            // 存在多个盘的情况下，将上面的盘看做一个盘，最下面的看做一个盘
            // 先将上面的 “一个盘” 移动到 B，移动过程会借用 C 盘
            run(num - 1, a, c, b);

            // 将剩余的一个盘子移动到 C 塔
            System.out.println("第 " + num + " 个盘从 " + a + " -> " + c);

            // 将 B 塔上的所有的盘移动到 C 塔
            run(num - 1, b, a, c);
        }
    }

}
