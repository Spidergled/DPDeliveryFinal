
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
        int res;
        if(o1.getDeliveryTime()==o2.getDeliveryTime()){
             res= o1.getSendingName().compareTo(o2.getSendingName());            
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
