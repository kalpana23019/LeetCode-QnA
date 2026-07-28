class Solution {
    public int missingNumber(int[] nums) {
        

        int n = nums.length;
        int expectedSum = n*(n+1)/2;

        int actualsum =0;

        for(int  num : nums){
            actualsum += num;
        }

        return expectedSum - actualsum;
    }

}