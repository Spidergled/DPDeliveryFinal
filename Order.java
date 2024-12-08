import java.util.Objects;

/**
 * Model an order to be delivered from one
 * location to another.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @version 2024.10.07 DP classes 
 */
public abstract class Order
{
    private String sendingName;//declarar campo sendingName
    private Location location;
    private Location destination;
    private int deliveryTime;//declarar campo deliveryTime
    private double weight;//declarar campo weight
    private String destinationName;//declarar campo destinationName
    private String deliveryPersonName;
    private Surcharge surcharge;
    private Urgency urgency;
   
    

    
    /**
     * Constructor for objects of class Order
     * @param sendingName The sender's name.
     * @param location The pickup location, must not be null.
     * @param destination The destination location, must not be null.
     * @param deliveryTime The hour of delivery.
     * @param weight  The order's weight
     * @param destinationName The name of the person receiving the order.
     * @throws NullPointerException If either location is null.
     */
    public Order(String sendingName, Location location, Location destination, int deliveryTime, 
    double weight, String destinationName)
    {
        //TODO modificar el constructor o crear otro constructor si necesario
        if(location == null) {
            throw new NullPointerException("Location location");
        }
        if(destination == null) {
            throw new NullPointerException("Destination location");
        }
        this.location = location;
        this.destination = destination;
        this.sendingName = sendingName;
        this.deliveryTime = deliveryTime;
        this.weight = weight;
        this.destinationName = destinationName;
        this.deliveryPersonName = ""; 
        this.surcharge = surcharge;
        this.urgency = urgency;
        
      
        
       
    }
    

    /**
     * @return The name of the delivery person.
     */
    public String getDeliveryPersonName()
    {
    //TODO Debe poder devolver el nombre de la persona de reparto.
    return deliveryPersonName;
    }

    /**
     * Set the new name of the delivery person.
     * @param The new name of the delivery person.
     */
    public void setDeliveryPersonName(String deliveryPersonName)
    {
    //TODO Debe poder modificarse el nombre de la persona de reparto.
    this.deliveryPersonName = deliveryPersonName;
    }

    /**
     * @return The destination location.
     */
    public Location getDestination()
    {
     //TODO Debe poder devolver la localización donde hay que llevar el Order.
        return destination;
    }
    
    /**
     * @return Devuelve la localizacion del Order.
     */
    public Location getLocationOrder()
    {
        return location;
    }
    
    /**
     * @return La hora de la entrega.
     */
    public int getDeliveryTime() {
    return deliveryTime;
    }
    
    /**
     * @return El peso del pedido
     */
    public double getWeight() {
    return weight;
    }
    
    /**
     * @return El nombre de la persona
     */
    public String getSendingName() {
    return sendingName;
    } 
    
     /**
     * @return El nombre del destino
     */
    public String getDestinationName() {
    return destinationName;
    } 

    /**
     * Return details of the passenger, such as where it is.
     * @return A string representation of the passenger.
     */
    public Urgency getUrgency() {
        return urgency;
    }
    public String toString()
    {
        return "Order "+"info a decidir"+" travelling from " +
        location + " to " + destination;
    }

    /**
     * Show the final information about the order, including the sender's name, the 
     * destination and name of the deliveryPerson who delivers it.
     * @return String con la informacion final del pedido
     */
    public String showFinalInfo()
    {
        
        return "Order delivered at: "+ getDeliveryTime() +" by: "+ getDeliveryPersonName() +" to: " + getDestinationName()+ " from: " + getSendingName();
    }
     public static enum Surcharge {
        MEDIUM("Medium", 10),
        LOW("Low", 4);

        private final String name;
        private final int value;

        Surcharge(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    /**
     * Enumeración que representa los niveles de urgencia.
     */
    public static enum Urgency {
        EMERGENCY("Emergency", 5),
        IMPORTANT("Important", 3),
        NONESSENTIAL("Non essential", 1);

        private final String name;
        private final int value;

        Urgency(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return name;
        }
    }


}
