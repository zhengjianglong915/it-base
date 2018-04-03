package cn.zhengjianglong.simple.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhengjianglong
 * @since 2018-04-03
 */
public class BST {

    public ArrayList<Integer> bst(int[][] graph) {
        //元素个数
        int n = graph.length;
        ArrayList<Integer> result = new ArrayList<Integer>();
        // 队列，LinkedLis实现queue接口
        Queue<Integer> thequeue = new LinkedList<Integer>();
        //marked[i]标记是否被遍历过,遍历过为true
        boolean[] marked = new boolean[n];
        for (int i = 0; i < n; i++) {
            //初始化标记数组
            marked[i] = false;
        }

        // 搜索起始结点
        int startNode = 0;
        // 起始结点装入队列
        thequeue.offer(startNode);
        // 起始结点装入遍历数组
        result.add(startNode);
        // 更新起始结点的访问标志
        marked[startNode] = true;

        //队列非空
        while (!thequeue.isEmpty()) {
            int v1 = thequeue.poll();
            for (int k = 0; k < n; k++) {
                if (graph[v1][k] > 0 && marked[k] == false && v1 != k) {
                    // 压入队列
                    thequeue.offer(k);
                    // 更新访问标志位
                    marked[k] = true;
                    // 更新遍历数组
                    result.add(k);
                }
            }
        }
        return result;
    }
    public static void main(String[] args) {
        int[][] graph = new int[][]{
                {0, 1, 1, 1, 0},
                {1, 0, 0, 1, 0},
                {1, 0, 0, 0, 1},
                {1, 1, 0, 0, 0},
                {0, 0, 1, 0, 0}};

        ArrayList<Integer> result = new BST().bst(graph);
        System.out.println(result);
    }
}
