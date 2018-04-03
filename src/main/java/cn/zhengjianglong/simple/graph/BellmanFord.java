package cn.zhengjianglong.simple.graph;

/**
 * BellmanFord
 * <p>
 * 很明显时间复杂度为O(VE),因为每一次需要对边进行松弛，所以我们采用保存边的方式来存储图的的信息。
 * p保存的是前驱节点，d保存的是源点到每一个点的最短距离。我们最后又做了一次判断，如果还有可以松弛
 * 的边，那么可以保证的是图中有负权的环存在，这样的话就没有最短路径只说了，可以无限小。
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
            this.w = w; // 权重
        }
    }

    /**
     * BellmanFord算法
     * @param as 边
     * @param num 节点数量
     * @return
     */
    private static int[] bellmanFord(Edge[] as, int num) {

        int[] dist = new int[num];
        int[] path = new int[num];
        dist[0] = 0; // 原点
        path[0] = 0;
        // 初始化
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
         * 检测负环
         */
        for (int j = 0; j < as.length; j++) {
            if (dist[as[j].v] > dist[as[j].u] + as[j].w) {
                throw new RuntimeException("有负环");
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
