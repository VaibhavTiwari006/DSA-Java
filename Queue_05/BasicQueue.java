package Queue_05;
import java.util.Queue;
public class BasicQueue { 

    static class Queue {
        int[] data = new int[10];
        int front = 0;
        int rear = 0;

        void add(int val) {
            data[rear] = val;
            rear++;
        }

        int remove() {
            int val = data[front];
            front++;
            return val;
        }

        int peek() {
            return data[front];
        }

        int size() {
            return rear - front;
        }
    }

    public static void main(String[] args) {
        Queue q = new Queue();

        q.add(10);
        q.add(20);
        q.add(30);

        System.out.println(q.peek());   // 10
        System.out.println(q.remove()); // 10
        System.out.println(q.peek());   // 20
        System.out.println(q.size());   // 2
    }
}