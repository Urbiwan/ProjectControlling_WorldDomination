import org.palo.api.Connection;
import org.palo.api.ConnectionConfiguration;
import org.palo.api.Cube;
import org.palo.api.Database;
import org.palo.api.Dimension;
import org.palo.api.Element;


public class Test {

	public static void main(String[] args)
	{
		String server = "localhost";
		String port = "7921";
		String user = "admin";
		String pass = "admin";
				
		//ConnectionConfiguration conf = new ConnectionConfiguration("localhost", "PALO3ServerService");
		//Connection connect = org.palo.api.ConnectionFactory.getInstance().newConnection(conf);
		Connection connect = org.palo.api.ConnectionFactory.getInstance().newConnection(server, port, user, pass);
		Database database = connect.getDatabaseByName("Demo");
		Cube cube = database.getCubeByName("Sales");
		System.out.println(cube.getName());
		System.out.println();
		
		int dimCount = cube.getDimensionCount();
		Dimension products = cube.getDimensionByName("Products");
		Dimension regions = cube.getDimensionByName("Years");
		
		for(Dimension dim : cube.getDimensions())
			System.out.println('\t'+dim.getName());
		System.out.println();
		
		for(int row = -1; row < products.getElementCount(); row++)
		{
			if(row != -1)
				System.out.print(products.getElementAt(row).getName());
			
			for(int col = 0; col < regions.getElementCount(); col++)
			{
				if(row == -1)
				{
					if(col != -1)
						System.out.print('\t' + regions.getElementAt(col).getName());
				}
				else
				{
					Element[] query = new Element[dimCount];
					for(int i = 0; i < dimCount; i++)
					{
						Dimension dim = cube.getDimensionAt(i);
						if(dim.equals(products))
							query[i] = products.getElementAt(row);
						else if (dim.equals(regions))
							query[i] = regions.getElementAt(col);
						else
							query[i] = cube.getDimensionAt(i).getElementAt(0);
					}
					System.out.print('\t' + cube.getData(query).toString());
				}
			}
			
			System.out.println();
		}

		connect.disconnect();
	}
}
