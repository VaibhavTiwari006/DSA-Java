package Stack_04;
/*
    PROBLEM: Merge Overlapping Intervals

    ASSUMPTIONS:
    - Input consists of n intervals where each interval has a start and end time.
    - Start time is always <= end time.
    - Intervals may or may not overlap.
    - Input is not sorted.

    INPUT FORMAT:
    - First line: Integer n (number of intervals)
    - Next n lines: Two integers per line representing start and end of interval

    OUTPUT FORMAT:
    - Print merged non-overlapping intervals (one per line)
    - Each line contains: start end

    SAMPLE INPUT:
    6
    1 3
    2 6
    8 10
    15 18
    17 20
    5 7

    SAMPLE OUTPUT:
    1 7
    8 10
    15 20

    TIME COMPLEXITY: O(n log n)  (due to sorting)
    SPACE COMPLEXITY: O(n)       (stack + pair array)
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class MergeOverlappingIntervals {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int arr[][] = new int[n][2];

        for (int j = 0; j < n; j++) {
            String line = br.readLine();
            arr[j][0] = Integer.parseInt(line.split(" ")[0]);
            arr[j][1] = Integer.parseInt(line.split(" ")[1]);
        }

        mergeOverlappingIntervals(arr);
    }

    public static void mergeOverlappingIntervals(int[][] arr) {
        Pair[] pairs = new Pair[arr.length];

        for (int i = 0; i < arr.length; i++) {
            pairs[i] = new Pair(arr[i][0], arr[i][1]);
        }

        Arrays.sort(pairs);

        Stack<Pair> st = new Stack<>();

        for (int i = 0; i < pairs.length; i++) {
            if (i == 0) {
                st.push(pairs[i]);
            } else {
                Pair top = st.peek();

                if (pairs[i].st > top.et) {
                    st.push(pairs[i]);
                } else {
                    top.et = Math.max(top.et, pairs[i].et);
                }
            }
        }

        Stack<Pair> res = new Stack<>();

        while (!st.isEmpty()) {
            res.push(st.pop());
        }

        while (!res.isEmpty()) {
            Pair p = res.pop();
            System.out.println(p.st + " " + p.et);
        }
    }

    public static class Pair implements Comparable<Pair> {
        int st;
        int et;

        Pair(int st, int et) {
            this.st = st;
            this.et = et;
        }

        public int compareTo(Pair other) {
            return this.st - other.st; // sort by start time
        }
    }
}