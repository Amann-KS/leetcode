class Solution {
    public int totalNumbers(int[] digits) {
        
        int[] freq = new int[10];
        for (int d : digits) {
            freq[d]++;
        }

        int count = 0;

       
        for (int i = 100; i <= 998; i += 2) {
            int d1 = i / 100;       // Hundreds digit
            int d2 = (i / 10) % 10; // Tens digit
            int d3 = i % 10;        // Units digit

          
            int[] currentFreq = new int[10];
            currentFreq[d1]++;
            currentFreq[d2]++;
            currentFreq[d3]++;

           
            boolean valid = true;
            for (int d = 0; d < 10; d++) {
                if (currentFreq[d] > freq[d]) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                count++;
            }
        }

        return count;
    }
}