import java.util.Comparator;
/**
 * Comparator class for comparing delivery persons based on their distance to the warehouse.
 * If the distances are the same, the comparison is done based on the delivery person's name.
 * 
 * @author Yahya, Mario, Gled
 * @version (version number or date here)
 */
public class ComparadorDeliveryPerson implements Comparator<DeliveryPerson> {

    private Location warehouseLocation; // Ubicación del almacén

    // Constructor que recibe la ubicación del almacén
    /**
     * Constructor that initializes the comparator with the warehouse location.
     * 
     * @param warehouseLocation The location of the warehouse to compare distances from.
     */
    public ComparadorDeliveryPerson(Location warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }
    
    /**
     * Compares two delivery persons based on their distance to the warehouse.
     * If the distances are the same, the comparison is done by their names.
     * 
     * @param dp1 The first delivery person to compare.
     * @param dp2 The second delivery person to compare.
     * @return A negative integer, zero, or a positive integer as the first delivery person
     *         is closer to, at the same distance as, or farther from the warehouse than the second.
     */
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



