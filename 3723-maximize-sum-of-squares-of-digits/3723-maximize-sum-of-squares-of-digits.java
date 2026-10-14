class Solution {
    public String maxSumOfSquares(int num, int sum) {
      
        if ((long) num * 9 < sum || sum < 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder(num);
        int remSum = sum;

        for (int i = 0; i < num; i++) {
            
            int maxDigit = Math.min(9, remSum - (num - 1 - i) * 9);
          
            int d = Math.min(9, remSum);
          
            int minNeededForRest = Math.max(0, remSum - d);
          
            if (remSum - d > (num - 1 - i) * 9) {
                d = remSum - (num - 1 - i) * 9;
            }
            
            sb.append(d);
            remSum -= d;
        }

        return sb.toString();
    }
}