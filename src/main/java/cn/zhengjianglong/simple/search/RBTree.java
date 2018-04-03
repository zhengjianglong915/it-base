package cn.zhengjianglong.simple.search;

/**
 * �����  �򵥰棬û�з���
 *
 * @author zhengjianglong
 * @since 2018-04-02
 */
public class RBTree {
    private class RBNode {
        private int value; // ֵ
        private RBNode left, right; // ָ������������:��������������.
        private RBNode parent;
        boolean color;//���丸���ָ���������ӵ���ɫҲ���ǽ����ɫ.

        public RBNode(int val) {
            this.value = val;
        }
    }

    private RBNode nil;

    {
        nil = new RBNode(0);
        nil.color = BLACK;
    }

    private RBNode root;
    // ��
    private static final boolean RED = false;
    // ��
    private static final boolean BLACK = true;

    /**
     * ����
     *
     * @param node
     * @return
     */
    public RBNode leftRotate(RBNode node) {
        if (null == node) {
            return null;
        }

        RBNode rightNode = node.right;
        node.right = rightNode.left;

        // ���ø��ڵ�
        if (rightNode.left != null) {
            rightNode.left.parent = node;
        }

        rightNode.parent = node.parent;
        // Ϊ���ڵ�ĸ��ڵ��������ҽڵ�
        if (node.parent == null) {
            this.root = rightNode;
        } else if (node.parent.left == node) { // �������ڵ�
            node.parent.left = rightNode;
        } else {
            node.parent.right = rightNode;
        }

        rightNode.left = node;
        node.parent = rightNode;
        return rightNode;
    }

    /**
     * ����ת
     *
     * @param node
     * @return
     */
    public RBNode rightRotate(RBNode node) {
        if (null == node) {
            return null;
        }

        RBNode leftNode = node.left;
        node.left = leftNode.right;

        leftNode.parent = node.parent;
        if (node.parent == null) {
            this.root = leftNode;
        } else if (node.parent.left == node) {
            node.parent.left = leftNode;
        } else {
            node.parent.right = leftNode;
        }

        node.parent = leftNode; // �����Ҫ�������
        leftNode.right = node;
        return node;
    }

