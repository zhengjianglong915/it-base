package cn.zhengjianglong.simple.graph;

import java.util.ArrayList;
import java.util.Stack;

/**
 * ������ȱ���
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
        // marked[i]����Ƿ񱻱�����,������Ϊtrue
        boolean[] marked = new boolean[graph.length];
        for (int i = 0; i < n; i++) {
            // ��ʼ���������
            marked[i] = false;
        }
        // ������ʼ���ķ��ʱ�־
        marked[startNode] = true;
        result.add(0);

        while (!stack.isEmpty()) {
            int v = stack.peek();
            boolean flag = false;
            for (int k = 0; k < n; k++) {
                if (graph[v][k] > 0 && marked[k] == false && v != k) {
                    // ѹ��ջ
                    stack.push(k);
                    // ���·��ʱ�־λ
                    marked[k] = true;
                    // ���±�������
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
