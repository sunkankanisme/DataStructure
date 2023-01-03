package com.sunk.datastructure.chapter03;


public class ArrayCycleQueue {

    public static void main(String[] args) {
        final CycleQueue queue = new CycleQueue(5);
        queue.addQueue(1);
        queue.addQueue(2);
        queue.addQueue(3);
        queue.addQueue(4);
        queue.printQueue();
        System.out.println("============");
        final int queue1 = queue.getQueue();
        final int queue2 = queue.getQueue();
        final int queue3 = queue.getQueue();
        // final int queue4 = queue.getQueue();
        queue.printQueue();

    }

    static class CycleQueue {
        // 队列的最大容量
        private final int maxSize;

        // 队列头指针
        private int front;

        // 队列尾指针
        private int rear;

        // 存储队列元素的数组
        private final int[] arr;

        /*
         * 初始化队列
         */
        public CycleQueue(int arrMaxSize) {
            this.maxSize = arrMaxSize;
            arr = new int[maxSize];
            front = 0;
            rear = 0;
        }

        /*
         * 判断队列是否满
         */
        public boolean isFull() {
            return (rear + 1) % maxSize == front;
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

            arr[rear] = n;
            // 将 rear 后移，这里必须考虑取模
            rear = (rear + 1) % maxSize;
        }

        /*
         * 从队列中获取数据
         */
        public int getQueue() {
            if (isEmpty()) {
                throw new RuntimeException("队列为空，无法获取数据");
            }

            // 这里需要分析出 front 是指向队列的第一个元素
            // 1 先把 front 对应的值保存到临时变量
            // 2 将 front 后移，考虑取模
            // 3 将临时变量返回
            final int tmp = arr[front];
            front = (front + 1) % maxSize;
            return tmp;
        }

        /*
         * 显示队列的所有数据，从 front 开始遍历
         */
        public void printQueue() {
            for (int i = front; i < front + size(); i++) {
                System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
            }
        }

        /*
         * 显示当前队列的有效数据个数
         */
        public int size() {
            return (rear + maxSize - front) % maxSize;
        }

        /*
         * 显示队列的头数据
         */
        public int headQueue() {
            if (isEmpty()) {
                throw new RuntimeException("队列为空，没有数据");
            }

            return arr[front];
        }
    }
}
