class Solution {
    public String smallestNumber(String num, long t) {
        long tempT = t;
        int[] counts = new int[10]; 
        int[] primes = {2, 3, 5, 7};
        
        for (int p : primes) {
            while (tempT % p == 0) {
                counts[p]++;
                tempT /= p;
            }
        }

        
        if (tempT > 1) {
            return "-1";
        }

        int n = num.length();

        
        int firstZero = num.indexOf('0');
        int validPrefixLen = (firstZero == -1) ? n : firstZero;

       
        int[][] pref = new int[n + 1][10];
        for (int i = 0; i < validPrefixLen; i++) {
            int d = num.charAt(i) - '0';
            for (int p : primes) {
                pref[i + 1][p] = pref[i][p];
            }
            addDigitFactors(pref[i + 1], d, 1);
        }

       
        if (firstZero == -1) {
            int req2 = counts[2] - pref[n][2];
            int req3 = counts[3] - pref[n][3];
            int req5 = counts[5] - pref[n][5];
            int req7 = counts[7] - pref[n][7];

            if (minDigitsNeeded(req2, req3, req5, req7) <= 0) {
                return num;
            }
        }

       
        for (int i = validPrefixLen; i >= 0; i--) {
            int startDigit = (i < n) ? (num.charAt(i) - '0' + 1) : 1;

            for (int d = startDigit; d <= 9; d++) {
                int[] currentFactors = new int[10];
                addDigitFactors(currentFactors, d, 1);

                int req2 = counts[2] - pref[i][2] - currentFactors[2];
                int req3 = counts[3] - pref[i][3] - currentFactors[3];
                int req5 = counts[5] - pref[i][5] - currentFactors[5];
                int req7 = counts[7] - pref[i][7] - currentFactors[7];

                int remLen = n - 1 - i;
                if (minDigitsNeeded(req2, req3, req5, req7) <= remLen) {
                    String suffix = getMinDigitString(req2, req3, req5, req7, remLen);
                    return num.substring(0, i) + d + suffix;
                }
            }
        }

      
        int targetLen = Math.max(n + 1, minDigitsNeeded(counts[2], counts[3], counts[5], counts[7]));
        return getMinDigitString(counts[2], counts[3], counts[5], counts[7], targetLen);
    }

    private void addDigitFactors(int[] counts, int digit, int mult) {
        if (digit == 2) counts[2] += mult;
        else if (digit == 3) counts[3] += mult;
        else if (digit == 4) counts[2] += 2 * mult;
        else if (digit == 5) counts[5] += mult;
        else if (digit == 6) { counts[2] += mult; counts[3] += mult; }
        else if (digit == 7) counts[7] += mult;
        else if (digit == 8) counts[2] += 3 * mult;
        else if (digit == 9) counts[3] += 2 * mult;
    }

    private int minDigitsNeeded(int c2, int c3, int c5, int c7) {
        c2 = Math.max(0, c2);
        c3 = Math.max(0, c3);
        c5 = Math.max(0, c5);
        c7 = Math.max(0, c7);

        int cnt8 = c2 / 3, rem2 = c2 % 3;
        int cnt9 = c3 / 2, rem3 = c3 % 2;

        int cnt6 = 0;
        if (rem2 == 1 && rem3 == 1) {
            cnt6 = 1; rem2 = 0; rem3 = 0;
        } else if (rem2 == 2 && rem3 == 1) {
            cnt6 = 1; rem2 = 1; rem3 = 0;
        }

        int cnt4 = rem2 / 2;
        int cnt2 = rem2 % 2;
        int cnt3 = rem3;

        return c5 + c7 + cnt8 + cnt9 + cnt6 + cnt4 + cnt2 + cnt3;
    }

    private String getMinDigitString(int c2, int c3, int c5, int c7, int length) {
        c2 = Math.max(0, c2);
        c3 = Math.max(0, c3);
        c5 = Math.max(0, c5);
        c7 = Math.max(0, c7);

        int cnt8 = c2 / 3, rem2 = c2 % 3;
        int cnt9 = c3 / 2, rem3 = c3 % 2;

        int cnt6 = 0;
        if (rem2 == 1 && rem3 == 1) {
            cnt6 = 1; rem2 = 0; rem3 = 0;
        } else if (rem2 == 2 && rem3 == 1) {
            cnt6 = 1; rem2 = 1; rem3 = 0;
        }

        int cnt4 = rem2 / 2;
        int cnt2 = rem2 % 2;
        int cnt3 = rem3;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cnt2; i++) sb.append('2');
        for (int i = 0; i < cnt3; i++) sb.append('3');
        for (int i = 0; i < cnt4; i++) sb.append('4');
        for (int i = 0; i < c5; i++) sb.append('5');
        for (int i = 0; i < cnt6; i++) sb.append('6');
        for (int i = 0; i < c7; i++) sb.append('7');
        for (int i = 0; i < cnt8; i++) sb.append('8');
        for (int i = 0; i < cnt9; i++) sb.append('9');

        int onesCount = length - sb.length();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < onesCount; i++) res.append('1');
        res.append(sb);

        return res.toString();
    }
}