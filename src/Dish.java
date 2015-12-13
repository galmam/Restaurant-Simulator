import java.util.Vector;

public class Dish {
	
	private String Dish_Name;
	private long Dish_Cock_Time;
	private int reward;
	private Vector<Kitchen_Tool> KitchenTools; //Kitchen 
	private Vector<Ingredient> Ingredients; // Ingredients
	private int Diffculty_Rating; //an integer represent the difficulty cocking the dish
	
	
	public Kitchen_Tool getTool(int index){
		return this.KitchenTools.get(index);
	}
	

	
	public int NumOfToolsType(){
		return this.KitchenTools.size();
	}
	
	public int NumOfIngredientType(){
		return this.Ingredients.size();
	}
	
	public Ingredient getIngredient(int index){
		return this.Ingredients.get(index);
	}
	
	public Dish(int diff,String name,long cocktime,int reward,Vector<Kitchen_Tool> KitchenTools,Vector<Ingredient> Ingredients){
		this.Diffculty_Rating=diff;
		this.Dish_Name=name;
		this.Dish_Cock_Time=cocktime;
		this.Ingredients=Ingredients;
		this.KitchenTools=KitchenTools;
		this.reward=reward;
	}
	
	public Dish Clone(){
         return (new Dish(this.Diffculty_Rating,this.Dish_Name,this.Dish_Cock_Time,this.reward,this.KitchenTools,this.Ingredients));		
	}
	
	public String toString(){
		StringBuilder s=new StringBuilder ("--------------------------------------");
		s.append("\n");
		s.append("1)This dish name is: ");
		s.append(this.Dish_Name);
		s.append("\n");
		s.append("2)This dish cock time is: ");
		s.append(this.Dish_Cock_Time);
		s.append("\n");
		s.append("3)This dish difficulty rating  is: ");
		s.append(this.Diffculty_Rating);
		s.append("\n");
		s.append("4)This dish reward  is: ");
		s.append(this.reward);
		s.append("\n");
		s.append("5)This is the list of all this dish need kitchen tools");
		s.append("\n");
		s.append(this.KitchenTools.toString());
		s.append("\n");
		s.append("6)This is the list of all this dish needed ings");
		s.append("\n");
		s.append(this.Ingredients.toString());
		s.append("\n");
		s.append("--------------------------------------");
		s.append("\n");

		return s.toString();
		
	}
	public String getName(){
		return this.Dish_Name;
	}
	
	public long getCockTime(){
		return this.Dish_Cock_Time;
	}
	
	public int getDifficulty(){
		return this.Diffculty_Rating;
	}

}
