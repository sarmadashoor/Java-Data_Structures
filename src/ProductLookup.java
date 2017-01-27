/*  ProductLookup.java
 */
import data_structures.*;
import java.util.Iterator;

public class ProductLookup {

    DictionaryADT<String,StockItem> table;
    
    // Constructor.  There is no argument-less constructor, or default size
    public ProductLookup(int maxSize) {
        table = new Hashtable(maxSize);
    }

    // Adds a new StockItem to the dictionary
    public void addItem(String SKU, StockItem item) {
        table.insert(SKU, item);
    }

    // Returns the StockItem associated with the given SKU, if it is
    // in the ProductLookup, null if it is not.
    public StockItem getItem(String SKU) {
        return table.getValue(SKU);
    }

    // Returns the retail price associated with the given SKU value.
    // -.01 if the item is not in the dictionary
    public float getRetail(String SKU) {
        float r = -0.01f;
        StockItem i = table.getValue(SKU);
        if(i != null)
            r = i.retail;
        return r;
    }

    // Returns the cost price associated with the given SKU value.
    // -.01 if the item is not in the dictionary
    public float getCost(String SKU) {
        float c = -0.01f;
        StockItem i = table.getValue(SKU);
        if(i != null)
            c = i.cost;
        return c;
    }

    // Returns the description of the item, null if not in the dictionary.
    public String getDescription(String SKU) {
        String s = null;
        StockItem i = table.getValue(SKU);
        if(i != null)
            s = i.description;
        return s;
    }

    // Deletes the StockItem associated with the SKU if it is
    // in the ProductLookup.  Returns true if it was found and
    // deleted, otherwise false.  
    public boolean deleteItem(String SKU) {
        return table.remove(SKU);
    }

    // Prints a directory of all StockItems with their associated
    // price, in sorted order (ordered by SKU).
    public void printAll() {
        Iterator<StockItem> values = table.values();
        while(values.hasNext())
            System.out.println(values.next());
    }

    // Prints a directory of all StockItems from the given vendor, 
    // in sorted order (ordered by SKU).
    public void print(String vendor) {
        Iterator<StockItem> values = table.values();
        while(values.hasNext()) {
            StockItem item = values.next();
            if(((Comparable<String>)item.vendor).compareTo(vendor) == 0)
                System.out.println(item);
        }
    }

    // An iterator of the SKU keys.
    public Iterator<String> keys() {
        return table.keys();
    }

    // An iterator of the StockItem values.    
    public Iterator<StockItem> values() {
        return table.values();
    }
}