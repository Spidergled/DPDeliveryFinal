
/**
 * Write a description of class NonUrgentOrder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NonUrgentOrder extends Order
{
        public NonUrgentOrder(String sendingName, Location location, Location destination, int deliveryTime,
                          double weight, String destinationName, Surcharge surcharge, Urgency urgency) {
        super(sendingName, location, destination, deliveryTime, weight, destinationName, surcharge, urgency);
    }

    @Override
    public double charge() {
        return getSurcharge().getValue(); // El recargo es el valor original para pedidos no urgentes
    }

    @Override
    public int calculateEvaluationDP() {
        int evaluation = 5;
        addDeliveryPersonEvaluation(evaluation); // Acumula la evaluación
        return evaluation;
    }
}