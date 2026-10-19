class Solution {
    public int[] getConcatenation(int[] nums) {
        
        int[] ans = new int[nums.length + nums.length];

        for(int i = 0; i< nums.length ;i++){
            ans[i] =nums[i];
        }
        for(int i = 0; i< nums.length ;i++){
            ans[nums.length + i] =nums[i];
        }
        return ans;
    }
}