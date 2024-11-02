import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class OrderTest.
 *
 * @author  David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @version 2024.10.07 DP classes 
 */
public class OrderTest
{
    private Order order;
    private Location PickupLocation;
    private Location destinationLocation;
    /**
     * Default constructor for test class OrderTest
     */
    public OrderTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
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
     * Test basic creation of an order.
     * Ensure that the location and destination locations
     * have been set.
     */
    @Test
    public void testCreation()
    {
        assertNotNull("Localizacion no deberia ser nula", order.getLocationOrder());
        assertNotNull("Destino no deberia ser nulo", order.getDeliveryTime());
        assertEquals("Ubicacion de salida no es correcta", PickupLocation, order.getLocationOrder());
        assertEquals("Nombre de la persona que lo manda incorrecto", "Pepe", order.getSendingName()); 
        assertEquals("Nombre de la persona que lo recibe incorrecto", "Ricardo", order.getDestinationName());
        assertEquals("Peso del pedido incorrecto", 6.4, order.getWeight(), 0.1);
        assertEquals("Hora de la entrega incorrecta", 11, order.getDeliveryTime()); 
    
    }

    /**
     * Test of the getDeliveryPersonName method.
     * Ensure that this method gets and returns the name of the delivery person correctly.
     */
    @Test
    public void testGetDeliveryPersonName()
    {
        order.setDeliveryPersonName("Antonio"); 
        assertEquals("El nombre del repartidor no coincide", "Antonio", order.getDeliveryPersonName()); 
    }

    /**
     * Test of the getDestination method.
     * Ensure that this method gets and returns the destination location correctly.
     */
    @Test
    public void testGetDestination ()
    {
        assertEquals("Ubicacion de destino incorrecta", destinationLocation, order.getDestination());
    }
}
