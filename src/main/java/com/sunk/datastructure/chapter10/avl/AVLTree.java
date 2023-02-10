package com.sunk.datastructure.chapter10.avl;


public class AVLTree {

    /*
     * 创建 AVL 树的方法
     */
    private Node root;


    /*
     * 添加元素
     */
    public void add(int value) {
        if (root == null) {
            this.root = new Node(value);
        } else {
            root.add(new Node(value));
        }
    }

    /*
     * 中序遍历
     */
    public void infixOrder() {
        if (this.root == null) {
            return;
        }

        root.infixOrder();
    }

    /*
     * 查找节点
     */
    public Node search(int value) {
        if (root == null) {
            return null;
        }

        return root.search(value);
    }

    /*
     * 查找节点父节点
     */
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        }

        return root.searchParent(value);
    }

    /*
     * 删除节点
     */
    public void deleteNode(int value) {
        if (root == null) {
            return;
        }

        // 查找要删除的节点
        final Node targetNode = search(value);
        // 没有找到要删除的节点
        if (targetNode == null) {
            return;
        }

        // 查找要删除的节点的父节点，TODO 这里有问题，相当于直接把整棵树置空了
        final Node parentNode = searchParent(value);
        // 没有找到目标节点的父节点
        if (parentNode == null) {
            root = null;
            return;
        }

        /*
         * 第一种情况：要删除的节点是叶子节点
         */
        if (targetNode.left == null && targetNode.right == null) {
            // 判断 targetNode 是 parentNode 的 左/右 子节点，并分别置空删除
            if (parentNode.left != null && parentNode.left.value == targetNode.value) {
                parentNode.left = null;
            } else if (parentNode.right != null && parentNode.right.value == targetNode.value) {
                parentNode.right = null;
            }
        }

        /*
         * 第三种情况：删除的节点有左右子树
         */
        else if (targetNode.left != null && targetNode.right != null) {
            // 找到右子树的最小节点（或左子树的最大节点）
            targetNode.value = deleteRightTreeMin(targetNode.right);
        }

        /*
         * 第二种情况：删除的节点是单子树节点
         */
        else {
            // 1 如果目标节点节点存在左子节点
            if (targetNode.left != null) {
                // 1.1 目标节点是其父节点的左子节点
                if (parentNode.left.value == targetNode.value) {
                    parentNode.left = targetNode.left;
                }
                // 1.2 目标节点是其父节点的右子节点
                else {
                    parentNode.right = targetNode.left;
                }
            }
            // 2 如果目标节点节点存在右子节点
            else {
                // 2.1 目标节点是其父节点的左子节点
                if (parentNode.left.value == targetNode.value) {
                    parentNode.left = targetNode.right;
                }
                // 2.2 目标节点是其父节点的右子节点
                else {
                    parentNode.right = targetNode.right;
                }
            }
        }
    }

    /**
     * 从传入的二叉排序树中找到最小节点的值，同时删除最小的节点
     *
     * @param node 传入的节点
     **/
    private int deleteRightTreeMin(Node node) {
        Node target = node;

        // 循环的查找左节点，就会找到最小值
        while (target.left != null) {
            target = target.left;
        }

        // 这时 target 指向了最小值，删除最小的节点
        deleteNode(target.value);

        return target.value;
    }

    public Node getRoot() {
        return root;
    }

    /*
     * 内部节点类
     */
    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }

        /*
         * 右旋转
         */
        private void rightRotate() {
            // 1 以当前节点的值，创建新的节点
            final Node newNode = new Node(value);

            // 2 将新节点的右子节点指向当前节点的右子节点
            newNode.right = this.right;

            // 3 将新节点的左子节点指向当前节点的左子节点的右子节点
            newNode.left = this.left.right;

            // 4 将当前节点的值，修改为其左子节点的值
            this.value = this.left.value;

            // 5 将当前节点的左子节点指向，当前节点的左子节点的左子节点
            this.left = this.left.left;

            // 6 将当前节点的右子节点指向新节点
            this.right = newNode;
        }

        /*
         * 左旋转
         */
        private void leftRotate() {
            // 1 以当前节点的值，创建新的节点
            final Node newNode = new Node(value);

            // 2 将新节点的左子树设置为当前节点的左子树
            newNode.left = this.left;

            // 3 将新节点的右子树设置为当前节点的右子树的左子树
            newNode.right = this.right.left;

            // 4 将当前节点的值替换成右子节点的值
            this.value = this.right.value;

            // 5 把当前节点的右子树设置为右子节点的右子树
            this.right = this.right.right;

            // 6 将当前节点的左子节点设置为新节点
            this.left = newNode;
        }


        /*
         * 返回当前以当前节点为根节点的树高度
         */
        public int height() {
            // 这一行每一个节点递归时都会 + 1，已达到统计高度的目的
            return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
        }

        /*
         * 返回左子树的高度
         */
        public int leftHeight() {
            if (left == null) {
                return 0;
            }

            return left.height();
        }

        /*
         * 返回右子树的高度
         */
        public int rightHeight() {
            if (right == null) {
                return 0;
            }

            return right.height();
        }


        /*
         * 添加节点
         * - 递归的形式添加节点
         */
        public void add(Node node) {
            if (node == null) {
                return;
            }

            // 判断传入的节点的值，与当前子树的根节点的值得关系
            if (node.value < this.value) {
                if (this.left == null) {
                    // 当前节点的左子节点为空，直接挂载在左边
                    this.left = node;
                } else {
                    // 递归的向左子节点添加
                    this.left.add(node);
                }
            } else {
                if (this.right == null) {
                    this.right = node;
                } else {
                    // 递归的向右子节点添加
                    this.right.add(node);
                }
            }

            // 当添加完节点后，如果 右子树的高度 - 左子树高度 > 1，则进行左旋转
            if (this.rightHeight() - this.leftHeight() > 1) {
                // 符合左旋的情况下，如果它的右子树的右子树高度大于它的右子树的左子树高度，name先对它的右子树进行右旋转，再对当前节点进行左旋转
                if (this.right != null && this.right.rightHeight() < this.right.leftHeight()) {
                    // 先对右子树进行旋转
                    this.right.rightRotate();
                }

                leftRotate();
                return;
            }

            // 当添加完节点后，如果 左子树的高度 - 右子树高度 > 1，则进行右旋转
            if (this.leftHeight() - this.rightHeight() > 1) {
                // 如果它的左子树的右子树高度大于它的左子树的左子树高度，先对当前这个结点的左节点进行左旋转，再对当前结点进行右旋转的操作即可
                if (this.left != null && this.left.rightHeight() > this.left.leftHeight()) {
                    this.left.leftRotate();
                }

                rightRotate();
            }

        }

        /*
         * 中序遍历二叉树
         */
        public void infixOrder() {
            if (this.left != null) {
                this.left.infixOrder();
            }

            System.out.println(this);

            if (this.right != null) {
                this.right.infixOrder();
            }
        }


        /*
         * 查找指定节点
         */
        public Node search(int value) {
            if (value == this.value) {
                return this;
            } else if (value < this.value && this.left != null) {
                // 向左子树递归查找
                return this.left.search(value);
            } else if (value > this.value && this.right != null) {
                // 向右子树递归查找
                return this.right.search(value);
            }

            return null;
        }

        /*
         * 查找指定节点父节点方法
         */
        public Node searchParent(int value) {
            if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
                return this;
            } else {
                if (value < this.value && this.left != null) {
                    // 如果查找的值小于当前节点的值，并且当前节点的左子节点不为空，向左子树递归查找
                    return this.left.searchParent(value);
                } else if (value >= this.value && this.right != null) {
                    // 向右子树递归查找
                    return this.right.searchParent(value);
                } else {
                    // 到此处表示找不到，要删除的节点没有父节点
                    return null;
                }
            }
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }

}
