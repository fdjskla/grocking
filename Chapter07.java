import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.HashSet;


public class Chapter07 {
  
    public static void main(String[] args) {
        List<Edge<String>> edges = List.of(
            new Edge("A", "B", 2),
            new Edge("A", "C", 5),
            new Edge("B", "C", 1),
            new Edge("B", "D", 4),
            new Edge("C", "D", 7)
        );
        Graph<String> g = new Graph(edges);
        List<String> path = deikstra(g, "A", "C");
        System.out.println(path);
    }
    
    private static <T> List<T> deikstra(Graph<T> g, T from, T to) {
        Map<T, T> parents = new HashMap();
        Map<T, Integer> pathWeight = new HashMap();
        Set<T> processed = new HashSet();
        
        pathWeight.put(from, 0);
        T nextVertex = getVertexWitnMinWeight(pathWeight, processed);
        
        while (nextVertex!= null) {
            processed.add(nextVertex);
            List<Edge<T>> neighbours = g.edgeByFrom.getOrDefault(nextVertex, List.of());
            Integer currentWeight = pathWeight.getOrDefault(nextVertex, 0);
            
            for(Edge<T> edge: neighbours) {
                Integer oldPathWeight = pathWeight.get(edge.to);
                Integer newPathWeighht = currentWeight + edge.weight;
                if (oldPathWeight == null || oldPathWeight > newPathWeighht) {
                    pathWeight.put(edge.to, newPathWeighht);
                    parents.put(edge.to, edge.from);
                }
            }
            
            nextVertex = getVertexWitnMinWeight(pathWeight, processed);
        }
        System.out.println("weight - " + pathWeight.get(to));
        return getPath(parents, from, to);
    }
    
    private static <T> T getVertexWitnMinWeight(
        Map<T, Integer> pathWeight, 
        Set<T> processed) {
            Integer lowest = Integer.MAX_VALUE;
            T vertex = null;
            for (Map.Entry<T, Integer> e: pathWeight.entrySet()) {
                if (!processed.contains(e.getKey()) && e.getValue() < lowest) {
                    lowest = e.getValue();
                    vertex = e.getKey();
                }
            }
        return vertex;
    }
    
    private static <T> List<T> getPath(Map<T, T> parents, T from, T to) {
        if (to.equals(from)) {
            return List.of(to);
        }
        LinkedList<T> path = new LinkedList();
        path.add(to);
        T parent = parents.get(to);
        path.addFirst(parent);
        while (!parent.equals(from)) {
            parent = parents.get(parent);
            path.addFirst(parent);
        }
        return path;
    }
    

    private static class Graph<T> {
        public final List<Edge<T>> edges;
        public final Map<T, List<Edge<T>>> edgeByFrom;
        
        public Graph(List<Edge<T>> edges) {
            this.edges = edges;
            this.edgeByFrom = edges
            .stream()
            .collect(Collectors.groupingBy(e -> e.from));
        }
    }
    
    
    private static class Edge<T> {
        
        public final T from;
        public final T to;
        public final int weight;
        
        public Edge(T from, T to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}
