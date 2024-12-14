import java.util.Comparator;

public class ComparadorCollectionOrderTipo implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        int urgencyCompare = Integer.compare(o2.getUrgency().getValor(), o1.getUrgency().getValor());
        if (urgencyCompare != 0) {
            return urgencyCompare;
        }
        int timeCompare = Integer.compare(o1.getDeliveryTime(), o2.getDeliveryTime());
        if (timeCompare != 0) {
            return timeCompare;
        }
        return o1.getDestinationName().compareTo(o2.getDestinationName());
    }
}


