import java.util.Comparator;
/**
 * Comparator class for comparing orders based on their urgency type.
 * If the urgency types are the same, the comparison is done by delivery time.
 * If the delivery times are also the same, the comparison is done by the recipient's name.
 * 
 * @author Yahya, Mario, Gled
 * @version (version number or date here)
 */
public class ComparadorCollectionOrderTipo implements Comparator<Order> {
    /**
     * Compares two orders based on their urgency type.
     * If the urgency types are the same, the orders are compared by delivery time.
     * If the delivery times are also the same, the comparison is done by the recipient's name.
     * 
     * @param o1 The first order to compare.
     * @param o2 The second order to compare.
     * @return A negative integer, zero, or a positive integer as the first order
     *         is less than, equal to, or greater than the second order.
     */
    @Override
    public int compare(Order o1, Order o2) {
        // Comparar por el nombre del tipo de urgencia
        int comparison = o1.getUrgency().getName().compareTo(o2.getUrgency().getName());
        if (comparison == 0) {
            // Comparar por tiempo de entrega si las urgencias son iguales
            comparison = Integer.compare(o1.getDeliveryTime(), o2.getDeliveryTime());
        }
        if (comparison == 0) {
            // Comparar por nombre del destinatario si tiempo de entrega también es igual
            comparison = o1.getDestinationName().compareTo(o2.getDestinationName());
        }
        return comparison;
    }
}



