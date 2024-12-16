import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class deliveryCompanyTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class deliveryCompanyTest
{
    /**
     * Default constructor for test class deliveryCompanyTest
     */
    public deliveryCompanyTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
}
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeliveryCompanyTest {

    private DeliveryCompany company;
    private DeliveryPerson dp;
    private UrgentOrder urgentOrder;
    private NonUrgentOrder nonUrgentOrder;
    private MedicalOrder medicalOrder;

    @BeforeEach
    public void setUp() {
        company = new DeliveryCompany("DP Company");
        dp = new DeliveryPerson(company, new Location(0, 0), "DP1");

        // Crear diferentes tipos de pedidos
        urgentOrder = new UrgentOrder("Alice", new Location(1, 1), new Location(5, 5), 10, 2.0, "Store1", Urgency.EMERGENCY, Surcharge.MEDIUM);
        nonUrgentOrder = new NonUrgentOrder("Bob", new Location(2, 2), new Location(6, 6), 12, 1.0, "Store2", Urgency.NONESSENTIAL, Surcharge.LOW);
        medicalOrder = new MedicalOrder("Charlie", new Location(3, 3), new Location(7, 7), 8, 3.0, "Store3", Urgency.IMPORTANT, Surcharge.HIGH