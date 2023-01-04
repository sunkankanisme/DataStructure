package com.sunk.datastructure.chapter05;

/**
 * @author sunk
 * @since 2023/1/4
 **/
public class Main {

    public static void main(String[] args) {
        final ArrayStack<String> stack = new ArrayStack<>(4);
        // 测试入栈
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        stack.push("e");
        stack.list();

        // 测试出栈
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.list();
    }

}
