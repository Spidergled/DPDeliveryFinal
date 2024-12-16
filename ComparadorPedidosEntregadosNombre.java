import java.util.*;
/**
 * Comparator class for comparing delivery persons based on the number of orders delivered.
 * If two delivery persons have delivered the same number of orders, they are compared by their names.
 * 
 * @author Yahya, Mario, Gled
 * @version (version number or date here)
 */

public class ComparadorPedidosEntregadosNombre implements Comparator<DeliveryPerson>
{
    /**
     * Compares two delivery persons based on the number of orders they have delivered.
     * If the number of orders delivered is the same, the comparison is done by their names.
     * 
     * @param dp1 The first delivery person to compare.
     * @param dp2 The second delivery person to compare.
     * @return A negative integer, zero, or a positive integer as the first delivery person
     *         has delivered fewer, the same, or more orders than the second delivery person.
     *         If the numbers are equal, it returns a comparison based on their names.
     */
    public int compare (DeliveryPerson dp1, DeliveryPerson dp2){
        int res=Integer.compare(dp1.getOrdersDelivered(),dp2.getOrdersDelivered());
        if(res==0){
             res= dp1.getName().compareTo(dp2.getName());            
        }
        return res;
    }
}
