import java.util.Map;
import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Queue;
import java.util.Deque;

public class Chapter06 {
    
    public static void main(String[] args) {
        List<String> path = bfs(SMALL_GRAPH, "A", "D");
        System.out.println(path);
        
        path = bfs(BIG_GRAPH, "A", "C");
        System.out.println(path);
        
        List<Integer> ipath = bfs(INT_GRAPH, 1, 5);
        System.out.println(ipath);
    }
    
    private static final Map<String, List<String>> SMALL_GRAPH = Map.of(
        "A",List.of("B", "C", "A"),
        "B",List.of("C", "E", "B"),
        "C",List.of("D", "E"),
        "D",List.of("E", "B")
    );
    
    private static final Map<String, List<String>> BIG_GRAPH = Map.of(
        "X",List.of("B", "C", "Z"),
        "B",List.of("C", "E", "F"),
        "C",List.of("D", "E"),
        "D",List.of("E", "B"),
        "A",List.of("F", "K"),
        "Z",List.of("A"),
        "F",List.of(),
        "K",List.of("D", "X")
    );
    
    private static final Map<Integer, List<Integer>> INT_GRAPH = Map.of(
        1,List.of(3, 1),
        2,List.of(2, 3, 5),
        3,List.of(1, 4),
        4,List.of(2)
    );
    
    private static <T> List<T> bfs(
        Map<T, List<T>> graph, 
        T start, 
        T end
    ) {
        if (start.equals(end)) {
            return List.of();
        }
        Map<T, T> result = new HashMap<>();
        Set<T> viewed = new HashSet<>();
        Deque<T> q = new LinkedList<>();
        
        q.add(start);
        
        while(q.peek() != null) {
            T parent = q.pollFirst();
            if (!viewed.add(parent)) {
                continue;
            }
            List<T> children = graph.getOrDefault(parent,List.of());
            for(T child : children) {
                q.add(child);
                result.putIfAbsent(child, parent);
                if (child.equals(end)) {
                    LinkedList<T> path = new LinkedList();
                    path.addFirst(end);
                    path.addFirst(parent);
                    T prevPathElement = parent;
                    while (!prevPathElement.equals(start)) {
                        prevPathElement = result.get(prevPathElement);
                        path.addFirst(prevPathElement);
                    }
                    return path;
                }
            }
        }
        return List.of();
    }
}
