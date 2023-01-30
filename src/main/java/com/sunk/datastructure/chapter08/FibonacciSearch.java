package com.sunk.datastructure.chapter08;

import java.util.Arrays;

/**
 * 斐波那契查找算法
 *
 * @author sunk
 * @since 2023/1/30
 **/
public class FibonacciSearch {
    private static final int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};

        System.out.println(find(arr, 8));
    }

    /*
     * 后面需要使用到斐波那契数列，这里先构建一个
     */
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;

        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }

        return f;
    }

    /*
     * 编写查找算法，使用非递归的方式
     */
    public static int find(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        // 表示斐波那契分割数值的坐标，对应 [ mid = low + F(k-1) -1 ] 中的 k
        int k = 0;
        int mid = 0;

        // 获取到斐波那契数列
        final int[] fib = fib();

        // 获取到下标
        while (high > fib[k] - 1) {
            k++;
        }

        /*
         * 因为 F[k] 的值可能大于 arr 的长度，因此需要构造一个新的数组
         * 并且需要使用 arr 数组最后的数据，填充新数组中空余的部分
         * {1, 8, 10, 89, 1000, 1234} -> {1, 8, 10, 89, 1000, 1234, 1234, 1234, 1234}
         */
        int[] temp = Arrays.copyOf(arr, fib[k]);
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[arr.length - 1];
        }

        System.out.println("DEBUG: " + Arrays.toString(arr));
        System.out.println("DEBUG: " + Arrays.toString(temp));

        // 使用 while 循环查找目标，其中 k 值得变化参考原理图
        while (low <= high) {
            mid = low + fib[k - 1] - 1;

            if (target < temp[mid]) {
                // 此时应该向 mid 前面查找
                high = mid - 1;

                /*
                 * k = k - 1 说明：
                 * 1，全部元素 = 前面的元素 + 后边元素
                 * 2，f[k] = f[k-1] + f[k-2]
                 *
                 * 因为前面有 f[k-1] 个元素，所以可以继续拆分 f[k-1] = f[k-2] + f[k-3]
                 * 即在 f[k-1] 的前面继续查找，下次循环时 mid = f[k-1-1] -1
                 */
                k = k - 1;
            } else if (target > temp[mid]) {
                // 此时应该向 mid 后面查找
                low = mid + 1;

                /*
                 * k = k - 2 说明：
                 * 1，全部元素 = 前面的元素 + 后边元素
                 * 2，f[k] = f[k-1] + f[k-2]
                 *
                 * 因为后面有 f[ k-2 ] 个元素，f[k-2] = f[k-3] + f[k-4]
                 * 即在 f[k-2] 的前面进行查找，下次循环 mid = f[k-1-2] -1
                 */
                k = k - 2;
            } else {
                // 找到，需要确定返回的是哪个下标，因为 temp 是扩充后的数组
                return Math.min(mid, high);
            }
        }

        return -1;
    }

}
