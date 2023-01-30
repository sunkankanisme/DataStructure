package com.sunk.datastructure.chapter08;

import java.util.Arrays;

/**
 * 插值查找算法
 *
 * @author sunk
 * @since 2023/1/30
 **/
public class InsertValueSearch {
    private static int count = 0;

    public static void main(String[] args) {
        int[] arr = new int[100];

        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }

        System.out.println(find(arr, 36, 0, arr.length - 1));
    }

    public static int find(int[] arr, int target, int left, int right) {
        System.out.println("COUNT: " + count++);
        if (left > right || arr[0] > target || arr[arr.length - 1] < target) return -1;

        int middle = left + (target - arr[left]) * (right - left) / (arr[right] - arr[left]);

        if (arr[middle] > target) {
            return find(arr, target, left, middle - 1);
        } else if (arr[middle] < target) {
            return find(arr, target, middle + 1, right);
        } else {
            return middle;
        }
    }

}
