
import java.util.Arrays;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dasty
 */
public class SortingTester 
{
    public SortingTester()
    {
        Integer[] array = new Integer[10];
        for(int i = 0; i < 10; i++)
            array[i] = 9-i;
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(ObjectSorter.quickSort((Object[])array)));
    }
    
    protected static class ObjectSorter {
        public static Object[] quickSort(Object[] array) {
            return quickSort(array,0,array.length-1);
        }
        
        private static Object[] quickSort(Object[] array, int leftIndex, int rightIndex) {
            if(leftIndex < rightIndex) {
                int pivot = partition(array, leftIndex, rightIndex);
                quickSort(array, leftIndex, pivot-1);
                quickSort(array, pivot+1, rightIndex);
            }
            return array;
        }
        
        private static int partition(Object[] array, int leftIndex, int rightIndex) {
            int pivot = rightIndex;
            Object pivotItem = array[pivot];
            
            while(leftIndex < rightIndex)
            {
                while(((Comparable)array[leftIndex]).compareTo(array[pivot]) <= 0 && leftIndex < rightIndex)
                    leftIndex++;
                while(((Comparable)array[rightIndex]).compareTo(array[pivot]) >= 0 && leftIndex < rightIndex)
                    rightIndex--;
                if(leftIndex < rightIndex) {
                    Object tmp = array[leftIndex];
                    array[leftIndex] = array[rightIndex];
                    array[rightIndex] = tmp;
                } 
            }
            array[pivot] = array[leftIndex];
            array[leftIndex] = pivotItem;
            return leftIndex;
        }
    }
    
    public static void main(String[] args) {
        new SortingTester();
    }
}
