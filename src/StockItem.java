/*  StockItem.java

 */

public class StockItem implements Comparable<StockItem> {
    String SKU;
    String description;
    String vendor;
    float cost;
    float retail;
   
    // Constructor.  Creates a new StockItem instance.  
    public StockItem(String SKU, String description, String vendor,float cost, float retail) {
        this.SKU = SKU;
        this.description = description;
        this.vendor = vendor;
        this.cost = cost;
        this.retail = retail;
    }
    
    // Follows the specifications of the Comparable Interface.
    // The SKU is always used for comparisons, in dictionary order.  
    public int compareTo(StockItem n) {
        return ((Comparable<String>)SKU).compareTo(n.SKU);
    }
       
    // Returns an int representing the hashCode of the SKU.
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StockItem other = (StockItem) obj;
        if (((Comparable<String>)this.SKU).compareTo(other.SKU) != 0) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        byte [] b = SKU.getBytes();

        long hashVal = 0;
           
        for(int i=SKU.length()-1; i >= 0; i--) 
             hashVal = (hashVal << 5) + b[i]; 
        hashVal &= 0x000000007FFFFFF;     
        return (int) (hashVal);
    }
       
    // standard get methods
    public String getDescription() {
        return description;
    }
    
    public String getVendor() {
        return vendor;
    }
    
    public float getCost() {
        return cost;
    }
    
    public float getRetail() {
        return retail;
    }
        
    // All fields in one line, in order   
    public String toString() {
        return SKU+" "+description+" "+vendor+" "+cost+" "+retail;
    }
}