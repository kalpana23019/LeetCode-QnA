class Solution {
    public boolean isHappy(int n) {
        
        int slow = n;
        int fast = getsum(n);
        while(fast != 1 && fast != slow){
            slow = getsum(slow);
            fast=getsum(getsum(fast));
        }
        return fast ==1;  
    }
    private int getsum(int n){
        int total = 0;
        while(n>0){
            int r = n%10;
            n=n/10;
            total+= r*r;
        }
        return total;
    }

}