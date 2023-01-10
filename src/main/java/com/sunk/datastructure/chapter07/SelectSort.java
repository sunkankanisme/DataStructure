package com.sunk.datastructure.chapter07;

import java.util.Arrays;

/**
 * @author sunk
 * @since 2023/1/10
 **/
public class SelectSort {

    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};
        sort(arr);
    }

    public static void sort(int[] arr) {

        /*
         * 外层循环控制循环次数
         */
        for (int i = 0; i < arr.length - 1; i++) {
            /*
             * 内层循环，在 [i, arr.length) 的范围内查找最小元素，并与 i 交换
             * 初始情况任务当前轮的第一个元素（i）W为最小的
             */
            int minNumIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[minNumIndex]) {
                    minNumIndex = j;
                }
            }

            // 优化：当索引不一致的时候进行交换
            if (i != minNumIndex) Swap.swap(arr, i, minNumIndex);
            System.out.println(Arrays.toString(arr));
        }
    }
}
