import java.util.Comparator;
import java.util.Map;

public class ComparadorMapSendingNameDeliveryTime implements  Comparator<Order> {
    
    @Override
    public int compare(Order o1, Order o2) {
        int comparison = o1.getSendingName().compareTo(o2.getSendingName());
        if (comparison== 0) {
            comparison=Integer.compare(o1.getDeliveryTime(), o2.getDeliveryTime());
        }
        return comparison; 
    }
}


