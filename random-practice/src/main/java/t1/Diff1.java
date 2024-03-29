package t1;

import java.util.HashMap;

public class Diff1 {


    // The function assumes that the array is sorted
    static boolean findPair(int[] arr, int size, int n) {
        HashMap<Integer,
                Integer> mpp = new HashMap<Integer,
                Integer>();

        // Traverse the array
        for (int i = 0; i < size; i++) {

            // Update frequency
            // of arr[i]
            mpp.put(arr[i],
                    mpp.getOrDefault(arr[i], 0) + 1);

            // Check if any element whose frequency
            // is greater than 1 exist or not for n == 0
            if (n == 0 && mpp.get(arr[i]) > 1)
                return true;
        }

        // Check if difference is zero and
        // we are unable to find any duplicate or
        // element whose frequency is greater than 1
        // then no such pair found.
        if (n == 0)
            return false;

        for (int i = 0; i < size; i++) {
            if (mpp.containsKey(n + arr[i])) {
                System.out.print("Pair Found: (" + arr[i] + ", " +
                        +(n + arr[i]) + ")");
                return true;
            }
        }
        System.out.print("No Pair found");
        return false;
    }


    // Driver Code
    public static void main(String[] args) {
        int[] arr = {3, 1, 5, 8};
        int size = arr.length;
        int n = 0;
        findPair(arr, size, n);
    }
}
