package com.sunk.datastructure.chapter12.kmp;

/**
 * 使用暴力匹配完成字符串匹配问题
 *
 * @author sunk
 * @since 2023/2/16
 */
public class ViolenceMatch {

    public static void main(String[] args) {
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";

        final int match = match(str1, str2);
        System.out.println(match);
    }

    public static int match(String str1, String str2) {
        final char[] chars1 = str1.toCharArray();
        final char[] chars2 = str2.toCharArray();

        final int length1 = chars1.length;
        final int length2 = chars2.length;

        // i 指向 chars1，j 指向 chars2
        int i = 0;
        int j = 0;

        // 进行匹配和回溯
        while (i < length1 && j < length2) {
            if (chars1[i] == chars2[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }

        // 判断是否匹配成功
        if (j == length2) {
            return i - j;
        }

        return -1;
    }

}
