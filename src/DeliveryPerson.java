
public class DeliveryPerson implements Runnable {

	private String Name;
	private int Speed;
	private Address Restaurant_Address; //Restaurant 
	
	public DeliveryPerson(String name,int speed,Address address){
		this.Name=name;
		this.Speed=speed;
		this.Restaurant_Address=address;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
	
	public String toString(){
		String s="---------------------------"+"\n";
		s=s+"Delivery person Name is : "+this.Name+"\n";
		s=s+"Delivery person speed is : "+this.Speed+"\n";
		s=s+"-----------------------------------"+"\n";
		return s;
	}

}
