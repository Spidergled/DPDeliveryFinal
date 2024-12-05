import java.util.*;

/**
 * Model the operation of a taxi company, operating different
 * delivery persons. This version operates a single type of delivery person.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2024.10.07 DP classes
 */
public class DeliveryCompany  
{
    // TODO definir todos sus campos
    private String name;  //nombre de la compañía
    private List<DeliveryPerson> deliveryPersons; // Lista de repartidores
    private WareHouse wareHouse; // Almacén donde se almacenan los pedidos
    /**
     * Constructor for objects of class DeliveryCompany
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
    public List<Order> getOrders()
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

    /**
      * Find the closest free delivery person to the warehouse's location, if any.
      * @return A free delivery person, or null if there is none.
      */
    private DeliveryPerson getDeliveryPerson() {
      // Establecer la ubicación del almacén como el objetivo temporal
      /*
      Location warehouseLocation = wareHouse.getLocation();
      for (DeliveryPerson dp : deliveryPersons) {
          if(dp.isFree()){
              dp.setTargetLocation(warehouseLocation); //solo para repartidores libres
          }
        
      }
      */

      // Ordenar los deliveryPersons usando el comparador
      Collections.sort(deliveryPersons, new ComparadorDeliveryPerson());

      // Crear un iterador para recorrer la lista de deliveryPersons
      Iterator<DeliveryPerson> iterator = deliveryPersons.iterator();

      // Buscar el primer deliveryPerson libre usando el iterador
      DeliveryPerson closestFreeDeliveryPerson = null;
      while (iterator.hasNext() && closestFreeDeliveryPerson == null) {
        DeliveryPerson dp = iterator.next();
        if (dp.isFree()) {
            closestFreeDeliveryPerson = dp;
        }
      }

      // Retornar el deliveryPerson más cercano libre, o null si no hay ninguno
      return closestFreeDeliveryPerson;
    }


    /**
     * Request a pickup for the given order.
     * @param order The order to be delivered.
     * @return Whether a free delivery person is available.
     */
    public boolean requestPickup(Order order)
    {
        DeliveryPerson dp = getDeliveryPerson();
        if (dp != null) {
            dp.pickup(order);
            dp.setPickupLocation(wareHouse.getLocation());
            //dp.setTargetLocation(order.getLocationOrder());
            System.out.println("<<<< DeliveryPerson " + dp.getName() + 
                               " at " + dp.getLocation() +
                               " go to pick up order from " + order.getSendingName() + 
                               " at " + order.getLocationOrder());
            return true;
        }
        return false;
    }
    

    /**
     * A delivery person has arrived at a pickup point.
     * @param dp The delivery person at the pickup point.
     */
    public void arrivedAtPickup(DeliveryPerson dp)
    {
        Order order = dp.getCurrentOrder();
        if (order != null) {
            dp.setTargetLocation(order.getDestination());
            System.out.println("<<<< DeliveryPerson " + dp.getName() + " at "+ dp.getLocation()+" picks up order from " + order.getSendingName()
            + " to: " + order.getDestination());
        }
                                 
    }

    /**
     * A delivery person has arrived at a orders's destination.
     * @param dp The delivery person at the destination.
     * @param order The order being dropped off.
     */
    public void arrivedAtDestination(DeliveryPerson dp, Order order) {
        System.out.println("<<<< DeliveryPerson "+ dp.getName() + " at " + dp.getLocation() + " delivers Order at: " + order.getDeliveryTime() 
        + " from: " + order.getSendingName() + " to: " + order.getDestinationName());
                                     
    }
}