
import java.util.*;
/**
 * Write a description of class 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ComparadorOrdersHoraNombre implements Comparator<Order>
{
    public int compare (Order o1, Order o2){
        
        int nameComp = Integer.compare(o1.getDeliveryTime(), o2.getDeliveryTime());
        if(nameComp == 0) {
            nameComp= o1.getDestinationName().compareTo(o2.getDestinationName());
            
        }
        //System.out.println(o1.getDestinationName() + "-- "+ o2.getDestinationName() + ">>" + nameComp);
        return nameComp;
    
    }
       
}
