import java.util.Comparator;

/**
 * Comparator to order DeliveryPersons based on their distance to the target location.
 * If distances are equal, it orders them alphabetically by name.
 */
public class ComparadorDeliveryPerson implements Comparator<DeliveryPerson> {
    @Override
    public int compare(DeliveryPerson dp1, DeliveryPerson dp2) {
        Location warehouseLocation = new Location(5, 5); // Fija la posición del almacén.
        int distancia1 = dp1.getLocation().distance(warehouseLocation);
        int distancia2 = dp2.getLocation().distance(warehouseLocation);

        if (distancia1 != distancia2) {
            return Integer.compare(distancia1, distancia2); // Ordena por distancia.
        }
        return dp1.getName().compareTo(dp2.getName()); // Desempata por nombre.
    }
}
