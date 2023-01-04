package com.sunk.datastructure.chapter05;

/**
 * @author sunk
 * @since 2023/1/4
 **/
public class Calculator {

    public static void main(String[] args) {
        // 表达式
        // 1 + 2 * 2 * 3 - 2 = ？
        // String expression = "300 / 10 + 3 * 2 * 6 - 20";
        String expression = "2 * 3 + 3 * 2 - 20 * 2";

        // 创建两个栈
        final ArrayStack<Integer> numStack = new ArrayStack<>(10);
        final ArrayStack<Character> operStack = new ArrayStack<>(10);

        // 定义临时变量
        int num1, num2, res;

        // 定义运算符变量
        char oper;

        // 定义用于拼接多位数字的字符串
        StringBuilder keepNum = new StringBuilder();

        // 开始循环扫描表达式，依次得到 expression 的字符
        final char[] chars = expression.replace(" ", "").toCharArray();
        for (int i = 0; i < chars.length; i++) {
            final char currentChar = chars[i];
            // 判断当前字符是什么，然后做处理
            if (isOper(currentChar)) {
                // 是运算符的话，判断当前符号栈是否为空
                if (operStack.isEmpty()) {
                    // 为空，当前运算符直接入栈
                    operStack.push(currentChar);
                } else {
                    // 不为空，判断优先级
                    if (priority(currentChar) <= priority(operStack.peek())) {
                        // 当前的符号优先级低于栈顶符号优先级，则触发计算逻辑
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = cal(num1, num2, oper);
                        // 将结果入数栈
                        numStack.push(res);
                        // 将当前操作符入符号栈
                        operStack.push(currentChar);
                    } else {
                        // 当前符号优先级高于栈顶符号优先级，则直接入栈
                        operStack.push(currentChar);
                    }
                }
            } else {
                // 当前字符是数字，直接入数栈
                // - 当处理多位数时，不能发现一个数字位就直接入栈，发现数字之后要继续扫描后面的
                keepNum.append(currentChar);
                while (i + 1 < chars.length && !isOper(chars[i + 1])) {
                    keepNum.append(chars[++i]);
                }

                numStack.push(Integer.valueOf(keepNum.toString()));
                keepNum = new StringBuilder();
            }
        }

        // 处理剩下的符号和数字
        while (!operStack.isEmpty()) {
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = cal(num1, num2, oper);
            numStack.push(res);
        }

        // 数栈中的最后一个就是结果
        System.out.printf("%s = %d\n", expression, numStack.pop());
    }

    /*
     * 返回运算符的优先级
     * - 优先级使用数字表示，数字越大优先级越高
     * - 假定表达式仅包含加减乘除
     */
    public static int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    /*
     * 判断是否是运算符
     */
    public static boolean isOper(char val) {
        return val == '+'
                || val == '-'
                || val == '*'
                || val == '/';
    }

    /*
     * 计算方法
     * - 注意操作顺序
     */
    public static int cal(int num1, int num2, char oper) {
        int res = 0;

        switch (oper) {
            case '+':
                res = num2 + num1;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num2 * num1;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }

        return res;
    }
}
