package com.sunk.datastructure.chapter05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 实现逆波兰计算器
 *
 * @author sunk
 * @since 2023/1/5
 **/
public class PolandNotation {

    public static void main(String[] args) {
        // 1 先定义一个逆波兰表达式
        // (3 + 4) × 5 - 6 = 3 4 + 5 × 6 -
        String suffixExpression = "3 4 + 5 × 6 -";
        final int res = calculate(suffixExpression);
        System.out.println(res);
    }

    /*
     * 将逆波兰表达式的数据和运算符依次放到集合中
     */
    public static List<String> getListString(String suffixExpression) {
        final ArrayList<String> strings = new ArrayList<>();
        Collections.addAll(strings, suffixExpression.split(" "));
        return strings;
    }

    /*
     * 完成对逆波兰表达式的计算
     */
    public static int calculate(String suffixExpression) {
        final List<String> rpnList = getListString(suffixExpression);
        System.out.println(rpnList);

        // 创建一个栈
        final Stack<String> stack = new Stack<>();

        // 遍历集合
        for (String item : rpnList) {
            if (item.matches("[0-9]+")) {
                // 数字直接入栈
                stack.push(item);
            } else {
                // 操作符则从栈中弹出两个数运算后再入栈
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res = 0;

                switch (item) {
                    case "+":
                        res = num2 + num1;
                        break;
                    case "-":
                        res = num2 - num1;
                        break;
                    case "×":
                        res = num2 * num1;
                        break;
                    case "/":
                        res = num2 / num1;
                        break;
                    default:
                        System.out.println("操作符异常");
                        break;
                }

                stack.push(res + "");
            }
        }

        return Integer.parseInt(stack.pop());
    }

}
