package cn.zhengjianglong.simple.search;

/**
 * ƽ������� �򵥰汾
 * https://blog.csdn.net/lcore/article/details/8892648
 * @author zhengjianglong
 * @since 2018-04-02
 */
public class AvlTree {
    /**
     * avl���ڵ�
     */
    private static class AvlNode {

        AvlNode(int val) {
            this(val, null, null);
        }

        AvlNode(int val, AvlNode lt, AvlNode rt) {
            val = val;
            left = lt;
            right = rt;
            height = 0;
        }

        int val;      // �ڵ��е�����
        AvlNode left;         // �����
        AvlNode right;        // �Ҷ���
        int height;       // �ڵ�ĸ߶�
    }

    /**
     * ��avl���в������ݣ��ظ����ݸ���
     *
     * @param root
     * @param val
     * @return
     */
    public AvlNode insert(AvlNode root, int val) {
        if (root == null) {
            return new AvlNode(val);
        }
        if (root.val == val) {
            return root;
        } else if (root.val > val) {
            root.left = insert(root.left, val);
            if (height(root.left) - height(root.right) == 2) {
                if (val < root.left.val) { // LL��
                    root = rightLeftChild(root);
                } else {
                    // LR ��
                    root = leftRightChild(root);
                }
            }
        } else {
            root.right = insert(root.right, val);
            if (height(root.right) - height(root.left) == 2) {
                if (val > root.right.val) {
                    // RR��
                    root = rotateWithRightChild(root);
                } else {
                    // RL
                    root = rightLeftChild(root);
                }
            }
        }

        // ���¸߶�
        root.height = Math.max(height(root.left), height(root.right)) + 1;//���¸߶�
        return root;
    }

    public int height(AvlNode node) {
        return node == null ? -1 : node.height;
    }

    /**
     * ����������ת,������LL��
     *
     * @param node
     * @return
     */
    private AvlNode rotateWithLeftChild(AvlNode node) {
        AvlNode leftNode = node.left;
        node.left = leftNode.right;
        leftNode.right = node;

        // �������
        leftNode.height = Math.max(height(leftNode.left), height(leftNode.right)) + 1;
        node.height = Math.max(height(node.left), height(node.right)) + 1;

        return node;
    }

    /**
     * ����������ת��������RR��
     *
     * @param node
     * @return
     */
    private AvlNode rotateWithRightChild(AvlNode node) {
        AvlNode rightNode = node.right;
        node.right = rightNode.left;
        rightNode.left = node;

        // �������
        rightNode.height = Math.max(height(rightNode.left), height(rightNode.right)) + 1;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return rightNode;
    }


    /**
     * LR ����
     * @param node
     * @return
     */
    private AvlNode leftRightChild(AvlNode node) {
        node.left = rotateWithRightChild(node.left);
        return rotateWithLeftChild(node);
    }

    /**
     * RL
     * @param node
     * @return
     */
    private AvlNode rightLeftChild(AvlNode node) {
        node.right = rotateWithLeftChild(node.right);
        return rotateWithRightChild(node);
    }

    /**
     * Ѱ����Сֵ
     * @param avlNode
     * @return
     */
    private AvlNode findMin(AvlNode avlNode) {
        if (avlNode == null) {
            return null;
        }
        while (avlNode.left != null) {
            avlNode = avlNode.left;
        }
        return avlNode;
    }

}
