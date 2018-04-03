package cn.zhengjianglong.simple.graph;

/**
 * 弗洛伊德算法
 *
 * @author zhengjianglong
 * @since 2018-04-03
 */
public class Floyed {
    private final int INF = Integer.MAX_VALUE;

    /**
     * 弗洛伊德算法
     * @param matrix
     */
    public void floyd(int[][] matrix) {
        int[][] path = new int[matrix.length][matrix[0].length];
        int[][] dist = new int[matrix.length][matrix[0].length];
        int size = matrix.length;
        //初始化
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                path[i][j] = -1;
                dist[i][j] = matrix[i][j];
            }
        }

        for (int w = 0; w < size; w++) { // 中转点
            for (int u = 0; u < size; u++) { // 起始点
                for (int v = 0; v < size; v++) {  // 目标点
                    if (dist[u][w] != INF && dist[w][v] != INF && dist[u][w] + dist[w][v] < dist[u][v]) {
                        dist[u][v] = dist[u][w] + dist[w][v];
                        path[u][v] = w;
                    }
                }
            }
        }
    }
}