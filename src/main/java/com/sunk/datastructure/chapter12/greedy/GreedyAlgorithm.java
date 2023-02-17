package com.sunk.datastructure.chapter12.greedy;

import java.util.*;

/**
 * 使用贪心算法完成集合覆盖问题
 *
 * @author sunk
 * @since 2023/2/17
 */
public class GreedyAlgorithm {

    public static void main(String[] args) {
        // 创建电台集合
        final HashMap<String, HashSet<String>> broadcastMap = new HashMap<>();
        final HashSet<String> s1 = new HashSet<>();
        s1.add("北京");
        s1.add("上海");
        s1.add("天津");
        broadcastMap.put("K1", s1);

        final HashSet<String> s2 = new HashSet<>();
        s2.add("广州");
        s2.add("北京");
        s2.add("深圳");
        broadcastMap.put("K2", s2);

        final HashSet<String> s3 = new HashSet<>();
        s3.add("成都");
        s3.add("上海");
        s3.add("杭州");
        broadcastMap.put("K3", s3);

        final HashSet<String> s4 = new HashSet<>();
        s4.add("上海");
        s4.add("天津");
        broadcastMap.put("K4", s4);

        final HashSet<String> s5 = new HashSet<>();
        s5.add("杭州");
        s5.add("大连");
        broadcastMap.put("K5", s5);

        // 未覆盖的地区集合
        final HashSet<String> unBroadAreas = new HashSet<>();
        broadcastMap.forEach((k, v) -> unBroadAreas.addAll(v));
        System.out.println(unBroadAreas);

        // 创建集合存放当前已经选择的电台集合
        final ArrayList<String> selects = new ArrayList<>();

        while (!unBroadAreas.isEmpty()) {
            System.out.println("\nCURRENT UN BROAD: " + unBroadAreas);
            int maxJoinSetNum = 0;
            String maxJoinSetName = "";

            // 遍历所有的集合，取出当前的最优解
            for (Map.Entry<String, HashSet<String>> entry : broadcastMap.entrySet()) {
                final int joinNum = getSetJoinNum(unBroadAreas, entry.getValue());
                System.out.println("--- " + entry.getKey() + " -> " + joinNum);

                // 与未覆盖地区交集最多的集合即是当前最优解
                if (joinNum > maxJoinSetNum) {
                    maxJoinSetNum = joinNum;
                    maxJoinSetName = entry.getKey();
                }
            }

            // 将当前最优解存入集合
            System.out.println("SELECT: " + maxJoinSetNum + " | " + maxJoinSetName);
            selects.add(maxJoinSetName);

            // 更新未覆盖地区集合
            for (String s : broadcastMap.get(maxJoinSetName)) {
                unBroadAreas.remove(s);
            }
        }

        System.out.println(selects);
    }

    // 获取两个集合共同元素个数的方法
    public static int getSetJoinNum(Set<String> s1, Set<String> s2) {
        final HashSet<String> tmpSet = new HashSet<>(s2);
        // 获取当前集合与指定集合的交集，会覆盖原集合元素
        tmpSet.retainAll(s1);
        return tmpSet.size();
    }
}
