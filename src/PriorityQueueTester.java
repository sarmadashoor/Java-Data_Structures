
import data_structures.*;

public class PriorityQueueTester {

    public PriorityQueueTester() {
        doJobTest();
    }

    private void doJobTest() {
        PriorityQueue<Job> pq =
                //new OrderedArrayPriorityQueue<Job>();
                //new UnorderedArrayPriorityQueue<Job>();
                //new OrderedListPriorityQueue<Job>();
                new UnorderedListPriorityQueue<Job>();
        
        System.out.println(pq.remove()+" - should be null");
        System.out.println(pq.size()+" - should be 0");
        System.out.println(pq.peek()+" - should be null");
        System.out.println(pq.contains(new Job(5, "A"))+" - should be false");
        
        pq.insert(new Job(5, "A"));
        pq.insert(new Job(7, "B"));
        pq.insert(new Job(2, "C"));
        pq.insert(new Job(10, "D"));
        pq.insert(new Job(5, "E"));
        pq.insert(new Job(1, "F"));
        pq.insert(new Job(5, "G"));
        pq.insert(new Job(1, "H"));
        
        System.out.println(pq.peek()+" - should be 1 F");
        System.out.println(pq.contains(new Job(5, "A"))+" - should be true");
        System.out.println(pq.size()+" - should be 8");

        //Enable this to test the iterator        
        System.out.println("Now printing contents of the PQ, any order");
        for (Job j : pq) {
            System.out.println(j);
        }


        System.out.println("Now dequeuing, the must come out in proper order");
        while (!pq.isEmpty()) {
            System.out.println(pq.remove());
        }
        
        System.out.println(pq.remove()+" - should be null");
        System.out.println(pq.peek()+" - should be null");
        System.out.println(pq.size()+" - should be 0");
        System.out.println(pq.contains(new Job(5, "A"))+" - should be false");
    }

    private class Job implements Comparable<Job> {

        private int priority;
        private String label;

        public Job(int p, String s) {
            priority = p;
            label = s;
        }

        public int compareTo(Job j) {
            return priority - j.priority;
        }

        public String toString() {
            return label + " priority=" + priority;
        }
    }

    public static void main(String args[]) {
        new PriorityQueueTester();
    }
}
