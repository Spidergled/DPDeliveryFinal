/**
 * Represents a medical order to be delivered from one location to another.
 * Medical orders are delivered free of charge and provide a higher evaluation score
 * for the delivery person upon successful delivery.
 * This type of order does not include any surcharge.
 * 
 * @author (Yahya, Mario, Gled)
 * @version (a version number or a date)
 */
public class MedicalOrder extends Order
{
    
    public MedicalOrder(String sendingName, Location location, Location destination, int deliveryTime,
                        double weight, String destinationName, Urgency urgency) {
        super(sendingName, location, destination, deliveryTime, weight, destinationName, urgency, null);
    }

    /**
     * Calcula el cobro por un pedido MedicalOrder.
     * Los pedidos médicos son gratuitos.
     * 
     * @return 0 (sin costo para los pedidos médicos).
     */
    @Override
    public double charge() {
    double charge=0;
       return charge;
    }

    /**
     * Calcula la evaluación para el repartidor.
     * 
     * @return Una evaluacioón fija de 15
     */
    @Override
    public int calculateEvaluationDP() {
        return 15;
    }
}
