class Solution {
    public char findTheDifference(String s, String t) {
        int charSum = 0;
        
        for (char c : t.toCharArray()) {
            charSum += c;
        }

        for (char c : s.toCharArray()) {
            charSum -= c;
        }
        
        return (char) charSum;
    }
}
