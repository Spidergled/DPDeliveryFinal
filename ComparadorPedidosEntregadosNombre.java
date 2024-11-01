import java.util.*;
/**
 * Write a description of class ComparadorPedidosEntregadosNombre here.
 * 
 *  */

public class ComparadorPedidosEntregadosNombre implements Comparator<DeliveryPerson>
{
    public int compare (DeliveryPerson dp1, DeliveryPerson dp2){
        int res;
        if(dp1.getOrdersDelivered()==dp2.getOrdersDelivered()){
             res= dp1.getName().compareTo(dp2.getName());            
        }else{
            if(dp1.getOrdersDelivered()>dp2.getOrdersDelivered()){
                res= 1;
            }else{
                res= -1;
            }
        }
        return res;
    }
}
