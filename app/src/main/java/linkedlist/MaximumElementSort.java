package linkedlist;

import java.util.Arrays;

public class MaximumElementSort {

    public static void main(String[] args) {
        Solution solution = new Solution();

        int result = solution.maximumElementAfterDecrementingAndRearranging(new int[]{
            1, 2, 3
        });

        System.out.println(result);
    }


    static class Solution {

        public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
            Arrays.sort(arr);
            arr[0] = 1;

            int index = arr[0];
            for (int i = 0; i < arr.length; i++) {
                if (Math.abs(index - arr[i]) > 1) {
                    arr[i] = index + 1;
                }
                index = arr[i];
            }

            return arr[arr.length - 1];
        }
    }
}

