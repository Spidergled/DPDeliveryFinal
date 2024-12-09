import java.util.Comparator;

public class ComparadorDeliveryPerson implements Comparator<DeliveryPerson> {

    private Location warehouseLocation; // Ubicación del almacén

    // Constructor que recibe la ubicación del almacén
    public ComparadorDeliveryPerson(Location warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }

    @Override
    public int compare(DeliveryPerson dp1, DeliveryPerson dp2) {
        // Calcula las distancias al almacén
        int distance1 = dp1.getLocation().distance(warehouseLocation);
        int distance2 = dp2.getLocation().distance(warehouseLocation);

        // Compara por distancia, y si es igual, por nombre
        if (distance1 != distance2) {
            return Integer.compare(distance1, distance2);
        } else {
            return dp1.getName().compareTo(dp2.getName());
        }
    }
}



