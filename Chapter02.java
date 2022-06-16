import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;


public class Chapter02 {
    public static void main(String[] args) {
        selectMinTest();
        selectionSortTest();
    }
    
    private static void selectionSortTest() {
        int[] arr = new int[]{1,2,3,4};
        int[] res = selectionSort(arr);
        System.out.println(Arrays.toString(res));
        
        arr = new int[]{2,1,-1,55};
        res = selectionSort(arr);
        System.out.println(Arrays.toString(res));
        
        arr = new int[]{33,-33,0,213,324,9,0,-33,-67};
        res = selectionSort(arr);
        System.out.println(Arrays.toString(res));
        
        arr = new int[]{9, 8, 7, 6, 5};
        res = selectionSort(arr);
        System.out.println(Arrays.toString(res));
    }
    
    private static int[] selectionSort(int[] arr) {
        int[] result = new int[arr.length];
        Set<Integer> excluded = new HashSet();
        for( int i=0; i<arr.length; i++) {
            int minIndex = selectMinIndexExcluding(arr, excluded);
            excluded.add(minIndex);
            result[i] = arr[minIndex];
        }
        return result;
    }
    
    private static int selectMinIndex(int[] arr) {
        int min = arr[0];
        int minIndex = 0;
        for( int i=1; i<arr.length; i++) {
            int current = arr[i];
            if (current < min) {
                min = current;
                minIndex = i;
            }
        }
        return minIndex;
    }
    
    private static int selectMinIndexExcluding(int[] arr, Set<Integer> excluded) {
        Integer min = null;
        Integer minIndex = null;
        for( int i=0; i<arr.length; i++) {
            if (!excluded.contains(i)) {
                int current = arr[i];
                if (min == null || current < min) {
                    min = current;
                    minIndex = i;
                }
            }
        }
        return minIndex;
    }
    
    private static void selectMinTest() {
        int[] arr = new int[]{1,2,3,4};
        int m1 = selectMinIndex(arr);
        System.out.println("result + " + (m1 == 0) + " (" + m1 + ") ");
        
        arr = new int[]{2,1,-1,55};
        m1 = selectMinIndex(arr);
        System.out.println("result + " + (m1 == 2) + " (" + m1 + ") ");
    }
}
