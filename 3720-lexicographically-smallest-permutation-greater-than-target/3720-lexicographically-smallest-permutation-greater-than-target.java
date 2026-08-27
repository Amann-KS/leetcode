class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] totalCount = new int[26];
        for (char c : s.toCharArray()) {
            totalCount[c - 'a']++;
        }

        for (int i = n - 1; i >= 0; i--) {
            int[] currentCount = totalCount.clone();
            boolean validPrefix = true;
            for (int j = 0; j < i; j++) {
                char c = target.charAt(j);
                if (--currentCount[c - 'a'] < 0) {
                    validPrefix = false;
                    break;
                }
            }

            if (!validPrefix) continue;

            int targetChar = target.charAt(i) - 'a';
            for (int c = targetChar + 1; c < 26; c++) {
                if (currentCount[c] > 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(target.substring(0, i));
                    sb.append((char) ('a' + c));
                    currentCount[c]--;

                    for (int k = 0; k < 26; k++) {
                        while (currentCount[k] > 0) {
                            sb.append((char) ('a' + k));
                            currentCount[k]--;
                        }
                    }
                    return sb.toString();
                }
            }
        }

        return "";
    }
}