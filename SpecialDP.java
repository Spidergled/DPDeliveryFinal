import java.util.*;

/**
 * Represents a delivery person specializing in handling medical (emergency) orders.
 * The SpecialDP delivery person can only carry "EMERGENCY" orders and is limited to carrying one order at a time.
 * 
 * @author (Yahya, Mario, Gled)
 * @version (a version number or a date)
 */
public class SpecialDP extends DeliveryPerson {

    /**
     * Constructor for creating a SpecialDP delivery person.
     * Sets the maximum load to 1, meaning they can carry only one order at a time.
     * 
     * @param company The delivery company to which the delivery person belongs.
     * @param location The starting location of the delivery person.
     * @param name The name of the delivery person.
     */
    public SpecialDP(DeliveryCompany company, Location location, String name) {
        super(company, location, name);
        this.setMaxLoad(1); // Can carry only 1 order at a time
    }

    /**
     * Pickup an order if it is of type "EMERGENCY" and if the SpecialDP delivery person 
     * has space for more orders (limited to one order).
     * 
     * @param order The order to be picked up.
     * @throws IllegalArgumentException If the order is not of type "EMERGENCY" or if the maximum load is exceeded.
     */
    @Override
    public void pickup(Order order) {
        if (order.getUrgency() == Urgency.EMERGENCY && getOrdersToDeliver().size() < getMaxLoad()) {
            super.pickup(order);
        } else {
            throw new IllegalArgumentException("SpecialDP can only carry 'EMERGENCY' orders and can carry only one at a time.");
        }
    }

    /**
     * Check if the delivery person can handle a given order based on its urgency.
     * SpecialDP can only handle "EMERGENCY" orders.
     * 
     * @param urgency The urgency of the order.
     * @return True if the delivery person can handle the order, false otherwise.
     */
    public boolean puedeManejarPedido(Urgency urgency) {
        return urgency == Urgency.EMERGENCY;
    }

    /**
     * Return a string representation of the SpecialDP delivery person.
     * 
     * @return A string representing the SpecialDP delivery person.
     */
    /*@Override
    public String toString() {
        return "";
    }*/
}
