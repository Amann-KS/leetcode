class Solution {
    public int reverseDegree(String s) {
        int totalReverseDegree = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int reversedAlphabetIndex = 26 - (ch - 'a');
            int stringIndex = i + 1;
            
            totalReverseDegree += reversedAlphabetIndex * stringIndex;
        }

        return totalReverseDegree;
    }
}