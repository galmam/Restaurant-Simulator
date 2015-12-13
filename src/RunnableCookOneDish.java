import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.logging.Logger;


public class RunnableCookOneDish implements Runnable {

	private final static Logger LOGGER = Logger.getLogger("Logger");
	private Dish Dish;
	private WarehouseImpl warehouse;
	private Chef chef;
	private CountDownLatch DishesNeededToBeCocked;
	private CountDownLatch DishesAcquiringReamining;
	private Semaphore WarehouseLocker;
	
	public RunnableCookOneDish(Dish dish,WarehouseImpl warehouse,Chef chef,
			CountDownLatch counter,CountDownLatch Acquiringcounter,
			Semaphore WarehouseLocker){
		this.Dish=dish;
		this.warehouse=warehouse;
		this.chef=chef;
		this.DishesNeededToBeCocked=counter;
		this.DishesAcquiringReamining=Acquiringcounter;
		this.WarehouseLocker=WarehouseLocker;
	}

	public void run() {
		try {
		WarehouseLocker.acquire();		 
		warehouse.acquireAll(Dish);
		WarehouseLocker.release();		
		DishesAcquiringReamining.countDown();
		try { Thread.currentThread().sleep((long)(Dish.getCockTime()*chef.getEffciency()));} 
		catch (InterruptedException e) {}
        warehouse.returnTools(Dish);
        DishesNeededToBeCocked.countDown();  
		}
        catch (InterruptedException e1) {
        	LOGGER.warning("We have a Thread Interputed");
		}
	}

}
