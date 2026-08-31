/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        int firstIdx = -1;
        int prevIdx = -1;
        int minDist = Integer.MAX_VALUE;
        
        ListNode prev = head;
        ListNode curr = head.next;
        int index = 1;

        while (curr.next != null) {

            if ((curr.val > prev.val && curr.val > curr.next.val) ||
                (curr.val < prev.val && curr.val < curr.next.val)) {
                
                if (firstIdx == -1) {
                    firstIdx = index;
                } else {
                    minDist = Math.min(minDist, index - prevIdx);
                }
                prevIdx = index;
            }
            
            prev = curr;
            curr = curr.next;
            index++;
        }

       
        if (firstIdx == -1 || prevIdx == firstIdx) {
            return new int[]{-1, -1};
        }

        int maxDist = prevIdx - firstIdx;
        return new int[]{minDist, maxDist};
    }
}