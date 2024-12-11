import java.util.*;
/**
 * Write a description of class WareHouse here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class WareHouse
{
    
    private Location location;
    private Set<Order> orders;
    private Map <Order, DeliveryPerson> deliveredOrders;

    /**
     * Constructor for objects of class WareHouse
     * Inicializa la ubicación del almacén en la posición fija (5, 5).
     */
    public WareHouse()
    {
       this.location = new Location(5,5);  // Ubicación fija para la entrega inicial.
       this.orders = new TreeSet<>(new ComparadorCollectionOrderTipo());  // Ordena los pedidos por urgencia, hora y destino
       this.deliveredOrders =new TreeMap<>(new ComparadorMapSendingNameDeliveryTime()); // Ordena las entregas por remitente, hora 
    }
    
    
    /**
     * Devuelve la localizacion del almacén
     * @return la ubicacion del almacén
     */
    public Location getLocation(){
        return location;
    }
    
    
    /**
     * Devuelve la lista de pedidos (orders) que están en el almacén
     * @return Lista de pedidos en el almacén.
     */
    public Set<Order> getOrders(){
        return orders;  //devuelve una lista de pedidos que se encuentran en un almacen
    }
    
    /**
     * Añade un pedido (Order) al almacén
     * @param order pedido a añadir
     */
    public void addOrder(Order order){
        orders.add(order);  // Añadir el pedido a la lista

    }
    
     /**
     * Añade un pedido entregado al conjunto de entregas, asociando la persona de reparto.
     * @param order El pedido entregado.
     * @param deliveryPerson La persona de reparto que entregó el pedido.
     */
    public void addDeliveredOrder(Order order, DeliveryPerson deliveryPerson) {
    if (order == null || deliveryPerson == null) {
        throw new IllegalArgumentException("Order y DeliveryPerson no pueden ser nulos.");
    }
    deliveredOrders.put(order, deliveryPerson);
}

    
    /**
     * Devuelve el conjunto de los pedidos entregados y sus personas de reparto.
     * @return Conjunto de entradas de pedidos entregados y su persona de reparto.
     */
    Map <Order, DeliveryPerson> getDeliveredOrders(){
        return deliveredOrders;
    }


     /**
     * Devuelve y elimina el primer pedido de la lista de pedidos.
     * @return El pedido más prioritario (si existe), o null si no hay pedidos.
     */
    public Order retrieveOrder()
    {
        Iterator<Order> iterator = orders.iterator();
        if (iterator.hasNext()) {
            Order firstOrder = iterator.next();
            orders.remove(firstOrder);  // Elimina el primer pedido
            return firstOrder;
        }
        return null;  // Si no hay pedidos, devuelve null
    }
}
