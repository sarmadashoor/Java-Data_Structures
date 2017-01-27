/*  SchedulerDriver.java
   
*/    


public class SchedulerDriver {
    public static void main(String [] args) {
        final int NUMBER_OF_JOBS = 100;
        JobScheduler scheduler = new JobScheduler(NUMBER_OF_JOBS);
        ServiceRequest req;
        int priority;
        String client, category, description;
        
        String [] cat = {"Classroom","Network","Server","Lab","Faculty","Staff"};
        String [] fName = {"John","Bill","Sam","William","David","Robert",
                           "Arthur","Mike","Shawn","Joe","Chris","Jason",
                           "Mary","Susan","Ashley","Amy","Sally","Katie",
                           "Karen","Nancy","Louise","Kimberly","Marie"};
        String [] lName = {"Johnson","Smith","Jones","Searle","Simpson",
                           "Zimmermann","Rego","Newman","Peterson","O'Konski",
                           "Jamison","Nguyen","Chang","Ramirez","Perez",
                           "Donaldson","Lee","Epstein","Horowitz","O'Dell"};
        String [] problems = {"Software Installation", "Replace Hard Drive",
                              "Add RAM","Replace Video Card","Install DVD Drive",
                              "Reinstall Windows","Monitor not working",
                              "Replace network card","Fix cables",
                              "Boot failure","Unknown Software Problem"};
                              
        System.out.println("***** First printing jobs as they "+
                           "are inserted *****\n\n");
        
        for(int i=0; i < NUMBER_OF_JOBS; i++) {
            priority = (int) (5*Math.random()+1);
            client = fName[(int)(23*Math.random())] + " " +
                     lName[(int)(20*Math.random())];
            category = cat[(int)(6*Math.random())];
            description = problems[(int)(11*Math.random())];
            req = new ServiceRequest(priority,client,category,description);
            scheduler.insertJob(req);                        
            System.out.println(req+"\n====================================");
            }
         
        System.out.println("\n\n***** Now printing jobs as they come " +
                           "out of the scheduler *****");
        
        for(int i=0; i < NUMBER_OF_JOBS; i++) 
            System.out.println(scheduler.nextJob() + 
            "\n====================================");
        }
}     