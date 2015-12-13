
public class Address {

	private int X;
	private int Y;
	
	public Address(int x,int y){
		this.X=x;
		this.Y=y;
	}
	
	public  int CalcDistance(Address other){
		return (int) Math.round((Math.sqrt(Math.pow(X-other.GetX(), 2)+Math.pow(Y-other.GetY(), 2))));
		}
	
	public int GetX(){
		return this.X;
	}
	
	public int GetY(){
		return this.Y;
	}
	public String toString(){
		String s;
		s="the address is X:"+this.X+"and Y:"+this.Y;
		return s;
	}
	
	
}
