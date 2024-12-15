import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * La clase de prueba OrderTest.
 *
 * @author  
 * @version 2024.10.07 Clases DP
 */
public class OrderTest
{
    Order urgentOrder;
    Order nonUrgentOrder;
    Order medicalOrder;

    /**
     * Constructor por defecto para la clase de prueba OrderTest
     */
    public OrderTest()
    {
    }

    /**
     * Configura el entorno de prueba.
     *
     * Se llama antes de cada método de prueba.
     */
    @Before
    public void setUp()
    {
        // Inicializamos un objeto de cada tipo de Order antes de cada prueba
        urgentOrder = new UrgentOrder(
            "Lucy", 
            new Location(5, 5), 
            new Location(5, 2), 
            10, 
            1.2, 
            "Decathlon Cáceres",  
            Urgency.EMERGENCY,
            Surcharge.LOW
        );

        nonUrgentOrder = new NonUrgentOrder(
            "John", 
            new Location(2, 3), 
            new Location(8, 4), 
            12, 
            2.5, 
            "Carrefour Badajoz",  
            Urgency.IMPORTANT,
            Surcharge.MEDIUM
        );

        medicalOrder = new MedicalOrder(
            "Maria", 
            new Location(1, 1), 
            new Location(6, 6), 
            8, 
            0.8, 
            "Hospital Cáceres", 
            Urgency.NONESSENTIAL
        );
    }

    /**
     * Limpia el entorno de prueba.
     *
     * Se llama después de cada método de prueba.
     */
    @After
    public void tearDown()
    {
        urgentOrder = null;
        nonUrgentOrder = null;
        medicalOrder = null;
    }

    /**
     * Prueba de la creación básica de un pedido.
     * Asegura que las ubicaciones de origen y destino se hayan configurado correctamente.
     */
    @Test
    public void testCreation()
    {
        // UrgentOrder
        assertEquals("Lucy", urgentOrder.getSendingName());  
        assertEquals(new Location(5, 5), urgentOrder.getLocationOrder());  
        assertEquals(new Location(5, 2), urgentOrder.getDestination());  
        assertEquals(10, urgentOrder.getDeliveryTime());  
        assertEquals(1.2, urgentOrder.getWeight(), 0.001);  
        assertEquals("Decathlon Cáceres", urgentOrder.getDestinationName());  

        // NonUrgentOrder
        assertEquals("John", nonUrgentOrder.getSendingName());  
        assertEquals(new Location(2, 3), nonUrgentOrder.getLocationOrder());  
        assertEquals(new Location(8, 4), nonUrgentOrder.getDestination());  
        assertEquals(12, nonUrgentOrder.getDeliveryTime());  
        assertEquals(2.5, nonUrgentOrder.getWeight(), 0.001);  
        assertEquals("Carrefour Badajoz", nonUrgentOrder.getDestinationName());  

        // MedicalOrder
        assertEquals("Maria", medicalOrder.getSendingName());  
        assertEquals(new Location(1, 1), medicalOrder.getLocationOrder());  
        assertEquals(new Location(6, 6), medicalOrder.getDestination());  
        assertEquals(8, medicalOrder.getDeliveryTime());  
        assertEquals(0.8, medicalOrder.getWeight(), 0.001);  
        assertEquals("Hospital Cáceres", medicalOrder.getDestinationName());  
    }

    /**
     * Prueba del método getDeliveryPersonName.
     * Asegura que este método obtiene y devuelve correctamente el nombre del repartidor.
     */
    @Test
    public void testGetDeliveryPersonName()
    {
        urgentOrder.setDeliveryPersonName("DP1");  
        nonUrgentOrder.setDeliveryPersonName("DP2");
        medicalOrder.setDeliveryPersonName("DP3");

        assertEquals("DP1", urgentOrder.getDeliveryPersonName());
        assertEquals("DP2", nonUrgentOrder.getDeliveryPersonName());
        assertEquals("DP3", medicalOrder.getDeliveryPersonName());
    }

    /**
     * Prueba del método charge para cada tipo de pedido.
     * Asegura que el cargo se calcule correctamente para cada tipo de pedido.
     */
    @Test
    public void testCharge()
    {
        // UrgentOrder: el cargo debe ser el doble del valor del recargo
        double expectedUrgentCharge = urgentOrder.getSurcharge().getValor() * 2;
        assertEquals(expectedUrgentCharge, urgentOrder.charge(), 0.001);

        // NonUrgentOrder: el cargo debe ser igual al valor del recargo
        double expectedNonUrgentCharge = nonUrgentOrder.getSurcharge().getValor();
        assertEquals(expectedNonUrgentCharge, nonUrgentOrder.charge(), 0.001);

        // MedicalOrder: el cargo debe ser 0 ya que es gratuito
        assertEquals(0, medicalOrder.charge(), 0.001);
    }

    /**
     * Prueba del método calculateEvaluationDP para cada tipo de pedido.
     * Asegura que la evaluación para el repartidor se calcule correctamente.
     */
    @Test
    public void testCalculateEvaluationDP()
    {
        // UrgentOrder debe devolver una evaluación de 10
        assertEquals(10, urgentOrder.calculateEvaluationDP());
        

        // NonUrgentOrder debe devolver una evaluación de 5
        assertEquals(5, nonUrgentOrder.calculateEvaluationDP());
        
        // MedicalOrder debe devolver una evaluación de 15
        assertEquals(15, medicalOrder.calculateEvaluationDP());
        
    }
    @Test
    public void testGetCharge() {
    // UrgentOrder: cargo = doble del recargo
    double expectedUrgentCharge = urgentOrder.getSurcharge().getValor() * 2;
    assertEquals("Cargo incorrecto para UrgentOrder", expectedUrgentCharge, urgentOrder.charge(), 0.001);

    // NonUrgentOrder: cargo = valor del recargo
    double expectedNonUrgentCharge = nonUrgentOrder.getSurcharge().getValor();
    assertEquals("Cargo incorrecto para NonUrgentOrder", expectedNonUrgentCharge, nonUrgentOrder.charge(), 0.001);

    // MedicalOrder: cargo = 0 (es gratuito)
    assertEquals("Cargo incorrecto para MedicalOrder", 0, medicalOrder.charge(), 0.001);
    }

    
   
}

