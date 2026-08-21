class Solution {
    public int strStr(String haystack, String needle) {
        int m = haystack.length();
        int n = needle.length();

        if (n == 0) return 0;
        if (n > m) return -1;

     
        int[] lps = buildLPS(needle);

        int i = 0; 
        int j = 0; 

        while (i < m) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;

                if (j == n) {
                    return i - j; 
                }
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        return -1;
    }

    private int[] buildLPS(String needle) {
        int n = needle.length();
        int[] lps = new int[n];
        int len = 0;
        int i = 1;

        while (i < n) {
            if (needle.charAt(i) == needle.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }
}