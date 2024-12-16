/**
 * Represents an urgent order to be delivered from one location to another.
 * Urgent orders apply a double surcharge for delivery and provide a higher
 * evaluation score for the delivery person.
 * 
 * @author (Yahya, Mario, Gled)
 * @version (a version number or a date)
 */
public class UrgentOrder extends Order
{

    /**
     * Constructor for creating a non-urgent order.
     * 
     * @param sendingName The name of the sender.
     * @param location The pickup location of the order, must not be null.
     * @param destination The destination location of the order, must not be null.
     * @param deliveryTime The hour of delivery.
     * @param weight The weight of the order.
     * @param destinationName The name of the person receiving the order.
     * @param urgency The urgency level of the order.
     * @param surcharge The surcharge associated with the order.
     * @throws NullPointerException If either location or destination is null.
     */
    public UrgentOrder(String sendingName, Location location, Location destination, int deliveryTime,
                       double weight, String destinationName, Urgency urgency,Surcharge surcharge) {
        super(sendingName, location, destination, deliveryTime, weight, destinationName, urgency, surcharge);
    }
    
    /**
     * Calculates the charge for a non-urgent order based on its surcharge.
     * 
     * @return The charge amount, which is the value of the surcharge.
     */
    @Override
    public double charge() {
      double charge= getSurcharge().getValor() * 2; 
      return charge; 
        
    }
    
    /**
     * Calculates the evaluation score for the delivery person for a non-urgent order.
     * 
     * @return The evaluation score, which is fixed at 5.
     */
    @Override
    public int calculateEvaluationDP() {
        return 10;
    }
}