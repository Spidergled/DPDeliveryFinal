import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class DeliveryCompanyTest.
 * Tests the main functionalities of the DeliveryCompany class, including assigning pickups,
 * handling arrivals at pickup points, and handling deliveries at destinations.
 * 
 * @author Yahya, Mario, Gled
 * @version 2024.10.07 DP classes
 */
public class DeliveryCompanyTest {

    private DeliveryCompany company;
    private DeliveryPerson dp1;      
    private ExpressDP dp2;           
    private SpecialDP dp3;         
    private Order order1;            
    private Order order2;            
    private Order order3;            

    /**
     * Default constructor for test class DeliveryCompanyTest.
     */
    public DeliveryCompanyTest() {
    }

    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        company = new DeliveryCompany("Compañía DP Delivery Cáceres");

        Location dpLocation1 = new Location(0, 0);
        Location dpLocation2 = new Location(2, 2);
        Location dpLocation3 = new Location(3, 3);

        dp1 = new DeliveryPerson(company, dpLocation1, "DP1");
        dp2 = new ExpressDP(company, dpLocation2, "DP2");
        dp3 = new SpecialDP(company, dpLocation3, "DP3");

        company.addDeliveryPerson(dp1);
        company.addDeliveryPerson(dp2);
        company.addDeliveryPerson(dp3);

        Location pickup1 = new Location(1, 1);
        Location destination1 = new Location(5, 5);

        Location pickup2 = new Location(3, 3);
        Location destination2 = new Location(7, 7);

        Location pickup3 = new Location(4, 4);
        Location destination3 = new Location(8, 8);

        order1 = new UrgentOrder("Kevin", pickup1, destination1, 10, 1.2, "Decathlon Cáceres", Urgency.IMPORTANT, Surcharge.MEDIUM);
        order2 = new NonUrgentOrder("Laura", pickup2, destination2, 11, 1.5, "El Corte Inglés", Urgency.NONESSENTIAL, Surcharge.LOW);
        order3 = new MedicalOrder("David", pickup3, destination3, 12, 3.0, "Farmacia Central", Urgency.EMERGENCY);
    }

    /**
     * Tears down the test fixture.
     * Called after every test case method.
     */
    @After
    public void tearDown() {
    }



    /**
     * Test the arrivedAtPickup method for different types of DeliveryPerson and Order.
     */
    @Test
    public void testArrivedAtPickup() {
        company.requestPickup(order1);
        dp2.setLocation(order1.getLocationOrder());
        company.arrivedAtPickup(dp2);
        assertEquals(order1.getDestination(), dp2.getTargetLocation());

        company.requestPickup(order3);
        dp3.setLocation(order3.getLocationOrder());
        company.arrivedAtPickup(dp3);
        assertEquals(order3.getDestination(), dp3.getTargetLocation());
    }

}
