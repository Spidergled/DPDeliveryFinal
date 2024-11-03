import java.util.*;
/**
 * Write a description of class ComparadorDeliveryPersonOrders here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ComparadorNombreDeliveryPersonOrder implements Comparator<Order> {
 
    public int compare(Order o1, Order o2) {
        
        int nameComp = o1.getSendingName().compareTo(o2.getSendingName());
        if(nameComp == 0) {
            nameComp= Integer.compare(o1.getDeliveryTime(), o2.getDeliveryTime());
            
        }
        return nameComp;
    
    }
}
