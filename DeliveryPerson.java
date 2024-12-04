/**
 * Model the common elements of delivery persons.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @version 2024.10.07 DP classes 
 */
public class DeliveryPerson 
{
    private DeliveryCompany company;   
    private Location location;     
    private  Location targetLocation;    //he cambiado todo a private 
    private int idleCount;    
    private String name; 
    private int ordersDelivered;
    private Order currentOrder;
    /**
     * Constructor of class DeliveryPerson
     * @param company The delivery person's company. Must not be null.
     * @param location The delivery person's starting point. Must not be null.
     * @throws NullPointerException If company or location is null.
     */
    public DeliveryPerson(DeliveryCompany company, Location location, String name)
    {
        if(company == null) {
            throw new NullPointerException("company");
        }
        if(location == null) {
            throw new NullPointerException("location");
        }
        
        this.company = company;
        this.location = location;
        this.name = name;
        targetLocation = null;
        idleCount = 0;
        ordersDelivered = 0;
        currentOrder = null;
        //TODO resto de inicializaciones pendientes
    }

    /**
     * @return the name of the delivery person
     */
    public String getName()
    {
        return name; //devuelve el nombre de la persona que lo reparte 
    }

    /**
     * Get the location.
     * @return Where this delivery person is currently located.
     */
    public Location getLocation()
    {
        return location;   //localizacion donde se encuentra la persona repartidora
    }

    /**
     * Set the current location.
     * @param location Where it is. Must not be null.
     * @throws NullPointerException If location is null.
     */
    public void setLocation(Location location)
    {
        if(location != null) {
            this.location = location;//asigna una localizacion en caso de que no sea nula la locaclizacion 
        }
        else {
            throw new NullPointerException();
        }
    }

    /**
     * Get the target location.
     * @return Where this delivery person is currently headed, or null
     *         if it is idle.
     */
    public Location getTargetLocation()
    {
        return targetLocation;
    }

    /**
     * Set the required target location.
     * @param location Where to go. Must not be null.
     * @throws NullPointerException If location is null.
     */
    public void setTargetLocation(Location location)
    {
        if(location != null) {
            targetLocation = location;//establece la localizacion de detino 
        }
        else {
            throw new NullPointerException();
        }
    }

    /**
     * Receive a pickup location. This becomes the
     * target location.
     * @param location The pickup location.
     */
    public void setPickupLocation(Location location)
    {
        setTargetLocation(location);//establece la localizacion de recogida del paquete 
    }

    /**
     * Has the delivery person a target Location?
     * @return Whether or not this delivery person has a target Location.
     */
    public boolean hasTargetLocation(){
        return getTargetLocation() != null; //comprueba si los repartidores tienen una localizacion asigada como objetivo
    }

    /**
     * Clear the target location.
     */
    public void clearTargetLocation()
    {
        targetLocation = null; //limpia la localizacion objetivo
    }

    /**
     * @return on how many steps this delivery person has been idle.
     */
    public int getIdleCount()
    {
        return idleCount; //pasos que la persona repartidora ha dado
    }

    /**
     * Increment the number of steps on which this delivery person
     * has been idle.
     */
    public void incrementIdleCount()
    {
        idleCount++;
    }
    /**
     * @return on how many steps this delivery person has been idle.
     */
    public int getOrdersDelivered()
    {
        return ordersDelivered; //numero de pedidos entregados 
    }

    /**
     * Return details of the delivery person, such as where he/she is.
     * @return A string representation of the delivery person.
     */
    public String toString()
    {
        return getClass().getName() + " " +getName()+" at " + getLocation(); //devuelve detalles sobre la persona repartidora 
    }

    /**
     * Is the delivery person free?
     * @return Whether or not this delivery person is free.
     */
    public boolean isFree()
    {
        return currentOrder == null; //compruba si un repartidor esta libre
    }

    /**
     * Notify the company of our arrival at a pickup location.
     */
    public void notifyPickupArrival()
    {
        if(currentOrder != null){
            company.arrivedAtPickup(this); //notifica de la llegada del paquete a la zona de recogida 

        }
    }

    /**
     * Notify the company of our arrival at an order's destination.
     */
    public void notifyOrderArrival(Order order)
    {
            if (currentOrder != null) {
            company.arrivedAtDestination(this, currentOrder);
            deliverOrder(); // Deliver the order
            //setLocation(order.getDestination()); // Actualiza la ubicación del repartidor a la ubicación de entrega
        }
    }

    /**
     * Receive an order.
     * Set order's destination as its target location.
     * @param order The order.
     */
    public void pickup(Order order)
    {
        if (order == null) {
        throw new NullPointerException("Cannot pickup a null order");
        }
        currentOrder = order; //Asigna el pedido actual
        setTargetLocation(order.getDestination());
        order.setDeliveryPersonName(getName()); 
    
    }
    /**
     * Deliver the order.
     */
    public void deliverOrder()
    {
        if(currentOrder != null){  // Solo entrega si hay un pedido actual
        incrementOrdersDelivered();
        //company.addOrder(currentOrder);
        // Actualiza la ubicación del repartidor al destino del pedido
        setLocation(currentOrder.getDestination());
        currentOrder = null;  // Limpiar el pedido actual después de entregar
        clearTargetLocation();
      }
    }

    /**
     * @return how many orders this delivery person has delivered.
     */
    public int ordersDelivered()
    {
        
        return ordersDelivered;
    }

    /**
     * Increment the number of orders this delivery person has delivered.
     */
    protected void incrementOrdersDelivered()
    {
        ordersDelivered++; //incrementa el numero de paquetes entregados 
    }

    /**
     * Get the distance to the target location from the current location.
     * @return distance to target location.
     */
    public int distanceToTheTargetLocation()
    {
        if(targetLocation != null){
            return location.distance(targetLocation); //calcula la distancia hasta el lugar objetivo del repartidor 
        }
        return -1;

    }
    
    /**
     * Carry out a delivery person's actions.
     */
    public void act() {
    if (hasTargetLocation()) {
        Location nextMove = location.nextLocation(targetLocation);
        setLocation(nextMove);
        System.out.println("@@@  DeliveryPerson: " + getName() + " moving to: " + getLocation().getX() + " - " + getLocation().getY()); // Muestra movimient

        if (currentOrder != null) {
            if (location.equals(currentOrder.getLocationOrder())) {
                notifyPickupArrival();
            } else if (location.equals(currentOrder.getDestination())) {
                notifyOrderArrival(currentOrder);
            }
        }
    } else {
        incrementIdleCount();
    }
    }
    
    /**
     * Get the current order.
     * @return The current order, or null if there is none.
     */
     public Order getCurrentOrder() {
       return currentOrder;
    }


 
    /**  
     * Return details of the delivery person, such as the name, the location,
     * number of delivered orders and time (steps) without moving.
     * @return A string representation of the delivery person.
     */
    public String showFinalInfo()
    {
        //TODO  implementar este método
        return "DeliveryPerson " + getName() + " at " + getLocation() + " - orders delivered: " + ordersDelivered() + " - non active for: " + getIdleCount() + " times";

    }

}
