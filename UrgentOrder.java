
/**
 * Write a description of class UrgentOrder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UrgentOrder extends Order
{
    public UrgentOrder(String sendingName, Location location, Location destination, int deliveryTime,
                       double weight, String destinationName, Urgency urgency,Surcharge surcharge) {
        super(sendingName, location, destination, deliveryTime, weight, destinationName, urgency, surcharge);
    }

    @Override
    public double charge() {
      double charge= getSurcharge().getValor() * 2; 
      return charge; 
        
    }

    @Override
    public int calculateEvaluationDP() {
        return 10;
    }
}