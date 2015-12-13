import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.util.Vector;

		
public  class FileParsing {
	
	static public Menu MenuParsing(Document doc) {
		Vector<Dish> Dishes=new Vector<Dish>();    
	    NodeList Dish_Nodes = doc.getElementsByTagName("Dish");//we create a node list of all the dishes in the file     	
	    for (int temp = 0; temp < Dish_Nodes.getLength(); temp++) {	    	
	        
	    	Element e = (Element)(Dish_Nodes.item(temp)); // create an element with only one dish info	        
	        String name=e.getElementsByTagName("name").item(0).getTextContent(); //get the name of the dish
	        int difficulty=Integer.parseInt(e.getElementsByTagName("difficultyRating").item(0).getTextContent()); //get the difficulty rating
	        long cock_time=Long.parseLong(e.getElementsByTagName("expectedCookTime").item(0).getTextContent()); // get the cock time of the dish
	        int reward =Integer.parseInt(e.getElementsByTagName("reward").item(0).getTextContent());	        
	        Vector<Kitchen_Tool> KitchenTools=ParsingKitchenTools(e);// a vector that contains all the needed tools for this one dish
		    Vector<Ingredient> Ingredients=ParsingIngredients(e); // a vector that contains all the needed ings for this one dish		    
		    Dishes.add(new Dish(difficulty,name,cock_time,reward,KitchenTools,Ingredients)); //create a new dish and insert it into the data members dishes
		    
	    }// end for temp 
        return (new Menu(Dishes));
	}// end of MenuPrasing
	
	static private  Vector<Ingredient> ParsingIngredients(Element e){
		Vector<Ingredient> Ingredients=new Vector<Ingredient>();
        NodeList ings=e.getElementsByTagName("Ingredient"); // an element contains all the tools from the file of this one dish
	    for (int j = 0; j < ings.getLength(); j++) {
	    	Element ing  = (Element)(ings.item(j)); // get one ing element from the list
	    	String ing_name=ing.getElementsByTagName("name").item(0).getTextContent(); // get the ing name
	    	int ing_amount=Integer.parseInt(ing.getElementsByTagName("quantity").item(0).getTextContent()); // how much of this ing we need to cock this dish
	    	Ingredients.add(new Ingredient(ing_name,ing_amount)); // insert the info collected into the vector
	    } // end for j	
	    return Ingredients;
	} // end ParsingIngredients
	
	static private  Vector<Kitchen_Tool> ParsingKitchenTools(Element e){
        NodeList tools=e.getElementsByTagName("KitchenTool"); // an element contains all the tools from the file of this one dish
        Vector<Kitchen_Tool> KitchenTools=new Vector<Kitchen_Tool>(); // a vector that contains all the needed tools for this one dish
	    for (int i = 0; i < tools.getLength(); i++) {
	    	Element tool = (Element)(tools.item(i)); // get one tool element from the list
	    	String tool_name=tool.getElementsByTagName("name").item(0).getTextContent(); // get the tool name
	    	int tool_amount=Integer.parseInt(tool.getElementsByTagName("quantity").item(0).getTextContent()); // how much of this tool we need to cock this dish
	    	KitchenTools.add(new Kitchen_Tool(tool_name,tool_amount)); // insert the info collected into the vector
	    } // end for i		
	    return KitchenTools;
	}// end ParsingKitchenTools

	static private  Address ParsingAddress(Element e){
        String x=(e.getElementsByTagName("x").item(0).getTextContent());
        String y=(e.getElementsByTagName("y").item(0).getTextContent());
        return (new Address(Integer.parseInt(x),Integer.parseInt(y)));
	}
	 
	static private  Vector<OrderOfDish> ParsingOrderOfDishes(Menu m,Element e){
        NodeList Dishes= e.getElementsByTagName("Dish");
		Vector<OrderOfDish> OrderOfDishes= new Vector<OrderOfDish>();
        for(int i=0;i<Dishes.getLength();i++){
           	Element Dish=(Element)(Dishes.item(i));
           	String dish_name=Dish.getElementsByTagName("name").item(0).getTextContent();
           	int amount=Integer.parseInt(Dish.getElementsByTagName("quantity").item(0).getTextContent());
           	OrderOfDishes.add(new OrderOfDish(m,dish_name,amount));
         }// end for
        return OrderOfDishes;
	}
		
