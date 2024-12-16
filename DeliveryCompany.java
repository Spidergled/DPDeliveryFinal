import java.util.*;
import java.text.DecimalFormat;

/**
 * Model the operation of a taxi company, operating different
 * delivery persons. This version operates a single type of delivery person.
 * 
 * @author Yahya, Mario, Gled
 * @version 2024.10.07 DP classes
 */
public class DeliveryCompany  
{
    // TODO definir todos sus campos
    private String name;  //nombre de la compañía
    private List<DeliveryPerson> deliveryPersons; // Lista de repartidores
    private WareHouse wareHouse; // Almacén donde se almacenan los pedidos

    /**
     * Constructor for creating a DeliveryCompany object.
     * Initializes the company with the given name, sets up an empty list of delivery persons,
     * and creates a new warehouse instance.
     * 
     * @param name The name of the company.
     */
    public DeliveryCompany(String name)
    {
        this.name = name;
        this.deliveryPersons = new ArrayList<>();
        this.wareHouse = new WareHouse();
        //TODO implementar el resto del constructor 

    }

    /**
     * @return The name of the company.
     */
    public String getName()
    {
        return name; //devuelve el nombre de la compañia 
    }

    /**
     * @return The list of delivery persons.
     */
    public List<DeliveryPerson> getDeliveryPersons()
    {       
        return deliveryPersons; //devuelve una lista de repartidores
    }

    /**
     * @return The list of orders.
     */
    public Set<Order> getOrders()
    {
        return wareHouse.getOrders();//devuelve una lista de pedidos
    }

    /**
     * @param Add a new delivery person.
     */
    public void addDeliveryPerson(DeliveryPerson dp)
    {
        deliveryPersons.add(dp);//añade a un nuevo repartidor
    }

    /**
     * Add a new order in the whare house of the company.
     * @param order The new order.
     */
    public void addOrder(Order order)
    {
       wareHouse.addOrder(order);//añade un nuevo pedido al almacen de la compañia 
    }
    
    /**
     * @return Devuelve la ubicacion de Almacen
     */
    public Location getWareHouseLocation(){
        return wareHouse.getLocation(); //devuelve la ubicacion del almacen 
    }
    
    public WareHouse getWareHouse(){
        return wareHouse;
    }

    /**
      * Find the closest free delivery person to the warehouse's location, if any.
      * @return A free delivery person, or null if there is none.
      */
    private DeliveryPerson getDeliveryPerson(Order order) {
    
    // Obtén la ubicación del almacén
    Location warehouseLocation = wareHouse.getLocation();

    // Ordena la lista de repartidores usando el comparador
    deliveryPersons.sort(new ComparadorDeliveryPerson(warehouseLocation));
    
    
    // Utilizar iterador para buscar el primer repartidor libre
    Iterator<DeliveryPerson> iterator = deliveryPersons.iterator();
    while (iterator.hasNext()) {
        DeliveryPerson dp = iterator.next();
        //System.out.println("Verificando repartidor: " + dp.getClass().getSimpleName() + " " + dp.getName() + " - Libre: " + dp.isFree() + " - Puede manejar: "+ dp.puedeManejarPedido(order.getUrgency()));
        if (dp.isFree() && dp.puedeManejarPedido(order.getUrgency())) {
            //System.out.println("Repartidor asignado: " + dp.getClass().getSimpleName() + " " + dp.getName());
            return dp; // Devuelve el primer repartidor libre
        }
    }

    // Si no se encuentra un repartidor libre
    System.out.println("No se encontró un repartidor libre compatible para el pedido: " + order);
    return null;
    }



    /**
     * Request a pickup for the given order.
     * @param order The order to be delivered.
     * @return Whether a free delivery person is available.
     */
    public boolean requestPickup(Order order)
    {
          // Encuentra un DeliveryPerson libre compatible con el tipo de pedido
    DeliveryPerson dp = getDeliveryPerson(order);
    
    if (dp != null && dp.isFree()) {
        // Valida si puede manejar este pedido
        if (dp.puedeManejarPedido(order.getUrgency())) {
            // Configura el lugar de recogida y asigna el pedido
            //System.out.println("Repartidor " + dp.getName() + " puede manejar el pedido.");
            dp.pickup(order);
            dp.setPickupLocation(order.getLocationOrder());
            dp.setTargetLocation(order.getLocationOrder()); // Asigna targetLocation al lugar de recogida
            order.setDeliveryPersonName(dp.getName());
            
            // Mensaje de depuración: Pedido asignado al repartidor 
            //System.out.println("Pedido asignado: " + order + " a " + dp.getClass().getSimpleName() + " "+ dp.getName());
            
            // Mensaje de depuración
            System.out.println("<<<< "+dp.getClass().getSimpleName()+ " " + dp.getName() +
                               " at " + dp.getLocation() +
                               " go to pick up order from " + order.getSendingName() + 
                               " at " + order.getLocationOrder());
                               

            return true;
        } else {
            System.out.println("DeliveryPerson " + dp.getName() + " cannot handle this type of order.");
        }
    }else{
        System.out.println("No se encontró un repartidor libre compatible para el pedido: " + order);
    }
    // No se encontró un repartidor libre compatible
    return false;
    }
    

    /**
     * A delivery person has arrived at a pickup point.
     * @param dp The delivery person at the pickup point.
     */
    public void arrivedAtPickup(DeliveryPerson dp) {
    if (!dp.getOrdersToDeliver().isEmpty()) {
        for (Order currentOrder : dp.getOrdersToDeliver()) {
            if (dp.getLocation().equals(currentOrder.getLocationOrder())) {
                dp.setTargetLocation(currentOrder.getDestination());
                System.out.println("<<<< " + dp.getClass().getName() + " " + dp.getName() + " at " 
                    + dp.getLocation() + " picks up Order from " + currentOrder.getSendingName() 
                    + " to: " + currentOrder.getDestination());
            }
        }
    }
}


    /**
     * A delivery person has arrived at a orders's destination.
     * @param dp The delivery person at the destination.
     * @param order The order being dropped off.
     */
    public void arrivedAtDestination(DeliveryPerson dp, Order order) {
        DecimalFormat df = new DecimalFormat("#.##");
        wareHouse.addDeliveredOrder(order, dp);
        System.out.println("<<<< "+dp.getClass().getName()+ " "+ dp.getName()  + " at " + dp.getLocation() + " delivers Order at: " + order.getDeliveryTime() 
        + " from: " + order.getSendingName() + " to: " + order.getDestinationName()+ " (charge: " + df.format(order.charge()) + ")");
                                     
    }
}