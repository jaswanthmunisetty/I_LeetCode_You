class Solution {
    public int missingMultiple(int[] nums, int k) {
        HashSet<Integer> hash = new HashSet<>();
        for (int i : nums)
        {
            hash.add(i);
        }
        int x = k;
        while(hash.contains(x))
        {
            x+=k;
        }
        return x;
    }
}