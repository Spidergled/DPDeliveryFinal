import java.util.*;

/**
 * Provide a simple demonstration of running a stage-one
 * scenario. A single order and delivery person are created, and a pickup
 * requested. As the simulation is run, the order
 * should be picked up and then delivered to their destination.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @version 2024.10.07 DP classes
 */
public class DemoOneOrder
{
    DeliveryCompany company;
    private List<DeliveryPerson> actors; //simulation's actors, they are the delivery persons
                                         //of the company

    /**
     * Constructor for objects of class DemoOneOrder
     */
    public DemoOneOrder()
    {
        WareHouse warehouse = new WareHouse(); // Crea un nuevo almacen
        company = new DeliveryCompany("Compañía DP Delivery Cáceres", warehouse);
        actors = new ArrayList<>();
        reset();
        run();
    }

    /**
     * Run the demo for a fixed number of steps.
     */
    public void run()
    {        
        //Ejecutamos un número de pasos la simulación.
        //En cada paso, cada persona de reparto realiza su acción
        for(int step = 0; step < 100; step++) {
            step();
        }
        showFinalInfo();
    }

    /**
     * Run the demo for one step by requesting
     * all actors to act.
     */
    public void step()
    {
        for(DeliveryPerson actor : actors) {
            actor.act();
        }
    }

    /**
     * Reset the demo to a starting point.
     * A single delivery person and order are created, and a pickup is
     * requested for a single order.
     * @throws IllegalStateException If a pickup cannot be found
     */
    public void reset()
    {
        actors.clear();

        createDeliveryPersons();
        createOrders(); 
        showInicialInfo();
        runSimulation();
    }

    /**
     * DeliveryPersons are created and added to the company
     */
    private void createDeliveryPersons() {
        DeliveryPerson dp = new DeliveryPerson(company, new Location(10, 10),"DP1");
        company.addDeliveryPerson(dp);
        actors.addAll(company.getDeliveryPersons());
    }

    /**
     * Orders are created and added to the company
     */
    private void createOrders() {
        //new: all the orders are created in the warehouse location
        Location whLocation = company.getWarehouse().getLocation(); //TODO: inicializar la variable: Location whLocation = obtener la localización del almacén.
        Order order = new Order("Lucy", whLocation,
                new Location(5,2),10, 1.2, "Decathon Cáceres");
        company.addOrder(order);
    }

    /**
     * A pickup is requested for a single order.
     * @throws IllegalStateException If a pickup cannot be found
     */
    private void runSimulation() {
        List<Order> orders = company.getOrders();
        
        //TODO: Ordenar los pedidos ascendentemente por su hora de llegada y 
        //en caso de empate por el nombre de la persona de destino 
        Collections.sort(orders, (o1, o2) -> {
            int timeCom = Integer.compare(o1.getDeliveryTime(), o2.getDeliveryTime());
        if (timeCom == 0) {
            return o1.getDestinationName().compareTo(o2.getDestinationName());
        }
        return timeCom;
        });
        
        for(Order order : orders) {
            System.out.println(order.showFinalInfo());
            if(!company.requestPickup(order)) {
                throw new IllegalStateException("Failed to find a pickup.");        
            }
        }

    }

    /**
     * Initial info is showed with the information about delivery persons and orders
     */
    private void showInicialInfo() {

        System.out.println("--->> Simulation of the company: "+company.getName()+" <<---");
        System.out.println("-->> Delivery persons of the company <<--");
        System.out.println("-->> ------------------------------- <<--");
        
        // Ordenar los DeliveryPerson por nombre
        Collections.sort(actors, new ComparadorNombreDeliveryPerson());
        for (DeliveryPerson dp : actors) {
        System.out.println("DeliveryPerson " + dp.getName() + " at location " + dp.getLocation());
        }
        
        System.out.println(" ");
        System.out.println("-->> Orders to be picked up <<--");
        System.out.println("-->> ------------------------------- <<--");
        
        // Ordenar los pedidos (Orders) por nombre de la persona que envía y luego por hora de entrega
        Collections.sort(company.getOrders(), new ComparadorNombreDeliveryPersonOrder());
        for (Order order : company.getOrders()) {
        System.out.println(order.showFinalInfo());
        }
        
        //TODO ordenar (por el nombre de la persona que envía) y mostrar los pedidos
        //para ordenar una colección aplicando un comparador, esta sería 
        //la sintaxis (suponiendo que "orders" es una colección donde
        //la compañía almacena los pedidos):
        //Collections.sort(orders, new ComparadorOrderDeliveryPersonName());


        System.out.println(" ");        
        System.out.println("-->> Simulation start <<--");
        System.out.println("-->> ---------------- <<--");
        System.out.println(" ");        
    }

    /**
     * Final info is showed with the information about delivery persons and orders
     */
    private void showFinalInfo() {

        System.out.println(" ");
        System.out.println("-->> ----------------- <<--");
        System.out.println("-->> End of simulation <<--");        
        System.out.println("-->> ----------------- <<--");
        System.out.println(" ");

        System.out.println("-->> Delivery persons final information <<--");
        System.out.println("-->> ---------------------------------- <<--");
        Collections.sort(actors, new ComparadorPedidosEntregadosNombre());
        for (DeliveryPerson dp : actors) {
        System.out.println(dp.showFinalInfo());
        }
        //TODO ordenar (por número de pedidos entregados y si empate por nombre) 
        // y mostrar los objetos delivery persons

        System.out.println(" ");
        System.out.println("-->> Orders final information <<--");
        System.out.println("-->> ------------------------ <<--");
        //TODO ordenar (por hora de entrega y si empate por nombre de la persona 
        //  que recibe el pedido) y mostrar los pedidos
        Collections.sort(company.getOrders(), new ComparadorOrdersHoraNombre());
        for (Order order : company.getOrders()) {
        System.out.println(order.showFinalInfo());
        }

    }
}
       

