
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
        if(o1.getDeliveryTime()==o2.getDeliveryTime()){
             res= o1.getDestinationName().compareTo(o2.getDestinationName());            
        }else{
            if(o1.getDeliveryTime()>o2.getDeliveryTime()){
                res= 1;
            }else{
                res= -1;
            }
        }
        return res;
    }
}
    
      
   