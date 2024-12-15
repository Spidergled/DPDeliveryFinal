import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class DeliveryPersonTest.
 *
 * @author  David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @version 2024.10.07 DP classes 
 */
public class DeliveryPersonTest
{
    private DeliveryPerson dp;
    private Order order;
    //TODO
    //crear más campos (si es necesario) 
    /**
     * Default constructor for test class DeliveryPersonTest
     */
    public DeliveryPersonTest()
    {
    }

    /**
     * Create a delivery person.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        
        DeliveryCompany company = new DeliveryCompany("Compañía DP Delivery Cáceres");
        // Starting position for the taxi.
        Location dpLocation = new Location(0, 0);
        // Locations for the order.
        Location pickup = new Location(1, 2);
        Location destination = new Location(5, 6);
        
        Surcharge surcharge = Surcharge.MEDIUM; 
        Urgency urgency = Urgency.EMERGENCY; 
        order = new StandardOrder("Kevin", pickup, destination, 10, 1.2, "Decathlon Cáceres", urgency, surcharge);
        dp = new DeliveryPerson(company, dpLocation,"DP1");
        //TODO
        //Completar (si es necesario) este método
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    /**
     * Test creation and the initial state of a delivery person.
     */
    @Test
    public void testCreation()
    {
        assertEquals(true, dp.isFree());
        assertEquals(new Location(0,0), dp.getLocation()); // Verifica que la ubicación inicial del repartidor es (0,0).
        assertEquals("DP1", dp.getName()); // Verifica que el nombre del repartidor es el esperado.
        assertEquals(0,dp.getOrdersDelivered()); // Verifica que el numero de entregas realizadas es cero.
        assertEquals(Urgency.EMERGENCY, order.getUrgency());
        assertEquals(Surcharge.MEDIUM, order.getSurcharge());
            
        //TODO puede ser implementado comparando otros campos
    }

    /**
     * Test that a delivery person is no longer free after this person has
     * picked up an order.
     */
    @Test
    public void testPickup()
    {
        //TODO implementar este método
        dp.pickup(order);  // EL repartidor recoge el pedido.
        assertEquals(false,dp.isFree()); // Verifica que el repartidor ya no esta libre.
        assertEquals(order.getDestination(), dp.getTargetLocation()); // Verifica que el destino coincida con el del pedido.
        assertEquals(dp.getName(), order.getDeliveryPersonName()); // Verifica que el nombre del repartidor se asigno al pedido.
    }

    /**
     * Test that a delivery person becomes free again after delivering
     * an order.
     */
    @Test
    public void testDeliverOrder()
    {
        //TODO implementar este método
        dp.pickup(order); // El repartidor recoge el pedido.
        dp.deliverOrder(); // El repartidor entrega el pedido.
        assertEquals(true,dp.isFree()); // Verifica que el repartidor esta libre despues de la entrega.
        assertEquals(1, dp.getOrdersDelivered()); // Verifica que el contador de entregas ha incrementado.
    }

    /**
     * Test that a delivery person picks up and delivers an order within
     * a reasonable number of steps.
     */
    @Test
    public void testDelivery()
    {
        //TODO implementar este método
        dp.pickup(order);
        assertEquals(1, dp.getOrdersToDeliver().size()); // Verifica que el pedido está en la lista.

        int pasos = 0;
        
        while(!dp.getLocation().equals(order.getDestination()) && pasos < 100){
            dp.act();
            pasos++;
        }
        
        assertEquals(order.getDestination(), dp.getLocation());
        
        dp.deliverOrder();
        
        assertEquals(true, dp.isFree());
        assertEquals(1, dp.getOrdersDelivered());
        
        assertEquals(true, pasos < 100);
        
    }
    public void testObtenTotalCharge()
    {
        // creamos varios pedidos con diferentes cargos
        Order order1 = new StandardOrder("Alice", new Location(1, 1), new Location(3, 3), 10, 2.0, "Store1", Urgency.EMERGENCY, Surcharge.MEDIUM);
        Order order2 = new StandardOrder("Bob", new Location(2, 2), new Location(4, 4), 11, 1.5, "Store2", Urgency.IMPORTANT, Surcharge.LOW);
        dp.pickup(order1);
        dp.setLocation(order1.getDestination());
        dp.deliverOrder();
        dp.pickup(order2);
        dp.setLocation(order2.getDestination());
        dp.deliverOrder();
        
        double totalCharge = order1.charge() + order2.charge();
        assertEquals(totalCharge, dp.obtainTotalCharge());
    }
    public void testGetOrder()
    {
    Order order1 = new StandardOrder("Alice", new Location(1, 1), new Location(3, 3), 10, 2.0, "Store1", Urgency.EMERGENCY, Surcharge.MEDIUM);
    Order order2 = new StandardOrder("Bob", new Location(2, 2), new Location(4, 4), 11, 1.5, "Store2", Urgency.IMPORTANT, Surcharge.LOW);

    dp.pickup(order1);
    dp.pickup(order2);

    assertEquals(order1, dp.getCurrentOrder());
    }
    public void testact()
    {
    Order urgentOrder = new UrgentOrder("Alice", new Location(0, 0), new Location(5, 5), 10, 1.0, "Urgent Store", Urgency.EMERGENCY, Surcharge.MEDIUM);
    
    dp.pickup(urgentOrder);
    
    int n = 0;
    while(!dp.getLocation().equals(urgentOrder.getDestination()) && n < 100)
    {
        dp.act();
        n++;
    }
    assertEquals (urgentOrder.getDestination(), dp.getLocation());
    dp.deliverOrder();
    assertTrue(dp.isFree());
    }
}

