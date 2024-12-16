
/**
 * Represents a non-urgent order to be delivered from one location to another.
 * Non-urgent orders apply a standard surcharge for delivery and provide a fixed
 * evaluation score for the delivery person.
 * 
 * @author (yahya, mario, gled)
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