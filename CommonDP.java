import java.util.*;

/**
 * DeliveryPerson specializing in urgent and non-urgent orders.
 */
public class CommonDP extends DeliveryPerson {
    private int popularity;

    public CommonDP(DeliveryCompany company, Location location, String name) {
        super(company, location, name);
        this.setMaxLoad(4); //Puede llevar hasta 4 pedidos a la vez
        this.popularity = 6; //Popularidad inicial
    }

    @Override
    public void pickup(Order order) {
        if ((order.getUrgency() == Urgency.IMPORTANT || order.getUrgency() == Urgency.NONESSENTIAL) && getOrdersToDeliver().size() < getMaxLoad()) {
            super.pickup(order);
        } else {
            throw new IllegalArgumentException("CommonDP solo puede llevar pedidos de tipo Important o Non essential y hasta cuatro a la vez.");
        }
    }
    
    //IMPORTANT
    //NONESSENTIAL

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
            
            //Actualiza popularidad
            if (firstOrder.getUrgency() == Urgency.IMPORTANT) {
                popularity += 4;
            } else {
                popularity += 1;
            }
            
            getOrdersToDeliver().remove(firstOrder); // Elimina el pedido entregado de la colección
            
            // Entrega otros pedidos con la misma targetLocation
            while (!getOrdersToDeliver().isEmpty() && getOrdersToDeliver().first().getDestination().equals(getTargetLocation())) {
                Order nextOrder = getOrdersToDeliver().first();
                notifyOrderArrival(nextOrder);
                incrementOrdersDelivered();
                incrementTotalCharged(nextOrder.charge());
                incrementValuation(nextOrder.calculateEvaluationDP());
                getOrdersToDeliver().remove(nextOrder);
            }
        
            if (!getOrdersToDeliver().isEmpty()) {
                setTargetLocation(getOrdersToDeliver().first().getDestination());
            } else {
                clearTargetLocation();
            }
        }
    }

    public int getPopularity() {
        return popularity;
    }
    
    public boolean puedeManejarPedido(Urgency urgency) {
        return urgency == Urgency.IMPORTANT || urgency == Urgency.NONESSENTIAL;
    }
}
