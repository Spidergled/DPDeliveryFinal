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
        
        // Pruebas del método nextLocation para verificar adyacencia
        Location proximoDestino = startLocation.nextLocation(destination);
        
        // Verificamos si el siguiente paso está un paso más cerca del destino
        assertEquals(2, proximoDestino.getX());  // X debe ser 2 (incrementado en 1)
        assertEquals(2, proximoDestino.getY());  // Y debe ser el mismo, ya que no cambia
        
        // Verificación con un destino más lejano
        Location proximoDestino2 = startLocation.nextLocation(newDestination);
        assertEquals(2, proximoDestino2.getX());  // X debe ser 2
        assertEquals(3, proximoDestino2.getY());  // Y debe ser 3 (incrementado en 1)
        
        // Verificación de adyacencia con un movimiento en el eje Y
        Location destinoVertical = new Location(3, 3).nextLocation(new Location(3, 5));
        assertEquals(3, destinoVertical.getX());  // X no cambia
        assertEquals(4, destinoVertical.getY());  // Y incrementa en 1

    }
}
