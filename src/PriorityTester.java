
import data_structures.BinaryHeapPriorityQueue;
import data_structures.ListPriorityQueue;
import java.util.Iterator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dasty
 */
public class PriorityTester 
{
    
    public PriorityTester()
    {
        ListPriorityQueue<Integer> pq = new ListPriorityQueue<Integer>();
        
        BinaryHeapPriorityQueue<Integer> bh = new BinaryHeapPriorityQueue<Integer>();
        int STRUCTURE_SIZE = 512;
        int[] array = new int[STRUCTURE_SIZE];
        int[] scrambled_array = new int[STRUCTURE_SIZE];
        for (int i = 0; i < STRUCTURE_SIZE; i++) {
            array[i] = scrambled_array[i] = (i);
        }

        // randomly rearrange array elements for better testing
        for (int i = 0; i < STRUCTURE_SIZE; i++) {
            int tmp = scrambled_array[i];
            int new_index = (int) (STRUCTURE_SIZE * Math.random());
            scrambled_array[i] = scrambled_array[new_index];
            scrambled_array[new_index] = tmp;
        }
        
        for(int i = 0; i < scrambled_array.length; i++)
            bh.insert(scrambled_array[i]);
        System.out.println(bh.size());
        //bh.clear();
        System.out.println(bh.size());
        for(Integer i : bh)
            System.out.println(i);
        
        pq.clear();
        
    }
    
    
    
    private class TestOrder implements Comparable
    {
        private long insertTime;
        public int value;
        public TestOrder(int i)
        {
            value = i;
            insertTime = System.nanoTime();
        }

        public int compareTo(Object o) {
            return value - ((TestOrder)o).value;
        }
        
        public String toString()
        {
            return "("+value+","+insertTime+")";
        }
    }
    
    public static void main(String[] args)
    {
        new PriorityTester();
    }
}
