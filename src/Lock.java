import java.util.logging.Logger;

public class Lock{
  
  private boolean isLocked ;
  private final static Logger LOGGER = Logger.getLogger("Logger");
  
  public Lock(){
	  this.isLocked=false;
  }
  public synchronized void lock()
  throws InterruptedException{
    while(isLocked){
      LOGGER.warning("The Chef "+Thread.currentThread().getName()+" is waiting for the warehouse to be empty");
      this.wait();      
    }
    isLocked = true;
    LOGGER.warning("The Chef "+Thread.currentThread().getName()+" is in the warehouse");
  }
  
  public synchronized void unlock(){
    isLocked = false;
    this.notifyAll();
    LOGGER.warning("The Chef "+Thread.currentThread().getName()+" is no longer in the warehouse");
  }
}
