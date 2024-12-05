import java.util.Comparator;

/**
 * Comparator to order DeliveryPersons based on their distance to the target location.
 * If distances are equal, it orders them alphabetically by name.
 */
public class ComparadorDeliveryPerson implements Comparator<DeliveryPerson> {
    @Override
    public int compare(DeliveryPerson dp1, DeliveryPerson dp2) {
        int distanceComparison = Integer.compare(dp1.distanceToTheTargetLocation(), dp2.distanceToTheTargetLocation());

        if (distanceComparison == 0) {
            return dp1.getName().compareTo(dp2.getName());
        }

        return distanceComparison;
    }
}
