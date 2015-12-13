import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.logging.Logger;


public class CallableCookWholeOrder implements Callable<Order> {
	
	private final static Logger LOGGER = Logger.getLogger("Logger");
	private CountDownLatch DishesNeededToBeCocked;
	private Order order;
	private  WarehouseImpl warehouse;
	private Chef chef;
	private long StartTime;
	private long EndTime;
	private CountDownLatch DishesAcquiringReamining; // how much dishes yet needed to acquire their tools and ings
	private Semaphore WarehouseLocker = new Semaphore(1);
	
	public CallableCookWholeOrder(Order order,WarehouseImpl warehouse,Chef chef){
		this.order=order;
		this.chef=chef;
		this.warehouse=warehouse;
		DishesAcquiringReamining=new CountDownLatch(order.getNumOfDishes());
		this.DishesNeededToBeCocked=new CountDownLatch(order.getNumOfDishes());
	}
	
 
	@Override
	public Order call() throws Exception {
		
		Thread.currentThread().setName(chef.getName()+" Cooking Order ID: "+order.getID());
		warehouse.lock.lock();		
		this.StartTime=Calendar.getInstance().getTimeInMillis();
		
			for(int j=0;j<order.getNumOfDishType();j++){
				for(int k=0;k<order.getQuantityOfDish(j);k++){
					new Thread(new RunnableCookOneDish(order.getDish(j),
							warehouse,
							chef,
							DishesNeededToBeCocked,
							DishesAcquiringReamining,
							WarehouseLocker)).start();
				}// end for Quantity of one dish type from order
			} // end for index on number of dish type in the order
			
		DishesAcquiringReamining.await();
		warehouse.lock.unlock();	
		StringBuilder s=new StringBuilder("We acquire all neede tools and ingredients and "); 
		s.append(chef.getName());
		s.append(" Start cooking Order ID:");
		s.append(order.getID());
		LOGGER.info(s.toString());

			
		DishesNeededToBeCocked.await();
		this.EndTime=Calendar.getInstance().getTimeInMillis();
		order.setCockingTime(EndTime-StartTime);
		LOGGER.info("Cocking time was about "+order.getCockingTime()+"ms");
		LOGGER.warning("We cocked all the dishes of Order:"+order.getID());
		chef.FinishCockingOrder(order);
		return this.order;
	}
	
	

}
