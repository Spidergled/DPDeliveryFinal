import java.util.Comparator;
import java.util.Map;
/**
 * Comparator class for comparing orders based on the sender's name.
 * If two orders have the same sender's name, they are compared by delivery time.
 * 
 * @author Yahya, Mario, Gled
 * @version (version number or date here)
 */
public class ComparadorMapSendingNameDeliveryTime implements  Comparator<Order> {
    /**
     * Compares two orders based on the sender's name.
     * If the sender's names are the same, it compares the orders by delivery time.
     * 
     * @param o1 The first order to compare.
     * @param o2 The second order to compare.
     * @return A negative integer, zero, or a positive integer as the first order
     *         is less than, equal to, or greater than the second order.
     */
    @Override
    public int compare(Order o1, Order o2) {
        int comparison = o1.getSendingName().compareTo(o2.getSendingName());
        if (comparison== 0) {
            comparison=Integer.compare(o1.getDeliveryTime(), o2.getDeliveryTime());
        }
        return comparison; 
    }
}


