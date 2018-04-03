package cn.zhengjianglong.simple.search;

/**
 * 二叉查找树
 *
 * @author zhengjianglong
 * @since 2018-04-02
 */
public class BST {

    private class Node {
        private Node left;
        private Node right;
        private int val;

        private Node(int val) {
            this.val = val;
        }
    }

    /**
     * 二分查找
     * @param root
     * @param val
     * @return
     */
    public Node search(Node root, int val) {
        if (null == root) {
            return null;
        }
        if (root.val == val) {
            return root;
        }  else if (root.val < val) {
            return search(root.right, val);
        } else {
            return search(root.left, val);
        }
    }

    /**
     * 插入
     * @param root
     * @param val
     * @return
     */
    public Node insert(Node root, int val) {
        if (null == root) {
            return new Node(val);
        }

        if (root.val == val) {
            return root;
        } else if (root.val > val) {
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }
        return root;
    }

    /**
     * 删除最小的元素
     * @param root
     * @return
     */
    public Node deleteMin(Node root) {
        if (root == null) {
            return null;
        }

        if (root.left != null) {
            return deleteMin(root.left);
        } else {
            return root.right;
        }
    }

    /**
     * 删除最大的值
     * @param root
     * @return
     */
    public Node deleteMax(Node root) {
        if (root == null) {
            return null;
        }

        if (root.right != null) {
            return deleteMax(root.right);
        } else {
            return root.left;
        }
    }

    /**
     * 删除
     * @param root
     * @param val
     * @return
     */
    public Node delete(Node root, int val) {
        if (root == null) {
            return null;
        }

        if (root.val == val) {
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left != null && root.right != null) {
                Node leftBig = max(root.left);
                root.val = leftBig.val;
                root.left = delete(root.left, leftBig.val);
            } else if (root.left != null) {
                return root.left;
            } else {
                return root.right;
            }
        } else if (root.val > val){
            root.left =  delete(root.left, val);
        } else {
            root.right = delete(root.right, val);
        }
        return root;
    }


    /**
     * 最小的节点
     * @param root
     * @return
     */
    public Node min(Node root) {
        if(root == null) {
            return null;
        }
        if (root.left != null) {
            return min(root.left);
        }
        return root;
    }

    /**
     * 最大的节点
     * @param root
     * @return
     */
    public Node max(Node root) {
        if(root == null) {
            return null;
        }
        if (root.right != null) {
            return max(root.right);
        }
        return root;
    }

}
