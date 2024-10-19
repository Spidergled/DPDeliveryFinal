/**
 * Model a location in a city.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @version 2024.10.07 DP classes 
 */
public class Location
{
    private int x;  //he cambiado de public a private
    private int y;  //he cambiado de public a private

    /**
     * Model a location in the city.
     * @param x The x coordinate. Must be positive.
     * @param y The y coordinate. Must be positive.
     * @throws IllegalArgumentException If a coordinate is negative.
     */
    public Location(int x, int y)
    {
        if(x < 0) {
            throw new IllegalArgumentException(
                "Negative x-coordinate: " + x);
        }
        if(y < 0) {
            throw new IllegalArgumentException(
                "Negative y-coordinate: " + y);
        }        
        this.x = x;
        this.y = y;
    }

    /**
     * @return The x coordinate.
     */
    public int getX()
    {
        return x;
    }

    /**
     * @return The y coordinate.
     */
    public int getY()
    {    
        return y;
    }

    /**
     * Generate the next location to visit in order to
     * reach the destination.
     * @param destination Where we want to get to.
     * @return A location in a direct line from this to
     *         destination.
     */
    public Location nextLocation(Location destination)
    {
        //TODO ahora mismo este método devuelve directamente el destino final
        //PERO DEBERIA ir calculando y devolviendo la siguiente posición por la que 
        // va pasando la persona que reparte hasta llegar al destino
        
        int nextX = this.x;
        int nextY = this.y;
        Location nuevoDestino;
        
        //Ajustar las cordenadas en X
        if(this.x < destination.getX()){
            nextX++;
        }else if(this.x > destination.getX()){
            nextX--;
        }
        
        //Ajustar las cordenadas en Y
        if(this.y < destination.getY()){
            nextY++;
        }else if(this.y > destination.getY()){
            nextY--;
        }
        
        //Crear la nueva posición
        nuevoDestino = new Location(nextX, nextY);
    
        //Devolver la nueva posicion, no el destino
        return nuevoDestino;
    }

    /**
     * Determine the number of movements required to get
     * from here to the destination.
     * @param destination The required destination.
     * @return the number of movement steps.
     */
    public int distance(Location destination)
    {
        //TODO implementar este método que devuelve el número total de pasos para alcanzar el destino
        
        int diferenciaX = this.x - destination.getX();
        int diferenciaY = this.y - destination.getY();
        int mayor = 0;
        
        //Convertir los valores en absolutos
        if(diferenciaX < 0){
            diferenciaX = -diferenciaX;
        }
        if(diferenciaY < 0){
            diferenciaY = -diferenciaY;
        }
        
        //Devolver el valor mayor entre diferenciaX y diferenciaY
        
        if(diferenciaX > diferenciaY){
            mayor = diferenciaX;
        }else{
            mayor = diferenciaY;
        }
        
        return mayor;
    }

    /**
     * @return A representation of the location.
     */
    public String toString()
    {
        return "location " + x + "," + y;
    }

    /**
     * Implement content equality for locations.
     * @return true if this location matches the other, false otherwise.
     */
    public boolean equals(Object other)
    {
        if(other instanceof Location) {
            Location otherLocation = (Location) other;
            return x == otherLocation.getX() &&
            y == otherLocation.getY();
        }
        else {
            return false;
        }
    }

    /**
     * Use the top 16 bits for the y value and the bottom for the x.
     * Except for very big grids, this should give a unique hash code
     * for each (x, y) pair.
     * @return A hashcode for the location.
     */
    public int hashCode()
    {
        return (y << 16) + x;
    }
}
