package com.sunk.datastructure.chapter10;

import lombok.Data;

/**
 * @author sunk
 * @since 2023/2/1
 **/
public class BinaryTree {

    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    /*
     * 前序遍历
     */
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        }
    }

    /*
     * 中序遍历
     */
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        }
    }

    /*
     * 后序遍历
     */
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        }
    }

    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        }

        return null;
    }

    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        }

        return null;
    }

    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        }

        return null;
    }

    /*
     * 删除节点
     */
    public void delNode(int no) {
        if (root != null) {
            if (root.no == no) {
                root = null;
            } else {
                root.delNode(no);
            }
        }
    }

    public void delNode2(int no) {
        if (root != null) {
            if (root.no == no) {
                if (root.left == null && root.right == null) {
                    root = null;
                } else if (root.left != null) {
                    root = root.left;
                } else {
                    root = root.right;
                }

            } else {
                root.delNode2(no);
            }
        }
    }

    /*
     * 创建节点对象
     */
    @Data
    static class HeroNode {
        private int no;
        private String name;
        private HeroNode left;
        private HeroNode right;

        public HeroNode(int no, String name) {
            this.no = no;
            this.name = name;
        }

        @Override
        public String toString() {
            return "HeroNode{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    '}';
        }

        /*
         * 前序遍历, 父左右
         */
        public void preOrder() {
            System.out.println(this);

            if (this.left != null) {
                this.left.preOrder();
            }

            if (this.right != null) {
                this.right.preOrder();
            }
        }

        /*
         * 中序遍历, 左父右
         */
        public void infixOrder() {
            if (this.left != null) {
                this.left.preOrder();
            }

            System.out.println(this);

            if (this.right != null) {
                this.right.preOrder();
            }
        }

        /*
         * 后序遍历, 左右父
         */
        public void postOrder() {
            if (this.left != null) {
                this.left.preOrder();
            }

            if (this.right != null) {
                this.right.preOrder();
            }

            System.out.println(this);
        }

        /*
         * 前序遍历查找
         */
        public HeroNode preOrderSearch(int no) {
            System.out.println("preOrderSearch");
            // 判断当前节点
            if (this.no == no) {
                return this;
            }

            // 左递归查找
            HeroNode tmpNode = null;
            if (this.left != null) {
                tmpNode = this.left.preOrderSearch(no);

                if (tmpNode != null) {
                    return tmpNode;
                }
            }

            // 右递归查找
            if (this.right != null) {
                tmpNode = this.right.preOrderSearch(no);
            }

            return tmpNode;
        }

        /*
         * 中序遍历查找
         */
        public HeroNode infixOrderSearch(int no) {
            HeroNode tmpNode = null;
            if (this.left != null) {
                tmpNode = this.left.infixOrderSearch(no);

                if (tmpNode != null) {
                    return tmpNode;
                }
            }

            System.out.println("infixOrderSearch");
            if (this.no == no) {
                return this;
            }

            if (this.right != null) {
                tmpNode = this.right.infixOrderSearch(no);
            }

            return tmpNode;
        }

        /*
         * 后序遍历查找
         */
        public HeroNode postOrderSearch(int no) {
            HeroNode tmpNode = null;
            if (this.left != null) {
                tmpNode = this.left.postOrderSearch(no);

                if (tmpNode != null) {
                    return tmpNode;
                }
            }

            if (this.right != null) {
                tmpNode = this.right.postOrderSearch(no);

                if (tmpNode != null) {
                    return tmpNode;
                }
            }

            System.out.println("postOrderSearch");
            if (this.no == no) {
                return this;
            }

            return null;
        }


        /*
         * 删除节点
         *
         * - 如果删除的节点是叶子节点，则删除该节点
         * - 如果删除的节点是非叶子节点，则删除该子树
         */
        public void delNode(int no) {
            if (this.left != null && this.left.no == no) {
                this.left = null;
                return;
            }

            if (this.right != null && this.right.no == no) {
                this.right = null;
                return;
            }

            if (this.left != null) {
                this.left.delNode(no);
            }

            if (this.right != null) {
                this.right.delNode(no);
            }
        }


        public void delNode2(int no) {
            if (this.left != null && this.left.no == no) {
                if (this.left.left == null && this.left.right == null) {
                    // 左右都为 null 则直接删除
                    this.left = null;
                } else if (this.left.left != null) {
                    // 优先选择左边继承父节点位置
                    // 此处包含左右都不为空或左不为空右为空
                    this.left = this.left.left;
                } else {
                    // 此处左为空右不为空使用右边继承父节点位置
                    this.left = this.left.right;
                }
            }

            if (this.right != null && this.right.no == no) {
                if (this.right.left == null && this.right.right == null) {
                    // 左右都为 null 则直接删除
                    this.right = null;
                } else if (this.right.left != null) {
                    // 优先选择左边继承父节点位置
                    // 此处包含左右都不为空或左不为空右为空
                    this.right = this.right.left;
                } else {
                    // 此处左为空右不为空使用右边继承父节点位置
                    this.right = this.right.right;
                }
            }

            if (this.left != null) {
                this.left.delNode2(no);
            }

            if (this.right != null) {
                this.right.delNode2(no);
            }

        }
    }

}
