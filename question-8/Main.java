import java.util.Scanner;

public class Main {

    // Function to check if two graphs are isomorphic
    static boolean areIsomorphic(int[][] graph1, int[][] graph2, int V) {
        if (graph1.length != V || graph2.length != V) {
            return false;
        }

        int[] permutation = new int[V];
        for (int i = 0; i < V; i++) {
            permutation[i] = i;
        }

        return checkIsomorphism(graph1, graph2, permutation, 0, V);
    }

    // Function to generate all permutations and check for isomorphism
    static boolean checkIsomorphism(int[][] graph1, int[][] graph2, int[] permutation, int index, int V) {
        if (index == V) {
            return arePermutationsIsomorphic(graph1, graph2, permutation, V);
        }

        for (int i = index; i < V; i++) {
            swap(permutation, index, i);
            if (checkIsomorphism(graph1, graph2, permutation, index + 1, V)) {
                return true;
            }
            swap(permutation, index, i);
        }

        return false;
    }

    // Function to check if a specific permutation makes two graphs isomorphic
    static boolean arePermutationsIsomorphic(int[][] graph1, int[][] graph2, int[] permutation, int V) {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (graph1[i][j] != graph2[permutation[i]][permutation[j]]) {
                    return false;
                }
            }
        }
        return true;
    }

    // Function to swap elements in an array
    static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();

        System.out.println("Enter the adjacency matrix for the first graph:");
        int[][] graph1 = new int[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph1[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Enter the adjacency matrix for the second graph:");
        int[][] graph2 = new int[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph2[i][j] = scanner.nextInt();
            }
        }

        boolean result = areIsomorphic(graph1, graph2, V);
        if (result) {
            System.out.println("The graphs are isomorphic.");
        } else {
            System.out.println("The graphs are not isomorphic.");
        }

        scanner.close();
    }
}
