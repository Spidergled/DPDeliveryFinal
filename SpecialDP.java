import java.util.*;

/**
 * DeliveryPerson specializing in medical orders.
 */
public class SpecialDP extends DeliveryPerson {
    public SpecialDP(DeliveryCompany company, Location location, String name) {
        super(company, location, name);
    }

    @Override
    public void pickup(Order order) {
        if (order.getUrgency() != Urgency.EMERGENCY) {
            throw new IllegalArgumentException("SpecialDP can only handle medical orders with EMERGENCY urgency.");
        }
        super.pickup(order);
    }
}