import java.util.*;
/**
 * Write a description of class ComparadorDeliveryPersonOrders here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ComparadorNombreDeliveryPersonOrder implements Comparator<Order> {
 
    public int compare(Order o1, Order o2) {

        return o1.getDeliveryPersonName().compareTo(o2.getDeliveryPersonName());
    }}
