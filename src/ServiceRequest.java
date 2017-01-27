

public class ServiceRequest implements Comparable<ServiceRequest> {
    private int priority, categoryPriority;
    private String client;
    private String category;
    private String description;
    
    private String[] categories = {"CLASSROOM", "NETWORK", "SERVER", "LAB", "FACULTY", "STAFF"};
    
    public ServiceRequest(int priority, String client, String category, String description) {
        setPriority(priority);
        this.client = client;
        setCategory(category);
        this.description = description;
    }
        
    public int getPriority() {
        return priority;
    } 
        
    public void setPriority(int n) {
        if(n < 1 || n > 5) 
            throw new IllegalArgumentException("Please provide a valid priority.");
        priority = n;
    }
            
    public String getClient() {
        return client;
    }
            
    public void setClient(String c) {
        client = c;
    }
       
    public String getCategory() {
        return category;
    }
        
    public void setCategory(String c) {
        categoryPriority = 0;
        c = c.toUpperCase();
        for(int currentCategoryIndex = 0; currentCategoryIndex < categories.length; currentCategoryIndex++) {
            if(c.equals(categories[currentCategoryIndex]))
                categoryPriority = currentCategoryIndex+1;
        }
        if(categoryPriority == 0)
            throw new IllegalArgumentException("Please provide a valid category.");
        category = c;
    }
       
    public String getDescription() {
        return description;
    }
         
    public void setDescription(String d) {
        
    }
     
    public String toString() {
        return "Priority: "+priority+" - Category: "+category+" - Client: "+client+" - Description: "+description;
    }
        
    public int compareTo(ServiceRequest req) {
        return (priority == req.priority ? categoryPriority - req.categoryPriority : priority - req.priority);
    }
          
}