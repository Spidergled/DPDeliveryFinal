import java.util.*;

/**
 * DeliveryPerson specializing in urgent and non-urgent orders.
 */
public class CommonDP extends DeliveryPerson {
    private int popularity;

    public CommonDP(DeliveryCompany company, Location location, String name) {
        super(company, location, name);
        this.popularity = 6;
    }

    @Override
    public void pickup(Order order) {
        if (order.getUrgency() != Urgency.IMPORTANT && order.getUrgency() != Urgency.NONESSENTIAL) {
            throw new IllegalArgumentException("CommonDP can only handle orders with IMPORTANT or NONESSENTIAL urgency.");
        }
        super.pickup(order);
    }

    @Override
    public void act() {
        if (!hasTargetLocation() || ordersToDeliver.isEmpty()) {
            incrementIdleCount();
            System.out.println("Idle: No orders to deliver.");
            return;
        }

        Location nextMove = getLocation().nextLocation(getTargetLocation());
        setLocation(nextMove);

        Order firstOrder = ordersToDeliver.first();
        if (getLocation().equals(firstOrder.getLocationOrder())) {
            notifyPickupArrival();
        }

        if (getLocation().equals(firstOrder.getDestination())) {
            notifyOrderArrival(firstOrder);
            incrementOrdersDelivered();
            incrementTotalCharged(firstOrder.charge());
            incrementValuation(firstOrder.calculateEvaluationDP());

            // Update popularity
            if (firstOrder.getUrgency() == Urgency.IMPORTANT) {
                popularity += 4;
            } else {
                popularity += 1;
            }

            // Deliver other orders with the same destination
            Iterator<Order> iterator = ordersToDeliver.iterator();
            while (iterator.hasNext()) {
                Order nextOrder = iterator.next();
                if (nextOrder.getDestination().equals(getTargetLocation())) {
                    notifyOrderArrival(nextOrder);
                    incrementOrdersDelivered();
                    incrementTotalCharged(nextOrder.charge());
                    incrementValuation(nextOrder.calculateEvaluationDP());
                    iterator.remove();
                }
            }
        }
    }

    public int getPopularity() {
        return popularity;
    }
}
