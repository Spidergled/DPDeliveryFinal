import java.util.Comparator;

public class ComparadorOrderTipo implements Comparator<Order> {
    
    @Override
    public int compare(Order o1, Order o2) {
        // Comparar por urgencia (Emergency, Important, Non-essential)
        int urgencyComparison = o1.getUrgency().compareTo(o2.getUrgency());
        if (urgencyComparison != 0) {
            return urgencyComparison;  // Si las urgencias son diferentes, retorna la comparación
        }
        
        // Si las urgencias son iguales, se compara la hora de entrega
        int timeComparison = Integer.compare(o1.getDeliveryTime(), o2.getDeliveryTime());
        if (timeComparison != 0) {
            return timeComparison;  // Si las horas de entrega son diferentes, retorna la comparación
        }
        
        // Si las horas de entrega son iguales, se compara el nombre de la persona que recibe el pedido
        return o1.getDestinationName().compareTo(o2.getDestinationName());
    }
}

