
import data_structures.*;

public class JobScheduler {

    PriorityQueue<ServiceRequest> pq;
    // Use your OrderedArrayPriorityQueue<E> implementation here.

    public JobScheduler(int maxJobs) {
        pq = new OrderedArrayPriorityQueue(maxJobs);
    }

    // Insert a new job in the scheduler, returns false if insertion fails.
    public boolean insertJob(ServiceRequest req) {
        return pq.insert(req);
    }

    // Returns the next job of highest precedence based on the specifications
    // in the assignment, and removes it from the scheduler.
    public ServiceRequest nextJob() {
        return pq.remove();
    }

    // Returns the next job of highest precedence based on the specifications
    // in the assignment, but does NOT remove it. 
    public ServiceRequest peekJob() {
        return pq.peek();
    }

    // Returns true if more jobs remain in the scheduler, false if the scheduler
    // is empty
    public boolean hasMoreJobs() {
        return !pq.isEmpty();
    }

    // Returns true if the scheduler is full.
    public boolean schedulerFull() {
        return pq.isFull();
    }

    // Prints all jobs with the given priority, using toString()
    public void printJobs(int priority) {
        for(ServiceRequest sr : pq)
            if(sr.getPriority() == priority)
                System.out.println(sr);
    }

    // Prints all jobs, using toString()
    public void printJobs() {
        for(ServiceRequest sr : pq)
            System.out.println(sr);
    }

    // Returns the total number of jobs waiting in the scheduler with the given
    // priority.
    public int numberWaiting(int priority) {
        int numberWaiting = 0;
        for(ServiceRequest sr : pq)
            if(sr.getPriority() == priority)
                numberWaiting++;
        return numberWaiting;
    }

    // Returns the total number of jobs waiting in the scheduler.
    public int numberWaiting() {
        int numberWaiting = 0;
        for(ServiceRequest sr : pq)
            numberWaiting++;
        return numberWaiting;
    }
}
