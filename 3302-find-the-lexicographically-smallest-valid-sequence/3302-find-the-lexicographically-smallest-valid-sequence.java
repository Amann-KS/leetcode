class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[] last = new int[m];
        int ptr = n - 1;

        for (int i = m - 1; i >= 0; i--) {
            while (ptr >= 0 && word1.charAt(ptr) != word2.charAt(i)) {
                ptr--;
            }
            last[i] = ptr;
            ptr--; 
        }

        int[] ans = new int[m];
        boolean changed = false;
        int word1Idx = 0;

        for (int i = 0; i < m; i++) {
            boolean matchFound = false;

            while (word1Idx < n) {
                boolean isMatch = (word1.charAt(word1Idx) == word2.charAt(i));
                
               
                boolean canUseMismatch = !changed && (i == m - 1 || last[i + 1] > word1Idx);

                if (isMatch || canUseMismatch) {
                    if (!isMatch) {
                        changed = true;
                    }
                    ans[i] = word1Idx;
                    word1Idx++;
                    matchFound = true;
                    break;
                }
                word1Idx++;
            }

            if (!matchFound) {
                return new int[0]; 
            }
        }

        return ans;
    }
}