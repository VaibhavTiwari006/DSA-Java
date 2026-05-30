package Queue_05;
public class Build_Normal_Queue {

    public static class CustomQueue {
        int[] data;
        int front;
        int size;

        public CustomQueue(int cap) {
            data = new int[cap];
            front = 0;
            size = 0;
        }

        int size() {
            return size;
        }

        void display() {
            for (int i = 0; i < size; i++) {
                int idx = (front + i) % data.length;
                System.out.print(data[idx] + " ");
            }
            System.out.println();
        }

        void add(int val) {
            if (size == data.length) {
                System.out.println("Queue Overflow");
            } else {
                int rear = (front + size) % data.length;
                data[rear] = val;
                size++;
            }
        }

        int remove() {
            if (size == 0) {
                System.out.println("Queue Underflow");
                return -1;
            }

            int val = data[front];
            front = (front + 1) % data.length;
            size--;

            return val;
        }

        int peek() {
            if (size == 0) {
                System.out.println("Queue Underflow");
                return -1;
            }

            return data[front];
        }
    }

    public static void main(String[] args) {
        CustomQueue q = new CustomQueue(5);

        q.add(10);
        q.add(20);
        q.add(30);

        q.display();

        System.out.println(q.peek());

        System.out.println(q.remove());

        q.display();
    }
}