import java.util.Vector;

/*
 * this class contain all the dished and their information on the menu
 */
public class Menu {
	
	private Vector<Dish> Dishes;
	
	
	public Dish getDish(String name){
		for(int i=0;i<this.Dishes.size();i++){
			if (Dishes.get(i).getName().equals(name)) {return Dishes.get(i);}
		}
		System.out.println("we didn't found "+name);
		return null;
	}
	public  Menu(Vector<Dish> dishes) {
		this.Dishes=dishes;
	}// end constructor 

	public Dish getDish(int index){
		 return this.Dishes.get(index).Clone();
	}
	
	public int NumOfDishes(){
		return this.Dishes.size();
	}
	
	public String toString(){
		StringBuilder s=new StringBuilder("---------------Restaurant Menu-------------");
		s.append("\n");
		s.append(this.Dishes.toString());
		s.append("\n");
		s.append("---------------------------------------------");		
		s.append("\n");
		return s.toString();
	}

}
