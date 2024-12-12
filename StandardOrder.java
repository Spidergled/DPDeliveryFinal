public class StandardOrder extends Order {
    public StandardOrder(String sendingName, Location location, Location destination, int deliveryTime,
                         double weight, String destinationName, Urgency urgency, Surcharge surcharge) {
        super(sendingName, location, destination, deliveryTime, weight, destinationName, urgency, surcharge);
    }
    public double charge() {
        return getWeight(); 
    }
    public int calculateEvaluationDP(){
        return 0; 
    }
}