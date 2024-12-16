import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

/**
 * The test class WareHouseTest.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class WareHouseTest {
    private WareHouse warehouse;
    private Order order1;
    private Order order2;
    private DeliveryPerson dp;

    /**
     * Default constructor for test class WareHouseTest
     */
    public WareHouseTest() {
    }

    /**
     * Set up the test fixtures.
     */
    @Before
    public void setUp() {
        warehouse = new WareHouse();
        order1 = new StandardOrder("Alice", new Location(1, 1), new Location(3, 3), 10, 2.0, "Store1", Urgency.EMERGENCY, Surcharge.MEDIUM);
        order2 = new StandardOrder("Bob", new Location(2, 2), new Location(4, 4), 11, 1.5, "Store2", Urgency.IMPORTANT, Surcharge.LOW);
        DeliveryCompany company = new DeliveryCompany("DP Company");
        dp = new DeliveryPerson(company, new Location(0, 0), "DP1");
    }

    /**
     * Tear down the test fixtures.
     */
    @After
    public void tearDown() {
    }

    @Test
    public void testAddOrder() {
        warehouse.addOrder(order1);
        warehouse.addOrder(order2);

        Set<Order> orders = warehouse.getOrders();
        assertTrue(orders.contains(order1));
        assertTrue(orders.contains(order2));
    }
    @Test
    public void testGetDeliveredOrders() {
        warehouse.addDeliveredOrder(order1, dp);
        warehouse.addDeliveredOrder(order2, dp);

        Map<Order, DeliveryPerson> deliveredOrders = warehouse.getDeliveredOrders();
        assertTrue(deliveredOrders.containsKey(order1));
        assertTrue(deliveredOrders.containsKey(order2));
        assertEquals(dp, deliveredOrders.get(order1));
        assertEquals(dp, deliveredOrders.get(order2));
    }

   


   
}
