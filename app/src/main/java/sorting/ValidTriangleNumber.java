package sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ValidTriangleNumber {

    public static void main(String[] args) {
        Solution solution = new Solution();

        assert solution.triangleNumber(new int[]{82, 15, 23, 82, 67, 0, 3, 92, 11}) == 17;
    }

    static class Solution {

        public int triangleNumber(int[] nums) {
            if (nums.length < 3) {
                return 0;
            }

            List<Integer> numbers = Arrays.stream(nums)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

            int count = 0;
            for (int cIdx = 0; numbers.size() - cIdx >= 3; cIdx++) {
                Integer c = numbers.get(cIdx);

                int aIdx;
                for (aIdx = cIdx + 1; aIdx < numbers.size() - 1; aIdx++) {
                    Integer a = numbers.get(aIdx);

                    int bIdx;
                    for (bIdx = numbers.size() - 1; bIdx > aIdx; bIdx--) {
                        Integer b = numbers.get(bIdx);
                        if (a + b > c) {
                            count = count + (bIdx - aIdx);
                            break;
                        }
                    }
                    if (bIdx - aIdx == 1) {
                        break;
                    }
                }
            }

            return count;
        }
    }
}
