
import java.util.Vector;


public class WarehouseImpl implements Warehouse {

	protected Vector<Ingredient> Ings;
	protected Vector<Kitchen_Tool> Tools;
	public  Lock lock;
	
	public WarehouseImpl (Vector<Ingredient> ings,Vector<Kitchen_Tool> tools) {
		this.Ings = ings;
		this.Tools = tools;
		this.lock=new Lock();
	}
	@Override
	public void acquireAll(Dish dish) {
		synchronized(this.Tools){        
			for(int i=0;i<dish.NumOfToolsType();i++){
				Kitchen_Tool ToolInWarehouse=getTool(dish.getTool(i).returnName());
				//if((ToolInWarehouse.returnQuantity()-dish.getTool(i).returnQuantity())>0){

				while((ToolInWarehouse.returnQuantity()-dish.getTool(i).returnQuantity())<0){ 
					try {this.Tools.wait();} 
					catch (InterruptedException e) {}
					}// end while
					
				ToolInWarehouse.getTool(dish.getTool(i).returnQuantity());
				}
			}// we acquire all the tools that we need for this dish
			for(int j=0;j<dish.NumOfIngredientType();j++){
				Ingredient IngInWarehouse=	getIng(dish.getIngredient(j).returnName());
				IngInWarehouse.getIngredient(dish.getIngredient(j).getQuantity());

			}// we aquire all the ings that we need for this dish
	}
	
	public void returnTools (Dish dish) {
		
		for(int i=0;i<dish.NumOfToolsType();i++){
			Kitchen_Tool ToolInWarehouse=getTool(dish.getTool(i).returnName());
			synchronized(this.Tools){
				ToolInWarehouse.returnTool(dish.getTool(i).returnQuantity());
				this.Tools.notifyAll();
				//System.out.println("we return the tools we use");
			}
			}
		
	}
	
	
	// we don't need all this methods under here they are auxiliry
	public String toString(){
		String s="-------------------------"+"\n";
		s=s+"This is the ings list in the Warehouse"+"\n";
		s=s+Ings+"\n";
		s=s+"This is the Tools list in the Warehouse"+"\n";
		s=s+Tools+"\n";
		s=s+"-------------------------"+"\n";
		return s;
	}
	
	
	
	
	private Ingredient getIng(String name){
		for(int i=0;i<this.Ings.size();i++){
			if (this.Ings.get(i).returnName().equals(name)){return this.Ings.get(i);}
		}
		return null;
	}
	
	
	private Kitchen_Tool getTool(String name){
		for(int i=0;i<this.Tools.size();i++){
			if (this.Tools.get(i).returnName().equals(name)){return this.Tools.get(i);}
		}
		return null;
	}

}
