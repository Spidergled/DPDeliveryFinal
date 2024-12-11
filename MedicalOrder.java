
/**
 * Write a description of class MedicalOrder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MedicalOrder extends Order
{
    
    public MedicalOrder(String sendingName, Location location, Location destination, int deliveryTime,
                        double weight, String destinationName, Surcharge surcharge, Urgency urgency) {
        super(sendingName, location, destination, deliveryTime, weight, destinationName, surcharge, urgency);
    }

    /**
     * Calcula el cobro por un pedido MedicalOrder.
     * Los pedidos médicos son gratuitos.
     * 
     * @return 0 (sin costo para los pedidos médicos).
     */
    @Override
    public double charge() {
        return 0;
    }

    /**
     * Calcula la evaluación para el repartidor.
     * 
     * @return Una evaluacioón fija de 15
     */
    @Override
    public int calculateEvaluationDP() {
        int evaluation = 15;
        addDeliveryPersonEvaluation(evaluation); // Lo que esta haciendo aqui es incrementar la valoracion del repartidor
        return evaluation;
    }
}
