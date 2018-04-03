package cn.zhengjianglong.simple.search;

/**
 * 红黑树  简单版，没有泛型
 *
 * @author zhengjianglong
 * @since 2018-04-02
 */
public class RBTree {
    private class RBNode {
        private int value; // 值
        private RBNode left, right; // 指向子树的链接:左子树和右子树.
        private RBNode parent;
        boolean color;//由其父结点指向它的链接的颜色也就是结点颜色.

        public RBNode(int val) {
            this.value = val;
            left = nil; // 默认指向空
            right = nil; // 默认指向空节点
        }
    }

    // 空节点
    private static RBNode nil;

    {
        nil = new RBNode(0);
        nil.color = BLACK;
    }

    private RBNode root;
    // 红
    private static final boolean RED = false;
    // 黑
    private static final boolean BLACK = true;

    /**
     * 左旋
     *
     * @param node
     * @return
     */
    public RBNode leftRotate(RBNode node) {
        if (nil == node) {
            return nil;
        }

        RBNode rightNode = node.right;
        node.right = rightNode.left;

        // 设置父节点
        if (rightNode.left != nil) {
            rightNode.left.parent = node;
        }

        rightNode.parent = node.parent;
        // 为父节点的父节点设置左右节点
        if (node.parent == nil) {
            this.root = rightNode;
        } else if (node.parent.left == node) { // 如果是左节点
            node.parent.left = rightNode;
        } else {
            node.parent.right = rightNode;
        }

        rightNode.left = node;
        node.parent = rightNode;
        return rightNode;
    }

    /**
     * 右旋转
     *
     * @param node
     * @return
     */
    public RBNode rightRotate(RBNode node) {
        if (nil == node) {
            return nil;
        }

        RBNode leftNode = node.left;
        node.left = leftNode.right;

        leftNode.parent = node.parent;
        if (node.parent == nil) {
            this.root = leftNode;
        } else if (node.parent.left == node) {
            node.parent.left = leftNode;
        } else {
            node.parent.right = leftNode;
        }

        node.parent = leftNode; // 这个需要放在最后
        leftNode.right = node;
        return node;
    }

