class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int sumDiff = 0;
        int qDiff = 0;

        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            int sign = (i < n / 2) ? 1 : -1;

            if (c == '?') {
                qDiff += sign;
            } else {
                sumDiff += sign * (c - '0');
            }
        }

        if ((qDiff & 1) != 0) {
            return true;
        }

        
        return sumDiff * 2 + qDiff * 9 != 0;
    }
}