public class ExpressDP extends DeliveryPerson {
    public ExpressDP(DeliveryCompany company, Location location, String name) {
      
        super(company, location, name);
        this.setMaxLoad(2); //Puede llevar hasta 2 pedidos a la vez
        
    }

    @Override
    public void pickup(Order order) {
        if (order.getUrgency() == Urgency.IMPORTANT && getOrdersToDeliver().size() < getMaxLoad()) {
            super.pickup(order); 
        } else { 
            throw new IllegalArgumentException("ExpressDP solo puede llevar pedidos de tipo Important y hasta dos a la vez."); 
        } 
    }

    @Override
    public void act() {
        if (!hasTargetLocation() || getOrdersToDeliver().isEmpty()) {
            incrementIdleCount();
        }

        Location nextMove = getLocation().nextLocation(getTargetLocation());
        setLocation(nextMove);

        System.out.println("@@@  " + getClass().getName() + " " + getName() + " moving to: " + getLocation().getX() + " - " + getLocation().getY());

        Order firstOrder = getOrdersToDeliver().first();

        if (getLocation().equals(firstOrder.getLocationOrder())) {
            notifyPickupArrival();
        }

        if (getLocation().equals(firstOrder.getDestination())) {
            notifyOrderArrival(firstOrder);
            incrementOrdersDelivered();
            incrementTotalCharged(firstOrder.charge());
            incrementValuation(firstOrder.calculateEvaluationDP());

            getOrdersToDeliver().remove(firstOrder); // Elimina el pedido entregado de la colección

            if (!getOrdersToDeliver().isEmpty()) {
                setTargetLocation(getOrdersToDeliver().first().getDestination());
            } else {
                clearTargetLocation();
            }
        }
    }
}

