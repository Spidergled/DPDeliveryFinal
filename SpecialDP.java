import java.util.*;

/**
 * DeliveryPerson specializing in medical orders.
 */
public class SpecialDP extends DeliveryPerson {
    public SpecialDP(DeliveryCompany company, Location location, String name) {
        super(company, location, name);
        this.setMaxLoad(1);
    }

    @Override
    public void pickup(Order order) {
        if (order.getUrgency() == Urgency.EMERGENCY && getOrdersToDeliver().size() < getMaxLoad()) {
            super.pickup(order); 
        } else { 
            throw new IllegalArgumentException("SpecialDP solo puede llevar pedidos de tipo Emergency y uno a la vez."); 
        } 
    }
    
    public boolean puedeManejarPedido(Urgency urgency) {
        return urgency == Urgency.EMERGENCY; 
    }
    
        @Override
    public String toString() {
    return  " ";
    }
}