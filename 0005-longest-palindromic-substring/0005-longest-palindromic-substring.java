class Solution {
    public String longestPalindrome(String s) {
        
        if(s==null || s.length()<2){
            return s;
        }
        int start =0;
        int end =0;

        for(int i = 0; i<s.length();i++){

            int odd = expand(s,i,i);
            int even = expand(s,i,i+1);

            int full = Math.max(odd ,even);

            if(full > end - start + 1){
                start = i -(full -1)/2;
                end =i+ full/2;
            }
        }
        return s.substring(start,end +1);
    }

    private int expand(String s, int left,int right){
        while(left >=0 && right < s.length() && s.charAt(left)== s.charAt(right)){
            left--;
            right++;
        }

        return right - left -1;
    }
}