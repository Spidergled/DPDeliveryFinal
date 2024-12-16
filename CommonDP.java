import java.util.*;

/**
 * A delivery person specializing in both urgent and non-urgent orders.
 * The CommonDP delivery person can carry up to 4 orders at a time and has a popularity score
 * that increases based on the urgency of the orders delivered. 
 * CommonDP can handle "IMPORTANT" and "NONESSENTIAL" orders.
 * 
 * @author (Yahya, Mario, Gled)
 * @version (a version number or a date)
 */
public class CommonDP extends DeliveryPerson {
    private int popularity;
    
    /**
     * Constructor for creating a CommonDP delivery person.
     * Sets the maximum load to 4 (the delivery person can carry up to 4 orders at a time),
     * and initializes the popularity to 6.
     * 
     * @param company The delivery company the person belongs to.
     * @param location The starting location of the delivery person.
     * @param name The name of the delivery person.
     */
    public CommonDP(DeliveryCompany company, Location location, String name) {
        super(company, location, name);
        this.setMaxLoad(4); // Puede llevar hasta 4 pedidos a la vez
        this.popularity = 6; // Popularidad inicial
    }

    /**
     * Pickup an order if the delivery person can handle it and has space.
     * CommonDP can only handle "IMPORTANT" or "NONESSENTIAL" orders, 
     * and they are limited to carrying up to 4 orders.
     * 
     * @param order The order to be picked up.
     * @throws IllegalArgumentException If the order's urgency is neither "IMPORTANT" nor "NONESSENTIAL",
     *                                  or if the delivery person has already reached the maximum load.
     */
    @Override
    public void pickup(Order order) {
        if (getOrdersToDeliver().size() < getMaxLoad()) {
            if (order.getUrgency() == Urgency.IMPORTANT || order.getUrgency() == Urgency.NONESSENTIAL) {
                super.pickup(order);
            } else {
                throw new IllegalArgumentException(getClass().getSimpleName() + " no puede manejar este tipo de pedido.");
            }
        } else {
            throw new IllegalStateException(getClass().getSimpleName() + " ha alcanzado su capacidad máxima.");
        }
    }
    
    
    /**
     * Performs the delivery actions for the CommonDP delivery person.
     * The delivery person will move to the next location, pick up the order, and deliver it.
     * After each delivery, the popularity score is updated based on the urgency of the order.
     */
    @Override
public void act() {
    if (!hasTargetLocation() || getOrdersToDeliver().isEmpty()) {
        incrementIdleCount();
        return;
    }

    // Calcula el siguiente movimiento hacia el destino
    Location nextMove = getLocation().nextLocation(getTargetLocation());
    setLocation(nextMove);

    System.out.println("@@@  " + getClass().getName() + " " + getName() + " moving to: " + getLocation().getX() + " - " + getLocation().getY());

    // Obtiene el primer pedido de la lista
    Order firstOrder = getOrdersToDeliver().first();

    // Si ha llegado al lugar de recogida del primer pedido
    if (getLocation().equals(firstOrder.getLocationOrder())) {
        notifyPickupArrival(); // Notifica que ha llegado al lugar de recogida
    }

    // Si ha llegado al destino del primer pedido
    if (getLocation().equals(firstOrder.getDestination())) {
        notifyOrderArrival(firstOrder); // Notifica que ha entregado el pedido

        // Actualiza la popularidad según la urgencia del pedido
        if (firstOrder.getUrgency() == Urgency.IMPORTANT) {
            popularity += 4;
        } else {
            popularity += 1;
        }

        // Elimina el primer pedido de la lista
        getOrdersToDeliver().remove(firstOrder);

        // Entrega otros pedidos con la misma ubicación de destino
        while (!getOrdersToDeliver().isEmpty() && getOrdersToDeliver().first().getDestination().equals(getTargetLocation())) {
            Order nextOrder = getOrdersToDeliver().first();
            
            // Actualiza la popularidad según la urgencia del pedido adicional
            if (nextOrder.getUrgency() == Urgency.IMPORTANT) {
                popularity += 4;
            } else {
                popularity += 1;
            }

            // Mostrar los movimientos del siguiente pedido
            //System.out.println("@@@@  " + getClass().getName() + " " + getName() + " is moving to deliver order: " + nextOrder.getOrderId());
            while (!getLocation().equals(nextOrder.getDestination())) {
                // Realiza los movimientos hasta el destino
                nextMove = getLocation().nextLocation(nextOrder.getDestination());
                setLocation(nextMove);
                
                System.out.println("@@@  " + getClass().getName() + " " + getName() + " moving to: " + getLocation().getX() + " - " + getLocation().getY());

            }

            // Una vez llegue al destino, notificar la entrega
            notifyOrderArrival(nextOrder);
            getOrdersToDeliver().remove(nextOrder); // Elimina el pedido entregado

            
        }

        // Si aún quedan pedidos por entregar, actualiza el destino
        if (!getOrdersToDeliver().isEmpty()) {
            setTargetLocation(getOrdersToDeliver().first().getDestination());
        } else {
            clearTargetLocation(); // Si no hay más pedidos, limpia el destino
        }
    }
}

    /**
     * Get the current popularity score of the delivery person.
     * The popularity score is based on the urgency of the orders delivered.
     * 
     * @return The popularity score of the delivery person.
     */
    public int getPopularity() {
        return popularity;
    }

    /**
     * Check if the delivery person can handle a given order based on its urgency.
     * 
     * @param urgency The urgency level of the order.
     * @return True if the delivery person can handle the order, false otherwise.
     */
    public boolean puedeManejarPedido(Urgency urgency) {
        return (urgency == Urgency.IMPORTANT || urgency == Urgency.NONESSENTIAL) &&
               getOrdersToDeliver().size() < getMaxLoad();
    }
    
   /* @Override
public String toString() {
    return  " - popularity: " + popularity;
}*/

    /**
     * Return a string representation of the CommonDP's final information delivery person, including their popularity score.
     * 
     * @return A string the final info with the name and popularity of the delivery person.
     */
    @Override
    public String showFinalInfo()
    {
         return super.showFinalInfo() + " - popularity: " + getPopularity();
    }

}



