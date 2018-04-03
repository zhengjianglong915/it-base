package cn.zhengjianglong.simple.search;

/**
 * 平衡二叉树 简单版本
 * https://blog.csdn.net/lcore/article/details/8892648
 * @author zhengjianglong
 * @since 2018-04-02
 */
public class AvlTree {
    /**
     * avl树节点
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

        int val;      // 节点中的数据
        AvlNode left;         // 左儿子
        AvlNode right;        // 右儿子
        int height;       // 节点的高度
    }

    /**
     * 在avl树中插入数据，重复数据复略
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
                if (val < root.left.val) { // LL型
                    root = rightLeftChild(root);
                } else {
                    // LR 型
                    root = leftRightChild(root);
                }
            }
        } else {
            root.right = insert(root.right, val);
            if (height(root.right) - height(root.left) == 2) {
                if (val > root.right.val) {
                    // RR型
                    root = rotateWithRightChild(root);
                } else {
                    // RL
                    root = rightLeftChild(root);
                }
            }
        }

        // 更新高度
        root.height = Math.max(height(root.left), height(root.right)) + 1;//更新高度
        return root;
    }

    public int height(AvlNode node) {
        return node == null ? -1 : node.height;
    }

    /**
     * 带左子树旋转,适用于LL型
     *
     * @param node
     * @return
     */
    private AvlNode rotateWithLeftChild(AvlNode node) {
        AvlNode leftNode = node.left;
        node.left = leftNode.right;
        leftNode.right = node;

        // 更新深度
        leftNode.height = Math.max(height(leftNode.left), height(leftNode.right)) + 1;
        node.height = Math.max(height(node.left), height(node.right)) + 1;

        return node;
    }

    /**
     * 带右子树旋转，适用于RR型
     *
     * @param node
     * @return
     */
    private AvlNode rotateWithRightChild(AvlNode node) {
        AvlNode rightNode = node.right;
        node.right = rightNode.left;
        rightNode.left = node;

        // 更新深度
        rightNode.height = Math.max(height(rightNode.left), height(rightNode.right)) + 1;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return rightNode;
    }


    /**
     * LR 类型
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
     * 寻找最小值
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
