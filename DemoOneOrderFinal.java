import java.util.*;

/**
 * Provide a simple demonstration of running a stage-one
 * scenario. A single order and delivery person are created, and a pickup
 * requested. As the simulation is run, the order
 * should be picked up and then delivered to their destination.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @version 2024.11.13 DP classes
 */
public class DemoOneOrderFinal
{
    DeliveryCompany company;
    private List<DeliveryPerson> actors; //simulation's actors, they are the delivery persons
    //of the company

    /**
     * Constructor for objects of class DemoOneOrder
     */
    public DemoOneOrderFinal()
    {
        company = new DeliveryCompany("Compañía DP Delivery Cáceres");
        actors = new ArrayList<>();

        reset();



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
        DeliveryPerson dp1 = new SpecialDP(company, new Location(3, 3),"DP2");
        
        company.addDeliveryPerson(dp1);
        actors.addAll(company.getDeliveryPersons());
    }

    /**
     * Orders are created and added to the company
     */
    private void createOrders() {
        //all the orders are created in the warehouse location
        //Parameters: sendingName, location, destination, deliveryTime, 
        //weight, destinationName, urgency, surchage (only for Urgent and NonUrgent orders)
        Location whLocation = company.getWareHouse().getLocation();
        Order order1 = new MedicalOrder("Kevin", whLocation,
                new Location(14,2),11, 2.2, "Ruta de la Plata",Urgency.EMERGENCY);
        company.addOrder(order1);
    }

    /**
     * A pickup is requested for a single order.
     * @throws IllegalStateException If a pickup cannot be found
     */
    private void runSimulation() {
        //Obtener los orders desde wareHouse
        //ya vienen ordenados por su tipo de urgency, hora de llegada y destinationName
        //TODO colección   orders = company.getOrders();
        Set<Order> orders=company.getOrders();
        Iterator<Order> it = orders.iterator();
        while(it.hasNext()) {
            Order order = it.next();
            if(!company.requestPickup(order)) {
                throw new IllegalStateException("Failed to find a pickup.");        
            }
        }
    }

    /**
     * Initial info is showed with the information about delivery persons and orders
     */
    private void showInicialInfo() {
        //Obtenemos los objetos DeliveryPerson de la compañía
        List<DeliveryPerson> deliveryPersons = company.getDeliveryPersons();
        //Obtenemos los objetos Orders del almacén (vienen ya ordenados)
        //TODO colección  orders = company.getOrders();
        Set<Order> orders=company.getOrders();

        System.out.println("--->> Simulation of the company: "+company.getName()+" <<---");
        System.out.println("-->> Delivery persons of the company <<--");
        System.out.println("-->> ------------------------------- <<--");
        //TODO ordenar (por su nombre) y mostrar los objetos delivery persons
        Collections.sort(deliveryPersons, new ComparadorNombreDeliveryPerson());
        for(DeliveryPerson  dp : deliveryPersons) {
            System.out.println(dp);
        }

        System.out.println("-->> Orders to be picked up <<--");
        System.out.println("-->> ---------------------- <<--");
        Iterator<Order> it = orders.iterator();
        while(it.hasNext()) {
            Order order = it.next();
            System.out.println(order.showInitialInfo());
        }
        System.out.println("");
        System.out.println("-->> Simulation start <<--");
        System.out.println("-->> ---------------- <<--");
        System.out.println("");        
    }

    /**
     * Final info is showed with the information about delivery persons and orders
     */
    private void showFinalInfo() {
        //Obtenemos los objetos DeliveryPerson de la compañía
        List<DeliveryPerson> deliveryPersons = company.getDeliveryPersons();
        //Obtenemos los orders entregados con sus objetos DeliveryPerson asociados
        //desde el almacén (vienen ya ordenados)
        //TODO declarar colección ordersDelivered = company.getWareHouse().getDeliveredOrders();
        Map <Order, DeliveryPerson> deliveredOrders=company.getWareHouse().getDeliveredOrders();
        System.out.println("");
        System.out.println("-->> ----------------- <<--");
        System.out.println("-->> End of simulation <<--");        
        System.out.println("-->> ----------------- <<--");
        System.out.println("");

        System.out.println("-->> Delivery persons final information <<--");
        System.out.println("-->> ---------------------------------- <<--");
        //TODO ordenar (por número de pedidos entregados y si empate por nombre) 
        // y mostrar los objetos delivery persons     
        Collections.sort(deliveryPersons, new ComparadorPedidosEntregadosNombre());
        for(DeliveryPerson  dp : deliveryPersons) {
            System.out.println(dp.showFinalInfo());
        }

        System.out.println("");
        System.out.println("-->> Orders final information <<--");
        System.out.println("-->> ------------------------ <<--");
        //TODO los pedidos entregados vienen en orden creciente por sendingName y  
        // en caso de empate por la hora de entrega y mostrar los pedidos y quién 
        // lo entregó
        //Mostrar los orders obtenidos
        
        Iterator<Map.Entry<Order, DeliveryPerson>> it = deliveredOrders.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<Order, DeliveryPerson> entry = it.next();
            Order order = entry.getKey();
            DeliveryPerson deliveryPerson = entry.getValue();
           System.out.println(order.showFinalInfo());
        }
}
}
