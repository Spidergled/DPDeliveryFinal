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
    private Order order1;
    private Order order2;
    private Order order3;
    private Order order4;
    
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
        // Locations for the orders.
        Location pickup1 = new Location(1, 2);
        Location destination1 = new Location(5, 6);
        
        Location pickup2 = new Location(2, 3);
        Location destination2 = new Location(6, 7);
        
        Location pickup3 = new Location(3, 4);
        Location destination3 = new Location(7, 8);
        
        Location pickup4 = new Location(4, 5);
        Location destination4 = new Location(8, 9);

        // Orders of different types
        order1 = new UrgentOrder("Kevin", pickup1, destination1, 10, 1.2, "Decathlon Cáceres", Urgency.EMERGENCY, Surcharge.MEDIUM);
        order2 = new UrgentOrder("Laura", pickup2, destination2, 11, 1.5, "El Corte Inglés", Urgency.IMPORTANT, Surcharge.LOW);
        
        order3 = new NonUrgentOrder("Carlos", pickup3, destination3, 12, 2.5, "Carrefour Cáceres", Urgency.NONESSENTIAL, Surcharge.LOW);
        order4 = new MedicalOrder("David", pickup4, destination4, 14, 3.0, "Farmacia Central", Urgency.EMERGENCY);

        dp = new DeliveryPerson(company, dpLocation, "DP1");
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
        assertEquals(0, dp.getOrdersDelivered()); // Verifica que el número de entregas realizadas es cero.
        
        // Verificar atributos del primer pedido
        assertEquals(Urgency.EMERGENCY, order1.getUrgency());
        assertEquals(Surcharge.MEDIUM, order1.getSurcharge());
    }

    /**
     * Test that a delivery person is no longer free after this person has
     * picked up an order.
     */
    @Test
    public void testPickup()
    {
        dp.pickup(order1);  // El repartidor recoge el pedido.
        assertEquals(false, dp.isFree()); // Verifica que el repartidor ya no está libre.
        assertEquals(order1.getDestination(), dp.getTargetLocation()); // Verifica que el destino coincida con el del pedido.
        assertEquals(dp.getName(), order1.getDeliveryPersonName()); // Verifica que el nombre del repartidor se asignó al pedido.
    }

    /**
     * Test that a delivery person becomes free again after delivering
     * an order.
     */
    @Test
    public void testDeliverOrder()
    {
        dp.pickup(order1); // El repartidor recoge el pedido.
        dp.deliverOrder(); // El repartidor entrega el pedido.
        assertEquals(true, dp.isFree()); // Verifica que el repartidor está libre después de la entrega.
        assertEquals(1, dp.getOrdersDelivered()); // Verifica que el contador de entregas ha incrementado.
    }

    /**
     * Test that a delivery person picks up and delivers an order within
     * a reasonable number of steps.
     */
    @Test
    public void testDelivery()
    {
        dp.pickup(order1);
        assertEquals(1, dp.getOrdersToDeliver().size()); // Verifica que el pedido está en la lista.

        int pasos = 0;
        
        while(!dp.getLocation().equals(order1.getDestination()) && pasos < 100){
            dp.act();
            pasos++;
        }
        
        assertEquals(order1.getDestination(), dp.getLocation());
        
        dp.deliverOrder();
        
        assertEquals(true, dp.isFree());
        assertEquals(1, dp.getOrdersDelivered());
        
        assertEquals(true, pasos < 100);
    }

    @Test
    public void testObtainTotalCharge()
    {
        dp.pickup(order1);
        dp.setLocation(order1.getDestination());
        dp.deliverOrder();
        dp.pickup(order2);
        dp.setLocation(order2.getDestination());
        dp.deliverOrder();

        double totalCharge = order1.charge() + order2.charge();
        assertEquals(totalCharge, dp.obtainTotalCharge(), 0.01);
    }

    @Test
    public void testGetOrder()
    {
        dp.pickup(order1);
        dp.pickup(order2);

        assertEquals(order1, dp.getCurrentOrder());
    }

    @Test
    public void testAct()
    {
        dp.pickup(order1);
        
        int n = 0;
        while(!dp.getLocation().equals(order1.getDestination()) && n < 100)
        {
            dp.act();
            n++;
        }
        assertEquals(order1.getDestination(), dp.getLocation());
        dp.deliverOrder();
        assertTrue(dp.isFree());
    }
}