	static public Vector<Order>  OrdersParsing(Menu m,Document doc) {
	    Vector<Order> orders = new Vector<Order>();		 
	    NodeList Orders_Nodes = doc.getElementsByTagName("Order"); 	    	
	    for (int temp = 0; temp < Orders_Nodes.getLength(); temp++) {
	    	//for each run of the loop we create a new order and insert it into the vectors of order	     
	    	Node nNode = Orders_Nodes.item(temp);
	        Element e = (Element) nNode;
	        String id =e.getAttribute("id");
            orders.add(new Order(id,ParsingOrderOfDishes(m,e),ParsingAddress(e)));	    		
	   }// end for temp
	   return orders;	
	}//end of OrdersParsing

	static private Vector<Chef> ChefsParsing(Document doc){
		Vector<Chef> Chefs=new Vector<Chef>();
		NodeList nList = doc.getElementsByTagName("Chef");//we create a nodelist of all the chefs in the file     	
	    for (int temp = 0; temp < nList.getLength(); temp++) {	    	
	        Element e = (Element)(nList.item(temp)); // create an element with only one chef info	        
	        String name=e.getElementsByTagName("name").item(0).getTextContent(); //get the name of the chef
	        double  Effciency_Rating=Double.parseDouble(e.getElementsByTagName("efficiencyRating").item(0).getTextContent()); //get the efficiencyRating of this chef
	        int enduranceRating =Integer.parseInt(e.getElementsByTagName("enduranceRating").item(0).getTextContent()); // get the enduranceRating of this chef
	        Chefs.add(new Chef(name,Effciency_Rating,enduranceRating)); // add a new chef into the vector that we return	
	    }// end for temp 
	    return Chefs;
	}
	
	static private Vector<DeliveryPerson> DeliveryPersonsParsing(Document doc){
		Vector<DeliveryPerson> DeliveryPersons=new Vector<DeliveryPerson>();
		NodeList nList = doc.getElementsByTagName("DeliveryPerson");//we create a nodelist of all the chefs in the file     	
	    for (int temp = 0; temp < nList.getLength(); temp++) {	    	
	        Element e = (Element)(nList.item(temp)); // create an element with only one DeliveryPerson info	        
	        String name=e.getElementsByTagName("name").item(0).getTextContent(); //get the name of the DeliveryPerson
	        int Speed=Integer.parseInt(e.getElementsByTagName("speed").item(0).getTextContent()); //get the speed  of this DeliveryPerson
	        Address Restaurant_Address=ParsingAddress((Element)(doc.getElementsByTagName("Address").item(0)));
	        DeliveryPersons.add(new DeliveryPerson(name,Speed,Restaurant_Address)); // add a new DeliveryPerson into the vector that we return	
	    }// end for temp 
	    return DeliveryPersons;
	}// end DeliveryPersonsParsing
	
	static public Managment ManagmentParsing(String filename) throws SAXException, IOException, ParserConfigurationException{
		
		//create a doc type to read from the xml file menu
	    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    Document docInitialData = dBuilder.parse("InitialData.xml");  
	    Document docOrders = dBuilder.parse("OrdersList.xml");   
	    Document docMenu = dBuilder.parse("Menu.xml");  

	    Vector<Chef> Chefs=ChefsParsing(docInitialData);
	    
	    Vector<DeliveryPerson> DeliveryPersons=DeliveryPersonsParsing(docInitialData);
	    
	    Element Tools = (Element)(docInitialData.getElementsByTagName("Tools").item(0));
	    Vector<Kitchen_Tool> KitchenTools=ParsingKitchenTools(Tools);
	    
	    Element Ings = (Element)(docInitialData.getElementsByTagName("Ingredients").item(0));
	    Vector<Ingredient> Ingredients=ParsingIngredients(Ings);
	    
	    WarehouseImpl Warehouse=new WarehouseImpl(Ingredients,KitchenTools);
	    
	    Menu m=MenuParsing(docMenu);
	    
	    Vector<Order> Orders=OrdersParsing(m,docOrders);
	    
	    
	    Element Address = (Element)(docInitialData.getElementsByTagName("Address").item(0));
	    Address Res_Address=ParsingAddress(Address);
	    
	    return (new Managment(Chefs,Res_Address,DeliveryPersons,Orders,Warehouse,m));
	    
	    
	    
	

	}

}
