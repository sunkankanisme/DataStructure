package com.sunk.datastructure.chapter07;

import java.util.Arrays;

/**
 * @author sunk
 * @since 2023/1/18
 **/
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2, 1, 1, 1, 1, 1};
        int[] temp = new int[arr.length];

        split(arr, 0, arr.length - 1, temp);

        System.out.println(Arrays.toString(arr));
    }

    /*
     * 分解的方法
     */
    public static void split(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            // 向左递归进行分解
            split(arr, left, mid, temp);

            // 向右递归分解
            split(arr, mid + 1, right, temp);

            // 合并
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 合并的方法
     *
     * @param arr    排序的原始数组
     * @param left   左边有序序列的初始索引
     * @param middle 中间索引
     * @param right  右边索引
     * @param temp   临时数组
     **/
    public static void merge(int[] arr, int left, int middle, int right, int[] temp) {
        System.out.println(":)");

        // 初始化 i 左边有序序列的初始索引
        int i = left;
        // 初始化 j 右边有序序列的初始索引
        int j = middle + 1;

        // 指向临时数组的插入索引
        int t = 0;

        // 1 把左右两边有序数组的数据按照规则填充到 temp 数组中
        while (i <= middle && j <= right) {
            // 如果左边有序序列的当前元素，小于等于右边有序序列的当前元素
            // 即将左边的当前元素，拷贝到 temp 数组
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }

        // 2 将剩余元素的数组元素复制到临时数组
        while (i <= middle) {
            temp[t++] = arr[i++];
        }

        while (j <= right) {
            temp[t++] = arr[j++];
        }

        // 3 将 temp 数组 [ 0 ~ right-left ] 元素拷贝到原数组 [ left ~ right ]
        t = 0;
        while (left <= right) {
            arr[left++] = temp[t++];
        }
    }
}
