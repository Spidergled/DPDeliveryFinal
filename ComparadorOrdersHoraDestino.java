
import java.util.*;
/**
 * Comparator class for comparing orders based on their delivery time.
 * If two orders have the same delivery time, they are compared by the recipient's name.
 * This ensures orders are sorted by delivery time and, in case of a tie, by destination name.
 * 
 * @author Yahya, Mario, Gled
 * @version (version number or date here)
 * */
public class ComparadorOrdersHoraDestino implements Comparator<Order>
{
    /**
     * Compares two orders based on their delivery time.
     * If the delivery times are the same, it compares the orders by the destination name.
     * 
     * @param o1 The first order to compare.
     * @param o2 The second order to compare.
     * @return A negative integer, zero, or a positive integer as the first order
     *         is less than, equal to, or greater than the second order.
     */
    public int compare (Order o1, Order o2){
        int res;
        int nameComp = Integer.compare(o1.getDeliveryTime(), o2.getDeliveryTime());
        if(nameComp == 0) {
            nameComp= o1.getDestinationName().compareTo(o2.getDestinationName());
            
        }
        return nameComp;
        
    }
}
    
      
   