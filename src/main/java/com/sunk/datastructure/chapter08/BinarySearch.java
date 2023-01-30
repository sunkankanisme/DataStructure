package com.sunk.datastructure.chapter08;

import java.util.ArrayList;

/**
 * 二分查找，使用递归实现
 *
 * @author sunk
 * @since 2023/1/30
 **/
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {0, 1, 3, 5, 34, 45, 67, 109, 302, 302, 302};
        int target = 40;
        System.out.println(find(arr, target, 0, arr.length));
        // -1

        int target2 = 302;
        System.out.println(find(arr, target2, 0, arr.length));
        // 8

        System.out.println(find2(arr, target2, 0, arr.length));
        // [8, 9, 10]
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

    /*
     * 查找出所有的目标值
     */
    public static ArrayList<Integer> find2(int[] arr, int target, int left, int right) {
        if (left > right) return null;
        int middle = left + (right - left) / 2;

        if (arr[middle] > target) {
            return find2(arr, target, left, middle - 1);
        } else if (arr[middle] < target) {
            return find2(arr, target, middle + 1, right);
        } else {
            // 以 mid 为中心进行左右扫描
            final ArrayList<Integer> results = new ArrayList<>();

            // 左边
            for (int i = middle - 1; i >= 0; i--) {
                if (arr[i] == target) {
                    results.add(i);
                }
            }

            // 当前
            results.add(middle);

            // 右边
            for (int i = middle + 1; i < arr.length; i++) {
                if (arr[i] == target) {
                    results.add(i);
                }
            }

            return results;
        }
    }

}
