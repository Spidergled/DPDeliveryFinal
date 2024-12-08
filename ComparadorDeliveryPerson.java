import java.util.Comparator;

public class ComparadorDeliveryPerson implements Comparator<DeliveryPerson> {
    @Override
    public int compare(DeliveryPerson dp1, DeliveryPerson dp2) {
        int distance1 = dp1.distanceToTheTargetLocation(); //CAMBIAR esto por la ubicacion del almacen, 
        int distance2 = dp2.distanceToTheTargetLocation(); // lo que se esta devolviendo ahi es la ubicacion de la entrega.
        if (distance1 != distance2) {
            return Integer.compare(distance1, distance2);
        } else {
            return dp1.getName().compareTo(dp2.getName());
        }
    }
}


