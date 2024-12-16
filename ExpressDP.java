/**
 * Represents a delivery person of type ExpressDP (Express Delivery Person).
 * ExpressDP delivery persons can only carry orders of the "IMPORTANT" urgency level,
 * and they are limited to carrying a maximum of two orders at a time.
 * 
 * @author (Yahya, Mario, Gled)
 * @version (a version number or a date)
 */
public class ExpressDP extends DeliveryPerson {

    /**
     * Constructor for creating an ExpressDP delivery person.
     * Sets the maximum load to 2, meaning they can carry up to two orders at a time.
     * 
     * @param company The delivery company to which the delivery person belongs.
     * @param location The starting location of the delivery person.
     * @param name The name of the delivery person.
     */
    public ExpressDP(DeliveryCompany company, Location location, String name) {
        super(company, location, name);
        this.setMaxLoad(2); // Can carry up to 2 orders at a time
    }

    /**
     * Pick up an order if it is of type "IMPORTANT" and if the ExpressDP
     * delivery person has space for more orders (up to the max load of 2).
     * 
     * @param order The order to be picked up.
     * @throws IllegalArgumentException If the order is not "IMPORTANT" or if the max load is exceeded.
     */
    @Override
    public void pickup(Order order) {
        if (order.getUrgency() == Urgency.IMPORTANT && getOrdersToDeliver().size() < getMaxLoad()) {
            super.pickup(order);
        } else {
            throw new IllegalArgumentException("ExpressDP can only carry IMPORTANT orders and up to two orders at a time.");
        }
    }

    /**
     * Performs the actions of the delivery person, including moving to the next location,
     * picking up the order, and delivering it.
     * If the delivery person is at the pickup location or the destination, the respective
     * arrival methods are triggered.
     */
    @Override
    public void act() {
        if (!hasTargetLocation() || getOrdersToDeliver().isEmpty()) {
            incrementIdleCount();
            return;
        }

        Location nextMove = getLocation().nextLocation(getTargetLocation());
        setLocation(nextMove);

        System.out.println("@@@  " + getClass().getName() + " " + getName() + " moving to: " + getLocation().getX() + " - " + getLocation().getY());

        Order firstOrder = getOrdersToDeliver().first();

        // If at the pickup location, notify arrival
        if (getLocation().equals(firstOrder.getLocationOrder())) {
            notifyPickupArrival();
        }

        // If at the destination, deliver the order
        if (getLocation().equals(firstOrder.getDestination())) {
            notifyOrderArrival(firstOrder);

            getOrdersToDeliver().remove(firstOrder); // Remove the delivered order from the collection

            if (!getOrdersToDeliver().isEmpty()) {
                setTargetLocation(getOrdersToDeliver().first().getDestination());
            } else {
                clearTargetLocation();
            }
        }
    }

    /**
     * Check if the delivery person can handle a given order based on its urgency.
     * ExpressDP can only handle orders with "IMPORTANT" urgency.
     * 
     * @param urgency The urgency of the order.
     * @return True if the urgency is "IMPORTANT", false otherwise.
     */
    public boolean puedeManejarPedido(Urgency urgency) {
        return urgency == Urgency.IMPORTANT;
    }

    /**
     * Return a string representation of the ExpressDP delivery person.
     * 
     * @return A string representing the ExpressDP delivery person.
     */
    /*@Override
    public String toString() {
        return "";
    }*/
}

