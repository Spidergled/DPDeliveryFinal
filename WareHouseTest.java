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
    private Order order3;
    private Order order4;
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
        
        // Creación de diferentes tipos de pedidos
        order1 = new UrgentOrder("Alice", new Location(1, 1), new Location(3, 3), 10, 2.0, "Store1", Urgency.EMERGENCY, Surcharge.MEDIUM);
        order2 = new UrgentOrder("Bob", new Location(2, 2), new Location(4, 4), 11, 1.5, "Store2", Urgency.IMPORTANT, Surcharge.LOW);
        
        order3 = new NonUrgentOrder("Charlie", new Location(5, 5), new Location(6, 6), 12, 2.5, "Store3", Urgency.NONESSENTIAL, Surcharge.LOW);
        order4 = new MedicalOrder("David", new Location(7, 7), new Location(8, 8), 14, 3.0, "Store4", Urgency.EMERGENCY);

        // Inicializamos la empresa y el repartidor
        DeliveryCompany company = new DeliveryCompany("DP Company");
        dp = new DeliveryPerson(company, new Location(0, 0), "DP1");
    }

    /**
     * Tear down the test fixtures.
     */
    @After
    public void tearDown() {
    }

    /**
     * Test the addOrder method for adding orders to the warehouse.
     */
    @Test
    public void testAddOrder() {
        warehouse.addOrder(order1);
        warehouse.addOrder(order2);
        warehouse.addOrder(order3);
        warehouse.addOrder(order4);

        Set<Order> orders = warehouse.getOrders();
        assertTrue(orders.contains(order1));
        assertTrue(orders.contains(order2));
        assertTrue(orders.contains(order3));
        assertTrue(orders.contains(order4));
    }

    /**
     * Test the getDeliveredOrders method to retrieve orders and their assigned delivery persons.
     */
    @Test
    public void testGetDeliveredOrders() {
        warehouse.addDeliveredOrder(order1, dp);
        warehouse.addDeliveredOrder(order2, dp);
        warehouse.addDeliveredOrder(order3, dp);
        warehouse.addDeliveredOrder(order4, dp);

        Map<Order, DeliveryPerson> deliveredOrders = warehouse.getDeliveredOrders();
        assertTrue(deliveredOrders.containsKey(order1));
        assertTrue(deliveredOrders.containsKey(order2));
        assertTrue(deliveredOrders.containsKey(order3));
        assertTrue(deliveredOrders.containsKey(order4));
        assertEquals(dp, deliveredOrders.get(order1));
        assertEquals(dp, deliveredOrders.get(order2));
        assertEquals(dp, deliveredOrders.get(order3));
        assertEquals(dp, deliveredOrders.get(order4));
    }
}
