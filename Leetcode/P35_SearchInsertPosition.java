/**
 Input: nums = [1,3,5,6], target = 5
Output: 2
Example 2:

Input: nums = [1,3,5,6], target = 2
Output: 1
***/
package Leetcode;
public class P35_SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
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
       return li;
    }
}