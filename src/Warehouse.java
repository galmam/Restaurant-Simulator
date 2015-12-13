//@ inv: for (0<=i<countIngs()) quantityOfSpecificIng(ings[i]) >= 0
//@ inv: for (0<=i<countTools()) quantityOfSpecificTool(ings[i]) >= 0
public interface Warehouse {
	
	//@ pre : for (0<=i<dish.countIngs()) quantityOfSpecificIng(ings[i])>=dish.getQuantity(ings[i])
	//@ pre : for (0<=i<dish.countTools()) quantityOfSpecificTool(tools[i])>=dish.getQuantity(tools[i])
	//@ post : for (0<=i<countIngs()) (quantityOfSpecificIng(ings[i])) + dish.getQuantity(ings[i]) == @before quantityOfSpecificIng(ings[i])
	//@ post : for (0<=i<countTools()) (quantityOfSpecificTool(tools[i])) + dish.getQuantity(tools[i]) == @before quantityOfSpecificTool(tools[i])
	public void acquireAll(Dish dish);
	
	//@ pre : none
	//@ post : for (0<=i<countIngs()) (quantityOfSpecificIng(ings[i]) - dish.getQuantity(ings[i]) == @before quantityOfSpecificIng(ings[i])
	//@ post : for (0<=i<countTools()) (quantityOfSpecificTool(tools[i]) - dish.getQuantity(tools[i]) == @before quantityOfSpecificTool(tools[i])
	public void returnTools (Dish dish);
	
}
