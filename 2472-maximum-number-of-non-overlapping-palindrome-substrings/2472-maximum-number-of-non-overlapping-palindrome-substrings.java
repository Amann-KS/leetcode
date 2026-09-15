class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int count = 0;
        int lastEnd = -1; 
       
        for (int i = 0; i < 2 * n - 1; i++) {
            int left = i / 2;
            int right = left + (i % 2);

            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                int len = right - left + 1;
                
               
                if (left > lastEnd && len >= k) {
                    count++;
                    lastEnd = right;
                    break; 
                }
                
               
                if (len > k + 1) {
                    break;
                }

                left--;
                right++;
            }
        }

        return count;
    }
}