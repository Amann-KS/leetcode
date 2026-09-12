class Solution {
    static class Interval {
        int l, r, weight, id;
        Interval(int l, int r, int weight, int id) {
            this.l = l;
            this.r = r;
            this.weight = weight;
            this.id = id;
        }
    }

    static class State {
        long score;
        List<Integer> ids;

        State(long score, List<Integer> ids) {
            this.score = score;
            this.ids = ids;
        }

       
        public static boolean isBetter(State a, State b) {
            if (a.score != b.score) {
                return a.score > b.score;
            }
            int size = Math.min(a.ids.size(), b.ids.size());
            for (int i = 0; i < size; i++) {
                if (!a.ids.get(i).equals(b.ids.get(i))) {
                    return a.ids.get(i) < b.ids.get(i);
                }
            }
            return a.ids.size() < b.ids.size();
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervalsList) {
        int n = intervalsList.size();
        Interval[] intervals = new Interval[n];
        for (int i = 0; i < n; i++) {
            List<Integer> interval = intervalsList.get(i);
            intervals[i] = new Interval(interval.get(0), interval.get(1), interval.get(2), i);
        }

       
        Arrays.sort(intervals, (a, b) -> Integer.compare(a.r, b.r));

        int[] rightEndpoints = new int[n];
        for (int i = 0; i < n; i++) {
            rightEndpoints[i] = intervals[i].r;
        }

      
        State[][] dp = new State[5][n + 1];
        for (int k = 0; k <= 4; k++) {
            for (int i = 0; i <= n; i++) {
                dp[k][i] = new State(0, new ArrayList<>());
            }
        }

        for (int k = 1; k <= 4; k++) {
            for (int i = 1; i <= n; i++) {
                Interval curr = intervals[i - 1];

              
                int j = binarySearch(rightEndpoints, curr.l);

              
                State best = dp[k][i - 1];

               
                State prev = dp[k - 1][j];
                List<Integer> newIds = new ArrayList<>(prev.ids);
                newIds.add(curr.id);
                Collections.sort(newIds);
                State take = new State(prev.score + curr.weight, newIds);

                if (State.isBetter(take, best)) {
                    best = take;
                }

                dp[k][i] = best;
            }
        }

        List<Integer> resList = dp[4][n].ids;
        int[] res = new int[resList.size()];
        for (int i = 0; i < resList.size(); i++) {
            res[i] = resList.get(i);
        }
        return res;
    }

   
    private int binarySearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int ans = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] < target) {
                ans = mid + 1; 
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }
}