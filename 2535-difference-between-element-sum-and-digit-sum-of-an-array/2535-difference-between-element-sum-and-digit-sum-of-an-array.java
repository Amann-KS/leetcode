class Solution {
    public int differenceOfSum(int[] nums) {
        int elementSum = 0;
        int digitSum = 0;

        for (int i=0; i<nums.length; i++){
            elementSum += nums[i] ;

            int temp = nums[i];
            while (temp > 0){
                digitSum += temp % 10;
                temp /= 10;
            }
        }
        return elementSum - digitSum;

    }
}