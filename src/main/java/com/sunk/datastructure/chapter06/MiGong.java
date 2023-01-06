package com.sunk.datastructure.chapter06;

/**
 * @author sunk
 * @since 2023/1/6
 **/
public class MiGong {

    public static void main(String[] args) {
        // 创建二维数组模拟迷宫，1 表示墙，0 表示可以走
        final int[][] map = initMap();

        // 使用递归来寻路
        final boolean b = setWay(map, 1, 1, 4, 1);
        System.out.println(b);

        // 最终打印 map
        printMap(map);

    }

    public static int[][] initMap() {
        int rows = 8;
        int cols = 7;
        final int[][] map = new int[rows][cols];

        // 上下加墙
        for (int i = 0; i < cols; i++) {
            map[0][i] = 1;
            map[rows - 1][i] = 1;
        }

        // 左右加墙
        for (int i = 0; i < rows; i++) {
            map[i][0] = 1;
            map[i][cols - 1] = 1;
        }

        // 中间加上自定义挡板
        map[3][1] = 1;
        map[3][2] = 1;

        // 打印迷宫
        System.out.println("初始化迷宫");
        printMap(map);

        return map;
    }

    public static void printMap(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }


    /**
     * 使用递归回溯来实现寻路
     * <p>在走迷宫的时候的策略 下 -> 右 -> 上 -> 左，如果该点走不通再回溯
     *
     * <p>- 当 map[i][j] = 0 表示该点还没有走过
     * <p>- 当 map[i][j] = 1 表示是墙，无法通行
     * <p>- 当 map[i][j] = 2 表示通路可以走
     * <p>- 当 map[i][j] = 3 表示该位置已经走过，但是走不通
     *
     * @param map 地图
     * @param i   初始坐标
     * @param j   初始坐标
     * @param x   目标坐标
     * @param y   目标坐标
     * @return 返回是否找到了路
     */
    public static boolean setWay(int[][] map, int i, int j, int x, int y) {
        System.out.println("=== " + i + " | " + j);

        if (map[x][y] == 2) {
            return true;
        } else {
            // 如果当前点还没有走过，则按照策略走
            if (map[i][j] == 0) {
                // 假定该点可以走通
                map[i][j] = 2;

                // 按策略 下 -> 右 -> 上 -> 左 依次尝试
                if (setWay(map, i + 1, j, x, y)) {
                    return true;
                } else if (setWay(map, i, j + 1, x, y)) {
                    return true;
                } else if (setWay(map, i - 1, j, x, y)) {
                    return true;
                } else if (setWay(map, i, j - 1, x, y)) {
                    return true;
                } else {
                    // 说明该点是走不通的是思路
                    map[i][j] = 3;
                    return false;
                }
            } else {
                // 如果该点不等于 0，则可能是 1,2,3
                return false;
            }
        }
    }

}
