import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.util.Vector;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.logging.Logger;
import java.util.logging.LogManager;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.util.logging.LogRecord;


public class Main {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, InterruptedException  {	
		
		final  Logger LOGGER = Logger.getLogger("Logger");
		FileHandler handler=new FileHandler("a.xml");
		handler.setFormatter(new CustomizeFormatter()); 
		LOGGER.getParent().getHandlers()[0].setFormatter(new CustomizeFormatter());
		//LOGGER.setUseParentHandlers(false);
		LOGGER.addHandler(handler);

		
		Calendar date1=Calendar.getInstance();
		date1.getTime();
		Managment m=FileParsing.ManagmentParsing("s");
		m.Simulation();
        

	}
}


