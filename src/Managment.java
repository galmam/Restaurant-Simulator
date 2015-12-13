import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

public class Managment {
	
	private final static Logger LOGGER = Logger.getLogger("Logger");
	private Vector<Chef> Chefs;
	private Address Restaurant_Address; 
	private Vector<DeliveryPerson> DeliveryPersons; //Persons
	private Vector<Order> Orders;
	private WarehouseImpl Warehouse;
	private Statistics Statistics;
	private Menu Menu;// a menu containing all the dishes on the menu
	private final Vector<Thread>  Workingchefs;
	private Vector<Future<Order>> CockedOrders;
	
	public Managment(Vector<Chef> chefs,Address restaurant_Address,Vector<DeliveryPerson> deliveryPersons,
			         Vector<Order> orders,WarehouseImpl warehouse,Menu menu){
		
		this.Chefs=chefs;
		this.DeliveryPersons=deliveryPersons;
		this.Restaurant_Address=restaurant_Address;
		this.Warehouse=warehouse;
		this.Orders=orders;
		this.Statistics=new Statistics();
		this.Menu=menu;
		this.Workingchefs= new Vector<Thread>(this.Chefs.size());
		this.CockedOrders=new Vector<Future<Order>>();
	}
	
	public   void Simulation() throws InterruptedException{ 
		for(int i=0;i<Chefs.size();i++){ 
			this.Workingchefs.add(new Thread(Chefs.get(i)));
			Chefs.get(i).setWarehouse(Warehouse);
			//Chefs.get(i).setCockedOrdersQueue(this.CockedOrders);
			Workingchefs.get(i).setName(Chefs.get(i).getName());
			Workingchefs.get(i).start();
			}// tell all the chefs to start working

		while(this.Orders.size()!=0){
			for (int i=0; i<this.Orders.size();i++){
				for(int j=0;(i<Orders.size())&&(j<this.Chefs.size());j++){
					if (Chefs.get(j).CanCookThisOrder(Orders.get(i))) { 
						Chefs.get(j).AddOrder(Orders.remove(i));
						}				
				}// end for  of chefs index
			}// end for of orders index
		}// end while there are still order to cook
		
		LOGGER.warning("We have Succssesfully cooked all the orders");
		ChefsShoutDown(); // stop all the chefs from receiving new orders and finish cooking

	}
	/*
	 * this function tells all the chefs to not accept anymore orders
	 */
	public void ChefsShoutDown(){
		for(int i=0;i<this.Chefs.size();i++){ this.Chefs.get(i).ShoutDown(); }
		LOGGER.warning("We told all the chefs to not accept any new order and finish cooking");
	}
	
	public  Chef getChef(int i){
		return this.Chefs.get(i);
	}
	
	public int NumOfChefs(){
		return this.Chefs.size();
	}
	
	public DeliveryPerson getDeliveryPerson(int i){
		return this.DeliveryPersons.get(i);
	}
	
	public int NumOfDeliveryPersons(){
		return this.DeliveryPersons.size();
	}
	public String PrintInventory(){
		return this.Warehouse.toString();
	}
	
	public void PrintOrders(){
		System.out.println( this.Orders.toString());
	}
	
	public void PrintMenu(){
		System.out.println(this.Menu.toString());
	}

}
