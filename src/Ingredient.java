
public class Ingredient {

	private String Ingredients_Name;
	private int Quantity ; 
	
	public Ingredient(String name,int num){
		this.Ingredients_Name=name;
		this.Quantity=num;
	}

	String returnName()    {return this.Ingredients_Name;}
	
	int  getQuantity()     {return this.Quantity;}
	
	void getIngredient(int num){
           this.Quantity=this.Quantity-num;
	}
	
	public String toString(){
		StringBuilder s=new StringBuilder("Ingredient Name: ");
		s.append(this.Ingredients_Name);
		s.append(" and his quantity is: ");
		s.append(this.Quantity);
		return s.toString();
	}
	
}
