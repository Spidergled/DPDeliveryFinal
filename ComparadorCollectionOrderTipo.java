import java.util.Comparator;

public class ComparadorCollectionOrderTipo implements Comparator<Order> {
    
    @Override
    public int compare(Order o1, Order o2) {
        int comparison = o1.getUrgency().getName().compareTo(o2.getUrgency().getName());
        if (comparison== 0) {
            comparison=Integer.compare(o1.getDeliveryTime(), o2.getDeliveryTime());
        }else
        if(comparison==0){
            comparison=o1.getDestinationName().compareTo(o2.getDestinationName());
        }
        return comparison; 
    }
}

