
import data_structures.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dasty
 */
public class Tester 
{
    ListADT<String> list;
    public Tester()
    {
        list = new LinkedListDS<String>();
        for(String s : list)
            System.out.println(s);
        System.out.println("------------------------");
        System.out.println(list.contains("b"));
        System.out.println(list.size());
        System.out.println("------------------------");
        for(String s : list)
            System.out.println(s);
    }
    public static void main(String[] args)
    {
        Tester tester = new Tester();
    }
}
