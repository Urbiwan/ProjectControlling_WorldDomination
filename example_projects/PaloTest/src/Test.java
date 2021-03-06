import org.palo.api.Connection;
import org.palo.api.ConnectionConfiguration;
import org.palo.api.ConnectionFactory;
import org.palo.api.Cube;
import org.palo.api.Database;
import org.palo.api.Dimension;
import org.palo.api.Element;


/*
 * Resource:
 * http://www.jedox.com/community/palo-forum/index.php?page=Thread&threadID=3311
 */
public class Test {

	public static void main(String[] args)
	{
		String server = "192.168.2.118";
		String port = "7777";
		String user = "admin";
		String pass = "admin";

		ConnectionConfiguration config = new ConnectionConfiguration("localhost","7777");
		config.setUser("admin");
		config.setPassword("admin");
		config.setTimeout(10000);
		config.setType(Connection.TYPE_HTTP);
		config.setLoadOnDemand(true);
		Connection connect = ConnectionFactory.getInstance().newConnection(config);
		
		Database db = connect.getDatabaseByName("Demo");
		Cube cube = db.getCubeByName("Sales");
		System.out.println(cube.getName());
		for (Dimension d : cube.getDimensions()) System.out.println(d.getName());
		System.out.println();
		
		
		connect.disconnect();
	}
}
