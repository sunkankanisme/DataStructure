package com.sunk.datastructure.chapter05;

/**
 * 使用数组模拟栈
 *
 * @author sunk
 * @since 2023/1/4
 **/
public class ArrayStack<T> {
    // 栈的大小
    private final int maxSize;
    // 数据存储的数组
    private final Object[] stack;
    // 指示栈顶的指针，初始化为 -1
    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new Object[maxSize];
    }

    /*
     * 判断栈满的方法
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /*
     * 判断栈空的方法
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /*
     * 入栈
     */
    public void push(T value) {
        // 先判断栈满
        if (isFull()) {
            System.out.printf("栈已满，数据 [%s] 插入失败\n", value);
            return;
        }

        // 指针上移，添加元素
        stack[++top] = value;
    }

    /*
     * 出栈
     */
    public T pop() {
        // 先判断栈是否空
        if (isEmpty()) {
            throw new RuntimeException("栈为空");
        }

        // 弹出元素，指针下移
        return (T) stack[top--];
    }

    /*
     * 遍历栈
     * - 遍历时需要从栈顶开始显示
     */
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
        }

        for (int i = top; i >= 0; i--) {
            System.out.printf("stack [%d] = %s\n", i, stack[i]);
        }
    }

    /*
     * 偷看栈顶的值
     */
    public T peek() {
        return (T) stack[top];
    }
}
