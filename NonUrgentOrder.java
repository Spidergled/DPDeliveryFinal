
/**
 * Write a description of class NonUrgentOrder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NonUrgentOrder extends Order
{
    public NonUrgentOrder(String sendingName, Location location, Location destination, int deliveryTime,
                          double weight, String destinationName, Urgency urgency, Surcharge surcharge) {
        super(sendingName, location, destination, deliveryTime, weight, destinationName, urgency,surcharge);
    }

   @Override
    public double charge() {
    double charge=getSurcharge().getValor();
      return charge; 
    }

    @Override
    public int calculateEvaluationDP() {
    return 5; 
    }
}