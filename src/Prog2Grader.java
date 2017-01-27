
import data_structures.*;

public class Prog2Grader {

    PriorityQueue<Job> pq;
    PriorityQueue<Integer> pqInt;

    public Prog2Grader() {
        try {
            pq = new OrderedArrayPriorityQueue<Job>();
            pqInt = new OrderedArrayPriorityQueue<Integer>(512000);
            System.out.println("Ordered Array-----------------------------------");
            runTests();
        } catch (Exception e) {
            System.err.println("Ordered Array Failed");
        }
        try {
            pq = new UnorderedArrayPriorityQueue<Job>();
            pqInt = new UnorderedArrayPriorityQueue<Integer>(512000);
            System.out.println("\n\nUnordered Array---------------------------------");
            runTests();
        } catch (Exception e) {
            System.err.println("Ordered Array Failed");
        }
        try {
            pq = new OrderedListPriorityQueue<Job>();
            pqInt = new OrderedListPriorityQueue<Integer>();
            System.out.println("\n\nOrdered List------------------------------------");
            runTests();
        } catch (Exception e) {
            System.err.println("Ordered Array Failed");
        }
        try {
            pq = new UnorderedListPriorityQueue<Job>();
            pqInt = new UnorderedListPriorityQueue<Integer>();
            System.out.println("\n\nUnordered List----------------------------------");
            runTests();
        } catch (Exception e) {
            System.err.println("Ordered Array Failed");
        }
    }

    private void runTests() {
        try {
            insertionTest();
        } catch (Exception ex) {
            System.err.println("Insertion Failed");
        }
        pq.clear();
        try {
            removalTest();
        } catch (Exception ex) {
            System.err.println("Removal Failed");
        }
        pq.clear();
        try {
            peekTest();
        } catch (Exception ex) {
            System.err.println("Peek Failed");
        }
        pq.clear();
        try {
            containsTest();
        } catch (Exception ex) {
            System.err.println("Contains Failed");
        }
        pq.clear();
        System.out.println("\n");
        timingTests();
    }

    private void insertionTest() throws Exception {
        if (pq.size() != 0) {
            throw new Exception("Insertion Failed");
        }

        pq.insert(new Job(5, "A"));

        if (pq.size() != 1) {
            throw new Exception("Insertion Failed");
        }

        pq.insert(new Job(7, "B"));
        pq.insert(new Job(2, "C"));
        pq.insert(new Job(10, "D"));
        pq.insert(new Job(5, "E"));
        pq.insert(new Job(1, "F"));
        pq.insert(new Job(5, "G"));
        pq.insert(new Job(1, "H"));

        if (pq.size() != 8) {
            throw new Exception("Insertion Failed");
        }

        //Enable this to test the iterator        
        System.out.println("Insertion Iterator: ");
        for (Job j : pq) {
            System.out.println(j);
        }
    }

    private void removalTest() throws Exception {
        if (pq.size() != 0) {
            throw new Exception("Removal Failed");
        }

        if (pq.remove() != null) {
            throw new Exception("Removal Failed");
        }

        pq.insert(new Job(5, "A"));
        pq.insert(new Job(7, "B"));
        pq.insert(new Job(2, "C"));
        pq.insert(new Job(10, "D"));

        Job j = pq.remove();
        if (j.label != "C" || j.priority != 2) {
            throw new Exception("Removal Failed");
        }

        if (pq.size() != 3) {
            throw new Exception("Removal Failed");
        }

        pq.insert(new Job(2, "C"));
        pq.insert(new Job(5, "E"));
        pq.insert(new Job(1, "F"));
        pq.insert(new Job(5, "G"));
        pq.insert(new Job(1, "H"));

        System.out.println("\nIn Order Removal: ");
        while (!pq.isEmpty()) {
            System.out.println(pq.remove());
        }

        if (pq.size() != 0) {
            throw new Exception("Removal Failed");
        }
    }

    private void peekTest() throws Exception {
        if (pq.size() != 0) {
            throw new Exception("Peek Failed");
        }

        if (pq.peek() != null) {
            throw new Exception("Peek Failed");
        }

        pq.insert(new Job(5, "A"));
        pq.insert(new Job(7, "B"));
        pq.insert(new Job(2, "C"));
        pq.insert(new Job(10, "D"));

        if (pq.peek().priority != 2 || pq.peek().label != "C") {
            throw new Exception("Peek Failed");
        }

        if (pq.size() != 4) {
            throw new Exception("Peek Failed");
        }
    }

    private void containsTest() throws Exception {
        if (pq.size() != 0) {
            throw new Exception("Contains Failed");
        }

        if (pq.contains(new Job(5, "A"))) {
            throw new Exception("Contains Failed");
        }

        pq.insert(new Job(5, "A"));
        pq.insert(new Job(7, "B"));
        pq.insert(new Job(2, "C"));
        pq.insert(new Job(10, "D"));

        if (!pq.contains(new Job(5, "A"))) {
            throw new Exception("Contains Failed");
        }

        if (pq.size() != 4) {
            throw new Exception("Contains Failed");
        }

        pq.insert(new Job(5, "E"));
        pq.insert(new Job(1, "F"));
        pq.insert(new Job(5, "G"));
        pq.insert(new Job(1, "H"));

        if (!pq.contains(new Job(5, "B"))) {
            throw new Exception("Contains Failed");
        }

        if (pq.contains(new Job(11, "B"))) {
            throw new Exception("Contains Failed");
        }

        if (pq.size() != 8) {
            throw new Exception("Contains Failed");
        }
    }

    private void timingTests() {
        int iterations = 10;
        int structureSize = 100;
        int loopStructureSize = structureSize;

        long start = 0, stop = 0;
        long[] insertTimes = new long[iterations];
        long[] removeTimes = new long[iterations];

        for (int i = 0; i < iterations; i++) {
            // build structure first
            pqInt.clear();
            start = System.currentTimeMillis();   // capture time to build       
            for (int j = 0; j < structureSize; j++) {
                int x = (int) (Integer.MAX_VALUE * Math.random());
                pqInt.insert(x);
            }
            stop = System.currentTimeMillis();
            insertTimes[i] = (stop - start);


            // time for removal (dequeue) operations
            start = System.currentTimeMillis();
            while (!pqInt.isEmpty()) {
                pqInt.remove();
            }
            stop = System.currentTimeMillis();
            removeTimes[i] = (stop - start);
            structureSize <<= 1;
        }

        // print results   
        int tmp = loopStructureSize;
        System.out.println("\nINSERTION TIMES:");
        for (int i = 0; i < iterations; i++) {
            System.out.println("n=" + tmp + "  Time: " + insertTimes[i]);
            tmp <<= 1;
        }
        tmp = loopStructureSize;
        System.out.println("\nRemoval TIMES:");
        for (int i = 0; i < iterations; i++) {
            System.out.println("n=" + tmp + "  Time: " + removeTimes[i]);
            tmp <<= 1;
        }
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
        new Prog2Grader();
    }
}
