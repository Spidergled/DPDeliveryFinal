
/**
 * Write a description of class MedicalOrder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MedicalOrder extends Order {
    public MedicalOrder(String sendingName, Location location, Location destination, int deliveryTime,
                        double weight, String destinationName, Surcharge surcharge, Urgency urgency) {
        super(sendingName, location, destination, deliveryTime, weight, destinationName, surcharge, urgency);
    }

    @Override
    public double charge() {
        double charge=0;
      addCharge(charge); 
      return charge; 
    }

    @Override
    public int calculateEvaluationDP() {
    return 15; 
    }
}