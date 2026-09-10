package Basics_01;
public class LargestElementInTheArray {
    public static void main(String args[]) {
        int arr[] = {3, 6, 6, 5, 9, 9, 6, -3, -43};
        int max = arr[0];
        Integer sec_max = null;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                sec_max = max;
                max = arr[i];
            }
            else if (arr[i] < max &&
                    (sec_max == null || arr[i] > sec_max)) {
                sec_max = arr[i];
            }
        }

        System.out.println("Largest: " + max);
        if (sec_max == null) {
            System.out.println("No second-largest distinct element");
        } else {
            System.out.println("Second largest: " + sec_max);
        }
    }
}