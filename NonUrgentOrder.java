
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

    
    public double charge() {
        return getSurcharge().getValue();
    }

    @Override
    public int calculateEvaluationDP() {
        return 5;
    }
}