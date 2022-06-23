import java.util.Arrays;

public class Chapter05 {
  
    public static void main(String[] args) {
        System.out.println("Hello, world!");
        testCombineArrays();
    }
    
    public static void testCombineArrays(){
        
        // two sorted arrays
        // combine into one array 
        
        int[] a1 = new int[] {2};
        int[] a2 = new int[] {};
        print(a1, a2);
        a1 = new int[] {};
        a2 = new int[] {1, 2, 3};
        print(a1, a2);
        a1 = new int[] {3};
        a2 = new int[] {1, 2};
        print(a1, a2);
        a1 = new int[] {1};
        a2 = new int[] {2, 3};
        print(a1, a2);
        a1 = new int[] {1, 2};
        a2 = new int[] {2, 3};
        print(a1, a2);
        
        a1 = new int[] {1, 2, 3, 4};
        a2 = new int[] {5, 6, 7};
        print(a1, a2);
        
        a1 = new int[] {0, 5, 6, 10};
        a2 = new int[] {1, 2, 3};
        print(a1, a2);
    }
    
    private static void print(int[] a1, int[] a2) {
        System.out.println("a1 - " + Arrays.toString(a1) +
        ", a2 - " + Arrays.toString(a2) +
        ", result - " + Arrays.toString(combineArrays(a1, a2)));
    }
    
    public static int[] combineArrays(int[] a1, int[] a2) {
        int size = a1.length + a2.length;
        int[] result = new int[size];
        
        int i1 = 0;
        int i2 = 0;
        for (int i = 0; i < size; i++) {
            if (i1 >= a1.length) {
                result[i] = a2[i2++];
                continue;
            }
            if (i2 >= a2.length) {
                result[i] = a1[i1++];
                continue;
            }
            int v1 = a1[i1];
            int v2 = a2[i2];
            if (v1 > v2) {
                result[i] = v2;
                i2++;
            } else {
                result[i] = v1;
                i1++;
            }
            
        }
        return result;
    }
    
}
