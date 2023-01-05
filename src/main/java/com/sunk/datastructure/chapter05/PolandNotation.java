package com.sunk.datastructure.chapter05;

import java.util.*;

/**
 * 实现逆波兰计算器
 *
 * @author sunk
 * @since 2023/1/5
 **/
public class PolandNotation {

    public static void main(String[] args) {
        final int res = calculate("1+((2+3)*4)-5");
        System.out.println(res);
    }

    /*
     * 完成对逆波兰表达式的计算
     */
    public static int calculate(String middleExpression) {
        final List<String> rpnList = convertMiddleToSuffixList(middleExpression);

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
                    case "*":
                        res = num2 * num1;
                        break;
                    case "/":
                        res = num2 / num1;
                        break;
                    default:
                        System.out.println("操作符异常: [" + item + "]");
                        break;
                }

                stack.push(res + "");
            }
        }

        return Integer.parseInt(stack.pop());
    }


    /*
     * 将中缀表达式转后缀表达式对应集合的功能
     */
    public static List<String> convertMiddleToSuffixList(String middleExpression) {
        // 1 将中缀表达式拆解，并存入集合中
        final List<String> list = parseMiddleExpression(middleExpression);

        // 2 定义一个符号栈
        final Stack<String> s1 = new Stack<>();
        // 3 定义结果集合，因为 s2 栈中间没有 pop 操作，最后 pop 时候结果还要逆序，所以此处可以直接使用 list
        final ArrayList<String> s2 = new ArrayList<>();

        // 4 遍历中缀表达式集合
        for (String item : list) {
            if (item.matches("\\d+")) {
                // 如果是一个数字，则加入到 s2
                s2.add(item);
            } else if (item.equals("(")) {
                // 如果是左括号则直接入 s1 栈
                s1.push(item);
            } else if (item.equals(")")) {
                // 如果是右括号，则依次弹出 s1 栈顶的运算符，存入 s2 中，直到遇到左括号，此时将这一对括号消除
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }

                // 消除对应左括号
                s1.pop();
            } else {
                // 当 item 的优先级小于或等于 s1 栈顶的运算符
                // 将 s1 栈顶的运算符弹出并存入 s2 中，后续再继续比较，直到遇到其他情况
                while (s1.size() != 0 && !"(".equals(s1.peek()) && priority(item) <= priority(s1.peek())) {
                    s2.add(s1.pop());
                }

                // 将当前的运算符压入 s1
                s1.push(item);
            }
        }

        // 将 s1 中剩余的运算符加到 s2 中
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }

        return s2;
    }

    /*
     * 将中缀表达式元素拆解，并存入集合
     * - 多位数字的处理
     */
    public static List<String> parseMiddleExpression(String middleExpression) {
        final ArrayList<String> arrayList = new ArrayList<>();

        // 解析表达式，分解数字和字符串
        final char[] chars = middleExpression.replace(" ", "").toCharArray();

        // 用于存储数字的临时变量
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < chars.length; i++) {
            final String c = chars[i] + "";

            if (!c.matches("\\d")) {
                // 当前字符非数字，直接存入集合中
                arrayList.add(c);
            } else {
                // 当前字符是数字，则进行多位数字的处理
                builder.append(c);

                while (i + 1 < chars.length && (chars[i + 1] + "").matches("\\d")) {
                    builder.append(chars[++i]);
                }

                arrayList.add(builder.toString());
                builder = new StringBuilder();
            }
        }

        return arrayList;
    }

    /*
     * 运算符优先级
     */
    public static int priority(String oper) {
        if (oper.matches("[*|/]")) {
            return 1;
        } else if (oper.matches("[+|-]")) {
            return 0;
        } else {
            return -1;
        }
    }
}
