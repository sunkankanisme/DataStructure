package com.sunk.datastructure.chapter12.kmp;

import java.util.Arrays;

/**
 * 使用 kmp 算法完成字符串匹配
 *
 * @author sunk
 * @since 2023/2/16
 */
public class KmpMatch {

    public static void main(String[] args) {
        String s1 = "BBC ABCDAB ABCDABCDABDE";
        String s2 = "ABCDABD";

        // 获取部分匹配表
        final int[] matchTable = getMatchTable(s2);
        System.out.println(Arrays.toString(matchTable));

        // 测试
        final int i = kmpMatch(s1, s2);
        System.out.println(i);
    }

    public static int kmpMatch(String s1, String s2) {
        // 根据目标字符串获得部分匹配表
        final int[] matchTable = getMatchTable(s2);

        // 遍历原字符串, i,j 分别指向 s1 和 s2
        for (int i = 0, j = 0; i < s1.length(); i++) {
            // TODO 这里不用调整 i 的大小 ！！！，与下面同样的问题，为什么是 j = matchTable[j - 1] ？？？
            // 需要处理 s1.charAt(i) ！= s2.charAt(j) 的情况，需要调整 j 的大小
            // BBC ABCDAB ABCDABCDABDE
            //     ABCDABD
            //            ABCDABD
            while (j > 0 && s1.charAt(i) != s2.charAt(j)) {
                j = matchTable[j - 1];
            }

            // 相等的情况下继续匹配
            if (s1.charAt(i) == s2.charAt(j)) {
                j++;
            }

            if (j == s2.length()) {
                return i - j + 1;
            }
        }

        return -1;
    }

    /**
     * 获取部分匹配值表
     *
     * @param s2 目标字符串，需要配置的字符串
     **/
    public static int[] getMatchTable(String s2) {
        final int[] next = new int[s2.length()];

        // 截止至第一个字符的部分匹配值必为 0
        next[0] = 0;

        // j 即表示匹配的索引用来查找重复的部分，又表示部分匹配值
        for (int i = 1, j = 0; i < s2.length(); i++) {
            // TODO 这里不太理解为什么从 next[j-1] 处取值，为啥不能直接重置为 0 呢，参考 https://www.bilibili.com/video/BV1Px411z7Yo/
            // 当两者不等时，需要从 next[j-1] 获取新的值
            // A B B B B B A B B B B C
            // 0 0 0 0 0 0 1 2 3 4 5 0
            // if (s2.charAt(i) != s2.charAt(j)) {
            //     j = 0;
            // }
            while (j > 0 && s2.charAt(i) != s2.charAt(j)) {
                j = next[j - 1];
            }

            // 满足此条件时，部分匹配值 +1
            if (s2.charAt(i) == s2.charAt(j)) {
                j++;
            }

            // System.out.println(i + " -> " + j);
            next[i] = j;
        }

        return next;
    }

}
