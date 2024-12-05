import java.util.*;

/**
 * Provide a simple demonstration of running a stage-one
 * scenario. Several orders and delivery persons are created. 
 * Pickups are requested. As the simulation is run, the orders
 * should be picked up and then delivered to their destination.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @version 2024.10.07 DP classes
 */
public class DemoAvanzada
{
    DeliveryCompany company;
    private List<DeliveryPerson> actors; //simulation's actors, they are the delivery persons
                                         //of the company

    /**
     * Constructor for objects of class DemoOneOrder
     */
    public DemoAvanzada()
    {
        company = new DeliveryCompany("Compañía DP Delivery Cáceres");
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
        DeliveryPerson dp1 = new DeliveryPerson(company, new Location(10, 13),"DP2");
        DeliveryPerson dp2 = new DeliveryPerson(company, new Location(0, 0),"DP1");
        DeliveryPerson dp3 = new DeliveryPerson(company, new Location(16, 18),"DP3");
        DeliveryPerson dp4 = new DeliveryPerson(company, new Location(11, 1),"DP5");
        DeliveryPerson dp5 = new DeliveryPerson(company, new Location(2, 10),"DP6");
        DeliveryPerson dp6 = new DeliveryPerson(company, new Location(7, 7),"DP8");
        DeliveryPerson dp7 = new DeliveryPerson(company, new Location(15, 9),"DP7");
        DeliveryPerson dp8 = new DeliveryPerson(company, new Location(8, 19),"DP4");
        company.addDeliveryPerson(dp1);
        company.addDeliveryPerson(dp2);
        company.addDeliveryPerson(dp3);
        company.addDeliveryPerson(dp4);
        company.addDeliveryPerson(dp5);
        company.addDeliveryPerson(dp6);
        company.addDeliveryPerson(dp7);
        company.addDeliveryPerson(dp8);
        actors.addAll(company.getDeliveryPersons());
    }

    /**
     * Orders are created and added to the company
     */
    private void createOrders() {  
        //new: all the orders are created in the warehouse location
        Location whLocation= company.getWareHouseLocation(); //TODO: inicializar la variable: Location whLocation = obtener la localización del almacén.
        Order order1 = new Order("Kevin", whLocation,
                new Location(10, 10),10, 1.2, "Decathon Cáceres");
        Order order2 = new Order("Margo", whLocation,
                new Location(19,0),10, 1.2, "Pintores");
        Order order3 = new Order("Edith", whLocation,
                new Location(2,2),11, 1.2, "Ruta de la Plata");
        Order order4 = new Order("Stuart", whLocation,
                new Location(7,1),11, 1.2, "Cruz de los caídos");
        Order order5 = new Order("Agnes", whLocation,
                new Location(19,19),12, 1.2, "Ruta de la Plata");
        Order order6 = new Order("Bob", whLocation,
                new Location(0,0),12, 1.2, "Decathon Cáceres");
        company.addOrder(order1);
        company.addOrder(order2);
        company.addOrder(order3);
        company.addOrder(order4);
        company.addOrder(order5);
        company.addOrder(order6);
    }


    /**
     * A pickup is requested for a single order.
     * @throws IllegalStateException If a pickup cannot be found
     */
    private void runSimulation() {
        List<Order> orders = company.getOrders();
        Collections.sort(orders, new ComparadorOrdersHoraNombre());
        for(Order order : orders) {
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
        //TODO ordenar (por su nombre) y mostrar los objetos delivery persons
        // no se ordenan , hay que ordenar los delivery persons !!!Collections.sort(actors, new ComparadorPedidosEntregadosNombre());
        
        // Obtener la lista de DeliveryPersons desde la compañía
        List<DeliveryPerson> persons = company.getDeliveryPersons();
    
        // Ordenar usando ComparadorPedidosEntregadosNombre
        Collections.sort(persons, new ComparadorPedidosEntregadosNombre());
        for (DeliveryPerson dp : persons) {
        System.out.println("DeliveryPerson: " + dp.getName() + " at: " + dp.getLocation());
        }

        System.out.println(" ");        
        System.out.println("-->> Orders to be picked up <<--");
        System.out.println("-->> ---------------------- <<--");  
        Collections.sort(company.getOrders(), new ComparadorNombreDeliveryPersonOrder());
        for (Order order : company.getOrders()) {
        System.out.println("Order from: "+ order.getSendingName() + " to: " + order.getDestinationName() + " at: " + order.getDeliveryTime() +
                           " weight: " + order.getWeight() + " from: " + order.getLocationOrder().getX()+" - " + order.getLocationOrder().getY() +
                           " to: " + order.getDestination().getX() + " - " + order.getDestination().getY());
        }


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
        List<DeliveryPerson> persons = company.getDeliveryPersons();
        Collections.sort(persons, new ComparadorPedidosEntregadosNombre());
        for(DeliveryPerson dp:persons){
            System.out.println(dp.showFinalInfo());
        }

        System.out.println(" ");
        System.out.println("-->> Orders final information <<--");
        System.out.println("-->> ------------------------ <<--");
        Collections.sort(company.getOrders(), new ComparadorOrdersHoraNombre());
        for(Order order:company.getOrders()){
            System.out.println(order.showFinalInfo());
        }

    }
}