    /**
     * ����
     *
     * @param val
     * @return
     */
    public void insert(int val) {
        RBNode z = new RBNode(val);

        RBNode y = null; // ��ʾҪ����ڵ�ĸ��ڵ�
        RBNode x = root;  // �����ڵ�

        /**
         * Ѱ�Ҳ����λ��
         */
        // 1. �����������һ�Ŷ�������������ڵ���ӵ�����������С�
        while (x != null) {
            y = x;
            if (z.value < x.value) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        /**
         * ����
         */
        z.parent = y;
        if (y == null) {
            root = z;
        } else if (z.value < y.value) {
            y.left = z;
        } else {
            y.right = z;
        }

        // Ĭ�Ͼ���null
        z.left = null;
        z.right = null;

        z.color = RED;

        /**
         * ����
         */
        insertFixUp(z);
    }

    /**
     * ������ɫ�ͽṹ��ά�����������
     *
     * @param z
     */
    private void insertFixUp(RBNode z) {
        while (z.parent.color == RED) {
            if (z.parent == z.parent.parent.left) {
                // ���ڵ����游������
                RBNode y = z.parent.parent.right; // ����ڵ�
                if (y.color == RED) {
                    /**
                     * ���1  z������ڵ��Ǻ�ɫ
                     * ͨ����ɫ������
                     */
                    y.color = BLACK;
                    z.parent.color = BLACK;
                    z.parent.parent.color = RED;

                    // �������Ͽ����Ƿ���Ҫ����
                    z = z.parent.parent;
                } else if (z == z.parent.right) {
                    /**
                     * ���2 z������ڵ��Ǻ�ɫ������z���Һ���
                     * ������ѡ���Ϊ���3
                     */
                    z = z.parent;
                    leftRotate(z);
                }
                /**
                 * �����
                 */
                z.parent.color = BLACK;
                z.parent.parent.color = RED;
                rightRotate(z.parent.parent);
            } else {
                // ����z�ĸ��ڵ㡱�ǡ�z���游�ڵ���Һ��ӡ�
                RBNode y = z.parent.parent.left;
                if (y.color == RED) {
                    // Case 1����������ڵ��Ǻ�ɫ
                    y.color = BLACK;
                    z.parent.color = BLACK;
                    // ���ϼ�������
                    z = z.parent.parent;
                } else if (z == z.parent.right) {
                    // case 2�� �����ӽڵ�
                    z = z.parent;
                    //!!! ���������
                    rightRotate(z);
                }

                // case3
                z.parent.parent.color = RED;
                z.parent.color = BLACK;
                // ����ת
                leftRotate(z.parent.parent);
            }
        }
        root.color = BLACK;
    }


    /**
     * ɾ���ڵ�
     * @param rmNode
     */
    private void remove(RBNode rmNode) {
        RBNode node = rmNode;
        // ԭ������ɫ
        RBNode replace;
        boolean color = rmNode.color;
        if (rmNode.left == null) {
            // ���Ӳ�����
            // ��ɾ�ڵ�ĺ�̽ڵ㡣(��Ϊ"ȡ���ڵ�")
            // ������ȡ��"��ɾ�ڵ�"��λ�ã�Ȼ���ٽ�"��ɾ�ڵ�"ȥ����
            replace = rmNode.right;
            transplant(rmNode, replace);
        } else if (rmNode.right == null) {
            replace = rmNode.left;
            transplant(rmNode, replace);
        } else {
            node = min(root.right);
            color = node.color;
            replace = node.right;
            if (node.parent == rmNode) {
                replace.parent = node;
            } else {
                // ��y���ҽڵ��滻y��  Ȼ������y���滻��ǰɾ���Ľڵ�
                transplant(node, replace);
                node.right = rmNode.right;
                node.right.parent = node;
            }

            // �滻�ڵ�
            transplant(rmNode, node);
            node.left = rmNode.left;
            node.left.parent = node;
            node.color = rmNode.color; // һ������ɫ
        }

        // ����Ǻ�ɫ������Ҫ����
        if (color == BLACK)
            deleteFixUp(replace);

    }

    /**
     * ����
     * @param x
     */
    private void deleteFixUp(RBNode x) {
        while(x != root && x.color == BLACK) {
            if (x == x.parent.left ) {
                // ���ӽڵ�
                RBNode w = x.parent.right;
                /**
                 * case 1���ֵܽڵ����ɫΪ��ɫ
                 */
                if (w.color == RED) {
                    x.parent.color = RED;
                    w.color = BLACK;
                    leftRotate(x.parent);
                    w = x.parent.right;
                }
                /**
                 * case 2 �ֵܽڵ����ɫΪ��ɫ�������������ӽڵ���ɫ��Ϊ��ɫ
                 */
                if (w.left.color == BLACK && w.right.color == BLACK) {
                    w.color = RED;
                    x = x.parent;
                } else if (w.right.color ==  BLACK) {
                    /**
                     * case 3: x���ֵܽڵ�w��ɫΪ��ɫ������w�������Ǻ�ɫ���ҽ���Ǻ�ɫ
                     */
                   w.color = RED;
                   w.left.color = BLACK;
                   rightRotate(w);
                   w = x.parent.right;
                 }

                /**
                 * case 4: x���ֵܽڵ�w��ɫ�Ǻ�ɫ������w����ڵ��Ǻ�ɫ���Һ����Ǻ�ɫ
                 */
                x.parent.color = BLACK;
                w.color = RED;
                w.right.color = BLACK;
                leftRotate(x.parent);
                x = root;  // ?
            } else {
                // ���Һ���

                RBNode w = x.parent.left;
                if (w.color == RED) {
                    /**
                     * case 1: �ֵܽڵ���ɫ�Ǻ�ɫ
                     */
                    w.color = BLACK;
                    x.parent.color = RED;
                    rightRotate(x.parent);
                    w = x.parent.left;
                }

                if (w.left.color == BLACK && w.right.color == BLACK) {
                    /**
                     * case 2: �ֵܽ���Ǻ�ɫ�����������ӽ��Ҳ�Ǻ�ɫ
                     */
                    w.color = RED;
                    x = x.parent;
                } else if(x.left.color == BLACK) {
                    /**
                     * case 3��
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
     * �����Ĺ���
     * @param parent ���ڵ�
     * @param replace ��ɾ��λ�ã��µ��滻Ԫ��
     */
    private void transplant(RBNode parent, RBNode replace) {
        if (parent.parent == null) {
            root = replace;
        } else if (parent == parent.parent.left) {
            parent.parent.left = replace;
        } else {
            parent.parent.right = replace;
        }
    }

    /**
     * ��СԪ��
     * @param root
     * @return
     */
    private RBNode min(RBNode root) {
        if (root == null) {
            return null;
        }
        RBNode node = root;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

}
