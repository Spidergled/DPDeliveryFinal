import java.util.*;
/**
 * Write a description of class ComparadorPedidosEntregadosNombre here.
 * 
 *  */

public class ComparadorPedidosEntregadosNombre implements Comparator<DeliveryPerson>
{
    public int compare (DeliveryPerson dp1, DeliveryPerson dp2){
        int res=Integer.compare(dp1.getOrdersDelivered(),dp2.getOrdersDelivered());
        if(res==0){
             res= dp1.getName().compareTo(dp2.getName());            
        }
        return res;
    }
}
