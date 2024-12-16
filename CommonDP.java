import java.util.*;

/**
 * DeliveryPerson specializing in urgent and non-urgent orders.
 */
public class CommonDP extends DeliveryPerson {
    private int popularity;

    public CommonDP(DeliveryCompany company, Location location, String name) {
        super(company, location, name);
        this.setMaxLoad(4); // Puede llevar hasta 4 pedidos a la vez
        this.popularity = 6; // Popularidad inicial
    }

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


    public int getPopularity() {
        return popularity;
    }

    public boolean puedeManejarPedido(Urgency urgency) {
        return (urgency == Urgency.IMPORTANT || urgency == Urgency.NONESSENTIAL) &&
               getOrdersToDeliver().size() < getMaxLoad();
    }
    
    @Override
public String toString() {
    return  " - popularity: " + popularity;
}

}



