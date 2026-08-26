class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int left = 0, count = 0;
        String result = "";

        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') {
                count++;
            }

            while (count == k) {
            
                while (s.charAt(left) == '0') {
                    left++;
                }

                String current = s.substring(left, right + 1);

                if (result.isEmpty() || current.length() < result.length() || 
                   (current.length() == result.length() && current.compareTo(result) < 0)) {
                    result = current;
                }
                if (s.charAt(left) == '1') {
                    count--;
                }
                left++;
            }
        }

        return result;
    }
}