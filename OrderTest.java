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
         // Inicializamos un objeto Order antes de cada prueba ( Mario, te falto esto)
        order = new Order("Lucy", new Location(5, 5), new Location(5, 2), 10, 1.2, "Decathlon Cáceres");
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        order = null;
    }

    /**
     * Test basic creation of an order.
     * Ensure that the location and destination locations
     * have been set.
     */
    @Test
    public void testCreation()
    {
        assertEquals("Lucy", order.getSendingName());  
        assertEquals(new Location(5, 5), order.getLocationOrder());  
        assertEquals(new Location(5, 2), order.getDestination());  
        assertEquals(10, order.getDeliveryTime());  
        assertEquals(1.2, order.getWeight(), 0.001);  
        assertEquals("Decathlon Cáceres", order.getDestinationName());  
    }

    /**
     * Test of the getDeliveryPersonName method.
     * Ensure that this method gets and returns the name of the delivery person correctly.
     */
    @Test
    public void testGetDeliveryPersonName()
    {
        order.setDeliveryPersonName("DP1");  
        assertEquals("DP1", order.getDeliveryPersonName());
    }

    /**
     * Test of the getDestination method.
     * Ensure that this method gets and returns the destination location correctly.
     */
    @Test
    public void testGetDestination ()
    {
        assertEquals(new Location(5, 2), order.getDestination());
    }
}
