class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int leftQ = 0, rightQ = 0;
        int leftSum = 0, rightSum = 0;
        for (int i = 0; i < n / 2; i++) {
            if (num.charAt(i) == '?') leftQ++;
            else leftSum += num.charAt(i) - '0';
        }
        for (int i = n / 2; i < n; i++) {
            if (num.charAt(i) == '?') rightQ++;
            else rightSum += num.charAt(i) - '0';
        }
        if ((leftQ + rightQ) % 2 == 1) {
            return true;
        }
        return 2 * (leftSum - rightSum) != 9 * (rightQ - leftQ);
    }
}