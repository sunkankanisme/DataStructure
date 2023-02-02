package com.sunk.datastructure.chapter10;


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
}
