import java.util.*;

public class Main {

    // Method to determine if a graph is bipartite using BFS
    public static boolean isBipartite(int V, List<int[]> edges) {
        // Create adjacency list
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        // Color array, -1 means no color
        int[] color = new int[V];
        Arrays.fill(color, -1);

        // Check for each vertex (in case the graph is disconnected)
        for (int i = 0; i < V; i++) {
            if (color[i] == -1) {
                if (!bfsCheck(i, adjList, color)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Helper method to perform BFS and check coloring
    private static boolean bfsCheck(int start, List<List<Integer>> adjList, int[] color) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        color[start] = 0;

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v : adjList.get(u)) {
                if (color[v] == -1) { // If not colored
                    color[v] = 1 - color[u];
                    queue.add(v);
                } else if (color[v] == color[u]) { // If adjacent vertex has the same color
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of vertices:");
        int V = scanner.nextInt();

        System.out.println("Enter the number of edges:");
        int E = scanner.nextInt();

        List<int[]> edges = new ArrayList<>();
        System.out.println("Enter the edges (u v):");
        for (int i = 0; i < E; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            edges.add(new int[] { u, v });
        }

        if (isBipartite(V, edges)) {
            System.out.println("The graph is bipartite.");
        } else {
            System.out.println("The graph is not bipartite.");
        }
    }
}
