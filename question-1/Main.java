import java.util.*;

public class Main {
    private Map<Integer, List<Integer>> adjacencyList;
    private Set<Integer> visited;

    public Main() {
        this.adjacencyList = new HashMap<>();
        this.visited = new HashSet<>();
    }

    public void addEdge(int u, int v) {
        adjacencyList.putIfAbsent(u, new ArrayList<>());
        adjacencyList.putIfAbsent(v, new ArrayList<>());
        adjacencyList.get(u).add(v);
        adjacencyList.get(v).add(u);
    }

    private void dfs(int node) {
        Stack<Integer> stack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (!visited.contains(current)) {
                visited.add(current);
                for (int neighbor : adjacencyList.get(current)) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }

    public boolean isConnected() {
        if (adjacencyList.isEmpty()) {
            return true;
        }

        int startNode = adjacencyList.keySet().iterator().next();
        dfs(startNode);

        return visited.size() == adjacencyList.size();
    }

    public int countConnectedComponents() {
        int connectedComponents = 0;
        visited.clear();

        for (int node : adjacencyList.keySet()) {
            if (!visited.contains(node)) {
                dfs(node);
                connectedComponents++;
            }
        }

        return connectedComponents;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main graph = new Main();

        System.out.println("Enter the number of edges:");
        int edgesCount = scanner.nextInt();

        System.out.println("Enter the edges (format: u v):");
        for (int i = 0; i < edgesCount; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            graph.addEdge(u, v);
        }

        if (graph.isConnected()) {
            System.out.println("The graph is connected.");
        } else {
            int components = graph.countConnectedComponents();
            System.out.println("The graph is not connected.");
            System.out.println("Number of connected components: " + components);
        }

        scanner.close();
    }
}
