package com.sunk.datastructure.chapter08;

/**
 * 二分查找，使用递归实现
 *
 * @author sunk
 * @since 2023/1/30
 **/
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {0, 1, 3, 5, 34, 45, 67, 109, 302};
        int target = 40;
        System.out.println(find(arr, target, 0, arr.length));

        int target2 = 302;
        System.out.println(find(arr, target2, 0, arr.length));
    }


    public static int find(int[] arr, int target, int left, int right) {
        if (left > right) return -1;
        int middle = left + (right - left) / 2;

        if (arr[middle] > target) {
            return find(arr, target, left, middle - 1);
        } else if (arr[middle] < target) {
            return find(arr, target, middle + 1, right);
        } else {
            return middle;
        }
    }

}
