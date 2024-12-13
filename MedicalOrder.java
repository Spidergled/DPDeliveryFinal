
/**
 * Write a description of class MedicalOrder here.
 * 
 * @author (your name) 
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
