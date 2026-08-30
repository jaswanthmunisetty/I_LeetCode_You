class Solution {
    public int majorityElement(int[] nums) {
        int candidate = nums[0];
        int count = 0;
        for (int curr : nums)
        {
            if (count == 0) candidate = curr;
            if (curr == candidate) count++;
            else count--;
        }
        return candidate;
    }
}