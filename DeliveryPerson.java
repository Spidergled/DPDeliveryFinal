import java.util.*; 
import java.util.Set;

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
    public TreeSet<Order> ordersToDeliver;
    private int valuation;
    private int maxLoad;
    private double totalCharged;
    
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
        this.valuation = 0;
        this.maxLoad = 1;
        this.maxLoad= Math.max(1, Math.min(5,maxLoad));
        this.totalCharged = 0.0;
        this.ordersToDeliver = new TreeSet<>(new ComparadorCollectionOrderTipo());
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
        //System.out.println("Repartidor: " + getName() + " - maxPedidos: " + getMaxLoad() + " - ordersToDeliver size: " + getOrdersToDeliver().size());

        return ordersToDeliver.size() < getMaxLoad(); //comprueba si un repartidor esta libre
    }

    /**
     * Notify the company of our arrival at a pickup location.
     */
    public void notifyPickupArrival()
    {
        if(!ordersToDeliver.isEmpty()){
            Order currentOrder = ordersToDeliver.first();
            company.arrivedAtPickup(this);
            setTargetLocation(currentOrder.getDestination());
            

        }
    }

    /**
     * Notify the company of our arrival at an order's destination.
     */
    public void notifyOrderArrival(Order order)
    {
            if (order!=null && ordersToDeliver.contains(order)) {
            company.arrivedAtDestination(this, order);
            deliverOrder();
            if(!ordersToDeliver.isEmpty()){
                setTargetLocation(ordersToDeliver.first().getDestination());
            }
            else{
                clearTargetLocation();
            }
            setLocation(order.getDestination()); // Actualiza la ubicación del repartidor a la ubicación de entrega
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
        
        if (ordersToDeliver.size() >= maxLoad) {
        throw new IllegalStateException("Cannot pickup more orders. Maximum load reached.");
        }
        ordersToDeliver.add(order);
        setTargetLocation(order.getDestination());
        order.setDeliveryPersonName(getName()); 
    
    }
    /**
 * Deliver the order.
 */
public void deliverOrder() {
    if (ordersToDeliver.isEmpty()) {
        throw new IllegalStateException("No hay pedidos para entregar.");
    }
    
    Order currentOrder = ordersToDeliver.pollFirst(); // Obtiene y elimina el primer pedido
    //System.out.println(getName() + " entregando el pedido: " + currentOrder);

    // Verifica si está en el destino del pedido
    /*
    while (!location.equals(currentOrder.getDestination())) {
        // Si no estamos en el destino, movernos hacia él
        Location nextStep = location.nextLocation(currentOrder.getDestination());
        setLocation(nextStep); // Actualiza la ubicación al siguiente paso
        System.out.println(getName() + " moviéndose a: " + location);
    }
    */
   
    // Ahora que el repartidor está en el destino, realizar la entrega
    incrementTotalCharged(currentOrder.charge()); // Incrementa el dinero cobrado
    incrementOrdersDelivered(); // Incrementa el número de pedidos entregados
    incrementValuation(currentOrder.calculateEvaluationDP()); // Actualiza la valoración automática
    company.addOrder(currentOrder); // Envía el pedido a la compañía para almacenarlo en el almacén

    //System.out.println(getName() + " ha entregado el pedido " + currentOrder + " en " + location);

    // Después de entregar, verifica si aún hay pedidos para entregar
    if (!ordersToDeliver.isEmpty()) {
        // Actualiza la ubicación objetivo al siguiente pedido
        Order nextOrder = ordersToDeliver.first();
        setTargetLocation(nextOrder.getDestination());
        //System.out.println(getName() + " moviéndose a la siguiente entrega en: " + nextOrder.getDestination());
    } else {
        clearTargetLocation(); // Si no hay más pedidos, borra la ubicación objetivo
        //System.out.println(getName() + " ha entregado todos los pedidos.");
    }
}

    
    /**
     * @return incrementa el total cobrado
     */
    public void incrementTotalCharged(double amount){
        totalCharged += amount;
    }
    
    /**
     * @return obtener el total cobrado
     */
    public double obtainTotalCharge() {
        return totalCharged;
    }
    
    /**
     * @return incrementa la valoración
     */
    public void incrementValuation(int points){
        valuation += points;
    }
    
    /*
    /**
     * @return how many orders this delivery person has delivered.
     */
    /*
    public int ordersDelivered()
    {
        
        return ordersDelivered;
    }
    */
    

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
    // Verifica si hay destino asignado o pedidos en la lista
    if (!hasTargetLocation() || ordersToDeliver.isEmpty()) {
        incrementIdleCount();
        //System.out.println(getName() + " está inactivo.");
        return;
         // Salir del método si no hay destino asignado o pedidos
    }

    // Calcula la siguiente posición hacia el destino
    Location nextMove = location.nextLocation(targetLocation);
    setLocation(nextMove);

    System.out.println("@@@  " + getClass().getName() + " " + getName() + " moving to: " + getLocation().getX() + " - " + getLocation().getY());

    // Obtener el primer pedido de la lista
    Order currentOrder = getCurrentOrder();
    if (currentOrder != null) {
        // Verifica si ha llegado a recoger el pedido
        if (location.equals(currentOrder.getLocationOrder())) {
            notifyPickupArrival(); // Notifica a la compañía que llegó al lugar de recogida
        }
        // Verifica si ha llegado al destino del pedido
        else if (location.equals(currentOrder.getDestination())) {
            notifyOrderArrival(currentOrder); // Notifica a la compañía que llegó al destino del pedido
        }
    }
    }

    
    /**
     * Get the current order.
     * @return The current order, or null if there is none.
     */
     public Order getCurrentOrder() {
        return ordersToDeliver.isEmpty() ? null : ordersToDeliver.first();
    }
    
    public double getTotalCharged() {
        return totalCharged;
    }
    
    public TreeSet<Order> getOrdersToDeliver() {
        return ordersToDeliver;
    }
    
    public int getValuation() {
        return valuation;
    }
    
    public void setValuation(int valuation){
        this.valuation = valuation;
    }
    
    public int getMaxLoad(){
        return maxLoad;
    }
    
    public void setMaxLoad(int maxload){
        this.maxLoad = maxload;
    }
    
    
    /**
     * Verifica si la persona de reparto puede manejar el pedido según su urgencia.
     * @param urgency La urgencia del pedido.
     * @return True si la persona de reparto puede manejar el pedido, false en caso contrario.
     */
    public boolean puedeManejarPedido(Urgency urgency) {
        return false; // Por defecto, una persona de reparto no puede manejar ningún pedido
    }

    
    /**  
     * Return details of the delivery person, such as the name, the location,
     * number of delivered orders and time (steps) without moving.
     * @return A string representation of the delivery person.
     */
    public String showFinalInfo()
    {
        //TODO  implementar este método
        String popularityInfo = this.toString();
        return getClass().getName() + " " + getName() + " at " + getLocation() + " - orders delivered: " + getOrdersDelivered() + " - non active for: " + getIdleCount() + " times" + " - total to be collected: "+ getTotalCharged() +" - valuation: "
        +getValuation() + popularityInfo;

    }

}