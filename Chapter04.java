import java.util.List;
import java.util.ArrayList;


public class Chapter04 {
    public static void main(String[] args) {
        testQuickSort();
    }
    
    public static void testQuickSort(){
        List<Integer> a = List.of();
        System.out.println(a + " -> " + quickSort(a));
        a = List.of(-127, 60, 60, 15, -10, 0);
        System.out.println(a + " -> " + quickSort(a));
        a = List.of(0);
        System.out.println(a + " -> " + quickSort(a));
        a = List.of(1, 60, 60, 15, 5, 0);
        System.out.println(a + " -> " + quickSort(a));
        a = List.of(0, -72, 33, 15, 5, -5);
        System.out.println(a + " -> " + quickSort(a));
    }

    public static List<Integer> quickSort(List<Integer> orig){
        if (orig.size() < 2) {
            return orig;
        }
        int center = orig.get(0);
        List<Integer> less = new ArrayList<>();
        List<Integer> greater = new ArrayList<>();
        
        boolean skipFirst = true;
        for (Integer e: orig) {
            if (skipFirst) {
                skipFirst = false;
                continue;
            }
            if (e > center) {
                greater.add(e); 
            } else {
                less.add(e);
            }
        }
        List<Integer> result = new ArrayList<>();
        result.addAll(quickSort(less));
        result.add(center);
        result.addAll(quickSort(greater));
        return result;
    }
}

