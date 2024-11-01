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
    private List<Order> orders;

    /**
     * Constructor for objects of class WareHouse
     * Inicializa la ubicación del almacén en la posición fija (5, 5).
     */
    public WareHouse()
    {
       this.location = new Location(5,5);  // Ubicación fija para la entrega inicial.
       this.orders = new ArrayList<>();
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
    public List<Order> getOrders(){
        return orders;
    }
    
    /**
     * Añade un pedido (Order) al almacén
     * @param order pedido a añadir
     */
    public void addOrder(Order order){
        orders.add(order);  // Añadir el pedido a la lista

        // Usar un comparador con una expresión lambda. "Sale en el tema 4, echarle un ojo"
        orders.sort((o1, o2) -> {
            int timeComparacion = Integer.compare(o1.getDeliveryTime(), o2.getDeliveryTime());
            if (timeComparacion == 0) {
                return o1.getSendingName().compareTo(o2.getSendingName());
            }
            return timeComparacion;
        });
    }
    

     /**
     * Devuelve y elimina el primer pedido de la lista de pedidos.
     * @return El pedido más prioritario (si existe), o null si no hay pedidos.
     */
    public Order retrieveOrder()
    {
        if (!orders.isEmpty()) {
            return orders.remove(0);  // Elimina y devuelve el primer pedido
        }
        return null;  // Si no hay pedidos, devuelve null
    }
}
