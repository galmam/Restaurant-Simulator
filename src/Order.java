import java.util.Vector;
import java.util.Date;

public class Order {
	
	private String  orderID;
	private Vector<OrderOfDish> OrderOfDishes;
	private int  Diffculty_Rating ;//an integer represent the difficulty cocking all the dish types in the order
	private int Order_Status ; // 1 is incomplete ,2 is inprogress ,3 complete ,4 is delivered 
    private Address Customer_Address; // 
    private long RealCockTime;
    private long DeliveyTime; // delivery time
    
    
    public Order(String id,Vector<OrderOfDish> OrderOfDishes,Address address){
    	this.Customer_Address=address;
    	this.orderID=id;
    	this.OrderOfDishes=OrderOfDishes;
    	this.Diffculty_Rating=CalcDifficulty();
    	this.Order_Status=1;
    }
    
    private int CalcDifficulty(){
    	int diff=0;
    	for(int i=0;i<this.OrderOfDishes.size();i++){
    		diff=diff+OrderOfDishes.get(i).getDishDifficulty();
    	}
    	return diff;
    }
    
    public String toString(){
    	StringBuilder s=new StringBuilder("--------Order Details------------");
    	s.append("\n");
    	s.append("Order ID : ");
    	s.append(this.orderID);
    	s.append("\n");
    	s.append("Customer Address is ");
    	s.append(this.Customer_Address.toString());
    	s.append("\n");
    	s.append("Order Diffculty Rating is : ");
    	s.append(this.Diffculty_Rating);
    	s.append("\n");
    	s.append(this.OrderOfDishes.toString());
    	s.append("\n");

    	return s.toString();
    }
    
    public int getDifficulty(){
    	return this.Diffculty_Rating;
    }
    
    public int getNumOfDishes(){
    	int num=0;
    	for(int i=0;i<this.OrderOfDishes.size();i++){
    		num=num+OrderOfDishes.get(i).getAmount();
    	}
    	return num;
    }
    
    public int getNumOfDishType(){
    	return this.OrderOfDishes.size();
    }
    
    public int getQuantityOfDish(int index){ // Quantity 
    	return this.OrderOfDishes.get(index).getAmount();
    }
    
    public Dish getDish(int index){
    	return this.OrderOfDishes.get(index).getDish();
    }
    
    public String getID(){
    	return this.orderID;
    }
    
    public void setCockingTime(long time){
    	this.RealCockTime=time;
    }
    
    public long getCockingTime(){
    	return this.RealCockTime;
    }
}
