package com.sunk.datastructure.chapter07;

import java.util.Arrays;

/**
 * @author sunk
 * @since 2023/1/19
 **/
public class RadixSort {

    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
        sort(arr);
    }

    public static void sort(int[] arr) {

        // 1 得到数组中最大的数的位数
        int max = arr[0];

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        // 得到最大的位数
        final int length = (max + "").length();

        // 2 使用循环进行排序
        for (int i = 0, n = 1; i < length; i++, n = n * 10) {
            // 第一轮排序，针对每个元素的个位进行处理
            // 定义一个二维数组，表示 10 个桶，每个桶都是一个一维数组，为了防止放入的数据
            int[][] bucket = new int[10][arr.length];

            // 为了记录每个桶中实际存放了多少个数据，定义一个数组记录各个桶每次放入的数据个数
            int[] bucketElementCounts = new int[10];

            for (int j = 0; j < arr.length; j++) {
                // 取出各个元素对应的位数进行排序：个，十，百，千，万
                int digitOfElement = arr[j] / n % 10;

                // 按照个位数字放入各个桶中
                final int count = bucketElementCounts[digitOfElement];
                bucket[digitOfElement][count] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }

            // 按照桶的顺序，依次取出元素，放回原来的数组
            int index = 0;
            for (int k = 0; k < bucket.length; k++) {
                // 如果桶中有数据，才放回到原数组，循环该桶的元素
                if (bucketElementCounts[k] != 0) {
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        arr[index++] = bucket[k][l];
                    }
                }

                // 将桶元素计数器清零
                bucketElementCounts[k] = 0;
            }

            System.out.println(Arrays.toString(arr));
        }
    }
}
