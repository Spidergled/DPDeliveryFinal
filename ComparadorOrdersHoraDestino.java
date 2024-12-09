
import java.util.*;
/**
 * Write a description of class ComparadorOrdersHoraDestino here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ComparadorOrdersHoraDestino implements Comparator<Order>
{
  public int compare(Order o1, Order o2) {
        // Validaciones para evitar errores de nulos
        if (o1 == null || o2 == null) {
            throw new IllegalArgumentException("Orders cannot be null.");
        }
        if (o1.getSendingName() == null || o2.getSendingName() == null) {
            throw new IllegalArgumentException("Sender's name cannot be null.");
        }

        // Comparación principal: por hora de entrega
        int res = Integer.compare(o1.getDeliveryTime(), o2.getDeliveryTime());

        // Criterio de desempate: por nombre de quien envía el pedido
        if (res == 0) {
            res = o1.getSendingName().compareTo(o2.getSendingName());
        }
        return res;
    }
}
    
      
   