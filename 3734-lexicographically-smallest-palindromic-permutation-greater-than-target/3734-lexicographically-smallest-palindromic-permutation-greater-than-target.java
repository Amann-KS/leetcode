class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int oddCount = 0;
        char mid = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 != 0) {
                oddCount++;
                mid = (char) ('a' + i);
            }
        }

        if (oddCount > 1) {
            return "";
        }

        int m = n / 2;
        int[] halfFreq = new int[26];
        for (int i = 0; i < 26; i++) {
            halfFreq[i] = freq[i] / 2;
        }

        String result = solve(halfFreq, mid, target, n, m);
        return result;
    }

    private String solve(int[] halfFreq, char mid, String target, int n, int m) {
        String best = null;

     
        for (int L = m; L >= 0; L--) {
            int[] currentHalf = halfFreq.clone();
            boolean possible = true;
            char[] prefix = new char[m];

           
            for (int i = 0; i < L; i++) {
                char c = target.charAt(i);
                if (currentHalf[c - 'a'] > 0) {
                    prefix[i] = c;
                    currentHalf[c - 'a']--;
                } else {
                    possible = false;
                    break;
                }
            }

            if (!possible) continue;

           
            if (L == m) {
                StringBuilder sb = new StringBuilder();
                sb.append(new String(prefix));
                if (n % 2 != 0) sb.append(mid);
                for (int i = m - 1; i >= 0; i--) sb.append(prefix[i]);

                String cand = sb.toString();
                if (cand.compareTo(target) > 0) {
                    if (best == null || cand.compareTo(best) < 0) best = cand;
                }
                continue;
            }

          
            char startChar = (char) (target.charAt(L) + 1);
            for (char c = startChar; c <= 'z'; c++) {
                if (currentHalf[c - 'a'] > 0) {
                    int[] tempHalf = currentHalf.clone();
                    char[] candPrefix = prefix.clone();
                    candPrefix[L] = c;
                    tempHalf[c - 'a']--;

                    
                    int idx = L + 1;
                    for (int ch = 0; ch < 26; ch++) {
                        while (tempHalf[ch] > 0) {
                            candPrefix[idx++] = (char) ('a' + ch);
                            tempHalf[ch]--;
                        }
                    }

                    StringBuilder sb = new StringBuilder();
                    sb.append(new String(candPrefix));
                    if (n % 2 != 0) sb.append(mid);
                    for (int i = m - 1; i >= 0; i--) sb.append(candPrefix[i]);

                    String cand = sb.toString();
                    if (cand.compareTo(target) > 0) {
                        if (best == null || cand.compareTo(best) < 0) best = cand;
                        break;
                    }
                }
            }
        }

        return best == null ? "" : best;
    }
}