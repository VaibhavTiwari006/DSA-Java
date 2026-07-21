package Sorting_06;

import java.util.Scanner;

public class HeapSort {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = in.nextInt();

        int[] arr = new int[n];

        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        HeapSort hs = new HeapSort();
        hs.sort(arr);

        System.out.println("Sorted Array:");
        hs.printArray(arr);

        in.close();
    }

    void sort(int[] arr) {
        int len = arr.length;

        // Build Max Heap
        for (int i = len / 2 - 1; i >= 0; i--) {
            heapify(arr, len, i);
        }

        // Extract elements one by one
        for (int i = len - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    void heapify(int[] arr, int n, int i) {
        int largest = i;
        int li = 2 * i + 1;
        int ri = 2 * i + 2;

        if (li < n && arr[li] > arr[largest]) {
            largest = li;
        }

        if (ri < n && arr[ri] > arr[largest]) {
            largest = ri;
        }

        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            heapify(arr, n, largest);
        }
    }

    void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}