
public class OrderOfDish {

	private Dish Dish;
	private int Quantity;
	
	public OrderOfDish(Menu m,String name,int num){
		this.Dish=m.getDish(name);
		this.Quantity=num;
	}
	
	public int getDishDifficulty(){
		return this.Dish.getDifficulty();
	}
	
	public String toString(){
		StringBuilder s=new StringBuilder();
		s.append("the dish ");
		s.append(this.Dish.getName());
		s.append(" need to be cocked ");
		s.append(this.Quantity);
		s.append(" times");
		s.append("\n");
		return s.toString();
	}
	
	public int getAmount(){
		return this.Quantity;
	}
	
	public Dish getDish(){
		return this.Dish;
	}
}
