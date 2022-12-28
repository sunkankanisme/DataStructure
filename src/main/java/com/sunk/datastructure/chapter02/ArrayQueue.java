package com.sunk.datastructure.chapter02;

import java.util.Arrays;

public class ArrayQueue {

    public static void main(String[] args) {
        final Queue queue = new Queue(3);
        queue.addQueue(1);
        queue.addQueue(2);
        queue.addQueue(3);
        queue.addQueue(4);
        queue.printQueue();
        final int queue1 = queue.getQueue();
        final int queue2 = queue.getQueue();
        final int queue3 = queue.getQueue();
        // final int queue4 = queue.getQueue();
        queue.printQueue();

    }

    static class Queue {
        // 队列的最大容量
        private int maxSize;

        // 队列头指针
        private int front;

        // 队列尾指针
        private int rear;

        // 存储队列元素的数组
        private int[] arr;

        /*
         * 初始化队列
         */
        public Queue(int arrMaxSize) {
            this.maxSize = arrMaxSize;
            arr = new int[maxSize];
            front = -1;
            rear = -1;
        }

        /*
         * 判断队列是否满
         */
        public boolean isFull() {
            return rear == maxSize - 1;
        }

        /*
         * 判断队列是否为空
         */
        public boolean isEmpty() {
            return rear == front;
        }

        /*
         * 添加数据到队列
         */
        public void addQueue(int n) {
            if (isFull()) {
                System.out.println("队列已满，无法加入数据");
                return;
            }

            rear++;
            arr[rear] = n;
        }

        /*
         * 从队列中获取数据
         */
        public int getQueue() {
            if (isEmpty()) {
                throw new RuntimeException("队列为空，无法获取数据");
            }

            front++;
            return arr[front];
        }

        /*
         * 显示队列的所有数据
         */
        public void printQueue() {
            System.out.println(Arrays.toString(arr));
        }

        /*
         * 显示队列的头数据
         */
        public int headQueue() {
            if (isEmpty()) {
                throw new RuntimeException("队列为空，没有数据");
            }

            return arr[front + 1];
        }
    }
}
