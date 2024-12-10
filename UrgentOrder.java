
/**
 * Write a description of class UrgentOrder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UrgentOrder extends Order
{
        public UrgentOrder(String sendingName, Location location, Location destination, int deliveryTime,
                       double weight, String destinationName, Surcharge surcharge, Urgency urgency) {
        super(sendingName, location, destination, deliveryTime, weight, destinationName, surcharge, urgency);
    }

    @Override
    public double charge() {
       double charge= getSurcharge().getValue() * 2; 
       addCharge(charge);
       return charge;
        
    }

    @Override
    public int calculateEvaluationDP() {
    return 10;
    }
}