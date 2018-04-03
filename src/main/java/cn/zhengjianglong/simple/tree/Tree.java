package cn.zhengjianglong.simple.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author zhengjianglong
 * @since 2018-04-03
 */
public class Tree {
    private class TreeNode {
        int val;
        TreeNode left, right;
    }


    public void preOrder(TreeNode root) {
        if (root == null)
            return;
        System.out.println(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    /**
     * 非递归前序遍历
     *
     * @param root
     */
    public void preOrder2(TreeNode root) {
        if (root == null)
            return;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                System.out.println(node.val);
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                node = node.right;
            }
        }
    }

    /**
     * 中序遍历
     * @param root
     */
    public void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            System.out.println(root.val);
            inOrder(root.right);
        }
    }

    /**
     * 中序遍历非递归
     * @param root
     */
    public void inOrder2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        while (node != null && !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                System.out.println(node.val);
                node = node.right;
            }
        }
    }

    /**
     * 后续遍历
     * @param root
     */
    public void postOrder(TreeNode root){
        if(root != null){
            postOrder(root.left);
            postOrder(root.right);
            System.out.println(root.val);
        }
    }

    /**
     * 后序遍历 非递归
     * @param root
     */
    public void postOrder2(TreeNode root){
        if(root == null)
            return;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        TreeNode pre = null;
        TreeNode node;
        while(!stack.isEmpty()){
            node = stack.peek();
            if((node.left == null && node.right == null) ||
                    (pre != null && (pre == node.left || pre == node.right))){
                System.out.println(node.val);
                stack.pop();
                pre = node;
            }else{
                if( node.left != null)
                    stack.push(node.left);
                if(node.right != null)
                    stack.push(node.right);
            }
        }
    }

    /**
     * 层次遍历
     * @param root
     */
    public void levelTravel(TreeNode root){
        if(root==null)return;
        Queue<TreeNode> q=new LinkedList<TreeNode>();
        q.offer(root);
        while(!q.isEmpty()){
            TreeNode temp = q.poll();
            System.out.println(temp.val);
            if(temp.left!=null)
                q.offer(temp.left);
            if(temp.right!=null)
                q.offer(temp.right);
        }
    }
}
