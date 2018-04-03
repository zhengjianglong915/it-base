package cn.zhengjianglong.simple.graph;

/**
 * BellmanFord
 * <p>
 * ������ʱ�临�Ӷ�ΪO(VE),��Ϊÿһ����Ҫ�Ա߽����ɳڣ��������ǲ��ñ���ߵķ�ʽ���洢ͼ�ĵ���Ϣ��
 * p�������ǰ���ڵ㣬d�������Դ�㵽ÿһ�������̾��롣�������������һ���жϣ�������п����ɳ�
 * �ıߣ���ô���Ա�֤����ͼ���и�Ȩ�Ļ����ڣ������Ļ���û�����·��ֻ˵�ˣ���������С��
 *
 * @author zhengjianglong
 * @since 2018-04-03
 */
public class BellmanFord {
    private static final int INFINITY = 10000;
    private static class Edge {
        private int u;
        private int v;
        private int w;
        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w; // Ȩ��
        }
    }

    /**
     * BellmanFord�㷨
     * @param as ��
     * @param num �ڵ�����
     * @return
     */
    private static int[] bellmanFord(Edge[] as, int num) {

        int[] dist = new int[num];
        int[] path = new int[num];
        dist[0] = 0; // ԭ��
        path[0] = 0;
        // ��ʼ��
        for (int i = 1; i < dist.length; i++) {
            dist[i] = INFINITY;
            path[i] = -1;
        }

        // relax
        for (int i = 1; i < dist.length; i++) {
            for (int j = 0; j < as.length; j++) {
                if (dist[as[j].v] > dist[as[j].u] + as[j].w) {
                    dist[as[j].v] = dist[as[j].u] + as[j].w;
                    path[as[j].v] = as[j].u;
                }
            }
        }

        /**
         * ��⸺��
         */
        for (int j = 0; j < as.length; j++) {
            if (dist[as[j].v] > dist[as[j].u] + as[j].w) {
                throw new RuntimeException("�и���");
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        Edge a1 = new Edge(0, 1, -1);
        Edge a2 = new Edge(0, 2, 4);
        Edge a3 = new Edge(1, 2, 3);
        Edge a4 = new Edge(3, 1, 1);
        Edge a5 = new Edge(1, 3, 2);
        Edge a6 = new Edge(3, 2, 5);
        Edge a7 = new Edge(1, 4, 2);
        Edge a8 = new Edge(4, 3, -3);
        Edge[] as = new Edge[]{a1, a2, a3, a4, a5, a6, a7, a8};
        int N = 5;
        int[] dist = bellmanFord(as, N);
        for (int i = 0; i < N; i++) {
            System.out.println(dist[i]);
        }
    }
}
