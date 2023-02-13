package com.sunk.datastructure.chapter10;

import java.util.Arrays;

import static com.sunk.datastructure.chapter07.Swap.swap;

/**
 * @author sunk
 * @since 2023/2/3
 **/
public class HeapSort {

    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9, 20};
        // int[] arr = new int[100000000];
        // for (int i = 0; i < arr.length; i++) {
        //     arr[i] = (int) (Math.random() * 8000000);
        // }

        final long startSecond = System.currentTimeMillis() / 1000;
        sort(arr);
        final long endSecond = System.currentTimeMillis() / 1000;

        System.out.println(endSecond - startSecond);
    }

    /**
     * 堆排序
     * <p>
     * 升序排序，构建大顶堆
     *
     * @param arr 输入数据数组
     **/
    public static void sort(int[] arr) {
        /*
         * 1 将传入的数组生成一个大顶堆
         * - 从最后一个非叶子结点开始，依次进行调整
         */
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjust(arr, i, arr.length);
        }

        /*
         * 2 将堆顶元素与末尾元素进行交换，将最大值沉到底
         * 3 调整数组范围，继续生成大顶堆，获取后续的最大元素，共生成 size - 1 次
         */
        for (int i = arr.length - 1; i > 0; i--) {
            // 交换
            swap(arr, 0, i);

            // TODO: 再进行调整，这里为什么只要一轮呢
            adjust(arr, 0, i);
        }

    }


    /**
     * 将传入的顺序二叉树（数组）调整成一个大顶堆
     *
     * @param arr 顺序二叉树
     * @param i   表示本轮调整非叶子节点的位置
     * @param len 对前多少个元素进行调整
     **/
    public static void adjust(int[] arr, int i, int len) {
        System.out.print("ADJUST [" + i + "][" + len + "]:\t" + Arrays.toString(Arrays.copyOf(arr, len)) + " -> ");

        // 先取出当前元素的值
        int temp = arr[i];

        // 以 i 为父节点的左子节点 k，循环变量 k 后续变为为 k 的左子节点
        for (int k = i * 2 + 1; k < len; k = k * 2 + 1) {
            // 左子节点的值小于右子节点的值
            if (k + 1 < len && arr[k] < arr[k + 1]) {
                // 将 k 指向右子节点
                k = k + 1;
            }

            // 将大的子节点升级为父节点
            if (arr[k] > temp) {
                // 将较大的值作为当前节点
                arr[i] = arr[k];

                // 将 i 指向 k 执行下一轮循环
                i = k;
            } else {
                break;
            }
        }

        // 当 for 循环结束之后，我们已经将以 i 为父节点的树整理为大顶堆（最大值在最顶）
        // 将 temp 放到调整后的 i 的位置
        arr[i] = temp;

        System.out.println("\t" + Arrays.toString(Arrays.copyOf(arr, len)));
    }

}
