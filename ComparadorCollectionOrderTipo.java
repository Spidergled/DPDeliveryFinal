import java.util.Comparator;

public class ComparadorCollectionOrderTipo implements Comparator<Order> {
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



