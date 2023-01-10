package com.sunk.datastructure.chapter07;

import java.util.Arrays;

/**
 * @author sunk
 * @since 2023/1/10
 **/
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};
        sort(arr);
    }


    public static void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            // 定义待插入的数据，从数组中的第 2 个元素开始算
            int insertVal = arr[i];
            int insertIndex = i - 1;

            /*
             * 给 insertVal 找到需要插入的位置
             * 1. insertIndex >= 0 防止下标越界
             * 2. insertVal < arr[insertIndex] 表示待插入的数据还没有找到适当的位置
             *      需要将 arr[insertIndex] 后移
             * 3. 当退出循环时表示找到了插入的位置，为 index + 1
             */
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }

            // 优化：判断是否需要赋值
            if (insertIndex + 1 != i) arr[insertIndex + 1] = insertVal;
            System.out.println(Arrays.toString(arr));
        }
    }
}
