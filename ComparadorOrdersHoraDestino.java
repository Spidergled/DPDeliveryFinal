
import java.util.*;
/**
 * Write a description of class ComparadorOrdersHoraDestino here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ComparadorOrdersHoraDestino implements Comparator<Order>
{
    public int compare (Order o1, Order o2){
        int res;
        int nameComp = Integer.compare(o1.getDeliveryTime(), o2.getDeliveryTime());
        if(nameComp == 0) {
            nameComp= o1.getDestinationName().compareTo(o2.getDestinationName());
            
        }
        return nameComp;
        
    }
}
    
      
   