import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Clase de pruebas para Order y sus subclases.
 */
public class OrderTest {
    private Order urgentOrder;
    private Order nonUrgentOrder;
    private Order medicalOrder;

    /**
     * Configuración inicial antes de cada prueba.
     */
    @Before
    public void setUp() {
        // Inicialización de diferentes tipos de pedidos
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
     * Limpieza después de cada prueba.
     */
    @After
    public void tearDown() {
        urgentOrder = null;
        nonUrgentOrder = null;
        medicalOrder = null;
    }

    /**
     * Prueba para verificar la correcta inicialización de objetos Order.
     */
    @Test
    public void testCreation() {
        // Verificar inicialización de UrgentOrder
        assertEquals("Lucy", urgentOrder.getSendingName());
        assertEquals(new Location(5, 5), urgentOrder.getLocationOrder());
        assertEquals(new Location(5, 2), urgentOrder.getDestination());
        assertEquals(10, urgentOrder.getDeliveryTime());
        assertEquals(1.2, urgentOrder.getWeight(), 0.001);
        assertEquals("Decathlon Cáceres", urgentOrder.getDestinationName());

        // Verificar inicialización de NonUrgentOrder
        assertEquals("John", nonUrgentOrder.getSendingName());
        assertEquals(new Location(2, 3), nonUrgentOrder.getLocationOrder());
        assertEquals(new Location(8, 4), nonUrgentOrder.getDestination());
        assertEquals(12, nonUrgentOrder.getDeliveryTime());
        assertEquals(2.5, nonUrgentOrder.getWeight(), 0.001);
        assertEquals("Carrefour Badajoz", nonUrgentOrder.getDestinationName());

        // Verificar inicialización de MedicalOrder
        assertEquals("Maria", medicalOrder.getSendingName());
        assertEquals(new Location(1, 1), medicalOrder.getLocationOrder());
        assertEquals(new Location(6, 6), medicalOrder.getDestination());
        assertEquals(8, medicalOrder.getDeliveryTime());
        assertEquals(0.8, medicalOrder.getWeight(), 0.001);
        assertEquals("Hospital Cáceres", medicalOrder.getDestinationName());
    }

    /**
     * Prueba del método getDeliveryPersonName.
     */
    @Test
    public void testGetDeliveryPersonName() {
        // Configurar los nombres de los repartidores
        urgentOrder.setDeliveryPersonName("Repartidor 1");
        nonUrgentOrder.setDeliveryPersonName("Repartidor 2");
        medicalOrder.setDeliveryPersonName("Repartidor 3");

        // Verificar que los nombres sean los correctos
        assertEquals("Repartidor 1", urgentOrder.getDeliveryPersonName());
        assertEquals("Repartidor 2", nonUrgentOrder.getDeliveryPersonName());
        assertEquals("Repartidor 3", medicalOrder.getDeliveryPersonName());
    }

    /**
     * Prueba del método getDestination.
     */
    @Test
    public void testGetDestination() {
        // Verificar las ubicaciones de destino de cada tipo de pedido
        assertEquals(new Location(5, 2), urgentOrder.getDestination());
        assertEquals(new Location(8, 4), nonUrgentOrder.getDestination());
        assertEquals(new Location(6, 6), medicalOrder.getDestination());
    }

    /**
     * Prueba del método getCharge.
     * Verifica que el cálculo del cargo sea correcto para cada tipo de pedido.
     */
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

    /**
     * Prueba del método calculateEvaluationDP.
     * Verifica que la evaluación del repartidor sea correcta según el tipo de pedido.
     */
    @Test
    public void testCalculateEvaluationDP() {
        // UrgentOrder debe devolver una evaluación de 10
        assertEquals("Evaluación incorrecta para UrgentOrder", 10, urgentOrder.calculateEvaluationDP());

        // NonUrgentOrder debe devolver una evaluación de 5
        assertEquals("Evaluación incorrecta para NonUrgentOrder", 5, nonUrgentOrder.calculateEvaluationDP());

        // MedicalOrder debe devolver una evaluación de 15
        assertEquals("Evaluación incorrecta para MedicalOrder", 15, medicalOrder.calculateEvaluationDP());
    }
}
