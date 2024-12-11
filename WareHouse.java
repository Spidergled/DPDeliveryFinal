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
    private Set<Map.Entry<Order, DeliveryPerson>> deliveredOrders;  // Conjunto de pedidos entregados y su persona de reparto

    /**
     * Constructor for objects of class WareHouse
     * Inicializa la ubicación del almacén en la posición fija (5, 5).
     */
    public WareHouse()
    {
       this.location = new Location(5,5);  // Ubicación fija para la entrega inicial.
       this.orders = new TreeSet<>(new ComparadorOrderTipo());  // Ordena los pedidos por urgencia, hora y destino
       this.deliveredOrders = new TreeSet<>(new ComparadorOrdesNombreYHora()); // Ordena las entregas por remitente, hora y destino
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
    public void addDeliveredOrder(Order order, DeliveryPerson deliveryPerson){
        deliveredOrders.add(new AbstractMap.SimpleEntry<>(order, deliveryPerson));  // Asocia el pedido con la persona de reparto
    }
    
    /**
     * Devuelve el conjunto de los pedidos entregados y sus personas de reparto.
     * @return Conjunto de entradas de pedidos entregados y su persona de reparto.
     */
    public Set<Map.Entry<Order, DeliveryPerson>> getDeliveredOrders(){
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
