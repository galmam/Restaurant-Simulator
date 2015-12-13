
public class Kitchen_Tool {
	
	private String Name;
	private int Quantity;
	
	public Kitchen_Tool(String name,int num){
		this.Name=name;
		this.Quantity=num;
	}

	public String returnName()    {return this.Name;}
	
	public int  returnQuantity()     {return this.Quantity;}
	
	void getTool(int num){
         this.Quantity=this.Quantity-num;
	}
	
	void returnTool(int num){
		this.Quantity=this.Quantity+num;
	}
	
	
	public String toString(){
		StringBuilder s=new StringBuilder("Kitchen tool Name: ");
		s.append(this.Name);
		s.append(" and his quantity is: ");
		s.append(this.Quantity);
		return s.toString();
	}
	
}
