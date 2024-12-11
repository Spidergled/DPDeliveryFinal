import java.util.Comparator;
import java.util.Map;

public class ComparadorOrdersNombreYHora implements Comparator<Map.Entry<Order, DeliveryPerson>> {

    @Override
    public int compare(Map.Entry<Order, DeliveryPerson> entry1, Map.Entry<Order, DeliveryPerson> entry2) {
        // Primero se compara el nombre del remitente (sendingName) del pedido
        int sendingNameComparison = entry1.getKey().getSendingName().compareTo(entry2.getKey().getSendingName());
        if (sendingNameComparison != 0) {
            return sendingNameComparison;  // Si los nombres del remitente son diferentes, retorna la comparación
        }

        // Si los nombres del remitente son iguales, se compara la hora de entrega (deliveryTime)
        int timeComparison = Integer.compare(entry1.getKey().getDeliveryTime(), entry2.getKey().getDeliveryTime());
        return timeComparison;  // Retorna la comparación de la hora de entrega
    }
}
