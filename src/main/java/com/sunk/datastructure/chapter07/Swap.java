package com.sunk.datastructure.chapter07;

/**
 * @author sunk
 * @since 2023/1/10
 **/
public class Swap {

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
