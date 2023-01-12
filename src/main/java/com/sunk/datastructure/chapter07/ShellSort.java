package com.sunk.datastructure.chapter07;

import java.util.Arrays;

/**
 * @author sunk
 * @since 2023/1/12
 **/
public class ShellSort {

    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        // sortSwitch(arr);
        sortMove(arr);
    }

    /*
     * 交换法希尔排序
     */
    private static void sortSwitch(int[] arr) {

        /*
         * 每一轮循环中，都根据 gap 的值进行分组，组内进行排序
         *
         * - gap = arr.length / 2：间隔初始值为数组长度除二
         * - gap > 0：只要间隔大于零，则继续进行下一次分组
         * - gap = gap / 2：间隔的更新，每次是上次的结果除二
         */
        for (int gap = arr.length / 2; gap > 0; gap = gap / 2) {
            for (int i = gap; i < arr.length; i++) {
                // 遍历各组中所有的元素，一共 gap 组，每组有 len / gap 个元素，步长是 -5
                // 循环初始值为 i - gap，后续比较 [j, j+gap] 的大小并交换
                for (int j = i - gap; j >= 0; j = j - gap) {
                    // 如果当前元素大于加上步长后的那个元素，说明需要交换
                    if (arr[j] > arr[j + gap]) {
                        Swap.swap(arr, j, j + gap);
                    }

                }
            }

            System.out.println(Arrays.toString(arr));
        }
    }

    /*
     * 移位法希尔排序
     */
    public static void sortMove(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap = gap / 2) {
            // 从第 gap 个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int index = i;
                int value = arr[index];

                if (arr[index] < arr[index - gap]) {
                    // 组内比较大小
                    while (index - gap >= 0 && value < arr[index - gap]) {
                        // 组内移动元素
                        arr[index] = arr[index - gap];

                        index -= gap;
                    }

                    // 当退出循环后，就给 value 找到了插入的位置
                    arr[index] = value;
                }
            }

            System.out.println(Arrays.toString(arr));
        }
    }

}
