import java.util.Vector;
/*
 * note this class need to sync!!!!
 * sync methods should be added
 */

public class Statistics {
	
	private double Money_Gained;
	private Vector<Order> Delivered_Orders;
	private Vector<Ingredient>  Consumed_Ingredients; 
	
	public Statistics(){
		this.Money_Gained=0;
		this.Delivered_Orders=new Vector<Order>();
		this.Consumed_Ingredients=new Vector<Ingredient>();
	}
	
	

}
