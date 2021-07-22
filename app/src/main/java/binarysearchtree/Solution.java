package binarysearchtree;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int result = solution.partitionDisjoint(new int[]{5,0,3,8,6});

        assert result == 3;
    }

    public int partitionDisjoint(int[] nums) {

        for (int left = 0; left < nums.length - 1; left++) {
            int leftMax = nums[left];
            for (int idx = 0; idx < left ; idx++) {
                if (leftMax < nums[idx]) {
                    leftMax = nums[idx];
                }
            }

            int rightMin = nums[nums.length - 1];
            for (int right = left + 1; right < nums.length; right++) {
                if (rightMin > nums[right]) {
                    rightMin = nums[right];
                }
            }

            if (leftMax < rightMin) {
                return left + 1;
            }
        }

        return 0;
    }
}
