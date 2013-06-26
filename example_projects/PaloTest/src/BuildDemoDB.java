
import org.palo.api.*;


public class BuildDemoDB {

	public static void main(String[] args)
	{
		String server = "localhost";
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
		Dimension b = db.addDimension("b");
		Dimension[] dim = new Dimension[] {a, b};
		Cube sales = db.addCube("Sales", dim);
		
		Element aa = a.getDefaultHierarchy().addElement("aa", 0);
		Element ab = a.getDefaultHierarchy().addElement("ab", 0);
		Element ac = a.getDefaultHierarchy().addElement("ac", 0);
		Element ba = b.getDefaultHierarchy().addElement("ba", 0);
		Element bb = b.getDefaultHierarchy().addElement("bb", 0);
		
		System.out.println(a.getDefaultHierarchy().canBeModified());
		
		sales.setData(new Element[] {ab, ba},  3.2);
		
		System.out.println(sales.getData(new Element[] {ab,ba}));
		
		for (Object o : sales.getDataArray(new Element[][] {{aa, ab, ac},{ba}}))
			System.out.println(o.toString().length());
		
		connect.disconnect();
	}
}
