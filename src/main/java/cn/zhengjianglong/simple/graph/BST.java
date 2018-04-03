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
        //Ԫ�ظ���
        int n = graph.length;
        ArrayList<Integer> result = new ArrayList<Integer>();
        // ���У�LinkedLisʵ��queue�ӿ�
        Queue<Integer> thequeue = new LinkedList<Integer>();
        //marked[i]����Ƿ񱻱�����,������Ϊtrue
        boolean[] marked = new boolean[n];
        for (int i = 0; i < n; i++) {
            //��ʼ���������
            marked[i] = false;
        }

        // ������ʼ���
        int startNode = 0;
        // ��ʼ���װ�����
        thequeue.offer(startNode);
        // ��ʼ���װ���������
        result.add(startNode);
        // ������ʼ���ķ��ʱ�־
        marked[startNode] = true;

        //���зǿ�
        while (!thequeue.isEmpty()) {
            int v1 = thequeue.poll();
            for (int k = 0; k < n; k++) {
                if (graph[v1][k] > 0 && marked[k] == false && v1 != k) {
                    // ѹ�����
                    thequeue.offer(k);
                    // ���·��ʱ�־λ
                    marked[k] = true;
                    // ���±�������
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
