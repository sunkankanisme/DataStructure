package com.sunk.datastructure.chapter08;

/**
 * 线性查找
 *
 * @author sunk
 * @since 2023/1/30
 **/
public class SeqSearch {

    public static void main(String[] args) {
        int[] arr = {1, 4, 2, 7, 5, 4, 5, 6};
        int target = 5;

        System.out.println(find(arr, target));
    }

    /*
     * 实现线性查找
     * - 找到一个满足条件的就返回
     */
    public static int find(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }

        return -1;
    }
}
