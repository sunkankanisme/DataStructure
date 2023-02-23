package com.sunk.datastructure.chapter12.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 实现马踏棋盘（骑士周游）算法
 *
 * @author sunk
 * @since 2023/2/23
 */
public class HorseChessboard {

    // 棋盘的列数
    private static int x;
    // 棋盘的行数
    private static int y;
    // 棋盘，上面的数字表示当前位置在第几步被访问
    private static int[][] chessboard;
    // 标记是否棋盘的所有位置都被访问过了
    private static boolean isFinished;

    public static void main(String[] args) {
        // 初始化棋盘
        x = 8;
        y = 8;
        chessboard = new int[x][y];
        printChessboard();

        // 开始周游
        // 优化前：花费时间：73.541 秒
        // 优化后：花费时间：0.009 秒
        final long start = System.currentTimeMillis();
        run(new Point(4, 1), 1);
        final long end = System.currentTimeMillis();
        System.out.println("花费时间：" + (end - start) / 1000D + " 秒");

        // 打印棋盘
        printChessboard();

    }

    /**
     * 完成骑士周游问题的算法
     *
     * @param chessboard 棋盘，算法过程中会对棋盘进行标记是第几步踩到哪一格
     * @param current    骑士当前位置
     * @param step       现在是第几步，初始应为 1
     **/
    public static void run(Point current, int step) {
        // !!! 注意：操作棋盘时，需要传入的 x，y 逆转，因为二维数组的特性
        chessboard[current.y][current.x] = step;

        // 获取当前点的下一个点的集合
        final ArrayList<Point> next = next(current);
        sort(next);

        while (!next.isEmpty()) {
            // 取出一个可以走的位置，同时需要满足未访问
            final Point point = next.remove(0);

            // !!! 注意：操作棋盘时，需要传入的 x，y 逆转，因为二维数组的特性
            if (chessboard[point.y][point.x] == 0) {
                run(new Point(point.x, point.y), step + 1);
            }
        }

        /*
         * 判断马儿是否完成了任务，如果没有访问成功则进行回溯（将当前点恢复为原始状态）
         *
         * - 说明：step < x*y 成立分为两种情况
         * - 1 棋盘在目前的位置，还没有走完
         * - 2 棋盘处于一个回溯的过程
         */
        if (step < x * y && !isFinished) {
            chessboard[current.y][current.x] = 0;
        } else {
            isFinished = true;
        }
    }

    /**
     * 根据当前的位置，计算马儿当前还能走哪些位置
     *
     * @param currentPoint 当前的位置
     **/
    public static ArrayList<Point> next(Point currentPoint) {
        final ArrayList<Point> points = new ArrayList<>();

        final Point tmpPoint = new Point();

        /*
         * 判断临近的 8 个点能否走通，以 5 号点为例
         *
         * - [ currentPoint.x - 2 ] >= 0 : 马儿向左移动2列，是可以走通的
         * - [ currentPoint.y - 1 ] >= 0 : 马儿向上移动2格，是可以走通的
         * 此时代表马儿的左上角的点（5号点）是可以走通的
         */

        // 5
        if ((tmpPoint.x = currentPoint.x - 2) >= 0 && (tmpPoint.y = currentPoint.y - 1) >= 0) {
            points.add(new Point(tmpPoint));
        }

        // 6
        if ((tmpPoint.x = currentPoint.x - 1) >= 0 && (tmpPoint.y = currentPoint.y - 2) >= 0) {
            points.add(new Point(tmpPoint));
        }

        // 7
        if ((tmpPoint.x = currentPoint.x + 1) < x && (tmpPoint.y = currentPoint.y - 2) >= 0) {
            points.add(new Point(tmpPoint));
        }

        // 0
        if ((tmpPoint.x = currentPoint.x + 2) < x && (tmpPoint.y = currentPoint.y - 1) >= 0) {
            points.add(new Point(tmpPoint));
        }

        // 1
        if ((tmpPoint.x = currentPoint.x + 2) < x && (tmpPoint.y = currentPoint.y + 1) < y) {
            points.add(new Point(tmpPoint));
        }

        // 2
        if ((tmpPoint.x = currentPoint.x + 1) < x && (tmpPoint.y = currentPoint.y + 2) < y) {
            points.add(new Point(tmpPoint));
        }

        // 3
        if ((tmpPoint.x = currentPoint.x - 1) >= 0 && (tmpPoint.y = currentPoint.y + 2) < y) {
            points.add(new Point(tmpPoint));
        }

        // 4
        if ((tmpPoint.x = currentPoint.x - 2) >= 0 && (tmpPoint.y = currentPoint.y + 1) < y) {
            points.add(new Point(tmpPoint));
        }

        return points;
    }

    /**
     * 对【下一步的点的集合】进行排序，规则为查看每个下一步点的下一步，按照下下一步位置的个数进行非递减排序
     *
     * @param next 下一步的点的集合
     **/
    public static void sort(List<Point> next) {
        next.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                // 获取 o1 的下一步的所有位置个数
                final int o1NextSize = next(o1).size();
                // 获取 o2 的下一步的所有位置个数
                final int o2NextSize = next(o2).size();
                return o1NextSize - o2NextSize;
            }
        });
    }

    private static void printChessboard() {
        if (chessboard != null) {
            for (int[] ints : chessboard) {
                for (int anInt : ints) {
                    System.out.printf("%2s\t", anInt);
                }
                System.out.println();
            }

            System.out.println();
        }
    }

}
