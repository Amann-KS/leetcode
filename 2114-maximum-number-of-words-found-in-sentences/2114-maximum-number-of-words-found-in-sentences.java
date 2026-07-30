class Solution {
    public int mostWordsFound(String[] sentences) {
        int maxSpaces = 0;
        
        for (String s : sentences) {
            int spaces = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ' ') {
                    spaces++;
                }
            }
            maxSpaces = Math.max(maxSpaces, spaces);
        }
        
        return maxSpaces + 1;
    }
}
