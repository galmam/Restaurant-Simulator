import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.CountDownLatch;
import java.util.Vector;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.FutureTask;
import java.util.logging.Logger;


public class Chef implements Runnable {

	private final static Logger LOGGER = Logger.getLogger("Logger");
	private String Name;
	private double Effciency_Rating;
	private int Endurance_Rating;
	private int Current_Pressure;
	private WarehouseImpl Warehouse;
	private  ExecutorService ThreadsPool;
	private boolean isRuning;	
	private boolean isWaiting;
	
	public Chef(String name, double eff, int end){
		this.Name=name;
		this.Warehouse=null;
		this.Effciency_Rating=eff;
		this.Endurance_Rating=end;
		this.Current_Pressure=0;
		this.ThreadsPool=Executors.newCachedThreadPool();
		this.isRuning=true;
		this.isWaiting=true;
	}
	
	@Override
	public void run() {
		try {
			
		    while(this.isRuning){
		    	
		    	while(this.isWaiting){
		    		synchronized(this){
		    	    StringBuilder s=new StringBuilder(Name);
		    		s.append(" Is Waiting for a new order");
		    		LOGGER.info(s.toString());
		    		this.isWaiting=true;
		    		this.wait();
			    	}// end sync
		    		}//end while there are no order to cook
  		    }
		    this.ThreadsPool.shutdown();

		}// end try
    		catch (InterruptedException e) {
    			LOGGER.warning("We Interputed the Chef"+Name+" while he was waiting to other orders.");
    			Thread.currentThread().interrupt();}// end catch
	}// end run
	
	public synchronized  void ShoutDown(){
		if (this.isWaiting){ this.notifyAll(); }
		this.isWaiting=false;
		this.isRuning=false;
	}
	
	public void setCockedOrdersQueue(Vector<Order> queue){
		
	}
	
	public void  AddOrder(Order order){ // syncronzied
		synchronized(this){
		//this.NeededToBeCockedOrders.add(order);
		//this.notifyAll();
		//this.isWaiting=false;
		ThreadsPool.submit(new CallableCookWholeOrder(order,Warehouse,this));
		this.isWaiting=true;
		LOGGER.info("We added a new order to chef "+Name);
		}
	}
	
	public void setWarehouse(WarehouseImpl warehouse){
		this.Warehouse=warehouse;
	}
	
	public String toString(){
		StringBuilder s=new StringBuilder("----------------------------");
		s.append("\n");
		s.append("Chef Name: ");
		s.append(this.Name);
		s.append("\n");
		s.append("Chef Endurance :  ");
		s.append(this.Endurance_Rating);
		s.append("\n");
		s.append("Chef Effciency : ");
		s.append(this.Effciency_Rating);
		s.append("\n");
		s.append("Chef current pressure is : ");
		s.append("\n");
		s.append("----------------------------------");
		s.append("\n");

		return s.toString();
	}
	
	public String getName(){
		return this.Name;
	}
	
	public double getEffciency(){
		return this.Effciency_Rating;
	}
	
	public int getEndurance(){
		return this.Endurance_Rating;
	}
	
	public int getPressure(){
		return this.Current_Pressure;
	}
	
	public boolean setPressure(int num){
		System.out.println("we are trying to change the pressure");
		return true;
	}

	public boolean CanCookThisOrder(Order order){
		if ((order.getDifficulty())<=(this.Endurance_Rating-this.Current_Pressure))
		    {this.Current_Pressure=+order.getDifficulty();
		     return true;
		    }
		return false;
	}
	
	public synchronized void FinishCockingOrder(Order order){
		this.Current_Pressure=this.Current_Pressure-order.getDifficulty();
		LOGGER.warning("The Chef "+Name+" has finisih cooking and his pressure now is "+this.Current_Pressure);
		// we need to add the order to chef completed order queue for the delivery person
		this.isWaiting=false;
		this.notifyAll();
	}







}
