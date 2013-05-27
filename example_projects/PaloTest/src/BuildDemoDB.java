
import org.palo.api.Connection;
import org.palo.api.ConnectionConfiguration;
import org.palo.api.ConnectionFactory;
import org.palo.api.Cube;
import org.palo.api.Database;
import org.palo.api.Dimension;
import org.palo.api.Hierarchy;


public class BuildDemoDB {

	public static void main(String[] args)
	{
		String server = "192.168.2.118";
		String port = "7777";
		String user = "admin";
		String pass = "admin";
		
		ConnectionConfiguration config = new ConnectionConfiguration(server, port);
		config.setUser(user);
		config.setPassword(pass);
		config.setTimeout(10000);
		config.setType(Connection.TYPE_HTTP);
		config.setLoadOnDemand(true);
		Connection connect = ConnectionFactory.getInstance().newConnection(config);
		
		Database db = connect.getDatabaseByName("Demo");
		if (db != null) connect.removeDatabase(db);
		db = connect.addDatabase("Demo");
		
		Dimension a = db.addDimension("a");
		Dimension[] dim = new Dimension[] {a};
//		dim[0].getDefaultHierarchy()
		Cube sales = db.addCube("Sales", dim);
		
		
		
		
		connect.disconnect();
	}
}
