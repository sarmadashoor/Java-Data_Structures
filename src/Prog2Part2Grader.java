/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dasty
 */
public class Prog2Part2Grader {
    JobScheduler js;
    final int NUMBER_OF_JOBS = 100;
    String [] cat = {"classroom","network","server","lab","faculty","staff"};
    
    public Prog2Part2Grader() {
        js = new JobScheduler(NUMBER_OF_JOBS);
        
        for(int i=0; i < NUMBER_OF_JOBS; i++) {
            int priority = (int) (5*Math.random()+1);
            String category = randomizedCategory();
            js.insertJob(new ServiceRequest(priority,"DUMMY",category,"DGAF"));                        
        }
        
        for(int i=0; i < NUMBER_OF_JOBS; i++) 
            System.out.println(js.nextJob() + 
            "\n====================================");
        
        try {
            js.insertJob(new ServiceRequest(6, "DUMMY", "CLASSROOM", "DGAF"));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        try {
            js.insertJob(new ServiceRequest(2, "DUMMY", "SSROOM", "DGAF"));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    private String randomizedCategory() {
        StringBuilder category = new StringBuilder(cat[(int) (6*Math.random())]);
        for(int i = 0; i < category.length(); i++)
            if(Math.random() > .5)
                category.setCharAt(i, category.substring(i, i+1).toUpperCase().charAt(0));
        return category.toString();
    }
    
    public static void main(String [] args) {
        new Prog2Part2Grader();
    }
}
