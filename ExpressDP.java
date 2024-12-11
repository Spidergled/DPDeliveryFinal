public class ExpressDP extends DeliveryPerson {
    public ExpressDP(DeliveryCompany company, Location location, String name) {
      
        super(company, location, name);
        
    }

    @Override
    public void pickup(Order order) {
        if (order.getUrgency() != Urgency.IMPORTANT) {
            throw new IllegalArgumentException("ExpressDP can only handle orders with IMPORTANT urgency.");
        }
        super.pickup(order);
    }

    @Override
    public void act() {
        super.act();
        if (!ordersToDeliver.isEmpty() && ordersToDeliver.first().getDestination().equals(getTargetLocation())) {
            notifyOrderArrival(ordersToDeliver.first());
        }
    }
}
