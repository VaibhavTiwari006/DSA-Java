package Leetcode;
public class P704_BinarySearch {
    public int search(int[] nums, int target) {
        int li=0;
        int hi=nums.length-1;
        while(li <= hi){
            int mid = (li+hi)/2;
            if(target == nums[mid] ){
                return mid;
            }
            else if(target < nums[mid]){
                hi = mid-1;
            }
            else if(target > nums[mid]){
                li = mid+1;
            }
        }
        return -1;
    }
}
