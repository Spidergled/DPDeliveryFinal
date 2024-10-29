import java.util.*;
/**
 * Write a description of class ComparadorPedidosEntregadosNombre here.
 * 
 *  */

public class ComparadorPedidosEntregadosNombre implements Comparator<DeliveryPerson>
{
    public int compare (DeliveryPerson dp1, DeliveryPerson dp2){
        int res;
        if(dp1.getOrderesDelivered()==dp2.getOrderesDelivered()){
             res= dp1.getName().compareTo(dp2.getName());            
        }else{
            if(dp1.getOrderesDelivered()>dp2.getOrderesDelivered()){
                res= 1;
            }else{
                res= -1;
            }
        }
        return res;
    }
}
