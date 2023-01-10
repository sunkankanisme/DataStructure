package com.sunk.datastructure.chapter07;

import java.util.Arrays;

import static com.sunk.datastructure.chapter07.Swap.swap;

/**
 * @author sunk
 * @since 2023/1/10
 **/
public class BubbleSort {

    public static void main(String[] args) {
        final int[] arr = {3, 9, -1, 10, -2};
        sort1(arr);
    }

    public static void sort1(int[] arr) {
        System.out.println(Arrays.toString(arr));

        /*
         * 代码结构
         *
         * 1. 外循环的次数为 arr.length - 1
         * 2. 内循环的次数为 arr.length - 1 - i
         * 3. 中间的逻辑为判断当前元素和下一个元素，如果大的在前面则交换
         */
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 如果前面的数比后面的大则进行交换
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }

            System.out.println(Arrays.toString(arr));
        }
    }


    public static void sort2(int[] arr) {
        System.out.println(Arrays.toString(arr));

        // 标识这一趟是否进行了交换
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 如果前面的数比后面的大则进行交换
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    flag = true;
                }
            }

            if (!flag) {
                // 说明没有发生交换，跳出循环
                break;
            } else {
                // 交换了元素后重置 flag
                flag = false;
            }

            System.out.println(Arrays.toString(arr));
        }
    }
}
