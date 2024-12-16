import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test implementation of the Location class.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @version 2024.10.07 DP classes 
 */
public class LocationTest
{
    Location startLocation;
    Location destination;
    Location newDestination; //Nueva ubicacion 
    //TODO
    //crear más campos (si es necesario) 

    /**
     * Default constructor for test class LocationTest
     */
    public LocationTest()
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
        startLocation = new Location(1, 2);
        destination = new Location(2, 2);
        newDestination = new Location(5,5);  //Otra ubicacion para pruebas

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
     * Test the distance method of the Location class.
     */
    @Test
    public void testDistance()
    {
        assertEquals(startLocation.distance(new Location(5, 7)), 5);
        assertEquals(startLocation.distance(destination), 1);
        //Misma prueba utilizando otra aserción:
        assertTrue(startLocation.distance(destination) == 1);
        
        //prueba adicional
        assertEquals(newDestination.distance(new Location(9,5)), 4);  //Distancia debe ser 4
    }

    /**
     * Run tests of the nextLocation method of the Location class.
     */
    @Test
    public void testAdjacentLocations()
    {
        //TODO implementar este método
        // Testear la adyacencia entre dos localizaciones. Se puede hacer 
        // utilizando llamada al método "nextLocation".
         // Verificar el siguiente paso hacia un destino
        Location nextStep = startLocation.nextLocation(destination);
        assertEquals(2, nextStep.getX()); // X incrementa hacia el destino
        assertEquals(2, nextStep.getY()); // Y permanece igual

        // Probar movimiento hacia un destino más lejano
        Location nextStep2 = startLocation.nextLocation(newDestination);
        assertEquals(2, nextStep2.getX()); // X incrementa en 1
        assertEquals(3, nextStep2.getY()); // Y incrementa en 1

        // Prueba con movimiento en eje Y únicamente
        Location verticalMove = new Location(3, 3).nextLocation(new Location(3, 5));
        assertEquals(3, verticalMove.getX()); // X no cambia
        assertEquals(4, verticalMove.getY()); // Y incrementa en 1

        // Prueba de adyacencia directa
        Location adjacent = new Location(4, 4).nextLocation(new Location(5, 4));
        assertEquals(5, adjacent.getX()); // Movimiento en X hacia el destino
        assertEquals(4, adjacent.getY()); // Y permanece igual

        // Probar si nextLocation no da un paso inválido
        Location invalidMove = startLocation.nextLocation(startLocation);
        assertEquals(1, invalidMove.getX()); // No debe cambiar si el destino es igual
        assertEquals(2, invalidMove.getY());

    }
}