    /**
     * 插入
     *
     * @param val
     * @return
     */
    public void insert(int val) {
        RBNode z = new RBNode(val);

        RBNode y = nil; // 表示要插入节点的父节点
        RBNode x = root;  // 遍历节点

        /**
         * 寻找插入的位置
         */
        // 1. 将红黑树当作一颗二叉查找树，将节点添加到二叉查找树中。
        while (x != nil) {
            y = x;
            if (z.value < x.value) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        /**
         * 插入
         */
        z.parent = y;
        if (y == nil) {
            root = z;
        } else if (z.value < y.value) {
            y.left = z;
        } else {
            y.right = z;
        }

        // 指向nil
        z.left = nil;
        z.right = nil;
        z.color = RED;

        /**
         * 调整
         */
        insertFixUp(z);
    }

    /**
     * 调整颜色和结构，维护红黑树性质
     *
     * @param z
     */
    private void insertFixUp(RBNode z) {
        while (z.parent.color == RED) {
            if (z.parent == z.parent.parent.left) {
                // 父节点是祖父的左孩子
                RBNode y = z.parent.parent.right; // 叔叔节点
                if (y.color == RED) {
                    /**
                     * 情况1  z的叔叔节点是红色
                     * 通过变色来调整
                     */
                    y.color = BLACK;
                    z.parent.color = BLACK;
                    z.parent.parent.color = RED;

                    // 继续往上看看是否需要调整
                    z = z.parent.parent;
                } else if (z == z.parent.right) {
                    /**
                     * 情况2 z的叔叔节点是黑色，并且z是右孩子
                     * 进行左选择变为情况3
                     */
                    z = z.parent;
                    leftRotate(z);
                }
                /**
                 * 情况三
                 */
                z.parent.color = BLACK;
                z.parent.parent.color = RED;
                rightRotate(z.parent.parent);
            } else {
                // 若“z的父节点”是“z的祖父节点的右孩子”
                RBNode y = z.parent.parent.left;
                if (y.color == RED) {
                    // Case 1条件：叔叔节点是红色
                    y.color = BLACK;
                    z.parent.color = BLACK;
                    // 往上继续看看
                    z = z.parent.parent;
                } else if (z == z.parent.right) {
                    // case 2： 是左孩子节点
                    z = z.parent;
                    //!!! 这边是右旋
                    rightRotate(z);
                }

                // case3
                z.parent.parent.color = RED;
                z.parent.color = BLACK;
                // 左旋转
                leftRotate(z.parent.parent);
            }
        }
        root.color = BLACK;
    }


    /**
     * 删除节点
     * @param rmNode
     */
    private void remove(RBNode rmNode) {
        RBNode node = rmNode;
        // 原来的颜色
        RBNode replace;
        boolean color = rmNode.color;
        if (rmNode.left == nil) {
            // 左孩子不存在
            // 被删节点的后继节点。(称为"取代节点")
            // 用它来取代"被删节点"的位置，然后再将"被删节点"去掉。
            replace = rmNode.right;
            transplant(rmNode, replace);
        } else if (rmNode.right == nil) {
            replace = rmNode.left;
            transplant(rmNode, replace);
        } else {
            node = min(root.right);
            color = node.color;
            replace = node.right;
            if (node.parent == rmNode) {
                replace.parent = node;
            } else {
                // 用y的右节点替换y，  然后在用y在替换当前删除的节点
                transplant(node, replace);
                node.right = rmNode.right;
                node.right.parent = node;
            }

            // 替换节点
            transplant(rmNode, node);
            node.left = rmNode.left;
            node.left.parent = node;
            node.color = rmNode.color; // 一样的颜色
        }

        // 如果是黑色，则需要调整
        if (color == BLACK)
            deleteFixUp(replace);

    }

    /**
     * 调整
     * @param x
     */
    private void deleteFixUp(RBNode x) {
        while(x != root && x.color == BLACK) {
            if (x == x.parent.left ) {
                // 左孩子节点
                RBNode w = x.parent.right;
                /**
                 * case 1：兄弟节点的颜色为红色
                 */
                if (w.color == RED) {
                    x.parent.color = RED;
                    w.color = BLACK;
                    leftRotate(x.parent);
                    w = x.parent.right;
                }
                /**
                 * case 2 兄弟节点的颜色为黑色，并且其两个子节点颜色都为黑色
                 */
                if (w.left.color == BLACK && w.right.color == BLACK) {
                    w.color = RED;
                    x = x.parent;
                } else if (w.right.color ==  BLACK) {
                    /**
                     * case 3: x的兄弟节点w颜色为黑色，并且w的左结点是红色，右结点是黑色
                     */
                   w.color = RED;
                   w.left.color = BLACK;
                   rightRotate(w);
                   w = x.parent.right;
                 }

                /**
                 * case 4: x的兄弟节点w颜色是黑色，并且w的左节点是黑色，右孩子是红色
                 */
                x.parent.color = BLACK;
                w.color = RED;
                w.right.color = BLACK;
                leftRotate(x.parent);
                x = root;  // ?
            } else {
                // 是右孩子

                RBNode w = x.parent.left;
                if (w.color == RED) {
                    /**
                     * case 1: 兄弟节点颜色是红色
                     */
                    w.color = BLACK;
                    x.parent.color = RED;
                    rightRotate(x.parent);
                    w = x.parent.left;
                }

                if (w.left.color == BLACK && w.right.color == BLACK) {
                    /**
                     * case 2: 兄弟结点是黑色，且其两个子结点也是黑色
                     */
                    w.color = RED;
                    x = x.parent;
                } else if(x.left.color == BLACK) {
                    /**
                     * case 3：
                     */
                    w.color = RED;
                    w.left.color = BLACK;
                    leftRotate(w);
                    w = x.parent.left;
                }

                /**
                 * case 4
                 */
                x.parent.color = BLACK;
                w.color = RED;
                w.left.color = BLACK;
                rightRotate(x.parent);
                x = root;
            }
        }
    }


    /**
     * 调整的过程
     * @param parent 父节点
     * @param replace 被删除位置，新的替换元素
     */
    private void transplant(RBNode parent, RBNode replace) {
        if (parent.parent == nil) {
            root = replace;
        } else if (parent == parent.parent.left) {
            parent.parent.left = replace;
        } else {
            parent.parent.right = replace;
        }
    }

    /**
     * 最小元素
     * @param root
     * @return
     */
    private RBNode min(RBNode root) {
        if (root == nil) {
            return nil;
        }
        RBNode node = root;
        while (node.left != nil) {
            node = node.left;
        }
        return node;
    }

}
