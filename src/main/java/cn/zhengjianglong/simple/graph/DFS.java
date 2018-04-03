package cn.zhengjianglong.simple.graph;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 深度优先遍历
 *
 * @author zhengjianglong
 * @since 2018-04-03
 */
public class DFS {
    public ArrayList<Integer> depthFirst(int[][] graph) {
        int startNode = 0;
        Stack<Integer> stack = new Stack<Integer>();
        stack.add(startNode);
        ArrayList<Integer> result = new ArrayList<Integer>();
        int n = graph.length;
        // marked[i]标记是否被遍历过,遍历过为true
        boolean[] marked = new boolean[graph.length];
        for (int i = 0; i < n; i++) {
            // 初始化标记数组
            marked[i] = false;
        }
        // 更新起始结点的访问标志
        marked[startNode] = true;
        result.add(0);

        while (!stack.isEmpty()) {
            int v = stack.peek();
            boolean flag = false;
            for (int k = 0; k < n; k++) {
                if (graph[v][k] > 0 && marked[k] == false && v != k) {
                    // 压入栈
                    stack.push(k);
                    // 更新访问标志位
                    marked[k] = true;
                    // 更新遍历数组
                    result.add(k);
                    flag = true;
                    break;
                }
            }
            if (!flag)
                stack.pop();
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

        ArrayList<Integer> result = new DFS().depthFirst(graph);
        System.out.println(result);
    }
}
