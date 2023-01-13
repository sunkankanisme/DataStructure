package com.sunk.datastructure.chapter07;

import java.util.Arrays;

/**
 * @author sunk
 * @since 2023/1/12
 **/
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 5};
        sortAll(arr, 0, arr.length - 1);

        System.out.println(Arrays.toString(arr));
    }

    /*
     * 使用递归完成快速排序
     */
    private static void sort(int[] arr, int left, int right) {
        int l = left;
        int r = right - 1;
        // 取最右的数作为标准
        int stand = arr[right];

        while (l < r) {
            // 在左边一直找，直至找到大于或等于 stand 的值（索引）
            while (l <= r && arr[l] < stand) {
                l++;
            }

            // 在右边一直找，直至找到小于 stand 的值（索引）
            while (l <= r && arr[r] > stand) {
                r--;
            }

            // 如果 l >= r 成立，说明 stand 左右两侧的值已经分布完成
            if (l >= r) {
                Swap.swap(arr, l, right);
                break;
            }

            // 交换
            Swap.swap(arr, l, r);
        }

        System.out.println(Arrays.toString(arr) + " | " + left + " | " + right);

        if (l == r) {
            l++;
            r--;
        }

        if (left < r) {
            sort(arr, left, r);
        }

        if (right > left) {
            sort(arr, l, right);
        }
    }


    public static void sortAll(int[] arr, int left, int right) {
        if (left >= right) return;

        int p = sortOnce(arr, left, right);
        sortAll(arr, left, p - 1);
        sortAll(arr, p + 1, right);
    }


    public static int sortOnce(int[] arr, int left, int right) {
        int pivot = arr[right];

        int i = left;
        int j = right;

        while (i < j) {
            while (i < j && arr[i] < pivot) {
                i++;
            }

            while (i < j && arr[j] >= pivot) {
                j--;
            }

            Swap.swap(arr, i, j);
        }

        Swap.swap(arr, i, right);

        return i;
    }
}
