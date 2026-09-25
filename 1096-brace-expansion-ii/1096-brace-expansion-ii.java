class Solution {
    private int i = 0;

    public List<String> braceExpansionII(String expression) {
        i = 0;
        Set<String> resultSet = parseExpr(expression);
        List<String> sortedList = new ArrayList<>(resultSet);
        Collections.sort(sortedList);
        return sortedList;
    }

    
    private Set<String> parseExpr(String S) {
        Set<String> res = new HashSet<>();
        
        while (i < S.length() && S.charAt(i) != '}') {
            Set<String> termSet = parseTerm(S);
            res.addAll(termSet);
            
            if (i < S.length() && S.charAt(i) == ',') {
                i++; 
            }
        }
        
        return res;
    }

    
    private Set<String> parseTerm(String S) {
        Set<String> res = new HashSet<>();
        res.add(""); 
        
        while (i < S.length() && S.charAt(i) != ',' && S.charAt(i) != '}') {
            Set<String> factorSet = parseFactor(S);
         
            Set<String> nextRes = new HashSet<>();
            for (String a : res) {
                for (String b : factorSet) {
                    nextRes.add(a + b);
                }
            }
            res = nextRes;
        }
        
        return res;
    }

   
    private Set<String> parseFactor(String S) {
        Set<String> res = new HashSet<>();
        
        if (S.charAt(i) == '{') {
            i++; 
            res = parseExpr(S);
            i++;
        } else {
            StringBuilder sb = new StringBuilder();
            while (i < S.length() && Character.isLowerCase(S.charAt(i))) {
                sb.append(S.charAt(i));
                i++;
            }
            res.add(sb.toString());
        }
        
        return res;
    }
}