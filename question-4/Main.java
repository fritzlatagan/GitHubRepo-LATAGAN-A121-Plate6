import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Map to store the degree of each vertex
        Map<Integer, Integer> vertexDegrees = new HashMap<>();

        System.out.println("Enter the number of edges:");
        int numberOfEdges = scanner.nextInt();

        System.out.println("Enter the pairs of vertices for each edge (e.g., 1 2):");
        for (int i = 0; i < numberOfEdges; i++) {
            int vertex1 = scanner.nextInt();
            int vertex2 = scanner.nextInt();

            // Update degree for vertex1
            vertexDegrees.put(vertex1, vertexDegrees.getOrDefault(vertex1, 0) + 1);
            // Update degree for vertex2
            vertexDegrees.put(vertex2, vertexDegrees.getOrDefault(vertex2, 0) + 1);
        }

        System.out.println("Degrees of vertices:");
        for (Map.Entry<Integer, Integer> entry : vertexDegrees.entrySet()) {
            System.out.println("Vertex " + entry.getKey() + " has degree " + entry.getValue());
        }

        scanner.close();
    }
}
